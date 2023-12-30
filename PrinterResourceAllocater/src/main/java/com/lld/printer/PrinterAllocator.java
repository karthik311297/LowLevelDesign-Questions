package com.lld.printer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterAllocator
{
    private final ReentrantLock numLock = new ReentrantLock();
    private int numPrinters;
    private final Object waitLock = new Object();
    
    private final Object priorityLock = new Object();
    
    private final Object fastLock = new Object();
    private final ReentrantLock mutexLock = new ReentrantLock();
    private List<Integer> waitingProcesses;
    
    public void acquirePrinter(int pid) throws InterruptedException
    {
        if(obtainPrinter())
        {
            System.out.println(String.format("The PID %d has obtained the printer", pid));
        }
        else
        {
            addToWaitingProcesses(pid);
            while(numPrinters == 0 && pid != waitingProcesses.get(0))
            {
                System.out.println(String.format("The PID %d is waiting for the printer", pid));
                synchronized(waitLock)
                {
                    waitLock.wait();
                }
            }
            while(pid != waitingProcesses.get(0))
            {
                System.out.println(String.format("The PID %d is waiting to be processed", pid));
                synchronized(priorityLock)
                {
                    priorityLock.wait();
                }
            }
            while(numPrinters == 0)
            {
                System.out.println(String.format("The PID %d is waiting to be processed first", pid));
                synchronized(fastLock)
                {
                    fastLock.wait();
                }
            }
            removeFromWaitingProcess();
            obtainPrinter();
            System.out.println(String.format("The PID %d has obtained the printer after waiting", pid));
        }
    }
    
    private boolean obtainPrinter()
    {
        numLock.lock();
        int val = numPrinters;
        numPrinters = Math.max(0, numPrinters - 1);
        numLock.unlock();
        if(val > 0)
        {
            return true;
        }
        return false;
    }
    
    private void removeFromWaitingProcess()
    {
        mutexLock.lock();
        waitingProcesses.remove(0);
        Collections.sort(waitingProcesses);
        mutexLock.unlock();
    }
    
    private void addToWaitingProcesses(int pid)
    {
        mutexLock.lock();
        waitingProcesses.add(pid);
        Collections.sort(waitingProcesses);
        mutexLock.unlock();
    }
    
    public void releasePrinter(int pid, long millis) throws InterruptedException
    {
        Thread.sleep(millis);
        System.out.println(String.format("The PID %d has finished using the printer", pid));
        numLock.lock();
        ++numPrinters;
        numLock.unlock();
        synchronized(fastLock)
        {
            fastLock.notifyAll();
        }
        synchronized(priorityLock)
        {
            priorityLock.notifyAll();
        }
        synchronized(waitLock)
        {
            waitLock.notifyAll();
        }
    }
    
    
    public void initialize(int numPrinters)
    {
        this.numPrinters = numPrinters;
        this.waitingProcesses = new ArrayList<>();
    }
    
    
}

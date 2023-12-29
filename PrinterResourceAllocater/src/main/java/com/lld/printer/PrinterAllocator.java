package com.lld.printer;

import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class PrinterAllocator
{
    private AtomicInteger numPrinters;
    private final Object lock = new Object();
    private final ReentrantLock reentrantLock = new ReentrantLock();
    private PriorityQueue<Integer> waitingProcesses;
    
    public void acquirePrinter(int pid) throws InterruptedException
    {
        int nextVal = Math.max(0, numPrinters.get() - 1);
        if(numPrinters.getAndSet(nextVal) > 0)
        {
            System.out.println(String.format("The PID %d has obtained the printer", pid));
        }
        else
        {
            addToWaitingProcesses(pid);
            while(numPrinters.get() == 0 || pid != waitingProcesses.peek())
            {
                System.out.println(String.format("The PID %d is waiting for the printer", pid));
                synchronized(lock)
                {
                    lock.wait();
                }
            }
            numPrinters.decrementAndGet();
            removeFromWaitingProcess();
            System.out.println(String.format("The PID %d has obtained the printer after waiting", pid));
        }
    }
    
    private void removeFromWaitingProcess()
    {
        reentrantLock.lock();
        waitingProcesses.poll();
        reentrantLock.unlock();
    }
    
    private void addToWaitingProcesses(int pid)
    {
        reentrantLock.lock();
        waitingProcesses.add(pid);
        reentrantLock.unlock();
    }
    
    public void releasePrinter(int pid, long millis) throws InterruptedException
    {
        Thread.sleep(millis);
        System.out.println(String.format("The PID %d has finished using the printer", pid));
        numPrinters.incrementAndGet();
        synchronized(lock)
        {
            lock.notifyAll();
        }
    }
    
    
    public void initialize(int numPrinters)
    {
        this.numPrinters = new AtomicInteger(numPrinters);
        this.waitingProcesses = new PriorityQueue<>();
    }
    
    
}

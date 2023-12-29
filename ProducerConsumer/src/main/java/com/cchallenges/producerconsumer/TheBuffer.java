package com.cchallenges.producerconsumer;

import java.util.LinkedList;

public class TheBuffer
{
    private final Object isFull = new Object();
    private final Object isEmpty = new Object();
    private LinkedList<Integer> linkedList;
    private static final int CAPACITY = 10;
    
    private static final TheBuffer INSTANCE = new TheBuffer();
    
    public static TheBuffer getInstance()
    {
        return INSTANCE;
    }
    
    private TheBuffer()
    {
        this.linkedList = new LinkedList<Integer>();
    }
    
    public void produce(Integer val)
    {
        while(getSize() == CAPACITY)
        {
            System.out.println("Is Full");
            try
            {
                synchronized(isFull)
                {
                    isFull.wait();
                }
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
        linkedList.add(val);
        synchronized(isEmpty)
        {
            isEmpty.notify();
        }
        System.out.println("produced value: " + val);
    }
    
    public void consume()
    {
        while(isEmpty())
        {
            System.out.println("Is Empty");
            try
            {
                synchronized(isEmpty)
                {
                    isEmpty.wait();
                }
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }
        }
        int val;
        val = linkedList.poll();
        synchronized(isFull)
        {
            isFull.notify();
        }
        System.out.println("Consumed value: " + val);
    }
    
    private int getSize()
    {
        return linkedList.size();
    }
    
    public boolean isEmpty()
    {
        return linkedList.isEmpty();
    }
}

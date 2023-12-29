package com.cchallenges.producerconsumer;

public class Producer implements Runnable
{
    @Override
    public void run()
    {
        int val = 1;
        while(true)
        {
            TheBuffer.getInstance().produce(val);
            sleep();
            val++;
        }
    }
    
    private void sleep()
    {
        try
        {
            Thread.sleep(400);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
}

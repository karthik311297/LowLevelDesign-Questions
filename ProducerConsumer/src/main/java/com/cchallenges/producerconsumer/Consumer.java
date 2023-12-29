package com.cchallenges.producerconsumer;

public class Consumer implements Runnable
{
    @Override
    public void run()
    {
        while(true)
        {
            TheBuffer.getInstance().consume();
            sleep();
        }
    }
    
    private void sleep()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }
    }
}

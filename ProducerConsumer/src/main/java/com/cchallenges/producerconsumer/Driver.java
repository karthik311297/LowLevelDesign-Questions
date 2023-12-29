package com.cchallenges.producerconsumer;

public class Driver
{
    public static void main(String[] args)
    {
        Thread thread1 = new Thread(new Producer());
        Thread thread2 = new Thread(new Consumer());
        thread1.start();
        thread2.start();
    }
}

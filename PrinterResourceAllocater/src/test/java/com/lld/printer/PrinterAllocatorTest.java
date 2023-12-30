package com.lld.printer;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.testng.annotations.Test;

public class PrinterAllocatorTest
{
    @Test
    public void test() throws InterruptedException
    {
        final int NUM_THREADS = 10;
        CountDownLatch countDownLatch = new CountDownLatch(NUM_THREADS);
        PrinterAllocator printerAllocator = new PrinterAllocator();
        printerAllocator.initialize(3);
        ExecutorService executorService = Executors.newFixedThreadPool(NUM_THREADS);
        for(int i = 0; i < NUM_THREADS; i++)
        {
            int finalI = i;
            executorService.submit(() -> {
                try
                {
                    printerAllocator.acquirePrinter(finalI);
                    printerAllocator.releasePrinter(finalI, 600*((NUM_THREADS + 1) - finalI));
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    countDownLatch.countDown();
                }
            });
        }
        
        countDownLatch.await();
    }
}
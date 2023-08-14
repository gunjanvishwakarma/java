package com.gunjan.cuncurrency.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class SemaphoreTest
{
    public static void main(String[] args)
    {
        Semaphore semaphore = new Semaphore(50);
        AtomicInteger count = new AtomicInteger();
        while(true)
        {
            semaphore.acquireUninterruptibly();
            Thread t = new Thread(() -> {
                System.out.println("Total Running Thread " + count.intValue());
                semaphore.release();
                count.getAndDecrement();
            });
            t.start();
            count.getAndIncrement();
        }
    }
}

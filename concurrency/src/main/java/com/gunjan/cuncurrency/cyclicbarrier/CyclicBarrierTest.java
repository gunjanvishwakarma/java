package com.gunjan.cuncurrency.cyclicbarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierTest
{
    static Random rn = new Random();
    
    public static void main(String[] args)
    {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(100);
        
        for(int i = 0; i < 100; i++)
        {
            Thread t = new Thread(() -> {
                doWork();
                try
                {
                    System.out.println("Waiting for other threads to complete their work");
                    cyclicBarrier.await();
                    System.out.println("All threads finished their work");
                }
                catch(InterruptedException | BrokenBarrierException e)
                {
                    e.printStackTrace();
                }
            });
            t.start();
        }
    }
    
    
    public static void doWork()
    {
        
        int randomSleepTime = rn.nextInt(5000);
        try
        {
            Thread.sleep(randomSleepTime);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}

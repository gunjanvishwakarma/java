package com.gunjan.cuncurrency.countdownlatch;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchTest
{
    public static void main(String[] args)
    {
        CountDownLatch countDownLatch = new CountDownLatch(3);
        for(int i = 0; i < 10; i++)
        {
            int finalI = i;
            new Thread(() -> {
                try
                {
                    // Wait till count down become 0, 3 -> 2 -> 1 -> Go
                    countDownLatch.await();
                    System.out.println("Running.... " + finalI);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }).start();
        }
        
        System.out.println("On your mark");
        for(int i = 2; i >= 0; i--)
        {
            System.out.println(RUN.name(i));
            countDownLatch.countDown();
        }
    }
    enum RUN
    {
        READY(2),
        STEADY(1),
        GO(0);
        
        int value;
        
        RUN(int value)
        {
            this.value = value;
        }
        
        public static String name(int code)
        {
            for(RUN e : RUN.values())
            {
                if(code == e.value) return e.name();
            }
            return null;
        }
    }
}

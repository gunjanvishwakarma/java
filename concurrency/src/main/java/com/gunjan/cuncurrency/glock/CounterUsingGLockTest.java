package com.gunjan.cuncurrency.glock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.gunjan.cuncurrency.usingsynchronized.CounterUsingSynchronizedTest;

public class CounterUsingGLockTest
{
    public int counter() throws InterruptedException
    {
        CounterUsingGLock counter = new CounterUsingGLock();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 1000; i++)
        {
            Thread t = new Thread(() -> {
                try
                {
                    counter.count();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
            threads.add(t);
            t.start();
        }
        
        threads.stream().forEach(t -> {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
        
        return counter.count;
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        for(int i = 0; i < 100; i++)
        {
            int count = new CounterUsingSynchronizedTest().counter();
            Assert.assertEquals(1000, count);
        }
    }
}

package com.gunjan.cuncurrency.usingsynchronized;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class CounterUsingSynchronizedTest
{
    public int counter() throws InterruptedException
    {
        CounterUsingSynchronized counter = new CounterUsingSynchronized();
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < 1000; i++)
        {
            Thread t = new Thread(() -> {
                counter.count();
            });
            threads.add(t);

        }
        
        threads.stream().forEach(t -> {
            try
            {
                t.start();
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

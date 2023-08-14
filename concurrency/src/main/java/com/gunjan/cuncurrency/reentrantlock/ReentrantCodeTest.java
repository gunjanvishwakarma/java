package com.gunjan.cuncurrency.reentrantlock;

import java.util.ArrayList;
import java.util.List;

public class ReentrantCodeTest
{
    public static void main(String[] args)
    {
        List<Thread> threadList = new ArrayList<>();
        ReentrantCode reentrant = new ReentrantCode();
        for(int i = 0; i < 100; i++)
        {
            Thread t = new Thread(() -> {
                
                try
                {
                    reentrant.outer();
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            });
            threadList.add(t);
            t.start();
        }
        threadList.stream().forEach(t -> {
            try
            {
                t.join();
            }
            catch(InterruptedException e)
            {
                e.printStackTrace();
            }
        });
    }
}
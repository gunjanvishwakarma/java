package com.gunjan.cuncurrency.greentrantlock;

import java.util.ArrayList;
import java.util.List;

public class GReentrantCodeTest
{
    public static void main(String[] args)
    {
        List<Thread> threadList = new ArrayList<>();
        GReentrantCode reentrant = new GReentrantCode();
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

package com.gunjan.cuncurrency.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReentrantReadWriteLockTest
{
    public static void main(String[] args)
    {
        ReadWriteList<String> readWriteList = new ReadWriteList<>("a", "b", "c");
        
        List<Thread> threads = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < 1000; i++)
        {
            boolean bool = random.nextBoolean();
            int finalI = i;
            if(bool)
            {
                Thread t = new Thread(() -> {
                    // Reading, this will only block write, multiple read can go in parallel
                    String str = readWriteList.get(readWriteList.size() - 1);
                });
                t.start();
                threads.add(t);
            }
            else
            {
                Thread t = new Thread(() -> {
                    // Writing, This will block any read and new write
                    readWriteList.add(String.valueOf(finalI));
                });
                t.start();
                threads.add(t);
            }
            
            
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
        
        
    }
}

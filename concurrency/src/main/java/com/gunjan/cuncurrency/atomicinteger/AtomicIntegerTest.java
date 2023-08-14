package com.gunjan.cuncurrency.atomicinteger;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class AtomicIntegerTest implements Callable<Integer>
{
    private AtomicInteger atomicInteger;
    private Function<AtomicInteger,Integer> callable;
    
    public AtomicIntegerTest(AtomicInteger atomicInteger, Function<AtomicInteger,Integer> callable)
    {
        this.atomicInteger = atomicInteger;
        this.callable = callable;
    }
    
    @Override
    public Integer call()
    {
        return callable.apply(atomicInteger);
    }
    
    public static void main(String[] args) throws InterruptedException
    {
        ExecutorService executor = Executors.newFixedThreadPool(1000);
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Collection<Callable<Integer>> tasks = new ArrayList<>();
        
        for(int i = 0; i < 1000; i++)
        {
            tasks.add(new AtomicIntegerTest(atomicInteger, (a) -> a.incrementAndGet()));
        }
        
        List<Future<Integer>> future = executor.invokeAll(tasks);
        System.out.println("AtomicInteger = " + atomicInteger.get());
        executor.shutdown();
        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}
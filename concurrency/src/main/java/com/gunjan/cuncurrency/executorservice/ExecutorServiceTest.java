package com.gunjan.cuncurrency.executorservice;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceTest
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        new ExecutorServiceTest().example1();
        new ExecutorServiceTest().example2();
        new ExecutorServiceTest().example3();
        new ExecutorServiceTest().example4();
        new ExecutorServiceTest().example5();
        new ExecutorServiceTest().example6();
    }
    
    public void example1() throws InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        for(int i = 0; i < 10; i++)
        {
            int finalI = i;
            executorService.execute(() -> System.out.println("Running... " + finalI));
        }
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    public void example2() throws ExecutionException, InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        Future<?> future = executorService.submit(() -> System.out.println("Running......."));
        
        Object object = future.get();
        
        if(object == null)
        {
            System.out.println("submitted task completed successfully");
        }
        else
        {
            System.out.println("submitted task failed");
        }
        
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    public void example3() throws ExecutionException, InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        Future<String> future = executorService.submit(() -> "Callable Result");
        
        String str = future.get();
        
        System.out.println(str);
        
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    public void example4() throws ExecutionException, InterruptedException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        
        Collection<Callable<String>> callables = new ArrayList<>();
        
        for(int i = 0; i < 10; i++)
        {
            callables.add(() -> "Callable Result");
        }
        
        String str = executorService.invokeAny(callables);
        
        System.out.println(str);
        
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    
    public void example5() throws InterruptedException, ExecutionException
    {
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        
        
        Collection<Callable<String>> callables = new ArrayList<>();
        
        for(int i = 0; i < 10; i++)
        {
            callables.add(() -> "Callable Result");
        }
        
        List<Future<String>> futures = executorService.invokeAll(callables);
        
        for(int i = 0; i < futures.size(); i++)
        {
            String str = futures.get(i).get();
            System.out.println(str);
        }
        
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    public void example6() throws InterruptedException
    {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 5000;
        
        ExecutorService executorService = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>());
        executorService.submit(() -> System.out.println("Running..."));
        executorService.shutdown();
        executorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}

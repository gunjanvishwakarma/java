package com.gunjan.cuncurrency.forkjoinpool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ForkJoinPoolTest
{
    ForkJoinPool forkJoinPool = new ForkJoinPool(4);
    
    public static void main(String[] args)
    {
        int nos[] = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(new ForkJoinPoolTest().findSum(nos, 0, nos.length - 1));
    }
    
    public int findSum(int[] nos, int i, int j)
    {
        try
        {
            if(i == j) return nos[i];
            int mid = (i + j)/2;
            
            RecursiveTask<Integer> recursiveTask1 = new RecursiveTask<Integer>()
            {
                @Override
                protected Integer compute()
                {
                    return findSum(nos, i, mid);
                }
            };
            
            RecursiveTask<Integer> recursiveTask2 = new RecursiveTask<Integer>()
            {
                @Override
                protected Integer compute()
                {
                    return findSum(nos, mid + 1, j);
                }
            };
    
            
            ForkJoinTask<Integer> a = forkJoinPool.submit(recursiveTask1);
            ForkJoinTask<Integer> b = forkJoinPool.submit(recursiveTask2);
            
            Integer result = a.get() + b.get();
            return result;
        }
        catch(InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
        return -1;
    }
}

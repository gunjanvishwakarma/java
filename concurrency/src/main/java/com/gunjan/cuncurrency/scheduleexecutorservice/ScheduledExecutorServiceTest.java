package com.gunjan.cuncurrency.scheduleexecutorservice;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledExecutorServiceTest
{
    public static void main(String[] args) throws ExecutionException, InterruptedException
    {
        new ScheduledExecutorServiceTest().example1();
    }
    
    
    public void example1() throws ExecutionException, InterruptedException
    {
        ScheduledExecutorService scheduledExecutorService =
                Executors.newScheduledThreadPool(5);
        
        ScheduledFuture<String> scheduledFuture =
                scheduledExecutorService.schedule(() -> {
                            System.out.println("Executed!");
                            return "Called!";
                        },
                        5,
                        TimeUnit.SECONDS);
        String str = scheduledFuture.get();
        System.out.println(str);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    /**
     * This method schedules a task to be executed periodically. The task is executed the first time
     * after the initialDelay, and then recurringly every time the period expires. If any execution
     * of the given task throws an exception, the task is no longer executed. If no exceptions are
     * thrown, the task will continue to be executed until the ScheduledExecutorService is shut down.
     * If a task takes longer to execute than the period between its scheduled executions, the next
     * execution will start after the current execution finishes. The scheduled task will not be executed
     * by more than one thread at a time.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void example2() throws ExecutionException, InterruptedException
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        
        scheduledExecutorService.scheduleAtFixedRate(() -> System.out.println("Executed!"), 5, 5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
    
    /**
     *  This method works very much like scheduleAtFixedRate() except that the period is interpreted
     *  differently. In the scheduleAtFixedRate() method the period is interpreted as a delay between
     *  the start of the previous execution, until the start of the next execution. In this method,
     *  however, the period is interpreted as the delay between the end of the previous execution, until
     *  the start of the next. The delay is thus between finished executions, not between the beginning
     *  of executions.
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public void example3() throws ExecutionException, InterruptedException
    {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(5);
        scheduledExecutorService.scheduleWithFixedDelay(() -> System.out.println("Executed!"), 5, 5, TimeUnit.SECONDS);
        scheduledExecutorService.shutdown();
        scheduledExecutorService.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
    }
}

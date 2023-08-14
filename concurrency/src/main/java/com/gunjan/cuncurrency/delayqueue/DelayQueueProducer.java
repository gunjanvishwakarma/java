package com.gunjan.cuncurrency.delayqueue;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.BlockingQueue;

public class DelayQueueProducer
{
    
    private BlockingQueue<DelayObject> queue;
    
    private final Random random = new Random();
    
    private String name;
    
    public DelayQueueProducer(String name, BlockingQueue<DelayObject> queue)
    {
        super();
        this.queue = queue;
        this.name = name;
    }
    
    private Thread producerThread = new Thread(new Runnable()
    {
        @Override
        public void run()
        {
            while(true)
            {
                try
                {
                    
                    int delay = random.nextInt(10000);
                    DelayObject object = new DelayObject(
                            UUID.randomUUID().toString(), delay);
                    System.out.printf("[%s] - Put object = %s%n",
                            Thread.currentThread().getName(), object);
                    queue.put(object);
                    Thread.sleep(500);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }, "Producer Thread");
    
    public void start()
    {
        this.producerThread.setName(name);
        this.producerThread.start();
    }
    
}

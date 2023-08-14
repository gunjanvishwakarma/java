package com.gunjan.cuncurrency.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest
{

    public static void main(String[] args) throws Exception {
    
        BlockingQueue queue = new PriorityBlockingQueue();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        Thread.sleep(4000);
        new Thread(consumer).start();

    }
}
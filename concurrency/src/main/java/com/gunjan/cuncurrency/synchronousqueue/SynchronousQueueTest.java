package com.gunjan.cuncurrency.synchronousqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest
{

    public static void main(String[] args) throws Exception {
    
        /**
         * The SynchronousQueue is a queue that can only contain a single element internally.
         * A thread inseting an element into the queue is blocked until another thread takes
         * that element from the queue. Likewise, if a thread tries to take an element and no element is currently present, that thread is blocked until a thread insert an element into the queue.
         */
        BlockingQueue queue = new SynchronousQueue();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
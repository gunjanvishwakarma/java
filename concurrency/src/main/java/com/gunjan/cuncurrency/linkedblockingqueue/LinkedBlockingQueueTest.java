package com.gunjan.cuncurrency.linkedblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueTest
{

    public static void main(String[] args) throws Exception {
    
        /**
         * The LinkedBlockingQueue stores the elements internally in FIFO (First In, First Out) order.
         */
        BlockingQueue queue = new LinkedBlockingQueue();

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        Thread.sleep(4000);
    }
}
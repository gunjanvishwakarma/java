package com.gunjan.cuncurrency.arrayblockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ArrayBlockingQueueTest
{

    public static void main(String[] args) throws Exception {

        BlockingQueue queue = new ArrayBlockingQueue(3);

        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        //Thread.sleep(4000);
    }
}
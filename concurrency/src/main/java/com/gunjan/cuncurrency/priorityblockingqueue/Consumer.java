package com.gunjan.cuncurrency.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            System.out.println("Consumed :" + queue.take());
            System.out.println("Consumed :" + queue.take());
            System.out.println("Consumed :" + queue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
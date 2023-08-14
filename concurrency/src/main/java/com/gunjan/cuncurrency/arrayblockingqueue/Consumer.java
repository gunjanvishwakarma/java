package com.gunjan.cuncurrency.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable{

    protected BlockingQueue queue = null;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                Thread.sleep(1000);
                final Object take = queue.take();
                System.out.println("Consume => "+take);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
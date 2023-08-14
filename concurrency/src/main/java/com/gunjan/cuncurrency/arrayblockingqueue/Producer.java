package com.gunjan.cuncurrency.arrayblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    protected BlockingQueue queue = null;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            for (int i = 0; i < 100; i++) {
                queue.put(i);
                System.out.println("Produce => " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.gunjan.cuncurrency.priorityblockingqueue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{

    protected BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    public void run() {
        try {
            Element a = new Element(100);
            System.out.println("Producing : " + a);
            queue.put(a);
            Thread.sleep(1000);
            Element b = new Element(1000);
            System.out.println("Producing : " + b);
            queue.put(b);
            Thread.sleep(1000);
            Element c = new Element(500);
            System.out.println("Producing : " + c);
            queue.put(c);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


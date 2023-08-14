package com.gunjan.cuncurrency.delayqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

public class DelayQueueTest {
	
	public static void main(String[] args) {
		
		BlockingQueue queue = new DelayQueue();
		
		new DelayQueueProducer("Producer Thread-1",queue).start();
		
		new DelayQueueConsumer("Consumer Thread-1", queue).start();

	}

}

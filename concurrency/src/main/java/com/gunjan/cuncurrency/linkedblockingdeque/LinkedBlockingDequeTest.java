package com.gunjan.cuncurrency.linkedblockingdeque;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class LinkedBlockingDequeTest
{
    public static void main(String[] args) throws InterruptedException
    {
        BlockingDeque<String> deque = new LinkedBlockingDeque<String>();
    
        deque.addFirst("1");
        deque.addLast("2");
    
        String two = deque.takeLast();
        String one = deque.takeFirst();
        System.out.println(two);
        System.out.println(one);
    }
}

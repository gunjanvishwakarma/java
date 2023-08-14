package com.gunjan.cuncurrency.readwritelock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * com.gunjan.readwritelock.ReadWriteList.java
 * This class demonstrates how to use ReadWriteLock to add concurrency
 * features to a non-threadsafe collection
 */
public class ReadWriteList<E>
{
    private List<E> list = new ArrayList<>();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();
    
    public ReadWriteList(E... initialElements)
    {
        list.addAll(Arrays.asList(initialElements));
    }
    
    public void add(E element)
    {
        Lock writeLock = rwLock.writeLock();
        writeLock.lock();
        System.out.println("Adding Element Start");
        
        try
        {
            System.out.println(element);
            list.add(element);
        }
        finally
        {
            System.out.println("Adding Element End");
            writeLock.unlock();
        }
    }
    
    public E get(int index)
    {
        Lock readLock = rwLock.readLock();
        readLock.lock();
        System.out.println("Get Element Start");
        try
        {
            E e = list.get(index);
            System.out.println(e);
            return e;
        }
        finally
        {
            System.out.println("Get Element End");
            readLock.unlock();
        }
    }
    
    public int size()
    {
        Lock readLock = rwLock.readLock();
        readLock.lock();
        
        try
        {
            return list.size();
        }
        finally
        {
            readLock.unlock();
        }
    }
    
}


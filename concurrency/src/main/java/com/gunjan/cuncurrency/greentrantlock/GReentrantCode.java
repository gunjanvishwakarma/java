package com.gunjan.cuncurrency.greentrantlock;

public class GReentrantCode
{
    
    GReentrantLock reentrantLock = new GReentrantLock();
    
    public synchronized void outer() throws InterruptedException
    {
        try
        {
            reentrantLock.lock();
            System.out.println("Outer Entry");
            inner();
            System.out.println("Outer Exit");
        }
        finally
        {
            reentrantLock.unlock();
        }
        
        
    }
    
    public synchronized void inner() throws InterruptedException
    {
        try
        {
            reentrantLock.lock();
            System.out.println("Inner Entry");
            System.out.println("Inner Exit");
        }
        finally
        {
            reentrantLock.unlock();
        }
    }
}

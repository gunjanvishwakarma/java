package com.gunjan.cuncurrency.glock;

public class CounterUsingGLock
{
    int count = 0;
    GLock lock = new GLock();
    
    public void count() throws InterruptedException
    {
        try
        {
            lock.lock();
            count++;
        }
        finally
        {
            lock.unlock();
        }
    }
}


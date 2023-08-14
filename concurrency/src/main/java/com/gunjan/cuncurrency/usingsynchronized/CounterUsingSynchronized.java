package com.gunjan.cuncurrency.usingsynchronized;

public class CounterUsingSynchronized
{
    int count = 0;
    
    /**
     * Java's synchronized blocks makes no guarantees about the sequence in which threads trying
     * to enter them are granted access. Therefore, if many threads are constantly competing for
     * access to the same synchronized block, there is a risk that one or more of the threads are
     * never granted access - that access is always granted to other threads. This is called starvation.
     */
    public synchronized void count()
    {
        count++;
    }
}


package com.gunjan.cuncurrency.delayqueue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;


public class DelayObject implements Delayed
{
    private String data;
    private long startTime;
    
    public DelayObject(String data, long delay)
    {
        this.data = data;
        this.startTime = System.currentTimeMillis() + delay;
    }
    
    /**
     * a method that returns how much time is left before the delay completes, getDelay method is
     * important because Java decided to dequeue element from queue if getDelay() method returns a
     * value less than or equal to zero.”
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit)
    {
        long diff = startTime - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }
    
    /**
     * The Delayed interface extends the Comparable interface, so Delayed implementations must override
     * the compareTo() to specify how they should be ordered with respect to other Delayed objects.
     * @param o
     * @return
     */
    @Override
    public int compareTo(Delayed o)
    {
        DelayObject that = (DelayObject)o;
        return (int)(this.startTime - that.startTime);
    }
    
    @Override
    public String toString()
    {
        return "{" +
                "data='" + data + '\'' +
                ", startTime=" + startTime +
                '}';
    }
}

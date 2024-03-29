package com.gunjan.cuncurrency.gsemaphore;

public class GCountingSemaphore
{
  private int signals = 0;

  public synchronized void take() {
    this.signals++;
    this.notify();
  }

  public synchronized void release() throws InterruptedException{
      while(this.signals == 0)
      {
          wait();
      }
    this.signals--;
  }

}
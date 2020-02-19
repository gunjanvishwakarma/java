package com.gunjan;

public class Stack
{
    int size;
    int max;
    int[] element;
    
    public Stack(int max)
    {
        this.max = max;
        element = new int[max];
    }
    
    public void push(int i)
    {
        if(max == size) throw new OverFlow();
        element[size++] = i;
    }
    
    public int size()
    {
        return size;
    }
    
    public int pop()
    {
        if(isEmpty()) throw new UnderFlow();
        return element[--size];
    }
    
    public boolean isEmpty()
    {
        return size == 0;
    }
    
    public class UnderFlow extends RuntimeException
    {
    }
    
    public class OverFlow extends RuntimeException
    {
    }
}

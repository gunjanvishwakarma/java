package com.gunjan.cuncurrency.priorityblockingqueue;

public class Element implements Comparable{
    private int i;
    
    public Element(int i)
    {
        this.i = i;
    }
    
    @Override
    public int compareTo(Object o)
    {
        Element that = (Element) o;
        return this.i - that.i;
    }
    
    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("Element{");
        sb.append("i=").append(i);
        sb.append('}');
        return sb.toString();
    }
}

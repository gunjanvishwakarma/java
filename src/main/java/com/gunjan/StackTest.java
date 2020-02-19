package com.gunjan;

import org.junit.Assert;
import org.junit.Test;

public class StackTest
{
    @Test
    public void createStackObject()
    {
        new Stack(1);
    }
    
    @Test
    public void pushElement()
    {
        Stack stack = new Stack(1);
        stack.push(1);
        Assert.assertEquals(1,stack.size());
    }
    
    @Test
    public void popElement()
    {
        Stack stack = new Stack(1);
        stack.push(1);
        int i = stack.pop();
        Assert.assertEquals(1,i);
    }
    
    @Test
    public void checkEmptyStack()
    {
        Stack stack = new Stack(1);
        Assert.assertEquals(true,stack.isEmpty());
    }
    
    @Test
    public void pushAndPopVerifyStackIsEmpty()
    {
        Stack stack = new Stack(1);
        stack.push(1);
        stack.pop();
        Assert.assertEquals(true,stack.isEmpty());
    }
    
    @Test
    public void pushAndCheckStackIsNotEmpty()
    {
        Stack stack = new Stack(1);
        stack.push(1);
        Assert.assertEquals(false,stack.isEmpty());
    }
    
    @Test
    public void pushAndVerifyPopElement()
    {
        Stack stack = new Stack(1);
        stack.push(2);
        int i = stack.pop();
        Assert.assertEquals(2,i);
    }
    
    @Test
    public void pushMoreThanOneElementAndVerifyPopElement()
    {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        int one = stack.pop();
        int two = stack.pop();
        Assert.assertEquals(one,2);
        Assert.assertEquals(two,1);
    }
    
    @Test(expected = Stack.UnderFlow.class)
    public void popEmptyStack()
    {
        Stack stack = new Stack(1);
        stack.pop();
    }
    
    @Test(expected = Stack.OverFlow.class)
    public void pushFullStack()
    {
        Stack stack = new Stack(2);
        stack.push(1);
        stack.push(2);
        stack.push(3);
    }
}

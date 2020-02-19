package com.gunjan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class PrimeFactorTest
{
    @Test
    public void createPrimeFactor()
    {
        new PrimeFactor();
    }
    
    @Test
    public void factorPrime()
    {
        AssertPrimeFactor(1);
        AssertPrimeFactor(2, 2);
        AssertPrimeFactor(3, 3);
        AssertPrimeFactor(4, 2, 2);
        AssertPrimeFactor(5, 5);
        AssertPrimeFactor(6, 2, 3);
        AssertPrimeFactor(7, 7);
        AssertPrimeFactor(8, 2, 2, 2);
        AssertPrimeFactor(9, 3, 3);
        AssertPrimeFactor(10, 2, 5);
        AssertPrimeFactor(11, 11);
        AssertPrimeFactor(12, 2,2,3);
        AssertPrimeFactor(13, 13);
        AssertPrimeFactor(1*2*3*5*7*11*13*17*19, 2,3,5,7,11,13,17,19);
        AssertPrimeFactor(1000000, 2 , 2 , 2 , 2 , 2 , 2 , 5 , 5 , 5 , 5 , 5 , 5);
        
        
        
        
    }
    
    private void AssertPrimeFactor(Integer number, Integer... expected)
    {
        PrimeFactor primeFactor = new PrimeFactor();
        List<Integer> actual = primeFactor.factor(number);
        Assert.assertEquals(Arrays.asList(expected), actual);
    }
    
    private class PrimeFactor
    {
        
        public List<Integer> factor(int n)
        {
            ArrayList<Integer> factors = new ArrayList<>();
            for(int divisor = 2; n > 1; divisor++)
                for(; n%divisor == 0; n /= divisor)
                    factors.add(divisor);
            return factors;
        }
    }
}

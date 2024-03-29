package com.gunjan.cuncurrency.concurrenthashmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ConcurrentHashMapTest
{
    public static void main(String[] args)
    {
        /**
         *  The ConcurrentHashMap is very similar to the java.util.HashTable class,
         *  except that ConcurrentHashMap offers better concurrency than HashTable does.
         *  ConcurrentHashMap does not lock the Map while you are reading from it.
         *  Additionally, ConcurrentHashMap does not lock the entire Map when writing to it.
         *  It only locks the part of the Map that is being written to, internally.
         * Another difference is that ConcurrentHashMap does not throw ConcurrentModificationException
         * if the ConcurrentHashMap is changed while being iterated. The Iterator is not designed to
         * be used by more than one thread though.
         */
        ConcurrentMap concurrentMap = new ConcurrentHashMap();
    
        concurrentMap.put("key", "value");
    
        Object value = concurrentMap.get("key");
        System.out.println(value);
    }
}

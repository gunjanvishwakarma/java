package com.gunjan;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Sum
{
    public static void main(String[] args)
    {
        Supplier<List<Integer>> supplier = () -> List.of(1,2,3,4,5);
    
        Function<List<Integer>,List<Integer>> mapper = integers -> integers.stream().map( integer -> integer * integer).collect(Collectors.toList());
        
        Predicate<Integer> predicate = integer -> integer > 10;
    
        Consumer<List<Integer>> consumer = integers -> {
            System.out.println(integers.stream().filter(predicate).collect(Collectors.toList()));
        };
        
        consumer.accept(mapper.apply(supplier.get()));
    }
    
    public static void main1(String[] args)
    {
        System.out.println(sum(() -> 2, () -> 3, () -> 4).get());
        //System.out.println(take(100, filter(odd(square(integers())), integer -> integer > 144)));
        
        Supplier<List<Integer>> supplier = () -> take(10, filter(even(square(integers())), integer -> integer > 144));
        print(supplier, System.out::println);
        
        
    }
    
    static void print(Supplier<List<Integer>> supplier, Consumer<List<Integer>> consumer)
    {
        consumer.accept(supplier.get());
    }
    
    static Supplier<Integer> sum(Supplier<Integer>... integer)
    {
        return Arrays
                .stream(integer)
                .reduce((x, y) -> () -> x.get() + y.get())
                .get();
    }
    
    static List<Integer> take(int count, Iterator<Integer> iterator)
    {
        return iteratorToStream(iterator).limit(count).collect(Collectors.toList());
    }
    
    public static <T> Stream<T> iteratorToStream(Iterator<T> iterator)
    {
        return StreamSupport.stream(((Iterable)() -> iterator).spliterator(), false);
    }
    
    static Iterator<Integer> even(Iterator<Integer> iterator)
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return true;
            }
            
            @Override
            public Integer next()
            {
                while(true)
                {
                    Integer next = iterator.next();
                    if(next%2 == 0)
                    {
                        return next;
                    }
                }
            }
        };
    }
    
    
    static Iterator<Integer> filter(Iterator<Integer> iterator, Predicate<Integer> predicate)
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return true;
            }
            
            @Override
            public Integer next()
            {
                while(true)
                {
                    Integer next = iterator.next();
                    if(predicate.test(next))
                    {
                        return next;
                    }
                }
            }
        };
    }
    
    static Iterator<Integer> odd(Iterator<Integer> iterator)
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return true;
            }
            
            @Override
            public Integer next()
            {
                while(true)
                {
                    Integer next = iterator.next();
                    if(next%2 != 0)
                    {
                        return next;
                    }
                }
            }
        };
    }
    
    static Iterator<Integer> square(Iterator<Integer> iterator)
    {
        return new Iterator<>()
        {
            @Override
            public boolean hasNext()
            {
                return true;
            }
            
            @Override
            public Integer next()
            {
                Integer next = iterator.next();
                return next*next;
            }
        };
    }
    
    
    static Iterator<Integer> integers()
    {
        final int[] i = {0};
        return new Iterator<>()
        {
            
            @Override
            public boolean hasNext()
            {
                return true;
            }
            
            @Override
            public Integer next()
            {
                return i[0]++;
            }
        };
    }
}



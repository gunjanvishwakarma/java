package com.gunjan.lambda;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class AgeCollector implements Collector<Person, List<Integer>, List<Integer>> {


    @Override
    public Supplier<List<Integer>> supplier() {
        return ArrayList<Integer>::new;
    }

    @Override
    public BiConsumer<List<Integer>, Person> accumulator() {
        return (a, p) -> a.add(p.getAge());
    }

    @Override
    public BinaryOperator<List<Integer>> combiner() {
        return (l, r) -> {
            l.addAll(r);
            return l;
        };
    }

    @Override
    public Function<List<Integer>, List<Integer>> finisher() {
        return p -> p;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}

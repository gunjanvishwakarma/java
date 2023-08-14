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

public class ListCollector implements Collector<Person, List<Person>, List<Person>> {

    @Override
    public Supplier<List<Person>> supplier() {
        return ArrayList<Person>::new;
    }

    @Override
    public BiConsumer<List<Person>, Person> accumulator() {
        return (a, p) -> a.add(p);
    }

    @Override
    public BinaryOperator<List<Person>> combiner() {
        return (l, r) -> {
            l.addAll(r);
            return l;
        };
    }

    @Override
    public Function<List<Person>, List<Person>> finisher() {
        return p -> p;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.IDENTITY_FINISH);
    }
}

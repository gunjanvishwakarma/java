package com.gunjan.lambda;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

public class NamesByGenderCollector implements Collector<Person, Map<Person.Sex, List<String>>, Map<Person.Sex, List<String>>> {

    @Override
    public Supplier<Map<Person.Sex, List<String>>> supplier() {
        return HashMap<Person.Sex, List<String>>::new;
    }

    @Override
    public BiConsumer<Map<Person.Sex, List<String>>, Person> accumulator() {
        return
                (a, p) ->
                {
                    List<String> list = a.get(p.getGender());
                    if (list == null) {
                        list = new ArrayList<>();
                        a.put(p.getGender(), list);
                    }
                    list.add(p.getName());
                };
    }

    @Override
    public BinaryOperator<Map<Person.Sex, List<String>>> combiner() {
        return (l, r) -> {
            l.putAll(r);
            return l;
        };
    }

    @Override
    public Function<Map<Person.Sex, List<String>>, Map<Person.Sex, List<String>>> finisher() {
        return f -> f;
    }

    @Override
    public Set<Characteristics> characteristics() {
        return EnumSet.of(Characteristics.CONCURRENT);
    }
}

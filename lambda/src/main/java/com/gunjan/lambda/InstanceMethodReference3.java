package com.gunjan.lambda;

import java.util.function.BiFunction;

public class InstanceMethodReference3 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder = new Arithmetic3()::add;
        int result = adder.apply(10, 20);
        System.out.println(result);
    }
}

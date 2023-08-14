package com.gunjan.lambda;

import java.util.function.BiFunction;

public class MethodReference4 {
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> adder1 = Arithmetic2::add;
        BiFunction<Integer, Float, Float> adder2 = Arithmetic2::add;
        BiFunction<Float, Float, Float> adder3 = Arithmetic2::add;
        int result1 = adder1.apply(10, 20);
        float result2 = adder2.apply(10, 20.0f);
        float result3 = adder3.apply(10.0f, 20.0f);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}

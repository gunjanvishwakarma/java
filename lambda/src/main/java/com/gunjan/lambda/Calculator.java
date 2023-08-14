package com.gunjan.lambda;

public class Calculator {

    interface IntegerMath {
        int operation(int a, int b);
    }

    public int operateBinary(int a, int b, IntegerMath op) {
        return op.operation(a, b);
    }


    public static void main(String... args) {

        Calculator myApp = new Calculator();
        IntegerMath additionOld = new IntegerMath() {
            @Override
            public int operation(int a, int b) {
                return a + b;
            }
        };
        IntegerMath addition = (a, b) -> a + b;

        IntegerMath addition1 = (a, b) -> {
            // Multiple line method implementation
            return a + b;
        };

        IntegerMath subtractionOld = new IntegerMath() {
            @Override
            public int operation(int a, int b) {
                return a - b;
            }
        };

        IntegerMath subtraction = (a, b) -> a - b;
        System.out.println("40 + 2 = " +
                myApp.operateBinary(40, 2, addition));
        System.out.println("20 - 10 = " +
                myApp.operateBinary(20, 10, subtraction));
    }
}

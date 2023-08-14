package com.gunjan.lambda;

import java.util.function.IntConsumer;

public class Average implements IntConsumer {
    private int total = 0;
    private int count = 0;


    public double average() {
        return count > 0 ? ((double) total) / count : 0;
    }

    @Override
    public void accept(int i) {
        total += i;
        count++;
    }

    public void combine(Average other) {
        total += other.total;
        count += other.count;
    }
}

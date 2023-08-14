package com.gunjan.lambda;

public class Computer {
    int age = 10;
    int model = 1010;
    String brand = "Dell";

    public Computer() {

    }

    public Computer(int age) {
        this.age = age;
    }

    public Computer(int age, int model) {
        this.age = age;
        this.model = model;
    }

    public Computer(int age, int model, String brand) {
        this.age = age;
        this.model = model;
        this.brand = brand;
    }

    Integer getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Computer{");
        sb.append("age=").append(age);
        sb.append(", model=").append(model);
        sb.append(", brand='").append(brand).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

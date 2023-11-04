package com.gunjan.patternmatching;

class Vehicle1 {
    // Common properties and methods for all vehicles
}

class Car1 extends Vehicle1 {
    void drive() {
        System.out.println("Driving a car");
    }
}

class Bicycle extends Vehicle1 {
    void pedal() {
        System.out.println("Pedaling a bicycle");
    }
}

public class PatternMatchingExample {
    public static void main(String[] args) {
        Vehicle1 vehicle = new Car1();

        if (vehicle instanceof Car1 car) {
            car.drive();
        } else if (vehicle instanceof Bicycle bicycle) {
            bicycle.pedal();
        } else {
            System.out.println("Unknown vehicle");
        }
    }
}

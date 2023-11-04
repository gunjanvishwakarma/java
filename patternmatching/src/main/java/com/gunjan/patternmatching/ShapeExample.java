package com.gunjan.patternmatching;

sealed interface Shape permits Circle, Rectangle {
    double area();
}

final class Circle implements Shape {
    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double area() {
        return Math.PI * radius * radius;
    }
}

final class Rectangle implements Shape {
    private final double width;
    private final double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double area() {
        return width * height;
    }
}

public class ShapeExample {
    public static void main(String[] args) {
        Shape shape1 = new Circle(5.0);
        Shape shape2 = new Rectangle(4.0, 6.0);

        printArea(shape1);
        printArea(shape2);
    }

    public static void printArea(Shape shape) {
        double area = switch (shape) {
            case Circle c -> c.area();
            case Rectangle r -> r.area();
        };
        System.out.println("Area: " + area);
    }
}

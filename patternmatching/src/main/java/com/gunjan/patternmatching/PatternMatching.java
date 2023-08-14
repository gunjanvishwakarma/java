package com.gunjan.patternmatching;

public class PatternMatching {
    public static void whatType(Object o){
        switch (o){
            case String s -> System.out.println("String: " + s);
            case Integer i -> System.out.println("Integer: " + i);
            case null -> System.out.println("Null:"  + o);
            default -> System.out.println("Default: " + o);
        }
    }
    
    sealed interface Swim permits Duck,Fish{
        String swim();
    }

    static final class Fish implements Swim,Animal {
        @Override
        public String swim() {
            return "Fish can swim";
        }

        @Override
        public boolean pet() {
            return false;
        }
    }

    static final class Duck implements Swim,Animal {
        @Override
        public String swim() {
            return "Duck can swim";
        }

        @Override
        public boolean pet() {
            return true;
        }
    }
    
    sealed interface Animal permits Fish,Duck {
        boolean pet();
    }
    
    public static void isStatements(Animal a){
        if(a instanceof Duck d){
            System.out.println(d.pet());
            System.out.println(d.swim());
        }

        else if(a instanceof Fish d){
            System.out.println(d.pet());
            System.out.println(d.swim());
        }
    }

    public static void patternMatchingSwitch(Animal a){
        switch (a) {
            case Fish f -> {
                System.out.println(f.pet());
                System.out.println(f.swim());
            }
            case Duck d -> {
                System.out.println(d.pet());
                System.out.println(d.swim());
            }
        }
    }

    public static void main(String[] args) {
        whatType("Gunjan");
        whatType(10);
        whatType(null);
        whatType(10.1);
        isStatements(new Duck());
        isStatements(new Fish());
        patternMatchingSwitch(new Duck());
        patternMatchingSwitch(new Fish());
    }
}

package com.traits;

public interface Friendable
{
    String getName();
    default void listen(){
        System.out.println(getName() + "Listening...");
    }
}



class Human implements Friendable{
    
    @Override
    public String getName()
    {
        return "Bob";
    }
}

class Test{
    public static void main(String[] args)
    {
        new Human().listen();
        new Dog().listen();
    }
}

class Animal {
    private void listen(){
        System.out.println("Listening...");
    }
}

class Dog extends Animal implements Friendable{
    
    @Override
    public String getName()
    {
        return "Buddy";
    }
}


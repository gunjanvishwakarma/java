package com.gunjan.lambda;

import java.time.LocalDate;
import java.util.*;

public class Person implements Cloneable {
    static List<Person> persons = new ArrayList<>() {{
        add(new Person("Bob Smith", LocalDate.of(1986, 9, 10), Sex.MALE, "bob@gmail.com", 25));
        add(new Person("Alice Miller", LocalDate.of(1985, 9, 10), Sex.FEMALE, "alice@gmail.com", 27));
        add(new Person("John Davis", LocalDate.of(1984, 9, 10), Sex.MALE, "john.bit2k41@gmail.com", 27));
    }};

    private String name;
    private LocalDate birthday;
    private Sex gender;
    private String emailAddress;
    private int age;

    public Person(String name, LocalDate birthday, Sex gender, String emailAddress, int age) {
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.emailAddress = emailAddress;
        this.age = age;
    }

    public Person() {

    }

    public enum Sex {
        MALE,
        FEMALE
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Sex getGender() {
        return gender;
    }

    public void setGender(Sex gender) {
        this.gender = gender;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", birthday=" + birthday +
                ", gender=" + gender +
                ", emailAddress='" + emailAddress + '\'' +
                ", age=" + age +
                '}';
    }

    public static int compareByAge(Person a, Person b) {
        return a.birthday.compareTo(b.birthday);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}


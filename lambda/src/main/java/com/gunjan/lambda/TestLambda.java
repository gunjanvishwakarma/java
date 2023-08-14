package com.gunjan.lambda;

import com.google.common.collect.ImmutableSet;
import org.testng.annotations.Test;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class TestLambda {

    @Test
    public void test1() {
        printPersons(Person.persons, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
    }

    @Test
    public void test2() {
        printPersons(
                Person.persons,
                new CheckPerson() {
                    public boolean test(Person p) {
                        return p.getGender() == Person.Sex.MALE
                                && p.getAge() >= 18
                                && p.getAge() <= 25;
                    }
                }
        );


    }

    @Test
    public void test3() {
        printPersons(Person.persons, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);

    }

    @Test
    public void test4() {
        printPersonsWithPredicate(Person.persons, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25);
    }


    @Test
    public void test5() {
        printPersonsWithPredicateAndConsumer(Person.persons, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25
                ,
                person -> System.out.println(person)
        );


    }

    @Test
    public void test6() {
        printPersonsWithPredicateAndFunctionAndConsumer(Person.persons, (Person p) -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 28
                ,
                person -> person.getEmailAddress(), email -> System.out.println(email)
        );
    }

    @Test
    public void test7() {
        processElements(
                Person.persons,
                p -> p.getGender() == Person.Sex.MALE
                        && p.getAge() >= 18
                        && p.getAge() <= 28,
                p -> p.getEmailAddress(),
                email -> System.out.println(email)
        );
    }

    @Test
    public void test8() {
        Person.persons.stream().filter(p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() <= 25)
                .map(p -> p.getEmailAddress()).forEach(email -> System.out.println(email));
    }

    @Test
    public void test9() {
        Person.persons.stream().forEach(person -> System.out.println(person));
    }

    @Test
    public void test10() {
        Person.persons.stream().map(person -> person.getEmailAddress()).forEach(email -> System.out.println(email));
    }

    @Test
    public void test11() {
        Person[] rosterAsArray = Person.persons.toArray(new Person[Person.persons.size()]);

        Arrays.sort(rosterAsArray, new PersonAgeComparator());
        for (Person person : rosterAsArray) {
            System.out.println(person);
        }
    }

    @Test
    public void test12() {
        Person[] rosterAsArray = Person.persons.toArray(new Person[Person.persons.size()]);
        Arrays.sort(rosterAsArray, (p1, p2) -> p2.getBirthday().compareTo(p1.getBirthday()));
        for (Person person : rosterAsArray) {
            System.out.println(person);
        }
    }

    @Test
    public void test13() {
        Person[] rosterAsArray = Person.persons.toArray(new Person[Person.persons.size()]);
        Arrays.sort(rosterAsArray, (p1, p2) -> Person.compareByAge(p1, p2));
        for (Person person : rosterAsArray) {
            System.out.println(person);
        }
    }

    @Test
    public void test14() {
        Person[] rosterAsArray = Person.persons.toArray(new Person[Person.persons.size()]);

        BiFunction<Person, Person, Integer> fun = Person::compareByAge;
        Arrays.sort(rosterAsArray, (t, u) -> fun.apply(t, u));

        Arrays.sort(rosterAsArray, Person::compareByAge);

        for (Person person : rosterAsArray) {
            System.out.println(person);
        }
    }

    @Test
    public void test15() {
        Person[] rosterAsArray = Person.persons.toArray(new Person[Person.persons.size()]);
        ComparisonProvider myComparisonProvider = new ComparisonProvider();
        Arrays.sort(rosterAsArray, myComparisonProvider::compareByName);
    }

    @Test
    public void test16() {
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, String::compareToIgnoreCase);
        System.out.println(Arrays.asList(stringArray));
    }

    @Test
    public void test17() {
        String[] stringArray = {"Barbara", "James", "Mary", "John", "Patricia", "Robert", "Michael", "Linda"};
        Arrays.sort(stringArray, (s1, s2) -> s1.compareToIgnoreCase(s2));
    }

    @Test
    public void test18() {
        Set<Person> rosterSet1 = transferElements(Person.persons, () -> new HashSet<>());
        System.out.println(rosterSet1);
    }

    @Test
    public void test19() {
        Set<Person> rosterSet2 = transferElements(Person.persons, HashSet::new);
        System.out.println(rosterSet2);
    }

    @Test
    public void test20() {
        Set<Person> rosterSet3 = transferElements(Person.persons, HashSet::new);
        System.out.println(rosterSet3);
    }

    @Test
    public void test21() {
        double avarageAge = Person.persons.stream().filter(p -> p.getGender().equals(Person.Sex.MALE)).mapToDouble(Person::getAge).average().getAsDouble();
        System.out.println(avarageAge);
    }

    @Test
    public void test22() {
        Integer totalAge = Person.persons.stream().mapToInt(Person::getAge).sum();
        System.out.println(totalAge);
    }

    @Test
    public void test23() {
        Integer totalAgeReduce = Person.persons
                .stream()
                .map(Person::getAge)
                .reduce(
                        0,
                        (a, b) -> a + b);

        System.out.println(totalAgeReduce);
    }

    @Test
    public void test24() {
        Average averageCollect = Person.persons.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Average::new, Average::accept, Average::combine);

        System.out.println("Average age of male members: " +
                averageCollect.average());
    }

    @Test
    public void test25() {
        List<Integer> ages = Person.persons.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(Collectors.toList());
    }

    @Test
    public void test26() {
        List<Integer> ages1 = Person.persons.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .map(Person::getAge)
                .collect(ArrayList::new, ArrayList::add, (left, right) -> {
                    left.addAll(right);
                });
        System.out.println(ages1);
    }

    @Test
    public void test27() {
        Map<Person.Sex, List<Person>> byGender =
                Person.persons
                        .stream()
                        .collect(
                                groupingBy(Person::getGender));
        System.out.println(byGender);
    }

    @Test
    public void test28() {
        Map<String, Person> agesMap = Person.persons.stream()
                .filter(p -> p.getGender() == Person.Sex.MALE)
                .collect(Collectors.toMap(Person::getName, person -> person));
        System.out.println(agesMap);
    }

    @Test
    public void test29() {
        Map<Person.Sex, List<String>> namesByGender =
                Person.persons.stream().collect(
                        groupingBy(
                                Person::getGender,
                                Collectors.mapping(
                                        Person::getName,
                                        Collectors.toList())));
        System.out.println(namesByGender);

    }

    @Test
    public void test30() {
        List<Integer> ages = Person.persons.stream().collect(new AgeCollector());
        System.out.println(ages);

    }

    @Test
    public void test31() {
        ImmutableSet<Person> ages = Person.persons.stream().collect(new ImmutableSetCollector<Person>());
        System.out.println(ages);
    }

    @Test
    public void test32() {
        Map<Person.Sex, List<String>> ages = Person.persons.stream().collect(new NamesByGenderCollector());
        System.out.println(ages);
    }

    @Test
    public void test33() {
        BinaryOperator<Integer> adder = (n1, n2) -> n1 + n2;

        System.out.println(adder.apply(3, 4));
    }

    @Test
    public void test34() {
        boolean exist = Person.persons.stream().collect(Collectors.collectingAndThen(new AgeCollector(), p -> p.contains(25)));
        System.out.println(exist);
    }

    @Test
    public void test35() {
        Map<Integer, Set<String>> map = Person.persons.stream().collect(groupingBy(Person::getAge, Collector.of(HashSet::new, (set, p) -> set.add(p.getName()), (l, r) -> {
            l.addAll(r);
            return l;
        })));
        System.out.println(map);
    }


    @Test
    public void test36() throws CloneNotSupportedException {

        int totalSize = 8000000;
        Person person = new Person("bob", LocalDate.of(1986, 9, 10), Person.Sex.MALE, "bob@gmail.com", 25);

        long start0 = System.currentTimeMillis();
        for (int j = 0; j < totalSize; j++) {
            Person personClone = (Person) person.clone();
            personClone.setAge(j);
            personClone.setName("Bob" + j);
            personClone.setEmailAddress("bob@gmail.com" + j);
            Person.persons.add(personClone);
        }
        long end0 = System.currentTimeMillis();

        long millis = end0 - start0;

        System.out.println("Time taken to create " + totalSize + " Person objects is " + getDurationBreakdown(millis));
        /*long start1 = System.currentTimeMillis();
        persons.parallelStream().filter(p -> {boolean status = p.getName().contains("Bob");
        for(int i = 0 ; i < 100000 ; i++){
            new String("==============");
        }
            return status;}).collect(new ListCollector());
        long end1 = System.currentTimeMillis();
        millis = end1 - start1;
        System.out.println("Time took to process using parallel stream of size " + totalSize + " is " + getDurationBreakdown(millis));

        long start2 = System.currentTimeMillis();
        persons.stream().filter(p -> {boolean status = p.getName().contains("Bob");
            for(int i = 0 ; i < 100000 ; i++){
                new String("==============");
            }
            return status;}).collect(new ListCollector());
        long end2 = System.currentTimeMillis();
        millis = end2 - start2;
        System.out.println("Time took to process using stream of size " + totalSize + " is " + getDurationBreakdown(millis));*/
    }

    @Test
    public void test37() {
        Function<Computer, Integer> getAge = Computer::getAge;
        Function<Integer, Computer> function = Computer::new;
        BiFunction<Integer, Integer, Computer> biFunction = Computer::new;
        TriFunction<Integer, Integer, String, Computer> triFunction = Computer::new;
        System.out.println(getAge.apply(function.apply(10)));
        System.out.println(biFunction.apply(10, 100));
        System.out.println(triFunction.apply(10, 100, "Lenovo"));

        Function<Integer, Computer[]> computerCreator = Computer[]::new;
        Computer[] computerArray = computerCreator.apply(5);
        System.out.println(computerArray.length);
    }

    @Test
    public void test38() {
        System.out.println(Person.persons);
        Map<Person.Sex, List<Person>> collect = Person.persons.stream().collect(groupingBy(Person::getGender));
        System.out.println(collect);
    }

    @Test
    public void test39() {
        System.out.println(Person.persons);
        Map<Person.Sex, List<String>> collect = Person.persons.stream().collect(groupingBy(Person::getGender, Collectors.mapping(Person::getName, Collectors.toList())));
        System.out.println(collect);
    }

    @Test
    public void test40() {
        System.out.println(Person.persons);
        Map<Person.Sex, Long> collect = Person.persons.stream().collect(groupingBy(Person::getGender, counting()));
        System.out.println(collect);
    }

    @Test
    public void test41() {
        System.out.println(Person.persons);
        Map<Person.Sex, Integer> collect = Person.persons.stream().collect(groupingBy(Person::getGender, collectingAndThen(counting(), Long::intValue)));
        System.out.println(collect);
    }

    @Test
    public void test42() {
        System.out.println(Person.persons);
        Integer totalAge = Person.persons.stream().map(Person::getAge).reduce(0, (total, age) -> total = total + age);
        System.out.println(totalAge);
    }

    @Test
    public void test43() {
        System.out.println(Person.persons);
        Integer totalAge = Person.persons.stream().mapToInt(Person::getAge).sum();
        System.out.println(totalAge);
    }

    @Test
    public void test44() {
        System.out.println(Person.persons);
        OptionalInt totalAge = Person.persons.stream().mapToInt(Person::getAge).max();
        System.out.println(totalAge.getAsInt());
    }

    @Test
    public void test45() {
        System.out.println(Person.persons);
        OptionalInt totalAge = Person.persons.stream().mapToInt(Person::getAge).min();
        System.out.println(totalAge.getAsInt());
    }

    @Test
    public void test46() {
        System.out.println(Person.persons);
        Optional<Person> person = Person.persons.stream().collect(maxBy(Comparator.comparing(Person::getAge)));
        System.out.println(person.get());
    }

    @Test
    public void test47() {
        System.out.println(Person.persons);
        Optional<Person> person = Person.persons.stream().collect(minBy(Comparator.comparing(Person::getAge)));
        System.out.println(person.get());
    }

    @Test
    public void test48() {
        System.out.println(Person.persons);
        String name = Person.persons.stream().collect(collectingAndThen(
                minBy(Comparator.comparing(Person::getAge)), person -> person.map(Person::getName).orElse("")));
        System.out.println(name);
    }

    @Test
    public void test49() {
        System.out.println(Person.persons);
        Map<Integer, List<String>> person = Person.persons.stream().collect(groupingBy(Person::getAge,
                mapping(Person::getName, filtering(name -> name.length() >= 4, toList()))));
        System.out.println(person);

    }

    @Test
    public void test50() {
        //System.out.println(persons);
        List<String> person = Person.persons
                .parallelStream()
                .map(Person::getName)
                .reduce(new ArrayList<>(), (names, name) -> {
                    names.add(name);
                    return names;
                }, (names1, names2) -> {
                    names1.addAll(names2);
                    return names1;
                });
        System.out.println(person);
    }

    @Test
    public void test51() {
        System.out.println(Person.persons);
        List<String> person = Person.persons
                .parallelStream()
                .map(Person::getName)
                .collect(toList());
        System.out.println(person);
    }

    @Test
    public void test52() {
        System.out.println(Person.persons);
        Map<String, Person.Sex> person = Person.persons
                .stream()
                .collect(toMap(Person::getName, Person::getGender));
        System.out.println(person);
    }

    @Test
    public void test53() {
        System.out.println(Person.persons);
        List<String> person = Person.persons
                .parallelStream()
                .map(Person::getName)
                .collect(toUnmodifiableList());
        System.out.println(person);
        person.add("Gunjan");
    }

    @Test
    public void test54() {
        System.out.println(Person.persons);
        String person = Person.persons
                .parallelStream()
                .map(Person::getName)
                .collect(joining(","));
        System.out.println(person);
    }

    @Test
    public void test55() {
        System.out.println(Person.persons);
        Map<Boolean, List<Person>> collect = Person.persons
                .stream()
                .collect(partitioningBy(p -> p.getGender() == Person.Sex.MALE));
        System.out.println(collect);
    }

    @Test
    public void test56() {
        System.out.println(Person.persons);
        List<String> personList = Person.persons
                .stream()
                .flatMap((Function<Person, Stream<String>>) person -> Arrays.asList(person.getName().split(" ")).stream())
                .collect(toList());
        System.out.println(personList);
    }

    @Test
    public void test57() {
        System.out.println(Person.persons);
        List<String> personList = Person.persons
                .stream()
                .flatMap(person -> Stream.of(person.getName().split(" "))).collect(toList());
        System.out.println(personList);
    }

    @Test
    public void test58() {
        System.out.println(Person.persons);
        List<Person> collect = Person.persons
                .stream().peek(person -> person.setAge(10)).collect(toList());
        System.out.println(collect);
    }

    @FunctionalInterface
    interface TriFunction<A, B, C, R> {
        R apply(A a, B b, C c);
        
        /*default <V> TriFunction<A,B,C,V> andThen(Function<? super R,? extends V> after)
        {
            Objects.requireNonNull(after);
            return (A a, B b, C c) -> after.apply(apply(a, b, c));
        }*/
    }

    public static void printPersons(List<Person> roster, CheckPerson tester) {
        for (Person p : roster) {
            if (tester.test(p)) {
                System.out.println(p.toString());
            }
        }
    }

    public static void printPersonsWithPredicate(List<Person> persons, Predicate<Person> tester) {
        for (Person p : persons) {
            if (tester.test(p)) {
                System.out.println(p.toString());
            }
        }
    }

    public static void printPersonsWithPredicateAndConsumer(List<Person> persons, Predicate<Person> tester, Consumer<Person> block) {
        for (Person p : persons) {
            if (tester.test(p)) {
                block.accept(p);
            }
        }
    }

    public static void printPersonsWithPredicateAndFunctionAndConsumer(
            List<Person> roster,
            Predicate<Person> tester,
            Function<Person, String> mapper, Consumer<String> block
    ) {
        for (Person p : roster) {
            if (tester.test(p)) {
                String data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <X, Y> void processElements(
            Iterable<X> source,
            Predicate<X> tester,
            Function<X, Y> mapper,
            Consumer<Y> block) {
        for (X p : source) {
            if (tester.test(p)) {
                Y data = mapper.apply(p);
                block.accept(data);
            }
        }
    }

    public static <T, SOURCE extends Collection<T>, DEST extends Collection<T>> DEST transferElements(SOURCE sourceCollection, Supplier<DEST> collectionFactory) {
        DEST result = collectionFactory.get();
        for (T t : sourceCollection) {
            result.add(t);
        }
        return result;
    }

    public static String getDurationBreakdown(long millis) {
        if (millis < 0) {
            throw new IllegalArgumentException("Duration must be greater than zero!");
        }

        long days = TimeUnit.MILLISECONDS.toDays(millis);
        long hours = TimeUnit.MILLISECONDS.toHours(millis) % 24;
        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis) % 60;
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) % 60;
        long milliseconds = millis % 1000;

        return String.format("%d:%d:%d", minutes, seconds, milliseconds);
    }
}

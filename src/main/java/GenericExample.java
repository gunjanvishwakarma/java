import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericExample {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        GenericClass<String, Integer> genericClass = new GenericClass<String, Integer>("Gunjan", 123);

        String str = genericClass.getT1();

        System.out.println(str);

        genericClass.setT1("Vishwakarma");

        str = genericClass.getT1();

        System.out.println(str);

        Integer integer = genericClass.getT2();

        System.out.println(integer);

        genericClass.setT2(12345);

        integer = genericClass.getT2();

        System.out.println(integer);


        GenericMethod genericMethod = new GenericMethod();
        String str1 = genericMethod.getObject("Gunjan", 1, 2.0, 1L);
        System.out.println(str1);

        Dog dog = genericMethod.getInstance(Dog.class);

        System.out.println(dog);

        Cat cat = genericMethod.getInstance(Cat.class);

        System.out.println(cat);

        List<A> listA = new ArrayList<>();
        List<B> listB = new ArrayList<>();
        List<C> listC = new ArrayList<>();
        List<Dog> listDog = new ArrayList<>();
        List<Dog> listCat = new ArrayList<>();
        List<Object> listObject = new ArrayList<>();

        //listA = listB;

        //listB = listA;

        // List<?> means a list typed to an unknown type,
        // This could be a List<A>, a List<B>, a List<String> etc.
        //Since you do not know what type the List is typed to,
        // you can only read from the collection, and you can only
        // treat the objects read as being Object instances.
        List<?> wildCard1 = new ArrayList<>();

        wildCard1 = listA;

        wildCard1 = listB;

        wildCard1 = listC;

        wildCard1 = listDog;

        wildCard1 = listCat;

        Object obj = wildCard1.get(0);

        System.out.println(obj);

        List<? extends A> wildCard2 = new ArrayList<>();

        wildCard2 = listA;

        wildCard2 = listB;

        wildCard2 = listC;

        //wildCard2 = listDog;

        //wildCard2 = listCat;

        A a = wildCard2.get(0);

        System.out.println(a);

        List<? super A> wildCard3 = new ArrayList<>();

        wildCard3.add(new A());

        wildCard3.add(new B());

        wildCard3.add(new C());

        wildCard3 = listA;

        //wildCard3 = listB;

        //wildCard3 = listC;

        //wildCard3 = listDog;

        //wildCard3 = listCat;

        wildCard3 = listObject;


    }
}

class A {
    private String str;

    public A() {

    }

    public A(String str) {
        this.str = str;
    }

    public String getValue() {
        return str;
    }
}

class B extends A {

}

class C extends A {

}

class Dog {

}

class Cat {

}

class WildCard {
    public static void main(String[] args) {
        List<A> listA = new ArrayList<>();
        List<B> listB = new ArrayList<>();
        List<C> listC = new ArrayList<>();
        List<Dog> listDog = new ArrayList<>();
        List<Dog> listCat = new ArrayList<>();
        List<Object> listObject = new ArrayList<>();

        processElements1(listA);
        processElements1(listB);
        processElements1(listC);

        insertElements(listA);
        //insertElements(listB);
        //insertElements(listC);
        //insertElements(listDog);
        //insertElements(listCat);
        insertElements(listObject);


        processElements3(listA);
        processElements3(listB);
        processElements3(listC);
        //processElements3(listDog);
        //processElements3(listCat);
        //processElements3(listObject);
    }

    public static void processElements1(List<?> elements) {
        for (Object o : elements) {
            System.out.println(o);
        }
    }

    public static void insertElements(List<? super A> list) {
        list.add(new A());
        list.add(new B());
        list.add(new C());
    }

    public static void processElements3(List<? extends A> elements) {
        for (A a : elements) {
            System.out.println(a.getValue());
        }
    }
}

class GenericClass<T1, T2> {

    private T1 t1;
    private T2 t2;

    GenericClass(T1 t1, T2 t2) {
        this.t1 = t1;
        this.t2 = t2;
    }

    public T1 getT1() {
        return t1;
    }

    public void setT1(T1 t1) {
        this.t1 = t1;
    }

    public T2 getT2() {
        return t2;
    }

    public void setT2(T2 t2) {
        this.t2 = t2;
    }
}

class GenericMethod {
    public <A, B, C, D> A getObject(A a, B b, C c, D d) {
        return a;
    }

    public static <T> T addAndReturn(T element, Collection<T> collection) {
        collection.add(element);
        return element;
    }

    public static <T> T getInstance(Class<T> theClass)
            throws IllegalAccessException, InstantiationException {
        return theClass.newInstance();
    }
}

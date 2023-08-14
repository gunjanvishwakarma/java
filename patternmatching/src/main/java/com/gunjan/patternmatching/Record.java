package com.gunjan.patternmatching;

interface I {
    boolean isNewCar();
}
record CarRecord(String regNumber, String owner) implements I{

    // custom compact constructor
    public CarRecord {
        if (regNumber.length() <= 4) {
            throw new IllegalArgumentException();
        }
    }

    // non canonical constructor, Non-canonical record constructor must delegate to another constructor
    CarRecord(String regNumber) {
        this(regNumber, "Gunjan");
        if(regNumber.length() < 4 ){
            throw new IllegalArgumentException();
        }
    }

    // Instance field is not allowed in record
    // private int age; 
    public static String currentYear = "2023"; // staic field is allowed in record
    
    // Instance method allowed
    public boolean isNewCar(){
        return regNumber.endsWith(currentYear);
    }

    // Static method allowed
    public static String getCurrentYear(){
        return currentYear;
    }
    
    @Override
    public String owner(){
        return owner.toUpperCase();
    }
}

// Not possible record class is final by default
//class X extends CarRecord { 
//    
//}


public class Record {
    public static void main(String[] args) {
        final CarRecord carRecord = new CarRecord("KA-2023","Gunjan");
        System.out.println(carRecord);
        System.out.println(carRecord.owner());
        System.out.println(carRecord.regNumber());
        System.out.println(carRecord.currentYear);
        System.out.println(CarRecord.currentYear);
        System.out.println(carRecord.getCurrentYear());
        System.out.println(CarRecord.getCurrentYear());
        System.out.println(carRecord.isNewCar());

    }
}

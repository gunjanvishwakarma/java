package com.gunjan.patternmatching;


sealed interface Vehicle permits Car{
    
} 

sealed class Car implements Vehicle permits Ford,Volvo{
    
}

final class Ford extends Car {
    
}

final class Volvo extends Car {

}
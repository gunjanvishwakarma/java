package com.gunjan.patternmatching;

public class Switch {
    enum Direction {NORTH,SOUTH,EAST,WEST}

    public static void main(String[] args) {

        Direction direction = Direction.NORTH;
        System.out.println(
                switch (direction){
                    // new switch break statement not required
//                  case NORTH,SOUTH -> 5;
//                  default -> 4;
                    case NORTH,SOUTH -> 5;
                    default -> 4;
                    // : syntax we must use yield
//                  case NORTH,SOUTH : yield 5;
//                  default : yield 4;
                    
                }
        );
    }
}

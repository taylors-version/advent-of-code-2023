package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        Day24 day24 = new Day24("input.txt");
        System.out.println("day24 puzzle1: " + day24.puzzle1(200000000000000L, 400000000000000L));
        System.out.println("day24 puzzle2: " + day24.puzzle2());
    }
}

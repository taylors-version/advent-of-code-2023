package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        Day21 day21 = new Day21("input.txt");
        System.out.println("day21 puzzle1: " + day21.puzzle1(64));
        System.out.println("day21 puzzle2: " + day21.puzzle2(26501365));
    }
}

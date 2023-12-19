package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        Day19 day19 = new Day19("inputP.txt", "inputW.txt");
        System.out.println("day19 puzzle1: " + day19.puzzle1());
        System.out.println("day19 puzzle2: " + day19.puzzle2());
    }
}

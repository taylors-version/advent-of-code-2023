package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Universe universe = new Universe();
        System.out.println("day11 puzzle1: " + universe.findShortestDistances("input.txt"));
        
    }
}

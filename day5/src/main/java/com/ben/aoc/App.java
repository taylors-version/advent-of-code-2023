package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        LocationFinder solver = new LocationFinder();
        System.out.println("day5 puzzle1: " + solver.findSmallestLocation("input.txt"));
        System.out.println("day5 puzzle2: " + solver.findSmallestLocation2("input.txt"));
    }
}

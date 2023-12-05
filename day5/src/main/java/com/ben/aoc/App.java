package com.ben.aoc;

import java.sql.Timestamp;

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
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Before puzzle2: " + timestamp);
        System.out.println("day5 puzzle2: " + solver.findSmallestLocation2("input.txt"));
        timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("After puzzle2: " + timestamp);
    }
}

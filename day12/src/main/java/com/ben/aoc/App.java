package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Record record = new Record();
        System.out.println("day12 puzzle1: " + record.getPossibleArrangements("input.txt"));
        System.out.println("day12 puzzle2: " + record.getPossibleArrangements2("input.txt"));
        
    }
}

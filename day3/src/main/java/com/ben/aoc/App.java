package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Schematic solver = new Schematic();
        System.out.println("day3 puzzle1: " + solver.getPartNumberCount( "input.txt"));
        System.out.println("day3 puzzle2: " + solver.getGears( "input.txt"));
    }
}

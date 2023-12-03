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
    }
}

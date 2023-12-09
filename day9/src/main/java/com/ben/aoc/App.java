package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Oasis oasis = new Oasis();
        System.out.println("day9 puzzle1: " + oasis.findNextValues("input.txt"));
        System.out.println("day9 puzzle2: " + oasis.findPreviousValues("input.txt"));
        
    }
}

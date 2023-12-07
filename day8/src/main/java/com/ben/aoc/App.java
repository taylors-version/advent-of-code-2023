package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BoatRace solver = new BoatRace();
        System.out.println("day8 puzzle1: " + solver.getWaysToWin("input.txt"));
    }
}

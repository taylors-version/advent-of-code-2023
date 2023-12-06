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
        System.out.println("day6 puzzle1: " + solver.getWaysToWin("input.txt"));
        System.out.println("day6 puzzle2: " + solver.getWaysToWin2("input.txt"));
    }
}

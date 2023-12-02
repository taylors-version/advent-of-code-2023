package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Solver solver = new Solver();
        System.out.println("day2 puzzle1: " + solver.solve(new int[]{ 12, 13, 14 }, "input.txt"));
        System.out.println("day2 puzzle2: " + solver.cubeSolve("input.txt"));
    }
}

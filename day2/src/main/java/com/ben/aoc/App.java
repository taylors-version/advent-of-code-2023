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
        System.out.println(solver.solve(new int[]{ 12, 13, 14 }, "input.txt"));
    }
}

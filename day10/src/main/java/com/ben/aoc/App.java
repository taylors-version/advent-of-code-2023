package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Pipes pipes = new Pipes();
        System.out.println("day10 puzzle1: " + pipes.findFurthestPoint("input.txt"));
        System.out.println("day10 puzzle2: " + pipes.findInsidePoints("input.txt"));
    }
}

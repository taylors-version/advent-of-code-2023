package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        GhostMap ghostMap = new GhostMap();
        System.out.println("day8 puzzle1: " + ghostMap.navigate("input.txt"));
        System.out.println("day8 puzzle2: " + ghostMap.navigateAsGhost("input.txt"));
    }
}

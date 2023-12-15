package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        AsciiHash asciiHash = new AsciiHash("input.txt");
        System.out.println("day15 puzzle1: " + asciiHash.getHashValue());
        System.out.println("day15 puzzle2: " + asciiHash.getFocussingPowen());
    }
}

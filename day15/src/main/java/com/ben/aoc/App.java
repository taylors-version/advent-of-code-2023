package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        Rocks north = new Rocks("input.txt");
        System.out.println("day15 puzzle1: " + north.getNorthWeight());
    }
}

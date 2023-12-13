package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        MirrorMap mirrorMap = new MirrorMap("input.txt");
        System.out.println("day14 puzzle1: " + mirrorMap.getReflectionSum());
    }
}

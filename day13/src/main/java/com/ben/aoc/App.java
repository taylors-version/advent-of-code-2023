package com.ben.aoc;


public class App 
{
    public static void main( String[] args )
    {
        MirrorMap mirrorMap = new MirrorMap("input.txt");
        System.out.println("day13 puzzle1: " + mirrorMap.getReflectionSum());
        System.out.println("day13 puzzle2: " + mirrorMap.getReflectionSmudgeSum());
    }
}

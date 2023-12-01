package com.ben.aoc;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Calibrator calibrator = new Calibrator();
        System.out.println(calibrator.calibrate("input.txt"));
    }
}

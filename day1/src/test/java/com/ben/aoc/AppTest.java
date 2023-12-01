package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Calibrator calibrator = new Calibrator();

    @Test
    public void test1txt()
    {
    	assertEquals(11, calibrator.calibrate("1.txt"));
    }
    
    @Test
    public void test2txt()
    {
    	assertEquals(22, calibrator.calibrate("2.txt"));
    }
    
    @Test
    public void test3txt()
    {
    	assertEquals(12, calibrator.calibrate("3.txt"));
    }
    
    @Test
    public void testExample()
    {
    	assertEquals(142, calibrator.calibrate("givenTest.txt"));
    }
}

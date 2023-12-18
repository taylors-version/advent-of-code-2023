package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day18.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day18 day18 = new Day18("givenTest.txt");
    	assertEquals(62, day18.puzzle1());
    }
    

    @Test
    public void test2Example()
    {
    	Day18 day18 = new Day18("givenTest.txt");
    	assertEquals(952408144115L, day18.puzzle2());
    }
    
}

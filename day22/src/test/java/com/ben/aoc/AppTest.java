package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day22.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day22 day22 = new Day22("givenTest.txt");
    	assertEquals(5, day22.puzzle1());
    }
    
    
    @Test
    public void testExample2()
    {
    	Day22 day22 = new Day22("givenTest.txt");
    	assertEquals(7, day22.puzzle2());
    }
   
    
}

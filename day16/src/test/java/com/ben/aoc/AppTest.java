package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day14.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day16 day16 = new Day16("givenTest.txt");
    	assertEquals(136, day16.puzzle1());
    }	
    
}

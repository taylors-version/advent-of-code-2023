package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day24.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day25 day25 = new Day25("givenTest.txt");
    	assertEquals(54, day25.puzzle1());
    }
    
}

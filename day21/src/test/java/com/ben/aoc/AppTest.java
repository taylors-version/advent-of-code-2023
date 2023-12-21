package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day21.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day21 day21 = new Day21("givenTest.txt");
    	assertEquals(16, day21.puzzle1(6));
    }

}

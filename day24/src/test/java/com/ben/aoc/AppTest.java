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
    	Day24 day24 = new Day24("givenTest.txt");
    	assertEquals(2, day24.puzzle1(7, 27));
    }
    
    @Test
    public void testExample2()
    {
    	Day24 day24 = new Day24("givenTest.txt");
    	assertEquals(47, day24.puzzle2());
    }

}

package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day17.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day17 day17 = new Day17("givenTest.txt");
    	assertEquals(102, day17.puzzle1());
    }	
    
    @Test
    public void test2Example()
    {
    	Day17 day17 = new Day17("givenTest.txt");
    	assertEquals(94, day17.puzzle2());
    }
    
    
    
}

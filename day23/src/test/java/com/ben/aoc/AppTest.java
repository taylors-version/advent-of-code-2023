package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day23.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day23 day23 = new Day23("givenTest.txt");
    	assertEquals(94, day23.puzzle1());
    }
    
    
    @Test
    public void testExample2()
    {
    	Day23 day23 = new Day23("givenTest.txt");
    	assertEquals(154, day23.puzzle2());
    }

    
}

package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Universe universe = new Universe();

    @Test
    public void testExample()
    {
    	assertEquals(374, universe.findShortestDistances("givenTest.txt"));
    }
    

}

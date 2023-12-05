package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	LocationFinder solver = new LocationFinder();

    @Test
    public void test1line()
    {
    	assertEquals(82, solver.findSmallestLocation("1.txt"));
    }
    
    @Test
    public void testExample()
    {
    	assertEquals(35, solver.findSmallestLocation("givenTest.txt"));
    }
    


}

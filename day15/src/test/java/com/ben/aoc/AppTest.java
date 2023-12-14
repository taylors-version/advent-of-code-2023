package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day15.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Rocks rocks = new Rocks("givenTest.txt");
    	assertEquals(136, rocks.getNorthWeight());
    }	

}

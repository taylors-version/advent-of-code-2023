package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day16.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day16 day16 = new Day16("givenTest.txt");
    	assertEquals(46, day16.puzzle1());
    }
    
    @Test
    public void testExample1()
    {
    	Day16 day16 = new Day16("1.txt");
    	assertEquals(5, day16.puzzle1());
    }	
    
    @Test
    public void test2Example()
    {
    	Day16 day16 = new Day16("givenTest.txt");
    	assertEquals(51, day16.puzzle2());
    }
    
}

package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day20.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day20 day20 = new Day20("givenTest.txt");
    	assertEquals(32000000, day20.puzzle1());
    }
    
    
    @Test
    public void testExampl2e()
    {
    	Day20 day20 = new Day20("givenTest2.txt");
    	assertEquals(11687500, day20.puzzle1());
    }
    

    @Test
    public void test2Example()
    {
    	Day20 day20 = new Day20("givenTest.txt");
    	assertEquals(0, day20.puzzle2());
    }
    
}

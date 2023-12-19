package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day19.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	Day19 day19 = new Day19("givenTestP.txt", "givenTestW.txt");
    	assertEquals(19114, day19.puzzle1());
    }
    

    @Test
    public void test2Example()
    {
    	Day19 day19 = new Day19("givenTestP.txt", "givenTestW.txt");
    	assertEquals(167409079868000L, day19.puzzle2());
    }
    
}

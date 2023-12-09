package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Oasis oasis = new Oasis();

    @Test
    public void testExample()
    {
    	assertEquals(114, oasis.findNextValues("givenTest.txt"));
    }
    

}

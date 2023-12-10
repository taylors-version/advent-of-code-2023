package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Pipes pipes = new Pipes();

    @Test
    public void testExample()
    {
    	assertEquals(8, pipes.findFurthestPoint("givenTest.txt"));
    }
    

}

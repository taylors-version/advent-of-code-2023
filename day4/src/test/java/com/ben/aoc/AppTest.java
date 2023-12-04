package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Solver solver = new Solver();

    @Test
    public void test1line()
    {
    	assertEquals(8, solver.solve("1.txt"));
    }
    
    @Test
    public void testExample()
    {
    	assertEquals(13, solver.solve("givenTest.txt"));
    }
   

}

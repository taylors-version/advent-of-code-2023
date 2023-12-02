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
    	assertEquals(1, solver.solve(new int[]{ 12, 13, 14 }, "1.txt"));
    }
    
    @Test
    public void test2lines()
    {
    	assertEquals(1, solver.solve(new int[]{ 12, 13, 14 }, "2.txt"));
    }
    
    
    
    @Test
    public void testExample()
    {
    	assertEquals(8, solver.solve(new int[]{ 12, 13, 14 }, "givenTest.txt"));
    }

}

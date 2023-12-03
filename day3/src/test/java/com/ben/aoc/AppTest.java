package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Schematic solver = new Schematic();
/*
    @Test
    public void test1line()
    {
    	assertEquals(1, solver.getPartNumberCount(new int[]{ 12, 13, 14 }, "1.txt"));
    }
    */
    
    
    @Test
    public void testExample()
    {
    	assertEquals(4361, solver.getPartNumberCount( "givenTest.txt"));
    }
    
  
}

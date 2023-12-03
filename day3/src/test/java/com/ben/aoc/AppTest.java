package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	Schematic solver = new Schematic();
    
    
    @Test
    public void testExample()
    {
    	assertEquals(4361, solver.getPartNumberCount( "givenTest.txt"));
    }
    
    @Test
    public void testGearsExample()
    {
    	assertEquals(467835, solver.getGears( "givenTest.txt"));
    }
    
    
  
}

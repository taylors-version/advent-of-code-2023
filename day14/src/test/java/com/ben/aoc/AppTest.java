package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for day14.
 */
public class AppTest 
{	    
	/*
    @Test
    public void testExample()
    {
    	Rocks rocks = new Rocks("givenTest.txt");
    	assertEquals(136, rocks.getNorthWeight());
    }	
    */
    @Test
    public void test2Example()
    {
    	Rocks rocks = new Rocks("givenTest.txt");
    	assertEquals(64, rocks.getNorthWeightAfterSpins());
    }	
    
}

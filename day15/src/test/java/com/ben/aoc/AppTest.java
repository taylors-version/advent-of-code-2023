package com.ben.aoc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for day15.
 */
public class AppTest 
{	    
	
    @Test
    public void testExample()
    {
    	AsciiHash asciiHash = new AsciiHash("givenTest.txt");
    	assertEquals(1320, asciiHash.getHashValue());
    }	

    @Test
    public void testExample2()
    {
    	AsciiHash asciiHash = new AsciiHash("givenTest.txt");
    	assertEquals(145, asciiHash.getFocussingPowen());
    }	

}

package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for day14.
 */
public class AppTest 
{	    
    @Test
    public void testExample()
    {
    	MirrorMap mirrorMap = new MirrorMap("givenTest.txt");
    	assertEquals(405, mirrorMap.getReflectionSum());
    }	
    
}

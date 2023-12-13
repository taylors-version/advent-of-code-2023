package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	MirrorMap mirrorMap = new MirrorMap();
	
    @Test
    public void testExampleHorizontal()
    {
    	assertEquals(400, mirrorMap.getReflectionSum("horizontal.txt"));
    }
    
    @Test
    public void testExampleVertical()
    {
    	assertEquals(5, mirrorMap.getReflectionSum("vertical.txt"));
    }
    
    
    @Test
    public void testExample()
    {
    	assertEquals(405, mirrorMap.getReflectionSum("givenTest.txt"));
    }
    

}

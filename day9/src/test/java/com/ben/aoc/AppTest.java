package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	GhostMap ghostMap = new GhostMap();

    @Test
    public void testExample()
    {
    	assertEquals(6, ghostMap.navigate("givenTest.txt"));
    }
    
    @Test
    public void testExampleGhost()
    {
    	assertEquals(6, ghostMap.navigateAsGhost("givenTest2.txt"));
    }

}

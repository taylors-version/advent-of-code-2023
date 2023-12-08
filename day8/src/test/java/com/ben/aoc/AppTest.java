package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	ChostMap ghostMap = new ChostMap();

    @Test
    public void testExample()
    {
    	assertEquals(6, ghostMap.navigate("givenTest.txt"));
    }


}

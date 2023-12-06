package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	BoatRace boatRace = new BoatRace();

    @Test
    public void testExample()
    {
    	assertEquals(288, boatRace.getWaysToWin("givenTest.txt"));
    }

}

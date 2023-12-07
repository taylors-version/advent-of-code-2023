package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for simple day1.
 */
public class AppTest 
{
	CamelCards camelCards = new CamelCards();

    @Test
    public void testExample()
    {
    	assertEquals(6440, camelCards.getWinAmount("givenTest.txt"));
    }
    /*
    @Test
    public void testSimple()
    {
    	assertEquals(6440, camelCards.getWinAmount("1.txt"));
    }*/

}

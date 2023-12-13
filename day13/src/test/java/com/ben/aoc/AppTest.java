package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Unit test for day13.
 */
public class AppTest 
{	
    @Test
    public void testExampleHorizontal()
    {
    	MirrorMap mirrorMap = new MirrorMap("horizontal.txt");
    	assertEquals(400, mirrorMap.getReflectionSum());
    }
    
    @Test
    public void testExampleVertical()
    {
    	MirrorMap mirrorMap = new MirrorMap("vertical.txt");
    	assertEquals(5, mirrorMap.getReflectionSum());
    }
    
    
    @Test
    public void testExample()
    {
    	MirrorMap mirrorMap = new MirrorMap("givenTest.txt");
    	assertEquals(405, mirrorMap.getReflectionSum());
    }	
	
    @Test
    public void testExampleSmudgeVertical()
    {
    	MirrorMap mirrorMap = new MirrorMap("vertical.txt");
    	assertEquals(300, mirrorMap.getReflectionSmudgeSum());
    }
    
    @Test
    public void testExampleSmudgeHorizontal()
    {
    	MirrorMap mirrorMap = new MirrorMap("horizontal.txt");
    	assertEquals(100, mirrorMap.getReflectionSmudgeSum());
    }
    
    @Test
    public void testExampleSmudge()
    {
    	MirrorMap mirrorMap = new MirrorMap("givenTest.txt");
    	assertEquals(400, mirrorMap.getReflectionSmudgeSum());
    }
	
    @Test
    public void testExampleSmudge1()
    {
    	MirrorMap mirrorMap = new MirrorMap("1.txt");
    	assertNotEquals(0, mirrorMap.getReflectionSmudgeSum());
    }
}

package com.ben.aoc;

import static org.junit.Assert.*;

import org.junit.Test;


public class AppTest 
{
	Record record = new Record();    
	
    @Test
    public void testExample1()
    {
    	assertEquals(1, record.getPossibleArrangements("1.txt"));
    }
    @Test
    public void testExample2()
    {
    	assertEquals(4, record.getPossibleArrangements("2.txt"));
    }
	
    @Test
    public void testExample3()
    {
    	assertEquals(1, record.getPossibleArrangements("3.txt"));
    }
    
    @Test
    public void testExample4()
    {
    	assertEquals(1, record.getPossibleArrangements("4.txt"));
    }
    @Test
    public void testExample5()
    {
    	assertEquals(4, record.getPossibleArrangements("5.txt"));
    }
    @Test
    public void testExample6()
    {
    	assertEquals(10, record.getPossibleArrangements("6.txt"));
    }
    
    @Test
    public void testExample()
    {
    	assertEquals(21, record.getPossibleArrangements("givenTest.txt"));
    }
    
    @Test
    public void test2Example1()
    {
    	assertEquals(1, record.getPossibleArrangements2("1.txt"));
    }
    @Test
    public void test2Example2()
    {
    	assertEquals(16384, record.getPossibleArrangements2("2.txt"));
    }
	
    @Test
    public void test2Example3()
    {
    	assertEquals(1, record.getPossibleArrangements2("3.txt"));
    }
    
    @Test
    public void test2Example4()
    {
    	assertEquals(16, record.getPossibleArrangements2("4.txt"));
    }
    @Test
    public void test2Example5()
    {
    	assertEquals(2500, record.getPossibleArrangements2("5.txt"));
    }
    @Test
    public void test2Example6()
    {
    	assertEquals(506250, record.getPossibleArrangements2("6.txt"));
    }
	
}

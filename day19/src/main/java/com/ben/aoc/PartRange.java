package com.ben.aoc;

public class PartRange implements Cloneable{
	int minX = 1;
	int maxX = 4000;
	int minM = 1;
	int maxM = 4000;
	int minA = 1;
	int maxA = 4000;
	int minS = 1;
	int maxS = 4000;
	
	public PartRange() {
		
	}
	
	@Override
	public String toString() {
		return "minX: " + minX + " maxX: " + maxX + " minM: " + minM + " maxM: " + maxM + " minA: " + minA + " maxA: " + maxA + " minS: " + minS + " maxS: " + maxS; 
	}
}

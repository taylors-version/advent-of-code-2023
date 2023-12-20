package com.ben.aoc;

public class FlipFlop {
	
	boolean state;
	String[] destinations;
	
	public FlipFlop(String[] destinations) {
		this.state = false;
		this.destinations = destinations;
	}
	
	public Integer getPulse(int input) {
		if(input == 0) {
			state = !state;
			return state ? 1 : 0;
		}
		
		return null;
	}

}

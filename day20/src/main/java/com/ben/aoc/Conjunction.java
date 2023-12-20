package com.ben.aoc;

import java.util.Map;

public class Conjunction {
	
	Map<String, Integer> memory;
	String[] destinations;
	
	public Conjunction(String[] destinations) {
		this.destinations = destinations;
	}
	
	public void addInput(String input) {
		memory.put(input, 0);
	}
	
	public Integer getPulse(int input, String sender) {
		memory.put(sender, input);
		int output = 0;
		
		for(int memValue : memory.values()) {
			if(memValue == 0) {
				output = 1;
			}
		}
		
		return output;
	}

}

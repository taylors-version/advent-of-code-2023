package com.ben.aoc;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Module {
	
	enum Type{
		BROADCASTER,
		FLIPFLOP,
		CONJUNCTION,
		NONE
	}
	String name;
	Type type;
	String[] destinations;
	boolean state;
	Map<String, Integer> memory = new HashMap<String, Integer>();
	
	public Module(Type type, String name, String[] destinations) {
		this.type = type;
		this.destinations = destinations;
		this.name = name;
	}
	
	public void addInput(String input) {
		memory.put(input, 0);
	}
	
	public Integer getPulse(int input, String sender) {
		switch (type) {
		case FLIPFLOP:
			if(input == 0) {
				state = !state;
				return state ? 1 : 0;
			}
			
			return null;
		case CONJUNCTION:
			memory.put(sender, input);
			int output = 0;
			
			for(int memValue : memory.values()) {
				if(memValue == 0) {
					output = 1;
				}
			}
			
			return output;
		case BROADCASTER:
			return input;
		default:
			return null;
		}
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(!(o instanceof Module)) {
			return false;
		}
		Module m = (Module) o;
		return m.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hashCode(name);
	}
}

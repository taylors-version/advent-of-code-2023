package com.ben.aoc;

import java.util.Objects;

public class Lens {
	
	private String name;
	private int strength;
	private int boxValue;
	
	public Lens(String name, int strength) {
		this.name = name;
		this.strength = strength;
		this.boxValue = calculateBoxValue(name);
	}
		
	private int calculateBoxValue(String name) {
		int result = 0;
		for (char c: name.toCharArray()) {
			int ascii = (int) c;
			result += ascii;
			result = result * 17;
			result = result % 256;
		}
		return result;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(o == null) {
			return false;
		}
		if(!(o instanceof Lens)) {
			return false;
		}
		Lens l = (Lens) o;
		return l.name.equals(this.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getBoxValue() {
		return boxValue;
	}

	public void setBoxValue(int boxValue) {
		this.boxValue = boxValue;
	}
	
	

}

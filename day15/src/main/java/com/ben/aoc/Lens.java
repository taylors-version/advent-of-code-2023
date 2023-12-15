package com.ben.aoc;

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

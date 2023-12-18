package com.ben.aoc;

public class Instruction {
	char direction;
	int distance;
	String colour;
	
	public Instruction(char direction, int distance, String colour) {
		this.direction = direction;
		this.distance = distance;
		this.colour = colour;
	}
}

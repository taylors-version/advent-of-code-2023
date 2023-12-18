package com.ben.aoc;

public class Instruction {
	char direction;
	int distance;
	int hexDistance;
	char hexDirection;
	String colour;
	
	public Instruction(char direction, char hexDirection, int distance, int hexDistance, String colour) {
		this.direction = direction;
		this.distance = distance;
		this.colour = colour;
		this.hexDirection = hexDirection;
		this.hexDistance = hexDistance;
	}
	
	@Override
	public String toString() {
		return direction + " " + distance;
	}
}

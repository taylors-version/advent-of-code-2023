package com.ben.aoc;

public class Part {

	int x;
	int m;
	int a;
	int s;
	
	public Part(int x, int m, int a, int s) {
		this.x = x;
		this.m = m;
		this.a = a;
		this.s = s;
	}
	
	public int getValue() {
		return x+m+a+s;
	}
}

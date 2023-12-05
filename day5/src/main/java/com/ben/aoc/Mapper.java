package com.ben.aoc;

public class Mapper {
	private int destinationStart;
	private int sourceStart;
	private int rangeLength;
	
	public Mapper(int destinationStart, int sourceStart, int rangeLength) {
		this.destinationStart = destinationStart;
		this.sourceStart = sourceStart;
		this.rangeLength = rangeLength;
	}
	
	public Mapper(String destinationStart, String sourceStart, String rangeLength) {
		this.destinationStart = Integer.parseInt(destinationStart);
		this.sourceStart = Integer.parseInt(sourceStart);
		this.rangeLength = Integer.parseInt(rangeLength);
	}
	
	public int getDestination(int source) {
		System.out.println("deststart: " + destinationStart + ", sourcestart: " + sourceStart + ", range: " + rangeLength + "source: " + source);
		if(source >= sourceStart && source < sourceStart + rangeLength) {
			System.out.println("in iff block");
			System.out.println("returning " + (destinationStart + (source - sourceStart)));
			return destinationStart + (source - sourceStart);
		}
		return -1;
	}
	
	public int getDestination(String source) {
		return getDestination(Integer.parseInt(source));
	}

	public int getDestinationStart() {
		return destinationStart;
	}

	public int getSourceStart() {
		return sourceStart;
	}

	public int getRangeLength() {
		return rangeLength;
	}
	
	
	
}

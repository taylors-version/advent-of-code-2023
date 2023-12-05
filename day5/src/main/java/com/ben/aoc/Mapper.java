package com.ben.aoc;

public class Mapper {
	private long destinationStart;
	private long sourceStart;
	private long rangeLength;
	
	public Mapper(int destinationStart, int sourceStart, int rangeLength) {
		this.destinationStart = destinationStart;
		this.sourceStart = sourceStart;
		this.rangeLength = rangeLength;
	}
	
	public Mapper(String destinationStart, String sourceStart, String rangeLength) {
		this.destinationStart = Long.parseLong(destinationStart);
		this.sourceStart = Long.parseLong(sourceStart);
		this.rangeLength = Long.parseLong(rangeLength);
	}
	
	public long getDestination(long source) {
		if(source >= sourceStart && source < sourceStart + rangeLength) {
			return destinationStart + (source - sourceStart);
		}
		return -1;
	}
	
	public long getDestination(String source) {
		return getDestination(Long.parseLong(source));
	}

	public long getDestinationStart() {
		return destinationStart;
	}

	public long getSourceStart() {
		return sourceStart;
	}

	public long getRangeLength() {
		return rangeLength;
	}
	
	
	
}

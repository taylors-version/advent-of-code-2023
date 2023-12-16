package com.ben.aoc;

import java.util.List;

public class Day17 {
	private char[][] mirrorPattern;
	
	public Day17(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		mirrorPattern = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			mirrorPattern[i] = lines.get(i).toCharArray();
		}
	}
		
	public long puzzle1() {		
		long result = 0;
		
		return result;
	}
	
	public long puzzle2() {
		long result = 0;

	
		return result;
	}
	
}

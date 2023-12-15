package com.ben.aoc;

import java.util.List;

public class Day16 {
	private char[][] rockPattern;
	
	public Day16(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		rockPattern = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			rockPattern[i] = lines.get(i).toCharArray();
		}
	}
		
	public long puzzle1() {
		long result = 0;
		

		
		return result;
	}
	
}

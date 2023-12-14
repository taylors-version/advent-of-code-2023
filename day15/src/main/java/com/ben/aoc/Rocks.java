package com.ben.aoc;

import java.util.List;

public class Rocks {
	private char[][] rockPattern;
	
	public Rocks(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		rockPattern = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			rockPattern[i] = lines.get(i).toCharArray();
		}
	}
		
	public long getNorthWeight() {
		long result = 0;
		
		int numberOfRows = rockPattern.length;
	
		for(int i = 0; i<numberOfRows; i++) {
			for(int j= 0; j<rockPattern[0].length; j++) {
				if(i==0 && rockPattern[i][j] == 'O') {
					result += numberOfRows;
				}else if(rockPattern[i][j] == 'O'){
					int heightToBeStoppedAt = 0;
					for(int k = i-1; k>=0; k--) {
						if(rockPattern[k][j] == 'O') {
							heightToBeStoppedAt++;
						}else if(rockPattern[k][j] == '#') {
							heightToBeStoppedAt = k+heightToBeStoppedAt+1;
							break;
						}
					}
					result += numberOfRows - heightToBeStoppedAt;
				}
			}
		}
		
		return result;
	}
}

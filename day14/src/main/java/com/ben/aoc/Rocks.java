package com.ben.aoc;

import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rocks {
	private static final long billion = 1000000000;
	private char[][] rockPattern;
	int zeroCounter = 0;
	
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
	
	public long getNorthWeightAfterSpins() {
		

		char[][] newRocks = rockPattern;
		
		Map<String, Long> seenMaps = new HashMap<String, Long>();
		boolean isRepeated = false;
		
		for(long i = 0; i<billion; i++) {
			newRocks = tiltNorth(newRocks);//N
			newRocks = Util.rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//W
			newRocks = Util.rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//S
			newRocks = Util.rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//E
			newRocks = Util.rotateMatrix(newRocks);
			String matrixString = matrixToString(newRocks);
			if(!isRepeated && seenMaps.containsKey(matrixString)) {
				isRepeated = true;
				long diff = i - seenMaps.get(matrixString);
				i+= diff * ((billion-i) / diff);
			}
			seenMaps.put(matrixToString(newRocks), i);
		}
		
		return calculateNorthLoad(newRocks);
	}
	
	private char[][] tiltNorth(char[][] rocks){
		char[][] newRocks = rocks;
		
		int numberOfRows = newRocks.length;
		for(int i = 1; i<numberOfRows; i++) {
			for(int j= 0; j<newRocks[0].length; j++) {
				if(newRocks[i][j] == 'O'){
					newRocks[i][j] = '.';
					for(int k = i-1; k>=0; k--) {
						if(newRocks[k][j] == 'O' || newRocks[k][j] == '#') {
							
							newRocks[k+1][j] = 'O';
							break;
						}else if(k==0) {
							newRocks[k][j] = 'O';
						}
					}
				}
			}
		}
		return newRocks;
	}
	
	private long calculateNorthLoad(char[][] rocks) {
		long result = 0;
		int numberOfRows = rocks.length;
		for(int i = 0; i<numberOfRows; i++) {
			for(int j= 0; j<rocks[0].length; j++) {
				if(rocks[i][j] == 'O'){
					result += numberOfRows - i;
				}
			}
		}
		
		return result;
	}
	
	private String matrixToString(char[][] matrix) {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<matrix.length-1; i++) {
			sb.append(Arrays.toString(matrix[i]));
			sb.append(",");
		}
		sb.append(Arrays.toString(matrix[matrix.length - 1]));
		return sb.toString();
	}
}

package com.ben.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rocks {
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
		
		/**
		for (long i = 0; i<1000000000; i++) {
			
		}**/
		char[][] newRocks = rockPattern;
		
		Map<String, Long> seenMaps = new HashMap<String, Long>();
		seenMaps.put(matrixToString(newRocks), 0L);
		boolean isRepeated = false;
		
		for(long i = 1; i<=1000000000; i++) {
			if(i%10000 == 0) {
				System.out.println(i);
			}
			newRocks = tiltNorth(newRocks);//N
			newRocks = rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//W
			newRocks = rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//S
			newRocks = rotateMatrix(newRocks);
			newRocks = tiltNorth(newRocks);//E
			newRocks = rotateMatrix(newRocks);
			if(!isRepeated && seenMaps.containsKey(matrixToString(newRocks))) {
				isRepeated = true;
				long repeated = seenMaps.get(matrixToString(newRocks));
				System.out.println("Found repeat of " + repeated + " at " + i);
				long pattern = i - repeated;
				/*
				long div = (1000000000 - (pattern*2))/pattern;
				long newI = div *pattern + repeated;
				*/
				long newI = repeated;
				while(newI < 1000000000 - (pattern*2)) {
					newI+=pattern;
				}
				System.out.println("setting i to : " + newI);
				i = newI;
			}
			seenMaps.put(matrixToString(newRocks), i);
		}
		
		/*for(int i = 0; i<newRocks.length; i++) {
			System.out.println(Arrays.toString(newRocks[i]));
		}*/
		
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
	
	private static char[][] rotateMatrix(char[][] matrix){
		char[][] result = new char[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][(matrix.length-1) -i] = matrix[i][j];
			}
		}
		return result;
	}
	
	private long calculateNorthLoad(char[][] rocks) {
		long result = 0;
		int numberOfRows = rocks.length;
		for(int i = 1; i<numberOfRows; i++) {
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
		for(int i=0; i<matrix.length; i++) {
			sb.append(Arrays.toString(matrix[i]));
		}
		return sb.toString();
	}
		
}

package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

public class MirrorMap {
	private List<char[][]> maps;
	int zeroCounter = 0;
	
	public MirrorMap(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		maps = new ArrayList<char[][]>();
		List<List<String>> mapStrings = new ArrayList<List<String>>();
		List<String> singleMap = new ArrayList<String>();
		for(String line : lines) {
			if (line.isBlank()) {
				mapStrings.add(singleMap);
				singleMap = new ArrayList<String>();
			}else {
				singleMap.add(line);
			}
				
		}
		mapStrings.add(singleMap);
		
		for(int i =0; i<mapStrings.size(); i++) {
			List<String> StringMap = mapStrings.get(i);
			char[][] map = new char[StringMap.size()][];
			for(int j = 0; j<StringMap.size(); j++) {
				map[j] = StringMap.get(j).toCharArray();
			}
			maps.add(map);
		}
	}
		
	public long getReflectionSum() {
		long result = 0;
		
		
		for(char[][] m : maps) {
			long horizontalSymmetry = checkHorizontalSymmetry(m, 0);
			long verticalSymmetry = checkVerticalSymmetry(m, 0);
			result += horizontalSymmetry*100;
			result += verticalSymmetry;
		}
		return result;
	}
	
	public long getReflectionSmudgeSum() {
		long result = 0;
		
		for(int i = 0; i<maps.size(); i++) {
			char[][] map = maps.get(i);
			result +=getSmudgedReflection(map);
		}
		
		return result;
	}
	
	private long getSmudgedReflection(char[][] map) {
		long horizontalSymmetry = checkHorizontalSymmetry(map, 0);
		long verticalSymmetry = checkVerticalSymmetry(map, 0);
		long oldReflection = horizontalSymmetry*100 + verticalSymmetry;

		
		char[][] newMap = new char[map.length][];
		for (int i = 0; i<newMap.length; i++) {
			newMap[i] = map[i].clone();
		}

		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j<map[i].length; j++) {
				newMap[i][j] = newMap[i][j] == '.' ? '#' : '.';
				
				if (checkHorizontalLineHasMatch(newMap, i)) {
					long horizontalValue = 100 * checkHorizontalSymmetry(newMap, horizontalSymmetry);
					if(horizontalValue!=0 && horizontalValue != oldReflection) {
						return horizontalValue;
					}
				}
				if (checkVerticalLineHasMatch(newMap, j)) {
					long verticalValue = checkVerticalSymmetry(newMap, verticalSymmetry);
					if(verticalValue!=0 && verticalValue != oldReflection) {
						return verticalValue;
					}
				}
				newMap[i][j] = newMap[i][j] == '.' ? '#' : '.';
			}
		}
		zeroCounter++;
		return 0;
	}
	
	
	private long checkHorizontalSymmetry(char[][] singleMap, long oldResult) {
		long result = 0;
		
		for(int i = 0; i<singleMap.length-1; i++) {
			boolean isSymmetry = true;
			for(int a = i, b=i+1; a >=0 && b<singleMap.length; a--, b++) {
				String aString = new String (singleMap[a]);
				String bString = new String (singleMap[b]);
				if(!aString.equals(bString)) {
					isSymmetry = false;
					break;
				}
			}
			if(isSymmetry && oldResult != i+1) {
				return i+1;
			}
		}
		
		return result;
	}
	
	private long checkVerticalSymmetry(char[][] singleMap, long oldResult) {
		long result = 0;
		
		for(int i = 0; i<singleMap[0].length-1; i++) {
			boolean isSymmetry = true;
			for(int a = i, b=i+1; a >=0 && b<singleMap[0].length; a--, b++) {
				StringBuilder aSb = new StringBuilder();
				StringBuilder bSb = new StringBuilder();
				for(int s =0; s<singleMap.length; s++) {
					aSb.append(singleMap[s][a]);
					bSb.append(singleMap[s][b]);
				}
				String aString = aSb.toString();
				String bString = bSb.toString();
				if(!aString.equals(bString)) {
					isSymmetry = false;
					break;
				}
			}
			if(isSymmetry && i+1 != oldResult) {
				return i+1;
			}
		}
		
		return result;
	}
	
	private boolean checkHorizontalLineHasMatch(char[][] singleMap, int row) {	
		String rowString = new String (singleMap[row]);
		for(int i = 0; i<singleMap.length-1; i++) {
			if(i!=row) {
				String iString = new String(singleMap[i]);
				if(rowString.equals(iString)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	private boolean checkVerticalLineHasMatch(char[][] singleMap, int column) {
		StringBuilder cSb = new StringBuilder();
		for(int s =0; s<singleMap.length; s++) {
			cSb.append(singleMap[s][column]);
		}
		String columnString = new String (cSb);
		
		for(int i = 0; i<singleMap[0].length-1; i++) {
			if(i!=column) {
				StringBuilder iSb = new StringBuilder();
				for(int s =0; s<singleMap.length; s++) {
					iSb.append(singleMap[s][i]);
				}
				String iString = iSb.toString();
				if(columnString.equals(iString)) {
					return true;
				}
			}
		}
		
		return false;	
	}
		
}

package com.ben.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class MirrorMap {
	private List<char[][]> maps;
		
	public long getReflectionSum(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		maps = new ArrayList<char[][]>();
		List<List<String>> mapStrings = new ArrayList<List<String>>();
		List<String> singleMap = new ArrayList<String>();
		//lines.add(0, "");
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
		
		for(char[][] m : maps) {
			long horizontalSymmetry = checkHorizontalSymmetry(m);
			long verticalSymmetry = checkVerticalSymmetry(m);
			result += horizontalSymmetry*100;
			result += verticalSymmetry;
		}
		return result;
	}
	
	private long checkHorizontalSymmetry(char[][] singleMap) {
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
			if(isSymmetry) {
				return i+1;
			}
		}
		
		return result;
	}
	
	private long checkVerticalSymmetry(char[][] singleMap) {
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
			if(isSymmetry) {
				return i+1;
			}
		}
		
		return result;
	}
		
}

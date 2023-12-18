package com.ben.aoc;

import java.util.List;

import com.ben.aoc.dijkstra.Dijkstra;

public class Day17 {
	static char[][] blockPattern;
	static int iLength;
	static int jLength;
	
	public Day17(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		blockPattern = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			blockPattern[i] = lines.get(i).toCharArray();
		}
		
		iLength = blockPattern.length;
		jLength = blockPattern[0].length;

	}
		
	public long puzzle1() {		
		Crucible crucibler = (Crucible) Dijkstra.calculateShortestPath(new Crucible(new IntPoint(0,0), 0, new Direction('r'), 0));
		Crucible crucibled = (Crucible) Dijkstra.calculateShortestPath(new Crucible(new IntPoint(0,0), 0, new Direction('d'), 0));

		int downValue;
		int rightValue;
		if(crucibled == null) {
			downValue = Integer.MAX_VALUE;
		}else {
			downValue = crucibled.getDistance();
		}
		
		if(crucibler == null) {
			rightValue = Integer.MAX_VALUE;
		}else {
			rightValue = crucibler.getDistance();
		}
		return Math.min(downValue, rightValue);
	}
	
	public long puzzle2() {
		UltraCrucible crucibler = (UltraCrucible) Dijkstra.calculateShortestPath(new UltraCrucible(new IntPoint(0,0), 0, new Direction('r'), 0));
		UltraCrucible crucibled = (UltraCrucible) Dijkstra.calculateShortestPath(new UltraCrucible(new IntPoint(0,0), 0, new Direction('d'), 0));

		int downValue;
		int rightValue;
		if(crucibled == null) {
			downValue = Integer.MAX_VALUE;
		}else {
			downValue = crucibled.getDistance();
		}
		
		if(crucibler == null) {
			rightValue = Integer.MAX_VALUE;
		}else {
			rightValue = crucibler.getDistance();
		}
		
		return Math.min(downValue, rightValue);
	}
	
}

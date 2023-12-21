package com.ben.aoc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day21 {
	
	List<String> lines;
	char[][] gardenMap;
	IntPoint startPosition;
	
	public Day21(String fileName) {
		lines = Util.readFile(getClass(), fileName);
		
		gardenMap = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			gardenMap[i] = line.toCharArray();
			if(line.indexOf('S') != -1) {
				int startX = line.indexOf('S');
				startPosition = new IntPoint(startX, i);
				gardenMap[i][startX] = '.';
			}
		}
		
	}
		
	public long puzzle1(int maxSteps) {		
		
		Set<IntPoint> possibleGardens = new HashSet<IntPoint>();
		possibleGardens.add(startPosition);
		
		for(int i=0; i<maxSteps; i++) {
			Set<IntPoint> nextGardens = new HashSet<IntPoint>();
			for(IntPoint garden : possibleGardens) {
				for(Point<Integer> neighbour : garden.allNeighbours()) {
					if(neighbour.getY()>=0 && neighbour.getY()<gardenMap.length && neighbour.getX()>=0 && neighbour.getX()<gardenMap[0].length) {
						if(gardenMap[neighbour.getY()][neighbour.getX()] == '.') {
							nextGardens.add((IntPoint) neighbour);
						}
					}
				}
			}
			possibleGardens = nextGardens;
		}
		
		return possibleGardens.size();
	}
	
	public long puzzle2(int maxSteps) {
		Set<IntPoint> possibleGardens = new HashSet<IntPoint>();
		possibleGardens.add(startPosition);
		
		int gridLength = gardenMap.length;
		int rem = maxSteps%gridLength;
		List<Integer> values = new ArrayList<Integer>();
		
		for(int i=1; i<=(rem + 2*gridLength); i++) {

			Set<IntPoint> nextGardens = new HashSet<IntPoint>();
			for(IntPoint garden : possibleGardens) {
				for(Point<Integer> neighbour : garden.allNeighbours()) {
					IntPoint boundedNeighbour = getBoundedPoint((IntPoint) neighbour);
					if(gardenMap[boundedNeighbour.getY()][boundedNeighbour.getX()] == '.') {
						nextGardens.add((IntPoint) neighbour);
					}
				}
			}
			possibleGardens = nextGardens;
			if(i%gridLength == rem) {
				values.add(possibleGardens.size());
			}			
		}
		
		int target = maxSteps/gridLength;

		return quadratic(values, target);
	}	
	
	private IntPoint getBoundedPoint(IntPoint unbounded) {
		int x = unbounded.getX();
		int y = unbounded.getY();
		while (x<0) {
			x+=gardenMap[0].length;
		}
		while (y<0) {
			y+=gardenMap.length;
		}
		x = x%gardenMap[0].length;
		y = y%gardenMap.length;
		return new IntPoint(x, y);
	}
	
	private long quadratic(List<Integer> first3Values, int target) {
		int x0 = first3Values.get(0);
		int x1 = first3Values.get(1);
		int x2 = first3Values.get(2);
		
		long c = x0;
		long a = (x2 - 2*x1 +c)/2;
		long b = (x1-x0) - a;
		
		return a*target*target + b*target + c;
	}
}

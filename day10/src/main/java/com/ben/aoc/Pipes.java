package com.ben.aoc;

import java.util.List;

import org.javatuples.Pair;
import org.javatuples.Triplet;

public class Pipes {
		
	public long findFurthestPoint(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		char[][] layout = new char[lines.size()][];
		
		Triplet<Integer, Integer, String> currentPosition = null;
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			layout[i] = line.toCharArray();
			if(line.indexOf('S') != -1) {
				currentPosition = new Triplet<Integer, Integer, String>(i, line.indexOf('S'), ".");
			}
		}
		
		char currentValue = '.';
		while(currentValue != 'S') {
			System.out.println(currentValue);
			System.out.println(currentPosition.toString());
			result++;
			currentPosition = getNextPosition(layout, currentPosition);
			currentValue = layout[currentPosition.getValue0()][currentPosition.getValue1()];

		}
		
		
		return (result + 1) /2;
	}
	
	private Triplet<Integer, Integer, String> getNextPosition(char[][] layout, Triplet<Integer, Integer, String> position) {
		Triplet<Integer, Integer, String> nextPosition = position;
		char currentPipe = layout[position.getValue1()][position.getValue0()];
		String directionFrom = position.getValue2();
		
		if(currentPipe == '|') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() + 1, "N");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() - 1, "S");
			}
		}else if(currentPipe == '-') {
			if("W".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0() + 1, position.getValue1(), "W");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0() - 1, position.getValue1(), "E");
			}
		}else if(currentPipe == 'L') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0() + 1, position.getValue1(), "W");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() - 1, "S");
			}
		}else if(currentPipe == 'J') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0() - 1, position.getValue1(), "E");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() - 1, "S");
			}
		}else if(currentPipe == '7') {
			if("S".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0() - 1, position.getValue1(), "E");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() + 1, "N");
			}
		}else if(currentPipe == 'F') {
			if("S".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(position.getValue0() + 1, position.getValue1(), "W");
			}else {
				return new Triplet<Integer, Integer, String>(position.getValue0(), position.getValue1() + 1, "N");
			}
		}else {
			int y = position.getValue0();
			int x = position.getValue1();
			int height = layout.length;
			//int width = layout[0].length;
			
			System.out.println("y, x-1: " + layout[y][x-1]);
			
			//Come from south
			if(y>1 && (layout[y-1][x] == '|' || layout[y-1][x] == 'F' || layout[y-1][x] == '7')) {
				return new Triplet<Integer, Integer, String>(y-1, x, "S");
			}//come from north
			else if(y<height - 1 && (layout[y+1][x] == '|' || layout[y+1][x] == 'L' ||layout[y+1][x] == 'J')) {
				return new Triplet<Integer, Integer, String>(y+1, x, "N");
			}//come from E
			else if(x>1 && (layout[y][x-1] == '-') || layout[y][x-1] == 'F' || layout[y][x-1] == 'L') {
				return new Triplet<Integer, Integer, String>(y, x-1, "E");
			}

		}		
		return nextPosition;
		
	}
		
}

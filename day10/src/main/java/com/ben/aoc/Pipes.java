package com.ben.aoc;

import java.util.Arrays;
import java.util.List;

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

			result++;
			currentPosition = getNextPosition(layout, currentPosition);
			int y = currentPosition.getValue0();
			int x = currentPosition.getValue1();
			currentValue = layout[y][x];

		}
		
		return (result + 1) /2;
	}
	
	public long findInsidePoints(String fileName) {
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
		
		char[][] types = new char[layout.length][layout[0].length];
		for(int i = 0; i<types.length; i++) {
			Arrays.fill(types[i], '0');
		}
		
		char currentValue = '.';
		while(currentValue != 'S') {
			currentPosition = getNextPosition(layout, currentPosition);
			int y = currentPosition.getValue0();
			int x = currentPosition.getValue1();
			currentValue = layout[y][x];
			if(currentValue == 'L' || currentValue == 'J' || currentValue == '|'  ) {
				types[y][x] = '*';
			}else {
				types[y][x] = 'X';
			}
		}
		
		for(int i = 1; i<types.length-1; i++) {
			int counter = 0;
			for (int j = 1; j<types[i].length-1; j++) {
				char type = types[i][j];
				if(type == '*') {
					counter++;
				}else if(type == '0' && counter %2 != 0) {
					result ++;
				}
			}
		}
		return result;
	}
	
	private Triplet<Integer, Integer, String> getNextPosition(char[][] layout, Triplet<Integer, Integer, String> position) {
		Triplet<Integer, Integer, String> nextPosition = position;
		int y = position.getValue0();
		int x = position.getValue1();
		char currentPipe = layout[y][x];
		String directionFrom = position.getValue2();
		
		if(currentPipe == '|') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y+1, x, "N");
			}else {
				return new Triplet<Integer, Integer, String>(y-1, x, "S");
			}
		}else if(currentPipe == '-') {
			if("W".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y, x+1, "W");
			}else {
				return new Triplet<Integer, Integer, String>(y, x-1, "E");
			}
		}else if(currentPipe == 'L') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y, x+1, "W");
			}else {
				return new Triplet<Integer, Integer, String>(y-1, x, "S");
			}
		}else if(currentPipe == 'J') {
			if("N".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y, x-1, "E");
			}else {
				return new Triplet<Integer, Integer, String>(y-1, x, "S");
			}
		}else if(currentPipe == '7') {
			if("S".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y, x-1, "E");
			}else {
				return new Triplet<Integer, Integer, String>(y+1, x, "N");
			}
		}else if(currentPipe == 'F') {
			if("S".equals(directionFrom)) {
				return new Triplet<Integer, Integer, String>(y, x+1, "W");
			}else {
				return new Triplet<Integer, Integer, String>(y+1, x, "N");
			}
		}else {

			int height = layout.length;
						
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

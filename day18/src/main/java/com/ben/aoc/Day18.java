package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

public class Day18 {
	List<String> lines;
	List<Instruction> instructions;
	
	public Day18(String fileName) {
		lines = Util.readFile(getClass(), fileName);
		
		instructions = new ArrayList<Instruction>();
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			String[] lineSplit = line.split(" ");
			
			String colour = lineSplit[2];
			char direction = lineSplit[0].charAt(0);
			int distance = Integer.parseInt(lineSplit[1]);
			char hexDirection = numberToDir(colour.charAt(7));
			String hexDistanceString = colour.substring(2, 7);
			int hexDistance = Integer.parseInt(hexDistanceString, 16);
			Instruction instruction = new Instruction(direction, hexDirection, distance, hexDistance, colour);
			instructions.add(instruction);
		}
	}
		
	public long puzzle1() {		
		List<Pair<Long, Long>> vertices = new ArrayList<Pair<Long,Long>>();
		
		long i = 0;
		long j = 0;
		long length = 0;
		Pair<Long, Long> vertice = new Pair<Long, Long>(0L,0L);
		vertices.add(vertice);
		
		for(Instruction instruction : instructions) {
			switch (instruction.direction) {
			case 'R':
				j += instruction.distance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'L':
				j -= instruction.distance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'D':
				i -= instruction.distance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'U':
				i += instruction.distance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			}
			length += instruction.distance;
			vertices.add(vertice);
		}
		
		long shoeLaceArea = getShoeLaceArea(vertices);
		
		long area = (long)(shoeLaceArea + length/2 + 1);
		
		return area;
	}
	
	public long puzzle2() {
		List<Pair<Long, Long>> vertices = new ArrayList<Pair<Long,Long>>();
		
		long i = 0;
		long j = 0;
		long length = 0;
		Pair<Long, Long> vertice = new Pair<Long, Long>(0L,0L);
		vertices.add(vertice);
		
		for(Instruction instruction : instructions) {
			switch (instruction.hexDirection) {
			case 'R':
				j += instruction.hexDistance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'L':
				j -= instruction.hexDistance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'D':
				i -= instruction.hexDistance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			case 'U':
				i += instruction.hexDistance;
				vertice = new Pair<Long, Long>(i, j);
				break;
			}
			length += instruction.hexDistance;
			vertices.add(vertice);
		}
		
		long shoeLaceArea = getShoeLaceArea(vertices);
		
		long area = (long)(shoeLaceArea + length/2 + 1);
		
		return area;
	}

	
	
	private long getShoeLaceArea(List<Pair<Long, Long>> vertices) {
		long area = 0;
		
		for(int i = 0; i<vertices.size() -1; i++) {
			Pair<Long, Long> vertice1 = vertices.get(i);
			Pair<Long, Long> vertice2 = vertices.get(i+1);
			area += ((vertice1.getValue1() + vertice2.getValue1()) * (vertice1.getValue0() - vertice2.getValue0()));
		}
		
		Pair<Long, Long> vertice1 = vertices.get(vertices.size()-1);
		Pair<Long, Long> vertice2 = vertices.get(0);
		area += ((vertice1.getValue1() + vertice2.getValue1()) * (vertice1.getValue0() - vertice2.getValue0()));
		return (long)Math.abs(area / 2.0);
	}
	
	private char numberToDir(char number) {
		switch (number) {
		case '0':
			return 'R';
		case '1':
			return 'D';
		case '2':
			return 'L';
		case '3':
			return 'U';
		}
		return 'R';
	}
	
}

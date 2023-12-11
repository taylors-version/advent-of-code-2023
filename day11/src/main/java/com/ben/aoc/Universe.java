package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

public class Universe {
	
	private char[][] universe;
	List<Integer> rowsToDouble = new ArrayList<Integer>();
	List<Integer> columnsToDouble = new ArrayList<Integer>();
	List<Pair<Integer, Integer>> galaxies = new ArrayList<Pair<Integer, Integer>>();
	List<Pair<Pair<Integer, Integer>,Pair<Integer, Integer>>> galaxyPairs = new ArrayList<Pair<Pair<Integer,Integer>,Pair<Integer, Integer>>>();
	
		
	public long findShortestDistances(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		universe = new char[lines.size()][];
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);

			universe[i] = line.toCharArray();
		}
		
		expand();
		
		for(int i = 0; i<universe.length; i++) {
			for (int j=0; j<universe[0].length; j++) {
				if(universe[i][j] == '#') {
					galaxies.add(new Pair<Integer, Integer>(i,j));
				}
			}
		}
		
		getPairsOfGalaxies();
		
		for(Pair<Pair<Integer, Integer>,Pair<Integer,Integer>> galaxyPair: galaxyPairs) {
			long distance = getDistance(galaxyPair, 1);
			result += distance;		
		}
		
		
		return result;
	}
	
	public long findShortestDistances2(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		universe = new char[lines.size()][];
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);

			universe[i] = line.toCharArray();
		}
		
		expand();
		
		for(int i = 0; i<universe.length; i++) {
			for (int j=0; j<universe[0].length; j++) {
				if(universe[i][j] == '#') {
					galaxies.add(new Pair<Integer, Integer>(i,j));
				}
			}
		}
		
		getPairsOfGalaxies();
		
		for(Pair<Pair<Integer, Integer>,Pair<Integer,Integer>> galaxyPair: galaxyPairs) {
			long distance = getDistance(galaxyPair, 1000000);
			result += distance;		
		}
		
		
		return result;
	}
	
	private void expand() {
	
		for(int i=0; i<universe.length; i++) {
			if(!doesRowHaveGalaxy(i)){
				rowsToDouble.add(i);
			}
		}
		for(int i=0; i<universe[0].length; i++) {
			if (!doesColumnHaveGalaxy(i)) {
				columnsToDouble.add(i);
			}
		}

	}
	
	private void getPairsOfGalaxies() {

		for (int i=0; i<galaxies.size(); i++) {
			for(int j=i+1; j<galaxies.size(); j++) {
				Pair<Pair<Integer,Integer>,Pair<Integer,Integer>> galaxyPair = new Pair<Pair<Integer,Integer>,Pair<Integer,Integer>>(galaxies.get(i), galaxies.get(j));
				galaxyPairs.add(galaxyPair);
			}
		}
	}
	
	private long getDistance(Pair<Pair<Integer, Integer>,Pair<Integer,Integer>> galaxyPair, long expansion) {
		long distance = 0;
		int rowsToExpand = 0;
		int columnsToExpand = 0;
		
		int x_min = Math.min(galaxyPair.getValue0().getValue1(), galaxyPair.getValue1().getValue1());
		int x_max = Math.max(galaxyPair.getValue0().getValue1(), galaxyPair.getValue1().getValue1());
		int y_min = Math.min(galaxyPair.getValue0().getValue0(), galaxyPair.getValue1().getValue0());
		int y_max = Math.max(galaxyPair.getValue0().getValue0(), galaxyPair.getValue1().getValue0());
		
		for(Integer i = x_min; i<x_max; i++) {
			if (rowsToDouble.contains(i)){
				rowsToExpand++;
			}
		}
		for(Integer i = y_min; i<y_max; i++) {
			if (columnsToDouble.contains(i)){
				columnsToExpand++;
			}
		}

		
		
		distance += (y_max + columnsToExpand*expansion) - y_min;
		distance += (x_max + rowsToExpand*expansion) - x_min;


		return distance;
	}
	
	private boolean doesRowHaveGalaxy(int row) {
		for(int j=0; j<universe[0].length; j++) {
			if(universe[row][j] == '#') {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean doesColumnHaveGalaxy(int column) {
		for(int j=0; j<universe.length; j++) {
			if(universe[j][column] == '#') {
				return true;
			}
		}
		
		return false;
	}
		
}

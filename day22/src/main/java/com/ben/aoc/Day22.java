package com.ben.aoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.javatuples.Pair;

public class Day22 {
	List<String> lines;
	List<Brick> bricks = new ArrayList<Brick>();
	Map<Integer, List<Pair<Integer, Integer>>> layers = new HashMap<Integer, List<Pair<Integer,Integer>>>();
	Map<Integer, List<Brick>> layerBricks = new HashMap<Integer, List<Brick>>();
	
	public Day22(String fileName) {
		lines = Util.readFile(getClass(), fileName);
		
		for(String l : lines) {
			String[] tildeSplit = l.split("~");
			String[] firstCoords = tildeSplit[0].split(",");
			String[] secondCoords = tildeSplit[1].split(",");
			int x1 = Integer.parseInt(firstCoords[0]);
			int y1 = Integer.parseInt(firstCoords[1]);
			int z1 = Integer.parseInt(firstCoords[2]);
			int x2 = Integer.parseInt(secondCoords[0]);
			int y2 = Integer.parseInt(secondCoords[1]);
			int z2 = Integer.parseInt(secondCoords[2]);
			
			Brick b = new Brick(x1, y1, z1, x2, y2, z2);
			bricks.add(b);
		}
		
		Collections.sort(bricks);
		
	}
		
	public long puzzle1() {		
		
		gravity();
		
		Set<Brick> removableBricks = new HashSet<Brick>();
		removableBricks.addAll(bricks);
		for(Brick b : bricks) {
			if(b.getSupportingBricks().size() == 1) {
				removableBricks.removeAll(b.getSupportingBricks());
			}
		}
		
		return removableBricks.size();
	}
	
	public long puzzle2() {
		long result = 0;
		
		return result;
	}
	
	private void gravity() {
		Brick groundBrick = bricks.get(0);
		if(groundBrick.z1<1) {
			groundBrick.fall(1);
		}
		
		layers.put(groundBrick.z2, groundBrick.getPoints());
		List<Brick> groundLayer = new ArrayList<Brick>();
		groundLayer.add(groundBrick);
		layerBricks.put(groundBrick.z2, groundLayer);
		
		
		for(int i = 1; i<bricks.size(); i++) {
			Brick brick = bricks.get(i);
			int lowestBase = getLowestSupportedLayer(brick);
			System.out.println("Brick: " + brick + " lowest supported: " + lowestBase);
			brick.fall(lowestBase + 1);
			if(!layers.containsKey(brick.z2)) {
				layers.put(brick.z2, brick.getPoints());
				List<Brick> layerBrick = new ArrayList<Brick>();
				layerBrick.add(brick);
				layerBricks.put(brick.z2, layerBrick);
			}else {
				List<Pair<Integer, Integer>> layer = layers.get(brick.z2);
				layer.addAll(brick.getPoints());
				layers.put(brick.z2, layer);
				List<Brick> layerBs = layerBricks.get(brick.z2);
				layerBs.add(brick);
				layerBricks.put(brick.z2, layerBs);
			}
			
		}
	}
	
	private int getLowestSupportedLayer(Brick brick) {
		
		int brickBase = brick.z1;
		for(int base = brickBase -1; base >0; base --) {
			if(layers.containsKey(base)) {
				List<Pair<Integer, Integer>> baseCoords = layers.get(base);
				for(Pair<Integer, Integer> p : baseCoords) {
					if(brick.getPoints().contains(p)) {
						for(Brick b : layerBricks.get(base)) {
							brick.isSupportedBy(b);
						}
						return base;
					}
				}
				
			}
		}
		
		return 0;
	}
}

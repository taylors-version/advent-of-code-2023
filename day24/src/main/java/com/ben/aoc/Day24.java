package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Pair;

public class Day24 {
	
	List<Hail> hailStones = new ArrayList<Hail>();
	
	public Day24(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			String[] lineSplit = line.split(" @ ");
			String[] positions = lineSplit[0].split(", ");
			String[] velocities = lineSplit[1].split(", ");
			
			long px = Long.parseLong(positions[0].trim());
			long py = Long.parseLong(positions[1].trim());
			long pz = Long.parseLong(positions[2].trim());
			int vx = Integer.parseInt(velocities[0].trim());
			int vy = Integer.parseInt(velocities[1].trim());
			int vz = Integer.parseInt(velocities[2].trim());
			
			hailStones.add(new Hail(px, py, pz, vx, vy, vz));
		}
		
	}
		
	public long puzzle1(long min, long max) {		
		long result = 0;
		
		for(int i = 0; i<hailStones.size() - 1; i++) {
			Hail h1 = hailStones.get(i);
			for(int j = i+1; j<hailStones.size(); j++) {
				Hail h2 = hailStones.get(j);
				Pair<Double, Double> intersection = h1.getCollision(h2);
				if(intersection != null) {
					double x = intersection.getValue0();
					double y = intersection.getValue1();
					if(x >= min && x <= max && y >= min && y <= max) {
						if(!h1.isPointInPast(intersection) && !h2.isPointInPast(intersection)) {
							result++;
						}
					}
				}
			}
		}
		
		return result;
	}
	
	public long puzzle2() {
		long result = 0;
		
		return result;
	}	
}

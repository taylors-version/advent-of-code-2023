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
		
		StringBuilder equations = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            String t = "t" + i;
            equations.append(t).append(" >= 0, ").append(hailStones.get(i).px).append(" + ").append(hailStones.get(i).vx).append(t).append(" == x + vx ").append(t).append(", ");
            equations.append(hailStones.get(i).py).append(" + ").append(hailStones.get(i).vy).append(t).append(" == y + vy ").append(t).append(", ");
            equations.append(hailStones.get(i).pz).append(" + ").append(hailStones.get(i).vz).append(t).append(" == z + vz ").append(t).append(", ");
        }
        String sendToMathematica = "Solve[{" + equations.substring(0, equations.length() - 2) +  "}, {x,y,z,vx,vy,vz,t0,t1,t2}]";
        System.out.println(sendToMathematica);
        
        //From Mathematica
      //{{x->391948057347762,y->141735778870011,z->235597456470414,vx->-213,vy->282,vz->52,t0->952175128507,t1->540953137053,t2->379199939675}} 
        
        long x = 391948057347762L;
        long y = 141735778870011L;
        long z = 235597456470414L;
        
		
		return x+y+z;
	}	
}

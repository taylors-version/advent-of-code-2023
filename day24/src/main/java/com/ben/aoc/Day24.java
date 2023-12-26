package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

import org.ejml.simple.SimpleMatrix;
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
	
	public double puzzle2() {
		
		
		SimpleMatrix A = generateMatrix(hailStones.get(0), hailStones.get(1), hailStones.get(2));
		SimpleMatrix b = generateVector(hailStones.get(0), hailStones.get(1), hailStones.get(2));
		
		
		SimpleMatrix x = A.solve(b);
		
		
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
        
        long px = (long) x.get(0);
        long py = (long) x.get(1);
        long pz = (long) x.get(2);
        
        
		return px+py+pz;
	}
	
	private SimpleMatrix generateMatrix(Hail a, Hail b, Hail c) {
		
		float[][] data = new float[6][6];
		data[0][0] = a.vy - b.vy;
		data[0][1] = b.vx - a.vx;
		data[0][2] = 0;
		data[0][3] = b.py - a.py;
		data[0][4] = a.px - b.px;
		data[0][5] = 0;
		
		data[1][0] = a.vy - c.vy;
		data[1][1] = c.vx - a.vx;
		data[1][2] = 0;
		data[1][3] = c.py - a.py;
		data[1][4] = a.px - c.px;
		data[1][5] = 0;
		
		data[2][0] = b.vz - a.vz;
		data[2][1] = 0;
		data[2][2] = a.vx - b.vx;
		data[2][3] = a.pz - b.pz;
		data[2][4] = 0;
		data[2][5] = b.px - a.px;
		
		data[3][0] = c.vz - a.vz;
		data[3][1] = 0;
		data[3][2] = a.vx - c.vx;
		data[3][3] = a.pz - c.pz;
		data[3][4] = 0;
		data[3][5] = c.px - a.px;
		
		data[4][0] = 0;
		data[4][1] = a.vz - b.vz;
		data[4][2] = b.vy - a.vy;
		data[4][3] = 0;
		data[4][4] = b.pz - a.pz;
		data[4][5] = a.py - b.py;
		
		data[5][0] = 0;
		data[5][1] = a.vz - c.vz;
		data[5][2] = c.vy - a.vy;
		data[5][3] = 0;
		data[5][4] = c.pz - a.pz;
		data[5][5] = a.py - c.py;
		
		
		SimpleMatrix m = new SimpleMatrix(data);
		
		return m;
	}
	
	private SimpleMatrix generateVector(Hail a, Hail b, Hail c) {
		float[] data = new float[6];
		data[0] = (b.py * b.vx - b.px * b.vy) - (a.py * a.vx - a.px * a.vy);
		data[1] = (c.py * c.vx - c.px * c.vy) - (a.py * a.vx - a.px * a.vy);
		data[2] = (b.px * b.vz - b.pz * b.vx) - (a.px * a.vz - a.pz * a.vx);
		data[3] = (c.px * c.vz - c.pz * c.vx) - (a.px * a.vz - a.pz * a.vx);
		data[4] = (b.pz * b.vy - b.py * b.vz) - (a.pz * a.vy - a.py * a.vz);
		data[5] = (c.pz * c.vy - c.py * c.vz) - (a.pz * a.vy - a.py * a.vz);
		
		SimpleMatrix m = new SimpleMatrix(data);
		return m;
	}
	
}

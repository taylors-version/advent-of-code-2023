package com.ben.aoc;

import org.javatuples.Pair;

public class Hail {
	
	long px;
	long py;
	long pz;
	final int vx;
	final int vy;
	final int vz;
	
	double m;
	double c;
	
	public Hail(long px, long py, long pz, int vx, int vy, int vz) {
		this.px = px;
		this.py = py;
		this.pz = pz;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		
		long x2 = px + vx;
		long y2 = py + vy;
		
		m = ((double)y2 - py) / (x2 - px);
        c = -(m * px) + py;
        
	}
	
	public Pair<Double, Double> getCollision(Hail h){
		if(m == h.m) {
			return null;
		}
		double x = (h.c - c) / (m - h.m);
	    double y = m * x + c;

	    return new Pair<Double, Double>(x, y);
	}
	
	public boolean isPointInPast(Pair<Double, Double> point) {
		double x = point.getValue0();
		double y = point.getValue1();
		
		if(vx>0 && x<px) {
			return true;
		}
		if(vx<0 && x>px) {
			return true;
		}
		if(vy>0 && y<py) {
			return true;
		}
		if(vy<0 && y>py) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "" + px + ", " + py + ", " + pz + " @ " + vx + ", " + vy + ", " + vz;
	}

}

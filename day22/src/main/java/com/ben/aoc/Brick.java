package com.ben.aoc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.javatuples.Pair;

public class Brick implements Comparable<Brick>{
	
	int x1;
	int y1;
	int z1;
	int x2;
	int y2;
	int z2;
	
	Set<Brick> supportingBricks = new HashSet<Brick>();
	
	/**
	 * Note: this class has a natural ordering that is inconsistent with equals
	 * 
	 * @param x1
	 * @param y1
	 * @param z1
	 * @param x2
	 * @param y2
	 * @param z2
	 */
	public Brick(int x1, int y1, int z1, int x2, int y2, int z2) {
		this.x1 = Math.min(x1, x2);
		this.x2 = Math.max(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.y2 = Math.max(y1, y2);
		this.z1 = Math.min(z1, z2);
		this.z2 = Math.max(z1, z2);
	}
	
	public void fall(int newBase) {
		int distance = z1 - newBase;
		z1 -= distance;
		z2 -= distance;
				
	}
	
	public List<Pair<Integer, Integer>> getPoints(){
		List<Pair<Integer, Integer>> coords = new ArrayList<Pair<Integer,Integer>>();
		if(x1==x2) {
			for(int i = y1; i<=y2; i++) {
				Pair<Integer, Integer> coord = new Pair<Integer, Integer>(x1, i);
				coords.add(coord);
			}
		}else {
			for(int i = x1; i<=x2; i++) {
				Pair<Integer, Integer> coord = new Pair<Integer, Integer>(i, y1);
				coords.add(coord);
			}
		}
		return coords;
	}
	
	public void isSupportedBy(Brick b) {
		List<Pair<Integer, Integer>> points = getPoints();
		for(Pair<Integer, Integer> p : b.getPoints()) {
			if(points.contains(p)) {
				addSupportingBrick(b);
				break;
			}
		}
	}
	
	public void addSupportingBrick(Brick support) {
		supportingBricks.add(support);
	}
	
	public Set<Brick> getSupportingBricks(){
		return supportingBricks;
	}
	
	@Override
	public String toString() {
		return x1 + ", " + y1 + ", " + z1 + "~" + x2 + ", " + y2 + ", " + z2;
	}
	
	@Override
	public int compareTo(Brick b) {
		if(b == null) {
			throw new NullPointerException();
		}
		
		return Integer.valueOf(z1).compareTo(Integer.valueOf(b.z1));
	}
	
	@Override 
	public boolean equals(Object o){
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(!(o instanceof Brick)) {
			return false;
		}
		Brick b = (Brick) o;
		
		if(x1 == b.x1 && x2 == b.x2 && y1 == b.y1 && y2 == b.y2 && z1 == b.z1 && z2 == b.z2) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(x1, x2, y1, y2, z1, z2);
	}
	

}

package com.ben.aoc;

import java.util.Objects;

import org.javatuples.Pair;

public class Point<T extends Number> {
	
	private Pair<T, T> xy;
	
	public Point(Pair<T, T> xy) {
		this.xy = xy;
	}
	
	public Point(T x, T y){
		Pair<T, T> point = new Pair<T, T>(x, y);
		this.xy = point;
	}
	
	public T getX() {
		return xy.getValue0();
	}
	
	public T getY() {
		return xy.getValue1();
	}
	
	public void setX(T x) {
		xy.setAt0(x);
	}
	
	public void setY(T y) {
		xy.setAt1(y);
	}
	
	public String toString() {
		return "(" + xy.getValue0() + ", " + xy.getValue1() + ")";
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(this == o) {
			return true;
		}
		if(!(o instanceof Point)) {
			return false;
		}
		Point p = (Point) o;
		if(this.getX() == p.getX() && this.getY() == p.getY()) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(this.getX(), this.getY());
	}

}

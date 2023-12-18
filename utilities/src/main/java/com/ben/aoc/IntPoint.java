package com.ben.aoc;

/**
 * Point class for integer matrices
 * Note in the below matrix the highlighted point([1][0]) is 0,1 (y=1, x=0)
 * [ ][ ]
 * [x][0]
 */
public class IntPoint extends Point<Integer> {

	public IntPoint(Integer x, Integer y) {
		super(x, y);
	}

	@Override
	public Point<Integer> above() {
		return new IntPoint(getX(), getY()-1);
	}

	@Override
	public Point<Integer> below() {
		return new IntPoint(getX(), getY()+1);
	}

	@Override
	public Point<Integer> right() {
		return new IntPoint(getX()+1, getY());
	}

	@Override
	public Point<Integer> left() {
		return new IntPoint(getX()-1, getY());
	}
	


}

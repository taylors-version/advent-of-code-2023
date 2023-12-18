package com.ben.aoc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import com.ben.aoc.dijkstra.State;

public class Crucible extends State {
	final IntPoint location;
	final int cost;
	final Direction direction;
	final int directionMoves;
	
	public Crucible(IntPoint location, int cost, Direction direction, int directionMoves) {
		this.location = location;
		this.cost = cost;
		this.direction = direction;
		this.directionMoves = directionMoves;
	}

	@Override
	public int getCost() {
		return cost;
	}

	@Override
	public List<State> next() {
		LinkedList<State> nextList = new LinkedList<>();
		if (directionMoves > 3)
			return nextList;

		if (directionMoves <= 2) {
			IntPoint nextPoint = (IntPoint) location.getByDirection(direction);
			int v = getValueP(nextPoint);
			if (v >= 0) {
				nextList.add(new Crucible(nextPoint, v, direction, directionMoves + 1));
			}
		}

		// anticlockwise turn
		IntPoint nextPoint = (IntPoint) location.getByDirection(direction.rotateAntiClockwise());

		int v = getValueP(nextPoint);
		if (v >= 0) {
			nextList.add(new Crucible(nextPoint, v, direction.rotateAntiClockwise(), 1));
		}

		// clockwise turn
		nextPoint = (IntPoint) location.getByDirection(direction.rotateClockwise());

		v = getValueP(nextPoint);
		if (v >= 0) {
			nextList.add(new Crucible(nextPoint, v, direction.rotateClockwise(), 1));
		}

		return nextList;
	}

	@Override
	public String toString() {
		return "" + location.toString() + " " + direction.toString() + " " + directionMoves;
	}

	@Override
	public boolean isFinished() {
		if (directionMoves <= 3 && location.getY() == Day17.blockPattern.length - 1
				&& location.getX() == Day17.blockPattern[0].length - 1) {
			return true;
		}
		return false;
	}
	
	public int getValueP(IntPoint p) {
		int i = p.getY();
		int j = p.getX();
		if (i < 0 || i >= Day17.iLength || j < 0 || j >= Day17.jLength) {
			return -1;
		}
		char ch = Day17.blockPattern[i][j];

		return (int) (ch - '0');

	}
	
	@Override
	public boolean equals(Object o){
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(!(o instanceof Crucible)) {
			return false;
		}
		Crucible c = (Crucible)o;
		if(location.equals(c.location) && direction.equals(c.direction) && directionMoves == c.directionMoves) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {

		return Objects.hash(location, direction, directionMoves);
	}

}

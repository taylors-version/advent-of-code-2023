package com.ben.aoc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import org.javatuples.Pair;

import com.ben.aoc.dijkstra.State;

public class UltraCrucible extends State {
	final Pair<Integer, Integer> location;
	final int cost;
	final Pair<Integer, Integer> direction;
	final int directionMoves;
	
	public UltraCrucible(Pair<Integer, Integer> location, int cost, Pair<Integer, Integer> direction, int directionMoves) {
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
		if (directionMoves > 10)
			return nextList;

		if (directionMoves <= 10) {
			Pair<Integer, Integer> nextPoint = getNextPoint(location, direction);
			int v = getValueP(nextPoint);
			if (v >= 0) {
				nextList.add(new UltraCrucible(nextPoint, v, direction, directionMoves + 1));
			}
		}
		if(directionMoves>=4) {
			// anticlockwise turn
			Pair<Integer, Integer> newDirection = new Pair<Integer, Integer>(-direction.getValue1(), direction.getValue0());
			Pair<Integer, Integer> nextPoint = getNextPoint(location, newDirection);
	
			int v = getValueP(nextPoint);
			if (v >= 0) {
				nextList.add(new UltraCrucible(nextPoint, v, newDirection, 1));
			}
	
			// clockwise turn
			newDirection = new Pair<Integer, Integer>(direction.getValue1(), -direction.getValue0());
			nextPoint = getNextPoint(location, newDirection);
	
			v = getValueP(nextPoint);
			if (v >= 0) {
				nextList.add(new UltraCrucible(nextPoint, v, newDirection, 1));
			}
		}
		return nextList;
	}

	@Override
	public String toString() {
		return "" + location.toString() + " " + direction.toString() + " " + directionMoves;
	}

	@Override
	public boolean isFinished() {
		if (directionMoves >=4 && directionMoves <= 10 && location.getValue0() == Day17.blockPattern.length - 1
				&& location.getValue1() == Day17.blockPattern[0].length - 1) {
			return true;
		}
		return false;
	}
	
	public Pair<Integer, Integer> getNextPoint(Pair<Integer, Integer> current, Pair<Integer, Integer> dir) {
		return new Pair<Integer, Integer>(location.getValue0() + dir.getValue0(),
				location.getValue1() + dir.getValue1());
	}
	
	public int getValueP(Pair<Integer, Integer> p) {
		int i = p.getValue0();
		int j = p.getValue1();
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
		if(!(o instanceof UltraCrucible)) {
			return false;
		}
		UltraCrucible c = (UltraCrucible)o;
		int i = location.getValue0();
		int j = location.getValue1();
		int di = direction.getValue0();
		int dj = direction.getValue1();
		
		int ci = c.location.getValue0();
		int cj = c.location.getValue1();
		int cdi = c.direction.getValue0();
		int cdj = c.direction.getValue1();
		if(i == ci && j == cj && di == cdi && dj == cdj && directionMoves == c.directionMoves) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int i = location.getValue0();
		int j = location.getValue1();
		int di = direction.getValue0();
		int dj = direction.getValue1();
		return Objects.hash(i, j, di, dj, directionMoves);
	}

}

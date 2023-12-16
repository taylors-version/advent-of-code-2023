package com.ben.aoc;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.javatuples.Pair;


public class Node {
	private char[][] mirrorPattern;
	private int i;
	private int j;
	private char type;
	
	public Node(int i, int j, char[][] mirrorPattern) {
		this.type = mirrorPattern[i][j];
		this.i = i;
		this.j = j;
		this.mirrorPattern = mirrorPattern;
	}
	
	/**
	 * 
	 * @param direction beam is travelling r,d,l,u
	 * @return
	 */
	public Set<Pair<Node, Character>> getNeighbours(char direction) {
		Set<Pair<Node, Character>> neighbours = new HashSet<Pair<Node, Character>>();
		
		switch(direction) {
		case 'r':
			if(type == '.' || type == '-') {
				if (j<mirrorPattern[0].length-1) {
					Node neighbour = new Node(i, j+1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'r');
					neighbours.add(pair);
				}
			}if(type == '/' || type == '|') {
				if (i>0) {
					Node neighbour = new Node(i-1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'u');
					neighbours.add(pair);
				}
			}if(type == '\\' || type == '|') {
				if (i<mirrorPattern.length-1) {
					Node neighbour = new Node(i+1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'd');
					neighbours.add(pair);
				}
			}
			break;
		case 'l':
			if(type == '.' || type == '-') {
				if (j>0) {
					Node neighbour = new Node(i, j-1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'l');
					neighbours.add(pair);
				}
			}if(type == '\\' || type == '|') {
				if (i>0) {
					Node neighbour = new Node(i-1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'u');
					neighbours.add(pair);
				}
			}if(type == '/' || type == '|') {
				if (i<mirrorPattern.length-1) {
					Node neighbour = new Node(i+1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'd');
					neighbours.add(pair);
				}
			}
			break;
		case 'u':
			if(type == '.' || type == '|') {
				if (i>0) {
					Node neighbour = new Node(i-1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'u');
					neighbours.add(pair);
				}
			}if(type == '\\' || type == '-') {
				if (j>0) {
					Node neighbour = new Node(i, j-1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'l');
					neighbours.add(pair);
				}
			}if(type == '/' || type == '-') {
				if (j<mirrorPattern[0].length-1) {
					Node neighbour = new Node(i, j+1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'r');
					neighbours.add(pair);
				}
			}
			break;
		case 'd':
			if(type == '.' || type == '|') {
				if (i<mirrorPattern.length-1) {
					Node neighbour = new Node(i+1, j, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'd');
					neighbours.add(pair);
				}
			}if(type == '/' || type == '-') {
				if (j>0) {
					Node neighbour = new Node(i, j-1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'l');
					neighbours.add(pair);
				}
			}if(type == '\\' || type == '-') {
				if (j<mirrorPattern[0].length-1) {
					Node neighbour = new Node(i, j+1, mirrorPattern);
					Pair<Node, Character> pair = new Pair<Node, Character>(neighbour, 'r');
					neighbours.add(pair);
				}
			}
			break;
		}		
		return neighbours;
	}
	
	@Override
	public String toString() {
		return "i: " + i + ", j: " + j + " type: " + type;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		if(o == this) {
			return true;
		}
		if(!(o instanceof Node)){
			return true;
		}
		Node n = (Node) o;
		if(n.i == i && n.j == j && n.type == type) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(i, j, type);
	}

}

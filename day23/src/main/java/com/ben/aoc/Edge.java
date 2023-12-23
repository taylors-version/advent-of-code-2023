package com.ben.aoc;

import java.util.Objects;

public class Edge {

	IntPoint startNode;
	IntPoint endNode;
	int distance;
	
	public Edge(IntPoint startNode, IntPoint endNode, int distance) {
		this.startNode = startNode;
		this.endNode = endNode;
		this.distance = distance;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (this == o) {
			return true;
		}
		if(! (o instanceof Edge)) {
			return false;
		}
		Edge e = (Edge) o;
		if(startNode.equals(e.startNode) && endNode.equals(e.endNode)) {
			return true;
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(startNode, endNode);
	}
	
	@Override
	public String toString() {
		return "" + startNode + " -> " + endNode + ": " + distance;
	}
	
}

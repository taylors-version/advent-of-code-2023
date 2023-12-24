package com.ben.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.javatuples.Pair;

public class Day23 {
	Set<IntPoint> nodes = new HashSet<IntPoint>();
	List<Edge> edges = new ArrayList<Edge>();
	
	IntPoint startPoint;
	IntPoint endPoint;
	static char[][] trail;
	static int iLength;
	static int jLength;
	
	public Day23(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		trail = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			trail[i] = lines.get(i).toCharArray();
		}
		iLength = trail.length;
		jLength = trail[0].length;
		
		startPoint = new IntPoint(1, 0);
		endPoint = new IntPoint(jLength - 2, iLength - 1);
		
		nodes.add(startPoint);
		nodes.add(endPoint);
		
		for(int i = 0; i<trail.length; i++) {
			for(int j = 0; j<trail[0].length; j++) {
				if(trail[i][j] != '#') {
					IntPoint here = new IntPoint(j, i);
					if(isPointANode(here)) {
						nodes.add(here);
					}
				}
			}
		}
		
	}
		
	public long puzzle1() {
		findEdges(true);
		
		for(Edge e : edges) {
			System.out.println(e);
		}
		
		return findMaxDistance();
	}
	
	public long puzzle2() {
		findEdges(false);
		
		return findMaxDistance();
	}
	
	private long findMaxDistance() {
		long result = 0;
		
		List<List<IntPoint>> allPaths = new ArrayList<List<IntPoint>>();
		
		
		findAllRoutes(startPoint, endPoint, new HashSet<IntPoint>(), new ArrayList<IntPoint>(), allPaths);
		
		for (int j = 0; j<allPaths.size(); j++) {
			List<IntPoint> p = allPaths.get(j);
			p.add(0, startPoint);
			int sum = 0;
			for(int i = 0; i<p.size()-1; i++) {
				IntPoint start = p.get(i);
				IntPoint end = p.get(i+1);
				for(Edge e : edges) {
					if(e.startNode.equals(start) && e.endNode.equals(end)) {
						sum += e.distance;
					}
				}
			}
			result = Math.max(sum, result);
			
		}
		
		return result;
	}
	
	private void findAllRoutes(IntPoint start, IntPoint end, Set<IntPoint> visited, List<IntPoint> path, List<List<IntPoint>> allPaths) {
		if(start.equals(end)) {
			List<IntPoint> successfulPath = new ArrayList<IntPoint>();
			successfulPath.addAll(path);
			allPaths.add(successfulPath);
			return;
		}
		
		visited.add(start);
		
		for(Edge e : edges) {
			if(e.startNode.equals(start)) {
				IntPoint endNode = e.endNode;
				if(!visited.contains(endNode)) {
					path.add(endNode);
					findAllRoutes(endNode, end, visited, path, allPaths);
					
					path.remove(endNode);
				}
			}
		}
		visited.remove(start);
	}
	
	private void findEdges(boolean ice) {
		edges = new ArrayList<Edge>();
		Queue<Pair<IntPoint, IntPoint>> nodeQueue = new ArrayDeque<Pair<IntPoint, IntPoint>>();
		nodeQueue.add(new Pair<IntPoint, IntPoint>(startPoint, startPoint));
		Set<Pair<IntPoint, IntPoint>> visitedNodes = new HashSet<Pair<IntPoint, IntPoint>>();
		
		IntPoint currentPoint;
		IntPoint startNode = startPoint;
		int count = 0;
		
		while(!nodeQueue.isEmpty()) {
			Pair<IntPoint, IntPoint> queuePair = nodeQueue.remove();
			startNode = queuePair.getValue0();
			currentPoint = queuePair.getValue1();
			IntPoint lastNode = startNode;

			if(currentPoint.equals(endPoint)) {
				Edge e = new Edge(startNode, currentPoint, count);
				edges.add(e);
				count = 0;
				break;
			}
			while(!nodes.contains(currentPoint)) {
				
				count++;
				
				
				IntPoint previousPoint = currentPoint;
				currentPoint = nextPoint(currentPoint, lastNode);
				if(currentPoint == null) {
					return;
				}
				lastNode = previousPoint;
				
			}

			Edge e = new Edge(startNode, currentPoint, count);
			edges.add(e);
			count = 1;
			startNode = currentPoint;
				
			List<Pair<IntPoint, IntPoint>> nextPoints = new ArrayList<Pair<IntPoint, IntPoint>>();
			if(!ice) {
				for(Point<Integer> p : currentPoint.allNeighbours()) {
					if(isPointInGrid((IntPoint) p) && !p.equals(lastNode)) {
						if(trail[p.getY()][p.getX()] != '#') {
							nextPoints.add(new Pair<IntPoint, IntPoint>(startNode, (IntPoint) p));
						}
					}
				}
			}else {
				IntPoint p = (IntPoint) currentPoint.above();
				if(isPointInGrid((IntPoint) p) && !p.equals(lastNode)) {
					char c = trail[p.getY()][p.getX()];
					if(c == '.' || c == '^') {
						nextPoints.add(new Pair<IntPoint, IntPoint>(startNode, (IntPoint) p));
					}
				}
				p = (IntPoint) currentPoint.right();
				if(isPointInGrid((IntPoint) p) && !p.equals(lastNode)) {
					char c = trail[p.getY()][p.getX()];
					if(c == '.' || c == '>') {
						nextPoints.add(new Pair<IntPoint, IntPoint>(startNode, (IntPoint) p));
					}
				}
				p = (IntPoint) currentPoint.below();
				if(isPointInGrid((IntPoint) p) && !p.equals(lastNode)) {
					char c = trail[p.getY()][p.getX()];
					if(c == '.' || c == 'v') {
						nextPoints.add(new Pair<IntPoint, IntPoint>(startNode, (IntPoint) p));
					}
				}
				p = (IntPoint) currentPoint.left();
				if(isPointInGrid((IntPoint) p) && !p.equals(lastNode)) {
					char c = trail[p.getY()][p.getX()];
					if(c == '.' || c == '<') {
						nextPoints.add(new Pair<IntPoint, IntPoint>(startNode, (IntPoint) p));
					}
				}
			}
			visitedNodes.add(queuePair);
			nodeQueue.addAll(nextPoints);
			nodeQueue.removeAll(visitedNodes);

		}
	}
	
	private boolean isPointANode(IntPoint point) {
		int pathCount = 0;
		for(Point<Integer> p : point.allNeighbours()) {
			if(isPointInGrid((IntPoint) p)) {
				char c = trail[p.getY()][p.getX()];
				if(c != '#') {
					pathCount++;
				}
			}
		}
		return pathCount >2;
	}
	
	private boolean isPointInGrid(IntPoint point) {
		if(point.getX() >=0 && point.getX() < jLength && point.getY() >= 0 && point.getY() < iLength) {
			return true;
		}
		return false;
	}
	
	private IntPoint nextPoint(IntPoint currentPoint, IntPoint lastPoint) {
		for(Point<Integer> p : currentPoint.allNeighbours()) {
			if(isPointInGrid((IntPoint) p) && !p.equals(lastPoint)) {
				if(trail[p.getY()][p.getX()] != '#') {
					return (IntPoint) p;
				}
			}
		}
		return null;
	}
	
}

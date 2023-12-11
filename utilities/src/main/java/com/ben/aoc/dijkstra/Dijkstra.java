package com.ben.aoc.dijkstra;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Set;

public class Dijkstra {
	
	public static Graph calculateShortestPath(Graph graph, Node source) {
		source.setDistance(0);
		
		Set<Node> settledNodes = new HashSet<Node>();
		Set<Node> unsettledNodes = new HashSet<Node>();
		
		unsettledNodes.add(source);
		
		while (unsettledNodes.size() != 0) {
			Node currentNode = getLowestNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for(Entry<Node, Integer> adjacencyPair : currentNode.getAdjacentNodes().entrySet()) {
				Node adjacentNode = adjacencyPair.getKey();
				Integer edgeWeight = adjacencyPair.getValue();
				if(!settledNodes.contains(adjacentNode)) {
					CalculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		
		return graph;
	}
	
	private static Node getLowestNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		int lowestDistance = Integer.MAX_VALUE;
		for(Node node : unsettledNodes) {
			int nodeDistance = node.getDistance();
			if(nodeDistance<lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = node;
			}
		}
		return lowestDistanceNode;
	}
	
	private static void CalculateMinimumDistance(Node evaluationNode, Integer edgeWeight, Node sourceNode) {
		Integer sourceDistance = sourceNode.getDistance();
		if(sourceDistance + edgeWeight < evaluationNode.getDistance()) {
			evaluationNode.setDistance(sourceDistance + edgeWeight);
			LinkedList<Node> shortestPath = new LinkedList<Node>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			evaluationNode.setShortestPath(shortestPath);
		}
	}
}

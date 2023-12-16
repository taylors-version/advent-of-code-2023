package com.ben.aoc;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.javatuples.Pair;

public class Day16 {
	private char[][] mirrorPattern;
	
	public Day16(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		mirrorPattern = new char[lines.size()][];
		for(int i = 0; i<lines.size(); i++) {
			mirrorPattern[i] = lines.get(i).toCharArray();
		}
	}
		
	public long puzzle1() {		
		Node node = new Node(0, 0, mirrorPattern);
		return bfs(node, 'r').size();
	}
	
	public long puzzle2() {
		long result = 0;

		for (int i = 0; i<mirrorPattern.length; i++) {
			Node node = new Node(i, 0, mirrorPattern);
			result = Math.max(result, bfs(node, 'r').size());
			node = new Node(i, mirrorPattern[0].length-1, mirrorPattern);
			result = Math.max(result, bfs(node, 'l').size());
		}
		
		for (int j = 0; j<mirrorPattern[0].length; j++) {
			Node node = new Node(0, j, mirrorPattern);
			result = Math.max(result, bfs(node, 'd').size());
			node = new Node(mirrorPattern.length-1, j, mirrorPattern);
			result = Math.max(result, bfs(node, 'u').size());
		}
		
		return result;
	}
	
	public Set<Node> bfs(Node start, char direction){
		Set<Node> energised = new HashSet<Node>();
		Queue<Pair<Node, Character>> queue = new ArrayDeque<Pair<Node, Character>>();
		
		Set<Pair<Node, Character>> alreadyVisited = new HashSet<Pair<Node, Character>>();
		Pair<Node, Character> startPair= new Pair<Node, Character>(start, direction);
		queue.add(startPair);
		
		Pair<Node, Character> currentPair;
		
		while(!queue.isEmpty()) {
			currentPair = queue.remove();
			if(!alreadyVisited.contains(currentPair)) {
				alreadyVisited.add(currentPair);
				energised.add(currentPair.getValue0());
				queue.addAll(currentPair.getValue0().getNeighbours(currentPair.getValue1()));
				queue.removeAll(alreadyVisited);
			}
		}
		return energised;
	}
	
}

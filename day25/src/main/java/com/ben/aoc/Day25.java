package com.ben.aoc;

import java.util.List;

import org.jgrapht.Graph;
import org.jgrapht.alg.StoerWagnerMinimumCut;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.DefaultUndirectedGraph;

public class Day25 {
	
	Graph<String, DefaultEdge> graph = new DefaultUndirectedGraph<String, DefaultEdge>(DefaultEdge.class);
	
	public Day25(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			String[] lineSplit = line.split(": ");
			String name = lineSplit[0];
			
			if(!graph.containsVertex(name)) {
				graph.addVertex(name);
			}
			
			String[] links = lineSplit[1].split(" ");
			for(String node : links) {
				if(!graph.containsVertex(node)) {
					graph.addVertex(node);
				}
				graph.addEdge(name, node);
			}
		}
		
	}
		
	public long puzzle1() {		
		
		StoerWagnerMinimumCut<String, DefaultEdge> swMinCut = new StoerWagnerMinimumCut<String, DefaultEdge>(graph);
		
		long lhs = swMinCut.minCut().size();
		long rhs = graph.vertexSet().size() - lhs;
		
		return lhs * rhs;
	}


}

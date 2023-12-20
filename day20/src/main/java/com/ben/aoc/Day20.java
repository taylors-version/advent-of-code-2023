package com.ben.aoc;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.javatuples.Triplet;

import com.ben.aoc.Module.Type;

public class Day20 {
	private static final String START = "broadcaster";
	private static final String BUTTON = "button";
	
	List<String> lines;
	Map<String, Module> modules;
	
	public Day20(String fileName) {
		lines = Util.readFile(getClass(), fileName);
		
		modules = new HashMap<String, Module>();
		
		//Create each module
		for(String l : lines) {
			String[] lineSplitter = l.split(" -> ");
			if(lineSplitter[0].charAt(0)=='%') {
				String name = lineSplitter[0].substring(1);
				String[] destinations = lineSplitter[1].split(", ");
				Module module = new Module(Type.FLIPFLOP, name ,destinations);
				modules.put(name, module);
			}else if(lineSplitter[0].charAt(0)=='&') {
				String name = lineSplitter[0].substring(1);
				String[] destinations = lineSplitter[1].split(", ");
				Module module = new Module(Type.CONJUNCTION, name, destinations);
				modules.put(name, module);
			}else {
				String name = lineSplitter[0];
				if(!name.equals(START)) {
					System.out.println("Error on line: " + name);
				}
				String[] destinations = lineSplitter[1].split(", ");
				Module module = new Module(Type.BROADCASTER, name, destinations);
				modules.put(name, module);
			}
		}
		
		//Add initial inputs to conjunctions
		for(Module m: modules.values()) {
			for(String destination : m.destinations) {
				Module module = modules.get(destination);
				if(module != null && module.type == Type.CONJUNCTION) {
					module.addInput(m.name);
				}
			}
		}
	}
		
	public long puzzle1() {		
		long lowPulses = 0;
		long highPulses = 0;
		
		for(int i = 0; i<1000; i++) {
			lowPulses++; //button press
			Queue<Triplet<String, Integer, String>> queue = new ArrayDeque<Triplet<String,Integer,String>>(); //Destination, inputstate, inputname
			Triplet<String, Integer, String> broadcaster = new Triplet<String, Integer, String>(START, 0, BUTTON);
			
			queue.add(broadcaster);
			
			while(!queue.isEmpty()) {
				Triplet<String, Integer, String> trip = queue.remove();
				Module module = modules.get(trip.getValue0());
				if(module != null) {
					String[] destinations = module.destinations;
					Integer output = module.getPulse(trip.getValue1(), trip.getValue2());
					for(String destination : destinations) {
						if(output!=null && output.equals(1)) {
							highPulses++;
							Triplet<String, Integer, String> next = new Triplet<String, Integer, String>(destination, output, trip.getValue0());
							queue.add(next);
						}else if(output!=null && output.equals(0)) {
							lowPulses++;
							Triplet<String, Integer, String> next = new Triplet<String, Integer, String>(destination, output, trip.getValue0());
							queue.add(next);
						}
					}
				}
			}
			
		}
		
		return lowPulses * highPulses;
	}
	
	public long puzzle2() {
		long result = 0;
		
		return result;
	}	
}

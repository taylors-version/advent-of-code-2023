package com.ben.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.javatuples.Triplet;

import com.ben.aoc.Module.Type;
import com.ben.aoc.maths.Maths;

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
		boolean doRun = true;
		
		String rd = "rd";
		String bt = "bt";
		String fv = "fv";
		String pr = "pr";
		int rdLoop = 0;
		int btLoop = 0;
		int fvLoop = 0;
		int prLoop = 0;
		boolean rd1 = false;
		boolean bt1 = false;
		boolean fv1 = false;
		boolean pr1 = false;
		boolean rd0 = false;
		boolean bt0 = false;
		boolean fv0 = false;
		boolean pr0 = false;
		
		while(doRun) {
			
			if(rd1) {
				rdLoop++;
			}
			if(bt1) {
				btLoop++;
			}
			if(fv1) {
				fvLoop++;
			}
			if(pr1) {
				prLoop++;
			}
			
			Queue<Triplet<String, Integer, String>> queue = new ArrayDeque<Triplet<String,Integer,String>>(); //Destination, inputstate, inputname
			Triplet<String, Integer, String> broadcaster = new Triplet<String, Integer, String>(START, 0, BUTTON);
			
			queue.add(broadcaster);
			
			while(!queue.isEmpty()) {
				Triplet<String, Integer, String> trip = queue.remove();
				Module module = modules.get(trip.getValue0());
				if(module != null) {
					if(module.name.equals(rd) && trip.getValue1().equals(1)) {
						if(!rd1 && rdLoop ==0) {
							rd1 = true;
						}else if(rd1) {
							rd1 = false;
						}
					}
					if(module.name.equals(bt) && trip.getValue1().equals(1)) {
						if(!bt1 && btLoop ==0) {
							bt1 = true;
						}else if(bt1) {
							bt1 = false;
						}
					}
					if(module.name.equals(fv) && trip.getValue1().equals(1)) {
						if(!fv1 && fvLoop ==0) {
							fv1 = true;
						}else if(fv1) {
							fv1 = false;
						}
					}
					if(module.name.equals(pr) && trip.getValue1().equals(1)) {
						if(!pr1 && prLoop ==0) {
							pr1 = true;
						}else if(pr1) {
							pr1 = false;
						}
					}
					String[] destinations = module.destinations;
					Integer output = module.getPulse(trip.getValue1(), trip.getValue2());
					for(String destination : destinations) {
						if(output!=null) {
							Triplet<String, Integer, String> next = new Triplet<String, Integer, String>(destination, output, trip.getValue0());
							queue.add(next);
						}
					}
				}
			}
			
			if((rdLoop>0 && !rd1) && (btLoop>0 && !bt1) && (fvLoop>0 && !fv1) && (prLoop>0 && !pr1)) {
				doRun = false;
			}
		}
		List<Long> loops = new ArrayList<Long>();
		loops.add((long) rdLoop);
		loops.add((long) btLoop);
		loops.add((long) fvLoop);
		loops.add((long) prLoop);
		
		return Maths.lcm(loops);
	}	
}

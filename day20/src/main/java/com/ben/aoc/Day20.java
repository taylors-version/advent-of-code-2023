package com.ben.aoc;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.javatuples.Triplet;

import com.ben.aoc.Module.Type;
import com.ben.aoc.maths.Maths;

public class Day20 {
	private static final String RX = "rx";
	private static final String START = "broadcaster";
	private static final String BUTTON = "button";
	
	List<String> lines;
	Map<String, Module> modules;
	Module rxInput = null;
	
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
				if(destination.equals(RX)) {
					rxInput = m;
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
		String rxInputName = rxInput.name;
		List<Module> rxInProviders = new ArrayList<Module>();
		for(Module m : modules.values()) {
			for(String moduleName : m.destinations) {
				if(moduleName.equals(rxInputName)) {
					rxInProviders.add(m);
				}
			}
		}
		
		int[] rxInFirst1 = new int[rxInProviders.size()];
		int[] rxInSecond1 = new int[rxInProviders.size()];
		
		Arrays.fill(rxInFirst1, 0);
		Arrays.fill(rxInSecond1, 0);
		
		int count = 0;
		
		while(doRun) {
			count++;
			
			Queue<Triplet<String, Integer, String>> queue = new ArrayDeque<Triplet<String,Integer,String>>(); //Destination, inputstate, inputname
			Triplet<String, Integer, String> broadcaster = new Triplet<String, Integer, String>(START, 0, BUTTON);
			
			queue.add(broadcaster);
			
			while(!queue.isEmpty()) {
				Triplet<String, Integer, String> trip = queue.remove();
				Module module = modules.get(trip.getValue0());
				if(module != null) {
					for(int i=0; i<rxInProviders.size(); i++) {
						Module rxInProvider = rxInProviders.get(i);
						if(module.name.equals(rxInProvider.name)) {
							int value = module.getPulse(trip.getValue1(), trip.getValue2());
							if(value == 1 && rxInFirst1[i]==0) {
								rxInFirst1[i] = count;
							}else if(value ==1 && rxInSecond1[i]==0) {
								rxInSecond1[i] = count;
							}
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
			
			doRun = false;
			for(int i = 0; i<rxInSecond1.length; i++) {
				if(rxInSecond1[i] == 0) {
					doRun = true;
				}
			}
		}
		
		List<Long> loops = new ArrayList<Long>();
		for (int i = 0; i<rxInFirst1.length; i++) {
			loops.add((long)(rxInSecond1[i] - rxInFirst1[i]));
		}
		
		return Maths.lcm(loops);
	}	
	
	public boolean amCounting(int value, int counter, boolean zeroAfterCounting, boolean counting) {
		if(value == 1 && counter ==0 && !zeroAfterCounting) {
			return true;
		}
		if(value ==1 && counting && zeroAfterCounting) {
			return false;
		}		
		return counting;
	}
	
	public boolean amZeroAfterCounting(int value, int counter, boolean zeroAfterCounting) {
		if(zeroAfterCounting) {
			return true;
		}
		if(value ==0 && counter>0) {
			return true;
		}
		return false;
	}
}

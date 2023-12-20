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
		boolean rdCounting = false;
		boolean btCounting = false;
		boolean fvCounting = false;
		boolean prCounting = false;
		boolean rd0 = false;
		boolean bt0 = false;
		boolean fv0 = false;
		boolean pr0 = false;
		
		while(doRun) {
			
			if(rdCounting) {
				rdLoop++;
			}
			if(btCounting) {
				btLoop++;
			}
			if(fvCounting) {
				fvLoop++;
			}
			if(prCounting) {
				prLoop++;
			}
			
			Queue<Triplet<String, Integer, String>> queue = new ArrayDeque<Triplet<String,Integer,String>>(); //Destination, inputstate, inputname
			Triplet<String, Integer, String> broadcaster = new Triplet<String, Integer, String>(START, 0, BUTTON);
			
			queue.add(broadcaster);
			
			while(!queue.isEmpty()) {
				Triplet<String, Integer, String> trip = queue.remove();
				Module module = modules.get(trip.getValue0());
				if(module != null) {
					if(module.name.equals(rd)) {
						rdCounting = amCounting(trip.getValue1(), rdLoop, rd0, rdCounting);
						rd0 = amZeroAfterCounting(trip.getValue1(), rdLoop, rd0);
					}
					if(module.name.equals(bt)) {
						btCounting = amCounting(trip.getValue1(), btLoop, bt0, btCounting);
						bt0 = amZeroAfterCounting(trip.getValue1(), btLoop, bt0);
					}
					if(module.name.equals(fv)) {
						fvCounting = amCounting(trip.getValue1(), fvLoop, fv0, fvCounting);
						fv0 = amZeroAfterCounting(trip.getValue1(), fvLoop, fv0);
					}
					if(module.name.equals(pr)) {
						prCounting = amCounting(trip.getValue1(), prLoop, pr0, prCounting);
						pr0 = amZeroAfterCounting(trip.getValue1(), prLoop, pr0);
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
			
			if((rdLoop>0 && !rdCounting) && (btLoop>0 && !btCounting) && (fvLoop>0 && !fvCounting) && (prLoop>0 && !prCounting)) {
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

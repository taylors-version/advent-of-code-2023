package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

public class Box {
	private int number;
	private List<Lens> lenses;
	
	public Box(int number) {
		this.number = number;
		lenses = new ArrayList<Lens>();
	}
	
	public void addLens(Lens lens) {
		if(lenses.contains(lens)) {
			lenses.get(lenses.indexOf(lens)).setStrength(lens.getStrength());
		}else {
			lenses.add(lens);
		}
	}
	
	public void removeLens(Lens lens) {
		lenses.remove(lens);
		
	}
	
	public int getNumber() {
		return number;
	}
	
	public List<Lens> getLenses(){
		return lenses;
	}
}

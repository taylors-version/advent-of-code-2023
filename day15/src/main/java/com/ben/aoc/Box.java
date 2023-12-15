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
		if(lenses.isEmpty()) {
			lenses.add(lens);
			return;
		}
		int lensPosition = -1;
		for(int i = 0; i<lenses.size(); i++) {
			Lens l = lenses.get(i);
			if(l.getName().equals(lens.getName())) {
				lensPosition = i;
			}
		}
		if(lensPosition != -1) {
			lenses.remove(lensPosition);
			lenses.add(lensPosition, lens);
		}else {
			lenses.add(lens);
		}
	}
	
	public void removeLens(Lens lens) {
		if(lenses.isEmpty()) {
			return;
		}
		lenses.removeIf(l -> l.getName().equals(lens.getName()));
		
	}
	
	public int getNumber() {
		return number;
	}
	
	public List<Lens> getLenses(){
		return lenses;
	}
}

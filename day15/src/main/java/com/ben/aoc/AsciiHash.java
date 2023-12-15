package com.ben.aoc;

import java.util.List;

public class AsciiHash {
	private String[] sequence;
	
	public AsciiHash(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		
		sequence = lines.get(0).split(",");
	}
		
	public long getHashValue() {
		long result = 0;
		for(String s : sequence) {
			result += getIndividualHash(s.toCharArray());
		}
		return result;
	}
	
	public long getFocussingPowen() {
		long result = 0;
		Box[] boxes = new Box[256];
		for(int i = 0; i<boxes.length; i++) {
			Box b = new Box(i);
			boxes[i] = b;
		}
		
		for(String s : sequence) {
			if(s.contains("=")) {
				String[] lensString = s.split("=");
				Lens lens = new Lens(lensString[0], Integer.parseInt(lensString[1]));
				
				boxes[lens.getBoxValue()].addLens(lens);
			}else {
				Lens lens = new Lens(s.substring(0, s.length() - 1), 0);
				boxes[lens.getBoxValue()].removeLens(lens);
			}
		}
		
		for(int i=0; i<boxes.length; i++) {
			List<Lens> lenses = boxes[i].getLenses();
			for(int j = 0; j<lenses.size(); j++) {
				result += (i+1) * (j+1) * lenses.get(j).getStrength();
			}
			
		}
		
		return result;
	}
	
	private long getIndividualHash(char[] chars) {
		long result = 0;
		for (char c: chars) {
			int ascii = (int) c;
			result += ascii;
			result = result * 17;
			result = result % 256;
		}
		return result;
	}
}

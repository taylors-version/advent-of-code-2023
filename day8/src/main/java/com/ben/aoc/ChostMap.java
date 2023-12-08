package com.ben.aoc;

import java.util.List;

public class ChostMap {
	
	public long navigate(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		String rl = lines.get(0);
		int rlLength = rl.length();
		int lineNum = findNextInstructionLine(lines, "AAA");
		String nextLocation = "";

		while(true) {
			int rlIndex = (int) (result % rlLength);
			result++;
			if(rl.charAt(rlIndex) == 'R') {
				nextLocation = getRight(lines.get(lineNum));
			}else {
				nextLocation = getLeft(lines.get(lineNum));
			}
			//System.out.println("Step: " + result + "location: " + nextLocation);
			if(nextLocation.equals("ZZZ")) {
				return result;
			}
			lineNum = findNextInstructionLine(lines, nextLocation);
		}
		
	}
	
	private String getLeft(String instruction) {
		return instruction.substring(7, 10);
	}
	
	private String getRight(String instruction) {
		return instruction.substring(12, 15);
	}
	
	private int findNextInstructionLine(List<String> lines, String location) {
		for(int i= 2; i<lines.size(); i++) {
			if(lines.get(i).substring(0, 3).equals(location)) {
				return i;
			}
		}
		return 0;
	}
	
}

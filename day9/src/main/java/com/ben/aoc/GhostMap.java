package com.ben.aoc;

import java.util.ArrayList;
import java.util.List;

public class GhostMap {
	
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
			if(nextLocation.equals("ZZZ")) {
				return result;
			}
			lineNum = findNextInstructionLine(lines, nextLocation);
		}
		
	}
	

	public long navigateAsGhost(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		String rl = lines.get(0);
		int rlLength = rl.length();
		
		List<String> nextLocations = new ArrayList<String>();
		List<Integer> lineNums = new ArrayList<Integer>();
		List<Long> results = new ArrayList<Long>();
		
		for (int i=2; i<lines.size(); i++) {
			if (lines.get(i).charAt(2)=='A') {
				nextLocations.add(lines.get(i));
				lineNums.add(i);
			}
		}
		
		int numberOfInputs = nextLocations.size();

		for(int i=0; i<numberOfInputs; i++) {
			long count = 0;
			while(true) {
				int rlIndex = (int) (count % rlLength);
				count++;
				if(rl.charAt(rlIndex) == 'R') {
					nextLocations.set(i, getRight(lines.get(lineNums.get(i))));
				}else {
					nextLocations.set(i, getLeft(lines.get(lineNums.get(i))));
				}
				if(nextLocations.get(i).charAt(2) == 'Z') {
					results.add(count);
					break;
				}
				lineNums.set(i, findNextInstructionLine(lines, nextLocations.get(i)));
			}
		}
		
		
		return lcm(results);
		
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
	
	public static long lcm(List<Long> numbers) {
	    long result = lcm(numbers.get(0), numbers.get(1));
	    for (int i = 2; i < numbers.size(); i++) {
	      result = lcm(result, numbers.get(i));
	    }

	    return result;
	  }

	  public static long lcm(long a, long b) {
	    if (a == 0 && b == 0) {
	      return 0;
	    }

	    return Math.abs(a) / gcd(a, b) * Math.abs(b);
	  }
	  
	  public static long gcd(long a, long b) {
		    if (a == b) {
		      return a;
		    }

		    if (a == 0) {
		      return b;
		    }

		    if (b == 0) {
		      return a;
		    }

		    if (a % 2L == 0) {
		      if (b % 2L != 0) {
		        return gcd(a >> 1, b);
		      } else {
		        return gcd(a >> 1, b >> 1) << 1;
		      }
		    }

		    if (b % 2L == 0) {
		      return gcd(a, b >> 1);
		    }

		    if (a > b) {
		      return gcd((a - b) >> 1, b);
		    }

		    return gcd((b - a) >> 1, a);
		  }
		
}

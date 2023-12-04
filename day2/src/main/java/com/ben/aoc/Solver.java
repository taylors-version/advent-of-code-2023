package com.ben.aoc;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solver {
	
	private static final String REG_EX_RED = "\\d+ red";
	private static final String REG_EX_GREEN = "\\d+ green";
	private static final String REG_EX_BLUE = "\\d+ blue";
	
	public int solve(int[] ballMax, String fileName) {
		int result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			Matcher redMatcher = Pattern.compile(REG_EX_RED).matcher(line);
			Matcher greenMatcher = Pattern.compile(REG_EX_GREEN).matcher(line);
			Matcher blueMatcher = Pattern.compile(REG_EX_BLUE).matcher(line);
			
			boolean tooManyRed = false;
			boolean tooManyGreen = false;
			boolean tooManyBlue = false;
			while(redMatcher.find()) {
				String red = redMatcher.group();
				int count = Integer.parseInt(red.substring(0, red.indexOf(' ')));
				if (count > ballMax[0]) {
					tooManyRed = true;
				}
			}
			while(greenMatcher.find()) {
				String green = greenMatcher.group();
				int count = Integer.parseInt(green.substring(0, green.indexOf(' ')));
				if (count > ballMax[1]) {
					tooManyGreen = true;
				}
			}
			while(blueMatcher.find()) {
				String blue = blueMatcher.group();
				int count = Integer.parseInt(blue.substring(0, blue.indexOf(' ')));
				if (count > ballMax[2]) {
					tooManyBlue = true;
				}
			}
			
			
			if(!tooManyRed && !tooManyGreen && !tooManyBlue) {
				result +=(i+1);
			}
		}
		
		return result;
	}
	
	public int cubeSolve(String fileName) {
		int result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			Matcher redMatcher = Pattern.compile(REG_EX_RED).matcher(line);
			Matcher greenMatcher = Pattern.compile(REG_EX_GREEN).matcher(line);
			Matcher blueMatcher = Pattern.compile(REG_EX_BLUE).matcher(line);
			
			int minRed = 0;
			int minGreen = 0;
			int minBlue = 0;
			while(redMatcher.find()) {
				String red = redMatcher.group();
				int count = Integer.parseInt(red.substring(0, red.indexOf(' ')));
				if (count > minRed) {
					minRed = count;
				}
			}
			while(greenMatcher.find()) {
				String green = greenMatcher.group();
				int count = Integer.parseInt(green.substring(0, green.indexOf(' ')));
				if (count > minGreen) {
					minGreen = count;
				}
			}
			while(blueMatcher.find()) {
				String blue = blueMatcher.group();
				int count = Integer.parseInt(blue.substring(0, blue.indexOf(' ')));
				if (count > minBlue) {
					minBlue = count;
				}
			}
			
			result += minRed*minGreen*minBlue;
		}
		
		return result;
	}
}

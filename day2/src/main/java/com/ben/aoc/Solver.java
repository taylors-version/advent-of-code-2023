package com.ben.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Solver {
	
	private static final String REG_EX_RED = "\\d+ red";
	private static final String REG_EX_GREEN = "\\d+ green";
	private static final String REG_EX_BLUE = "\\d+ blue";
	
	public int solve(int[] ballMax, String fileName) {
		int result = 0;
		List<String> lines = readFile(fileName);
		
		
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
	
	
	public List<String> readFile(String fileName){
		List<String> lines = null ;
		try {
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());

			lines = Files.lines(path).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	
}

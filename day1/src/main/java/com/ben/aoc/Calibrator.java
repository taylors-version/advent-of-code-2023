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

public class Calibrator {

	public int calibrate(String fileName) {
		List<String> lines = readFile(fileName);
		
		int result = 0;
		
		for(String s : lines) {
			String sReversed = new StringBuilder(s).reverse().toString();
			Matcher firstMatcher = Pattern.compile("\\d").matcher(s);
			Matcher secondMatcher = Pattern.compile("\\d").matcher(sReversed);
			firstMatcher.find();
			secondMatcher.find();
			result += Integer.parseInt(firstMatcher.group() + secondMatcher.group());
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

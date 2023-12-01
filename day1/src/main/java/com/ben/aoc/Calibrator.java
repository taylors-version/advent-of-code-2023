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
	
	private static final String REG_EX_MATCHER = "one|two|three|four|five|six|seven|eight|nine";
	

	public int calibrate(String fileName) {
		List<String> lines = readFile(fileName);
		
		int result = 0;
		
		for(String s : lines) {
			String sReversed = new StringBuilder(s).reverse().toString();
			String reversedPattern = "(" + new StringBuilder(REG_EX_MATCHER).reverse().toString() + "|\\d)";
			Matcher firstMatcher = Pattern.compile("(" + REG_EX_MATCHER+"|\\d)").matcher(s);
			Matcher secondMatcher = Pattern.compile(reversedPattern).matcher(sReversed);
			firstMatcher.find();
			secondMatcher.find();
			result += Integer.parseInt(numberFromText(firstMatcher.group()) + numberFromText(new StringBuilder(secondMatcher.group()).reverse().toString()));
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
	
	public String numberFromText(String text) {
		switch (text) {
			case "one":
				return "1";
			case "two":
				return "2";
			case "three":
				return "3";
			case "four":
				return "4";
			case "five":
				return "5";
			case "six":
				return "6";
			case "seven":
				return "7";
			case "eight":
				return "8";
			case "nine":
				return "9";
			default:
				return text;
		}
	}
}

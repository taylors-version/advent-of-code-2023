package com.ben.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Schematic {
	
	private static final String REG_EX_NUMBERS = "\\d+";
	private static final String REG_EX_SYMBOLS = "\\S*[^(.|\\d)]\\S*";
	private static final String REG_EX_STAR = "\\*";

	
	public int getPartNumberCount(String fileName) {
		int result = 0;
		List<String> lines = readFile(fileName);
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			Matcher numberMatcher = Pattern.compile(REG_EX_NUMBERS).matcher(line);

			while(numberMatcher.find()) {
				boolean isPartNumber = false;
				int firstDigitLocation = numberMatcher.start();
				int lastDigitLocation = numberMatcher.end();
				int number = Integer.parseInt(numberMatcher.group());
				//check above including diagonal for a symbol
				if(i>0) {
					if(lines.get(i-1).substring(Math.max(firstDigitLocation-1, 0), Math.min(lastDigitLocation+1, line.length()-1)).matches(REG_EX_SYMBOLS)) {
						isPartNumber = true;
					}
				}
				//Check below including diagonals for a symbol
				if(i<lines.size() - 1) {
					if(lines.get(i+1).substring(Math.max(firstDigitLocation-1, 0), Math.min(lastDigitLocation+1, line.length()-1)).matches(REG_EX_SYMBOLS)) {
						isPartNumber = true;
					}	
				}
				//Check directly left and right for a symbol
				if(firstDigitLocation >0) {
					if(line.substring(firstDigitLocation-1, firstDigitLocation).matches(REG_EX_SYMBOLS)) {
							isPartNumber = true;
					}
				}
				if(lastDigitLocation <line.length()) {
					if(line.substring(lastDigitLocation, lastDigitLocation + 1).matches(REG_EX_SYMBOLS)) {
							isPartNumber = true;
					}
				}
				if(isPartNumber) {
					result += number;
				}
			}
		}
		
		return result;
	}
	

	public int getGears(String fileName) {
		int result = 0;
		List<String> lines = readFile(fileName);
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			Matcher starMatcher = Pattern.compile(REG_EX_STAR).matcher(line);

			while(starMatcher.find()) {
				int gearLocation = starMatcher.start();
				List<String> gearRatios = new ArrayList<String>();
				//check above lines for numbers, for each one check if it's adjacent to the star
				if(i>0) {
					Matcher numberMatcher = Pattern.compile(REG_EX_NUMBERS).matcher(lines.get(i-1));
					while(numberMatcher.find()) {
						int numberStart = numberMatcher.start();
						int numberEnd = numberMatcher.end();
						if(numberEnd>=gearLocation && numberStart<=gearLocation+1) {
							gearRatios.add(numberMatcher.group());
						}
					}
				}				
				//check above lines for numbers, for each one check if it's adjacent to the star
				if(i<lines.size() - 1) {
					Matcher numberMatcher = Pattern.compile(REG_EX_NUMBERS).matcher(lines.get(i+1));
					while(numberMatcher.find()) {
						int numberStart = numberMatcher.start();
						int numberEnd = numberMatcher.end();
						if(numberEnd>=gearLocation && numberStart<=gearLocation+1) {
							gearRatios.add(numberMatcher.group());
						}
					}
				}
				//Check directly left and right for a symbol
				if(gearLocation > 0) {
					Matcher numberMatcher = Pattern.compile(REG_EX_NUMBERS).matcher(line);
					numberMatcher.region(0, gearLocation);
					while(numberMatcher.find()) {
						if(numberMatcher.hitEnd() && numberMatcher.end()==gearLocation){
							gearRatios.add(numberMatcher.group());
						}
					}	
				}
				if(gearLocation < line.length()) {
					Matcher numberMatcher = Pattern.compile(REG_EX_NUMBERS).matcher(line);
					numberMatcher.region(gearLocation, line.length());
					if(numberMatcher.find()) {
						if(numberMatcher.start()==gearLocation+1) {
							gearRatios.add(numberMatcher.group());
						}
					}					
				}
				//Only add the gear to the result if there's exactly two ratios adjacent
				if(gearRatios.size()==2) {
					result+= Integer.parseInt(gearRatios.get(0)) * Integer.parseInt(gearRatios.get(1));
				}
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

package com.ben.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solver {
	
	public int solve(String fileName) {
		int result = 0;
		List<String> lines = readFile(fileName);
		
		for(String line:lines) {
			List<String> winningNumbers = Arrays.asList(line.substring(line.indexOf(':') + 1, line.indexOf('|')).trim().replaceAll(" +", " ").split(" "));
			List<String> cardNumbers = Arrays.asList(line.substring(line.indexOf('|') + 1, line.length()).trim().replaceAll(" +", " ").split(" "));
			
			int matches=0;
			for(String number: winningNumbers) {
				if (cardNumbers.contains(number)){
					matches++;
				}
			}
			if(matches >0) {
				result += Math.pow(2, matches-1);
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

package com.ben.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
	
	public int solveByRules(String fileName) {
		int result = 0;
		List<String> lines = readFile(fileName);
		
		Map<Integer, Integer> copiesOfCards = new HashMap<Integer, Integer>();
		copiesOfCards.put(1, 1);
		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			List<String> winningNumbers = Arrays.asList(line.substring(line.indexOf(':') + 1, line.indexOf('|')).trim().replaceAll(" +", " ").split(" "));
			List<String> cardNumbers = Arrays.asList(line.substring(line.indexOf('|') + 1, line.length()).trim().replaceAll(" +", " ").split(" "));
			
			int matches=0;
			for(String number: winningNumbers) {
				if (cardNumbers.contains(number)){
					matches++;
				}
			}
			for(int j = 1; j<=matches; j++) {
				int copiesOfNextCard = copiesOfCards.getOrDefault(i+j, 1) + copiesOfCards.getOrDefault(i, 1);
				copiesOfCards.put(i+j, copiesOfNextCard);
			}
			result+=copiesOfCards.getOrDefault(i, 1);
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

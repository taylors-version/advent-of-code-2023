package com.ben.aoc;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solver {
	
	public int solve(String fileName) {
		int result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
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
		List<String> lines = Util.readFile(getClass(), fileName);
		
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
}

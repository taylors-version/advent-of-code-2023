package com.ben.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.LongStream;

public class Record {

	
	public long getPossibleArrangements(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(String line : lines) {
			String[] arrangement = line.split(" ");
			String[] numbersString = arrangement[1].split(",");
			int[] values = new int[numbersString.length];
			for(int i = 0; i< numbersString.length; i++) {
				values[i] = Integer.parseInt(numbersString[i]);
			}
			
			List<String> combinations = getCombinations(arrangement[0]);
			
			for(String combination:combinations) {
				boolean isValid = isCombinationValid(combination, values);
				
				if(isValid) {
					//System.out.println(combination + " - " + Arrays.toString(values) + ": " + isValid);
					result++;
				}
				
			}
			/*String combination = ".#.###.#.######";
			boolean isValid = isCombinationValid(combination, values);*/

			
		}
		return result;
	}
	
	public long getPossibleArrangements2(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(String line : lines) {
			String[] arrangement = line.split(" ");
			String[] numbersString = arrangement[1].split(",");
			int[] values = new int[numbersString.length * 5];
			for(int i = 0; i< numbersString.length; i++) {
				for(int j=0; j<5; j++) {
					values[i+numbersString.length*j] = Integer.parseInt(numbersString[i]);
				}
			}
			
			StringBuilder sb = new StringBuilder();
			for(int i=0; i<4; i++) {
				sb.append(arrangement[0]).append("?");
			}
			sb.append(arrangement[0]);
			arrangement[0] = sb.toString();
			System.out.println(arrangement[0] + " " + Arrays.toString(values));
			/*
			List<String> combinations = getCombinations(arrangement[0]);
			
			for(String combination:combinations) {
				boolean isValid = isCombinationValid(combination, values);
				
				if(isValid) {
					//System.out.println(combination + " - " + Arrays.toString(values) + ": " + isValid);
					result++;
				}
				
			}
			*/
			/*String combination = ".#.###.#.######";
			boolean isValid = isCombinationValid(combination, values);*/

			
		}
		return result;
	}
	
	public List<String> getCombinations(String line){
		List<String> combinations = new ArrayList<String>();
		List<Integer> questionIndexes = new ArrayList<Integer>();
		
        int index = 0;
        while(index != -1){
            index = line.indexOf("?", index);
            if (index != -1) {
                questionIndexes.add(index);
                index++;
            }
        }

        int numberOfCombinations = (int) Math.pow(2, questionIndexes.size());
        
        for (int i=0; i<numberOfCombinations; i++) {
        	String binary = String.format("%" + questionIndexes.size() + "s", Integer.toBinaryString(i)).replace(' ', '0');
        	
        	StringBuilder sb = new StringBuilder();
        	int binaryIndex = 0;
        	for(int j = 0; j<line.length(); j++) {
        		if (line.charAt(j) == '?') {
        			sb.append(binary.charAt(binaryIndex) == '1' ? '.' : '#');
        			binaryIndex++;
        		}else {
        			sb.append(line.charAt(j));
        		}
        	}
        	combinations.add(sb.toString());
        }
        
		return combinations;
	}
	
	private boolean isCombinationValid(String combination, int[] values) {
		//##..###  1,1,3 false
		//#.#.### 1,1,3 true
		
		for(int i = 0; i<values.length; i++) {
			int firstHash = combination.indexOf("#");
		
			try {
				String substr = combination.substring(firstHash, firstHash + values[i]);
				if(!"#".repeat(values[i]).equals(substr)) {
					return false;
				}
			}catch(IndexOutOfBoundsException e) {
				return false;
			}
			if(firstHash + values[i] < combination.length()) {
				if(combination.charAt(firstHash + values[i]) == '#') {
					return false;
				}
			}else{
				return i == (values.length -1);
			}
			
			combination = combination.substring(firstHash+values[i]);

		}
		
		return combination.indexOf("#") == -1;
	}

		
}

package com.ben.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BoatRace {
	
	public int getWaysToWin(String fileName) {
		int result = 1;
		List<String> lines = Util.readFile(getClass(), fileName);
		String timeLine = lines.get(0);
		String distanceLine = lines.get(1);
		
		List<String> raceTimesStrings = Arrays.asList(timeLine.substring(timeLine.indexOf(':') + 1).trim().replaceAll(" +", " ").split(" "));
		List<String> raceDistanceStrings = Arrays.asList(distanceLine.substring(distanceLine.indexOf(':') + 1).trim().replaceAll(" +", " ").split(" "));
		List<Integer> raceTimes = raceTimesStrings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		List<Integer> raceDistances = raceDistanceStrings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		
		for(int i = 0; i<raceTimes.size(); i++) {
			int waysToWin = getWaysToWin(raceTimes.get(i), raceDistances.get(i));
			result = result * waysToWin;
		}
		
		return result;
	}
	
	public int getWaysToWin2(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		String timeLine = lines.get(0);
		String distanceLine = lines.get(1);
		
		String raceTimesString = timeLine.substring(timeLine.indexOf(':') + 1).trim().replaceAll(" +", "");
		String raceDistanceString = distanceLine.substring(distanceLine.indexOf(':') + 1).trim().replaceAll(" +", "");
		long raceTime = Long.parseLong(raceTimesString);
		long raceDistance = Long.parseLong(raceDistanceString);
		
		int waysToWin = getWaysToWin(raceTime, raceDistance);
		
		return waysToWin;
	}
	
	private int getWaysToWin(long raceTime, long raceDistance) {
		int waysToWin = 0;
		for (long i = 1; i< raceTime; i++) {
			if (i*raceTime-(i*i) >= raceDistance) {
				waysToWin++;
			}
		}
		return waysToWin;
	}
	
}

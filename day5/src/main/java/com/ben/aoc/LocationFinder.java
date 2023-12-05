package com.ben.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LocationFinder {
	private static String SEED_TO_SOIL = "seed-to-soil map:";
	private static String SOIL_TO_FERT = "soil-to-fertilizer map:";
	private static String FERT_TO_WATER = "fertilizer-to-water map:";
	private static String WATER_TO_LIGHT = "water-to-light map:";
	private static String LIGHT_TO_TEMP = "light-to-temperature map:";
	private static String TEMP_TO_HUMID = "temperature-to-humidity map:";
	private static String HUMID_TO_LOC = "humidity-to-location map:";
	
	public int findSmallestLocation(String fileName) {
		int result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		String seedList = lines.get(0);
		List<String> seedsStrings = Arrays.asList(seedList.substring(seedList.indexOf(':') + 1).trim().replaceAll(" +", " ").split(" "));
		List<Integer> seeds = seedsStrings.stream().map(s -> Integer.parseInt(s)).collect(Collectors.toList());
		
		int seedToSoilMapStart = lines.indexOf(SEED_TO_SOIL);
		int soilToFertMapStart = lines.indexOf(SOIL_TO_FERT);
		int fertToWaterMapStart = lines.indexOf(FERT_TO_WATER);
		int waterToLightMapStart = lines.indexOf(WATER_TO_LIGHT);
		int lightToTempMapStart = lines.indexOf(LIGHT_TO_TEMP);
		int tempToHumidMapStart = lines.indexOf(TEMP_TO_HUMID);
		int humidToLocMapStart = lines.indexOf(HUMID_TO_LOC);
		
		List<Mapper> seedMappers = getMappersForStringsInList(lines, seedToSoilMapStart+1, soilToFertMapStart-2);
		List<Mapper> soilMappers = getMappersForStringsInList(lines, soilToFertMapStart+1, fertToWaterMapStart-2);
		List<Mapper> fertMappers = getMappersForStringsInList(lines, fertToWaterMapStart+1, waterToLightMapStart-2);
		List<Mapper> waterMappers = getMappersForStringsInList(lines, waterToLightMapStart+1, lightToTempMapStart-2);
		List<Mapper> lightMappers = getMappersForStringsInList(lines, lightToTempMapStart+1, tempToHumidMapStart-2);
		List<Mapper> tempMappers = getMappersForStringsInList(lines, tempToHumidMapStart+1, humidToLocMapStart-2);
		List<Mapper> humidMappers = getMappersForStringsInList(lines, humidToLocMapStart+1, lines.size()-1);
		
		List<Integer> locations = new ArrayList<Integer>();

		for(Integer seed : seeds) {
			int soil = 0;
			int fert = 0;
			int water = 0;
			int light = 0;
			int temp = 0;
			int humid = 0;
			int loc = 0;
			
			System.out.println("seed: " + seed);
			
			for(Mapper seedMapper : seedMappers) {
				int newSoil = seedMapper.getDestination(seed);
				if(newSoil >= 0) {
					soil = newSoil;
				}
				System.out.println("setting seed: " + seed + " to soil: " + soil);
			}
			for(Mapper soilMapper : soilMappers) {
				int newFert = soilMapper.getDestination(soil);
				fert = newFert >= 0 ? newFert : fert; 
				System.out.println("setting soil: " + soil + " to fert: " + fert);
			}
			for(Mapper fertMapper : fertMappers) {
				int newWater = fertMapper.getDestination(fert);
				water = newWater >= 0 ? newWater : water; 
				System.out.println("setting fert: " + fert + " to water: " + water);
			}
			for(Mapper waterMapper : waterMappers) {
				int newLight =  waterMapper.getDestination(water);
				light = newLight >= 0 ? newLight : light; 
				System.out.println("setting water: " + water + " to light: " + light);
			}
			for(Mapper lightMapper : lightMappers) {
				int newTemp = lightMapper.getDestination(light);
				temp = newTemp >= 0 ? newTemp : temp; 
				System.out.println("setting light: " + light + " to temp: " + temp);
			}
			for(Mapper tempMapper : tempMappers) {
				int newHumid = tempMapper.getDestination(temp);
				humid = newHumid >= 0 ? newHumid : humid; 
				System.out.println("setting temp: " + temp + " to humid: " + humid);
			}
			for(Mapper humidMapper : humidMappers) {
				int newLoc = humidMapper.getDestination(humid);
				loc = newLoc >= 0 ? newLoc : loc; 
				System.out.println("setting humi: " + humid + " to loc: " + loc);
			}
			locations.add(loc);
		}
		
		Collections.sort(locations);
		for(int loc: locations) {
			System.out.println("location: " + loc);
		}
		return locations.get(0);
	}
	
	
	private List<Mapper> getMappersForStringsInList(List<String> strings, int startRange, int endRange){
		List<Mapper> mappers = new ArrayList<Mapper>();
		
		for(int i = startRange; i<=endRange; i++) {		
			System.out.println("Making Mapper for: " + strings.get(i));
			String[] numbers = strings.get(i).split(" ");
			mappers.add(new Mapper(numbers[0], numbers[1], numbers[2]));
		}
		
		return mappers;
	}
	
}

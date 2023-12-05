package com.ben.aoc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LocationFinder {
	private static String SEED_TO_SOIL = "seed-to-soil map:";
	private static String SOIL_TO_FERT = "soil-to-fertilizer map:";
	private static String FERT_TO_WATER = "fertilizer-to-water map:";
	private static String WATER_TO_LIGHT = "water-to-light map:";
	private static String LIGHT_TO_TEMP = "light-to-temperature map:";
	private static String TEMP_TO_HUMID = "temperature-to-humidity map:";
	private static String HUMID_TO_LOC = "humidity-to-location map:";
	
	public long findSmallestLocation(String fileName) {
		List<String> lines = Util.readFile(getClass(), fileName);
		String seedList = lines.get(0);
		List<String> seedsStrings = Arrays.asList(seedList.substring(seedList.indexOf(':') + 1).trim().replaceAll(" +", " ").split(" "));
		List<Long> seeds = seedsStrings.stream().map(s -> Long.parseLong(s)).collect(Collectors.toList());
		
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
		
		List<Long> locations = new ArrayList<Long>();

		for(Long seed : seeds) {
			long soil = 0;
			long fert = 0;
			long water = 0;
			long light = 0;
			long temp = 0;
			long humid = 0;
			long loc = 0;
						
			soil = seed;
			for(Mapper seedMapper : seedMappers) {
							
				if(seedMapper.getDestination(seed) >= 0) {
					soil = seedMapper.getDestination(seed);
				}
			}
			fert = soil;
			for(Mapper soilMapper : soilMappers) {
				
				if(soilMapper.getDestination(soil) >= 0) {
					fert = soilMapper.getDestination(soil);
				}
			}
			water = fert;
			for(Mapper fertMapper : fertMappers) {
				
				if(fertMapper.getDestination(fert) >= 0) {
					water = fertMapper.getDestination(fert);
				}
			}
			light = water;
			for(Mapper waterMapper : waterMappers) {
				
				if(waterMapper.getDestination(water) >= 0) {
					light = waterMapper.getDestination(water);
				}
			}
			temp = light;
			for(Mapper lightMapper : lightMappers) {
				
				if(lightMapper.getDestination(light) >= 0) {
					temp = lightMapper.getDestination(light);
				}
			}
			humid = temp;
			for(Mapper tempMapper : tempMappers) {
				
				if(tempMapper.getDestination(temp) >= 0) {
					humid = tempMapper.getDestination(temp);
				}
			}
			loc = humid;
			for(Mapper humidMapper : humidMappers) {
				
				if(humidMapper.getDestination(humid) >= 0) {
					loc = humidMapper.getDestination(humid);
				}
			}
			locations.add(loc);
		}
		
		Collections.sort(locations);

		return locations.get(0);
	}
	
	
	private List<Mapper> getMappersForStringsInList(List<String> strings, int startRange, int endRange){
		List<Mapper> mappers = new ArrayList<Mapper>();
		
		for(int i = startRange; i<=endRange; i++) {		
			String[] numbers = strings.get(i).split(" ");
			mappers.add(new Mapper(numbers[0], numbers[1], numbers[2]));
		}
		
		return mappers;
	}
	
}

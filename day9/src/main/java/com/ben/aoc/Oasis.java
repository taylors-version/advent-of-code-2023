package com.ben.aoc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.LongStream;

public class Oasis {
		
	public long findNextValues(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(String line : lines) {
			String[] stringValues = line.split(" ");
			long[] values = new long[stringValues.length];
			for(int i = 0; i< stringValues.length; i++) {
				values[i] = Long.parseLong(stringValues[i]);
			}
			long[] value = getNextValue(values);
			result+= value[value.length - 1];
		}
		return result;
	}
	
	public long findPreviousValues(String fileName) {
		long result = 0;
		List<String> lines = Util.readFile(getClass(), fileName);
		
		for(String line : lines) {
			String[] stringValues = line.split(" ");
			long[] values = new long[stringValues.length];
			for(int i = 0; i< stringValues.length; i++) {
				values[i] = Long.parseLong(stringValues[i]);
			}
			long[] value = getPreviousValue(values);
			result+= value[value.length - 1];
		}
		return result;
	}
	
	private long[] getNextValue(long[] values) {
		if(Arrays.stream(values).allMatch(v -> v==0)) {
			return LongStream.concat(Arrays.stream(values), LongStream.of(0L)).toArray();
		}else {
			long[] subArray = getNextValue(LongStream.range(0, values.length - 1)
			        .map(i -> values[(int) (i + 1)] - values[(int) i]).toArray());
			long[] newValues =Arrays.copyOf(values, values.length + 1);
			newValues[newValues.length - 1] = newValues[newValues.length - 2] + subArray[subArray.length - 1];
			return newValues;
		}
	}
	
	private long[] getPreviousValue(long[] values) {
		if(Arrays.stream(values).allMatch(v -> v==0)) {
			return LongStream.concat(Arrays.stream(values), LongStream.of(0L)).toArray();
		}else {
			long[] subArray = getPreviousValue(LongStream.range(0, values.length - 1)
			        .map(i -> values[(int) (i + 1)] - values[(int) i]).toArray());
			long[] newValues =Arrays.copyOf(values, values.length + 1);
			//Append the calculated value to the end rather than the start as this is easier with arrays
			newValues[newValues.length - 1] = newValues[0] - subArray[subArray.length - 1];
			return newValues;
		}
	}
		
}

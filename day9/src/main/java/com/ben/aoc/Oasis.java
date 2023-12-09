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
			System.out.println(value[value.length - 1]);
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
		
}

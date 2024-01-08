package com.ben.aoc.maths;

import java.util.Arrays;
import java.util.List;

public class Maths {

	public static long lcm(List<Long> numbers) {
		long result = lcm(numbers.get(0), numbers.get(1));
		for (int i = 2; i < numbers.size(); i++) {
			result = lcm(result, numbers.get(i));
		}

		return result;
	}

	public static long lcm(long a, long b) {
		if (a == 0 && b == 0) {
			return 0;
		}

		return Math.abs(a) / gcd(a, b) * Math.abs(b);
	}

	public static long gcd(long a, long b) {
		if (a == b) {
			return a;
		}

		if (a == 0) {
			return b;
		}

		if (b == 0) {
			return a;
		}

		if (a % 2L == 0) {
			if (b % 2L != 0) {
				return gcd(a >> 1, b);
			} else {
				return gcd(a >> 1, b >> 1) << 1;
			}
		}

		if (b % 2L == 0) {
			return gcd(a, b >> 1);
		}

		if (a > b) {
			return gcd((a - b) >> 1, b);
		}

		return gcd((b - a) >> 1, a);
	}
	
	public static <T> List<T[]> getAllPermutations(int n, T[] elements, List<T[]> permutations){
		
		
        if(n == 1) {
        	T[] copy = Arrays.copyOf(elements, elements.length);
            permutations.add(copy);
        } else {
            for(int i = 0; i < n-1; i++) {
            	getAllPermutations(n - 1, elements, permutations);
                if(n % 2 == 0) {
                    swap(elements, i, n-1);
                } else {
                    swap(elements, 0, n-1);
                }
            }
            getAllPermutations(n - 1, elements, permutations);
        }

		
		return permutations;
	}
	
    private static <T> void swap(T[] elements, int a, int b) {

        T tmp = elements[a];
        elements[a] = elements[b];
        elements[b] = tmp;
    }

}

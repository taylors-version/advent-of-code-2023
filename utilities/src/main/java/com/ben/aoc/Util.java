package com.ben.aoc;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Util {
	
	public static List<String> readFile(Class<?> klass, String fileName){
		List<String> lines = null ;
		try {
			Path path = Paths.get(klass.getClassLoader().getResource(fileName).toURI());

			lines = Files.lines(path).collect(Collectors.toList());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public static char[][] transposeMatrix(char[][] matrix){
		char[][] result = new char[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
	
	public static int[][] transposeMatrix(int[][] matrix){
		int[][] result = new int[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
	
	public static long[][] transposeMatrix(long[][] matrix){
		long[][] result = new long[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
	
	public static Object[][] transposeMatrix(Object[][] matrix){
		Object[][] result = new Object[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][i] = matrix[i][j];
			}
		}
		return result;
	}
	
	public static char[][] rotateMatrix(char[][] matrix){
		char[][] result = new char[matrix[0].length][matrix.length];
		for(int i = 0; i<matrix.length; i++) {
			for(int j = 0; j<matrix[0].length; j++) {
				result[j][(matrix.length-1) -i] = matrix[i][j];
			}
		}
		return result;
	}
}

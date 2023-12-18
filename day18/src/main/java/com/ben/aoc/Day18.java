package com.ben.aoc;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import org.javatuples.Pair;

public class Day18 {
	List<String> lines;
	boolean[][] dug;
	Set<Pair<Long, Long>> dugSet = new HashSet<Pair<Long,Long>>();

	
	public Day18(String fileName) {
		lines = Util.readFile(getClass(), fileName);
		
	}
		
	public long puzzle1() {		
		long result = 0;
		int rightBorder = 0;
		int leftBorder = Integer.MAX_VALUE;
		int upBorder = Integer.MAX_VALUE;
		int downBorder = 0;
		
		int rightSize = 0;
		int leftSize = 0;
		int downSize = 0;
		int upSize = 0;
		
		List<Instruction> instructions = new ArrayList<Instruction>();

		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			String[] lineSplit = line.split(" ");
			char direction = lineSplit[0].charAt(0);
			int distance = Integer.parseInt(lineSplit[1]);
			String colour = lineSplit[2];
			instructions.add(new Instruction(direction, distance, colour));
			
			switch (direction) {
			case 'R':
				rightSize += distance;
				break;
			case 'L':
				leftSize += distance;
				break;
			case 'D':
				downSize += distance;
				break;
			case 'U':
				upSize += distance;
				break;
			}
		}
		
		dug = new boolean[downSize + upSize][rightSize + leftSize];
		
		int i = downSize;
		int j = rightSize;
		dug[i][j] = true;
		
		
		for(Instruction instruction : instructions) {
			switch (instruction.direction) {
			case 'R':
				for(int x = 0; x<instruction.distance; x++) {
					i++;
					dug[i][j] = true;
				}
				break;
			case 'L':
				for(int x = 0; x<instruction.distance; x++) {
					i--;
					dug[i][j] = true;
				}
				break;
			case 'D':
				for(int x = 0; x<instruction.distance; x++) {
					j++;
					dug[i][j] = true;
				}
				break;
			case 'U':
				for(int x = 0; x<instruction.distance; x++) {
					j--;
					dug[i][j] = true;
				}
				break;
			}
			rightBorder = Math.max(rightBorder, i);
			leftBorder = Math.min(leftBorder, i);
			upBorder = Math.min(upBorder, j);
			downBorder = Math.max(downBorder, j);
		}
		
		floodFill((rightBorder + leftBorder)/2, (downBorder + upBorder)/2, true);
		
		for(int i1 = 0; i1<dug.length; i1++) {
			for(int j1 = 0; j1<dug[0].length; j1++) {
				if(dug[i1][j1]) {
					result++;
				}
			}
		}
		
		return result;
	}
	
	public long puzzle2() {
		long result = 0;
		
		long rightBorder = 0;
		long leftBorder = Integer.MAX_VALUE;
		long upBorder = Integer.MAX_VALUE;
		long downBorder = 0;
		
		List<Instruction> instructions = new ArrayList<Instruction>();

		
		for(int i = 0; i<lines.size(); i++) {
			String line = lines.get(i);
			String[] lineSplit = line.split(" ");
			
			String colour = lineSplit[2];
			char direction = numberToDir(colour.charAt(7));
			String hexDistance = colour.substring(2, 7);
			int distance = Integer.parseInt(hexDistance, 16);
			
			instructions.add(new Instruction(direction, distance, colour));
		}
			
		long i = 0;
		long j = 0;
		Pair<Long, Long> start = new Pair<Long, Long>(0L,0L);
		dugSet.add(start);
		
		
		for(Instruction instruction : instructions) {
			switch (instruction.direction) {
			case 'R':
				for(int x = 0; x<instruction.distance; x++) {
					i++;
					Pair<Long, Long> pair = new Pair<Long, Long>(i,j);
					dugSet.add(pair);
				}
				break;
			case 'L':
				for(int x = 0; x<instruction.distance; x++) {
					i--;
					Pair<Long, Long> pair = new Pair<Long, Long>(i,j);
					dugSet.add(pair);
				}
				break;
			case 'D':
				for(int x = 0; x<instruction.distance; x++) {
					j++;
					Pair<Long, Long> pair = new Pair<Long, Long>(i,j);
					dugSet.add(pair);
				}
				break;
			case 'U':
				for(int x = 0; x<instruction.distance; x++) {
					j--;
					Pair<Long, Long> pair = new Pair<Long, Long>(i,j);
					dugSet.add(pair);
				}
				break;
			}
			rightBorder = Math.max(rightBorder, i);
			leftBorder = Math.min(leftBorder, i);
			upBorder = Math.min(upBorder, j);
			downBorder = Math.max(downBorder, j);
		}
		
		floodFillSet((rightBorder + leftBorder)/2, (downBorder + upBorder)/2);
		
		
		
		return dugSet.size();
	}
	
	private void floodFill(int i, int j, boolean value) {
   
		int iMax = dug.length;
		int jMax = dug[0].length;
		
         Set<Pair<Integer, Integer>> visited = new HashSet<Pair<Integer,Integer>>();
         
         
        // Creating queue for bfs
         Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
   
        // Pushing pair of {x, y}
         Pair<Integer, Integer> source = new Pair<Integer, Integer>(i,j);
         queue.add(source);
          
        visited.add(source);
   
        // Until queue is empty
        while (!queue.isEmpty()) 
        {
            // Extracting front pair
            Pair<Integer, Integer> coord = queue.peek();
            int i1 = coord.getValue0();
            int j1 = coord.getValue1();
            boolean preValue = dug[i1][j1];
             
            dug[i1][j1] = value;
     
            // Popping front pair of queue
            queue.remove();
 
            // For Upside Point
            Pair<Integer, Integer> upPair = new Pair<Integer, Integer>(i1-1, j1);
            if (validCoord(i1 - 1, j1, iMax, jMax) && !visited.contains(upPair) && dug[i1 - 1][j1] == preValue) {
                queue.add(upPair);
                visited.add(upPair);
            }
            
            // For Downside Point
            Pair<Integer, Integer> downPair = new Pair<Integer, Integer>(i1+1, j1);
            if (validCoord(i1 + 1, j1, iMax, jMax) && !visited.contains(downPair) && dug[i1 + 1][j1] == preValue) {
                queue.add(downPair);
                visited.add(downPair);
            }
            
            // For Rightside Point
            Pair<Integer, Integer> rightPair = new Pair<Integer, Integer>(i1, j1+1);
            if (validCoord(i1, j1 + 1, iMax, jMax) && !visited.contains(rightPair) && dug[i1][j1 + 1] == preValue) {
                queue.add(rightPair);
                visited.add(rightPair);
            }
     
            // For Leftside Point
            Pair<Integer, Integer> leftPair = new Pair<Integer, Integer>(i1, j1-1);
            if (validCoord(i1, j1 - 1, iMax, jMax) && !visited.contains(leftPair) && dug[i1][j1 - 1] == preValue) {
                queue.add(leftPair);
                visited.add(leftPair);
            }
        }
    }
	
	private boolean validCoord(int i, int j, int iMax, int jMax) {
        if (i < 0 || j < 0 || i >= iMax || j >= jMax) {
            return false;
        }

        return true;
    }
	
	private void floodFillSet(long i, long j) {
		   		
         Set<Pair<Long, Long>> visited = new HashSet<Pair<Long,Long>>();
         
         
        // Creating queue for bfs
         Queue<Pair<Long, Long>> queue = new LinkedList<>();
   
        // Pushing pair of {x, y}
         Pair<Long, Long> source = new Pair<Long, Long>(i,j);
         queue.add(source);
          
        visited.add(source);
   
        // Until queue is empty
        while (!queue.isEmpty()) 
        {
            // Extracting front pair
            Pair<Long, Long> coord = queue.peek();
            long i1 = coord.getValue0();
            long j1 = coord.getValue1();
             
            dugSet.add(new Pair<Long, Long>(i1, j1));
     
            // Popping front pair of queue
            queue.remove();
 
            // For Upside Point
            Pair<Long, Long> upPair = new Pair<Long, Long>(i1-1, j1);
            if (!visited.contains(upPair) && !dugSet.contains(upPair)) {
                queue.add(upPair);
                visited.add(upPair);
            }
            
            // For Downside Point
            Pair<Long, Long> downPair = new Pair<Long, Long>(i1+1, j1);
            if (!visited.contains(downPair) && !dugSet.contains(downPair)) {
                queue.add(downPair);
                visited.add(downPair);
            }
            
            // For Rightside Point
            Pair<Long, Long> rightPair = new Pair<Long, Long>(i1, j1+1);
            if (!visited.contains(rightPair) && !dugSet.contains(rightPair)) {
                queue.add(rightPair);
                visited.add(rightPair);
            }
     
            // For Leftside Point
            Pair<Long, Long> leftPair = new Pair<Long, Long>(i1, j1-1);
            if (!visited.contains(leftPair) && !dugSet.contains(leftPair)) {
                queue.add(leftPair);
                visited.add(leftPair);
            }
        }
    }
	
	private char numberToDir(char number) {
		switch (number) {
		case '0':
			return 'R';
		case '1':
			return 'D';
		case '2':
			return 'L';
		case '3':
			return 'U';
		}
		return 'R';
	}
	
}

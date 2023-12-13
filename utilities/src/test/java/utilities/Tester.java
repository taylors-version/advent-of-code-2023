package utilities;

import static org.junit.Assert.*;

import java.sql.Timestamp;
import java.util.Arrays;

import org.junit.Test;

import com.ben.aoc.Util;

public class Tester 
{
	
	@Test
    public void testMatrixTranspose()
    {
		char[][] chars = {{'a', 'b', 'c'}, {'d', 'e', 'f'}};
		char[][] transposedChars = {{'a', 'd'}, {'b', 'e'}, {'c', 'f'}};
				
    	assertTrue(Arrays.deepEquals(transposedChars, Util.transposeMatrix(chars)));
    }
	
	@Test
    public void testLargeMatrixTranspose()
    {
		int x = 10000;
		int y = 10000;
		
		long[][] longMatrix = new long[x][y];
		long[][] transposed = new long[y][x];
		
		for (int i =0; i<x; i++) {
			for (int j = 0; j<y; j++) {
				longMatrix[i][j] = (2*i)+j;
				transposed[j][i] = (2*i)+j;
			}
		}
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("Before transpose: " + timestamp);
		long[][] testedMatrix = Util.transposeMatrix(longMatrix);
		timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println("After transpose: " + timestamp);
				
    	assertTrue(Arrays.deepEquals(transposed, testedMatrix));
    }


}

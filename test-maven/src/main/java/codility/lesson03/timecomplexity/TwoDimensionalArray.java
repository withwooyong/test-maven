package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * How to find a index from a two dimensional array
 * 2 차원 배열에서 색인을 찾는 방법
 * 
 * https://github.com/CuGBabyBeaR/Interview-questions
 */

public class TwoDimensionalArray {
	
	private static Logger log = LoggerFactory.getLogger(TwoDimensionalArray.class);

	public static void main(String[] args) {
		int[][] A = {
				{ 4, 44, 5, 7, 63, 1 }, 
				{ 7, 88, 31, 95, 9, 6 }, 
				{ 88, 99, 6, 5, 77, 4 }
		};
		solution(A);
		
		/*
		 * http://codereview.stackexchange.com/questions/42906/partial-sums-of-two-dimensional-array
		 * Partial sums of two dimensional array
		 * [1,  2,  3,  4,  5,  6,  7], 
		 * [2,  4,  6,  8, 10, 12, 14], 
		 * [3,  6,  9, 12, 15, 18, 21], 
		 * [4,  8, 12, 16, 20, 24, 28], 
		 * [5, 10, 15, 20, 25, 30, 35], 
		 * [6, 12, 18, 24, 30, 36, 42], 
		 * [7, 14, 21, 28, 35, 42, 49]
		 */
		log.debug("{}", Arrays.deepToString(sumAboveLeft(new int[]{
		         7,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		         1, 1, 1, 1, 1, 1, 1,
		    })));
	}
	
	// What I'm trying to do is print the largest number within a two dimensional array and it's index location.
	// 최고값 위치 찾는법
	public static void solution(int[][] A) {
		int max = A[0][0];
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (A[i][j] > max) { // 특정숫자 찾을려면 여기서 
					max = A[i][j];
					log.debug("{} {} {}", i, j, max); // 2 1 99 최고값
				}
			}
		}
	}
	
	public static int[][] sumAboveLeft(int[] datasource) {
	    if (datasource == null || datasource.length == 0 || datasource.length != datasource[0] * datasource[0] + 1) {
	        throw new IllegalArgumentException();
	    }
	    // first value in the source data is the matriz size
	    final int size = datasource[0];
	    // because the first value is the size, we expect the loop limit to be unusual.
	    final int limit = datasource.length - 1;

	    int[][] result = new int[size][size];

	    for (int i = 0; i < limit; i++) {
	        // convert the linnear/flat address to a row/column
	        int row = i / size;
	        int col = i % size;

	        // rectangle-sum above us
	        int abovesum = row > 0 ? result[row - 1][col] : 0;
	        // rectangle-sum to left of us
	        int leftsum = col > 0 ? result[row][col - 1] : 0;
	        // rectangle-sum above-left of us.
	        int aboveleftsum = (col > 0 && row > 0) ? result[row - 1][col - 1] : 0;
	        // our value at this point (note the index+1 offset because the first value is the size)
	        int val = datasource[i + 1];
	        // the sum here is
	        // the value here
	        // plus above-left-rectangle-sum
	        // plus above-column-sum
	        // plus left-row-sum
	        result[row][col] = val
	                         + aboveleftsum
	                         + (leftsum - aboveleftsum)
	                         + (abovesum - aboveleftsum);
	    }
	    return result;
	}
}

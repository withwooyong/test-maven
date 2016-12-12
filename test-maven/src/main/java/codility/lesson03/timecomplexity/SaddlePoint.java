package codility.lesson03.timecomplexity;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://www.guideforschool.com/1935902-java-program-to-find-saddle-point-in-a-square-matrix/
 * 
 * Write a program to declare a square matrix A[ ][ ] of order ‘n’. 
 * Allow the user to input positive integers into this matrix. 
 * Perform the following tasks on the matrix:
 * 
 * (i) Output the original matrix.
 * (ii) Find the SADDLE POINT for the matrix. 
 * If the matrix has no saddle point, output the message “NO SADDLE POINT”.
 * 
 * [Note: A saddle point is an element of the matrix such that 
 * it is the minimum element for the row to which it belongs 
 * and the maximum element for the column to which it belongs. 
 * Saddle point for a given matrix is always unique.]
 * 
 * Example: In the Matrix
 * 4 5 6
 * 7 8 9
 * 5 1 3
 * 
 * Saddle point = 7 because it is the minimum element of row 2 and maximum element of column 1
 * This question came in Question 3 of ISC 2003 Practical Examination.
 */
public class SaddlePoint {
	
	private static Logger log = LoggerFactory.getLogger(SaddlePoint.class);

	public static void main(String args[]) throws IOException {
		
		int n = 3;
		// 행에서 최소값, 열에서 최고값 = 7
		int[][] A = { 
				//16,14,18  
				{ 4, 5, 6 },  // 15
				{ 7, 8, 9 },  // 24
				{ 5, 1, 3 } };// 9
		
		int[][] B = { 
			//8, 17, 8
			{ 2, 7, 5 }, // 14
			{ 3, 1, 1 }, // 5
			{ 2, 1, -7 },// -4
			{ 0, 2, 1 }, // 3
			{ 1, 6, 8 } };// 15		
		solution(n, A);
		solution2(B);
		
	}
	
	/*
	 *  * 쌍 (1, 1)은 다음과 같은 이유로 행렬 A의 평형 점입니다.
	 *  * * 0 행의 요소 합계는 2, 3 및 4 행의 요소 합계와 같습니다 (두 영역의 합은 14입니다)
	 *  * * 0 열의 요소 합계는 두 번째 열 요소의 합계와 같습니다 (두 영역의 합은 8입니다).
	 *  *
	 *  * 쌍 (3, 1)은 행렬 A의 또 다른 평형 점입니다.
	 *  *이 매트릭스에는 다른 평형 점이 포함되어 있지 않습니다.
	 */
	public static int solution2(int[][] A) {
		int row  = A.length;
		int column= A[0].length;
		log.debug("행 row={} 열 column={}", A.length, A[0].length);
		int max = 0;
		int min = 0;
		int x = 0;
		int f = 0;
		
		for (int i = 0; i < row; i++) { // Finding the minimum element of a row
			min = A[i][0]; // Initializing min with first element of every row
			x = 0;
			for (int j = 0; j < column; j++) {
				if (A[i][j] < min) {
					min = A[i][j];
					x = j; // Saving the column position of the minimum element of the row
				}
			}
			
			// Finding the maximum element in the column corresponding to the minimum element of row
			max = A[0][x]; // Initializing max with first element of that column
			for (int k = 0; k < column; k++) {
				if (A[k][x] > max) {
					max = A[k][x];
				}
			}
			
			log.debug("min={} max={}", min, max);

			// If the minimum of a row is same as maximum of the corresponding column, then, we have that element as the Saddle point
			if (max == min) {
				log.debug("Saddle point = {}", max);
				f = 1;
			}
		}
		return f;
		
	}
	
	public static int solution(int n, int[][] A) {
		
		int max, min, x, f = 0;
		for (int i = 0; i < n; i++) { // Finding the minimum element of a row
			min = A[i][0]; // Initializing min with first element of every row
			x = 0;
			// 행에서 최소값을 찾는다.
			for (int j = 0; j < n; j++) {
				if (A[i][j] < min) {
					min = A[i][j];
					x = j; // Saving the column position of the minimum element of the row
				}
			}

			// Finding the maximum element in the column corresponding to the minimum element of row
			// 열에서 최고값을 찾는다.
			max = A[0][x]; // Initializing max with first element of that column
			for (int k = 0; k < n; k++) {
				if (A[k][x] > max) {
					max = A[k][x];
				}
			}

			// If the minimum of a row is same as maximum of the corresponding column, then, we have that element as the Saddle point
			if (max == min) {
				log.debug("Saddle point = {}", max);
				f = 1;
			}
		}

		if (f == 0) {
			log.debug("{}", "No saddle point");
			
		}
		return 0;
	}
}

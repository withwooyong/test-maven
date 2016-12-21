package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/symmetric-matrix-program-in-java/
 * Enter the no. of rows :3
 * Enter the no. of columns :3 
 * Printing the input matrix :
 * 7     5     1
 * 5     8     3
 * 1     3     4
 */
public class SymmetricMatrixProgram {
	
	private static Logger log = LoggerFactory.getLogger(SymmetricMatrixProgram.class);

	// Symmetric matrix is a square matrix which is equal to its transpose. 
	// In the other words, if A[][] is a square matrix with (M x M) order, 
	// then this matrix is said to be symmetric 
	// if every element at ith row and jth column is equal to element at jth row and ith column 
	// i.e A[i][j] == A[j][i].
	// 대칭 매트릭스 Symmetric Matrix
	public static void main(String[] args) {
		int[][] matrix = {
				{ 7, 5, 1 },
				{ 5, 8, 3 },
				{ 1, 3, 4 },
		};		
		log.debug("{}", solution(matrix));
	}
	
	private static int solution(int[][] A) {
		int rows = A.length;
		int cols = A[0].length;
		if (rows != cols) {
			log.debug("The given matrix is not a square matrix, so it can't be symmetric.");
			return -1;
		} else {
			boolean symmetric = true;

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					if (A[i][j] != A[j][i]) { // i.e A[i][j] == A[j][i].
						symmetric = false;
						break;
					}
				}
			}

			if (symmetric) {
				log.debug("The given matrix is symmetric...");
			} else {
				log.debug("The given matrix is not symmetric...");
			}
		}
		return 0;
	}
}

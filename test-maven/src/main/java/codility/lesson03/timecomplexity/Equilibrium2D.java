package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * A non-empty zero-indexed matrix A consisting of N rows and M columns of integers is given.
 * A pair(P,Q) is called an equilibrium point of matrix A if following conditions are met:
 * * 0 <= P < N
 * * 0 <= Q < M
 * * the sum of elements in rows above the P-th row is equal to the sum of elements in rows below the P-th row;
 * * the sum of elements in columns to the left of the Q-th column is equal to the sum of elements in columns to the right of the Q-th column.
 * 
 * The sum of zero elements is assumed to be 0.
 * This is the case when P=0 or P = N-1 and/or when Q = 0 or Q = M-1.
 * 
 * For example, consider matrix A consisting of five rows and three columns, such that:
 * A[0][0] = 2  A[0][1] = 7  A[0][2] = 5
 * A[1][0] = 3  A[1][1] = 1  A[1][2] = 1 
 * A[2][0] = 2  A[2][1] = 1  A[2][2] = -7 
 * A[3][0] = 0  A[3][1] = 2  A[3][2] = 1 
 * A[4][0] = 1  A[4][1] = 6  A[4][2] = 8
 * 
 * Pair(1, 1) is an equilibrium point of matrix A, because:
 * * the sum of elements in the 0th row is equal to the sum of elements in the 2nd, 3rd and 4th rows(the sum of both regions is 14)
 * * the sum of elements in the 0th column is equal to the sum of elements in the 2nd column(the sum of both regions is 8).
 * 
 * Pair(3, 1) is another equlibrium point of matrix A.
 * This matrix contains no other equilibrium points.
 * 
 * return 2
 *     
 */
public class Equilibrium2D {
	
	private static Logger log = LoggerFactory.getLogger(Equilibrium2D.class);

	/*
	 *  * 쌍 (1, 1)은 다음과 같은 이유로 행렬 A의 평형 점입니다.
	 *  * * 0 행의 요소 합계는 2, 3 및 4 행의 요소 합계와 같습니다 (두 영역의 합은 14입니다)
	 *  * * 0 열의 요소 합계는 두 번째 열 요소의 합계와 같습니다 (두 영역의 합은 8입니다).
	 *  *
	 *  * 쌍 (3, 1)은 행렬 A의 또 다른 평형 점입니다.
	 *  *이 매트릭스에는 다른 평형 점이 포함되어 있지 않습니다.
	 */
	public static void main(String[] args) {
		// five rows and three columns 
		// 0 <= P < N(30)
		// 0 <= Q < M(30)
		int[][] A = new int[][] { 
			//8, 17, 8
			{ 2, 7, 5 }, // 14
			{ 3, 1, 1 }, // 5
			{ 2, 1, -7 },// -4
			{ 0, 2, 1 }, // 3
			{ 1, 6, 8 } };// 15
		log.debug("{}", solution(A));
	}
	
	private static int solution(int[][] A) {
		
		int[] row = new int[A.length];
		int[] column = new int[A[0].length];
		int equiRow = 0;
		int equiColumn = 0;

		log.debug("row.length={} column.length={}", row.length, column.length);
		int position = 0;
		int num = 0;
		for (int i = 0; i < row.length; i++) {
			for (int j = 0; j < column.length; j++) {
				num += A[i][j];
			}
			log.debug("각 행 가로의 합은 : {}", num);
			row[position++] = num;
			num = 0;
		}
		log.debug("{}", "");
		position = 0;
		for (int i = 0; i < column.length; i++) {
			for (int j = 0; j < row.length; j++) {
				num += A[j][i];
			}
			column[position++] = num;
			log.debug("각 열 세로의 합은 : {}", num);
			num = 0;
		}
		log.debug("{}", Arrays.toString(row));
		log.debug("{}", Arrays.toString(column));
		log.debug("{}", "");
		
		// 행에 대한 equilibrium index 
		for (int i = 0; i < row.length; i++) {
			int leftsum = 0; // initialize left sum for current index i
			int rightsum = 0; // initialize right sum for current index i
			
			for (int j = 0; j < i; j++) { // 왼쪽배열
				leftsum += row[j];
			}
			
			for (int k = i + 1; k < row.length; k++) { // 오른쪽배열
				rightsum += row[k];
			}
			//log.debug("row i={} leftsum={} rightsum={}", i, leftsum, rightsum);
			
			if (leftsum == rightsum) {
				log.debug("row equilibrium index is {}", i);
				equiRow++;
				//return i;
			}
		}
		
		// 열에 대한 equilibrium index 
		for (int i = 0; i < column.length; i++) {
			int leftsum = 0; // initialize left sum for current index i
			int rightsum = 0; // initialize right sum for current index i
			
			for (int j = 0; j < i; j++) { // 왼쪽배열
				leftsum += column[j];
			}
			
			for (int k = i + 1; k < column.length; k++) { // 오른쪽배열
				rightsum += column[k];
			}
			//log.debug("column i={} leftsum={} rightsum={}", i, leftsum, rightsum);
			
			if (leftsum == rightsum) {
				log.debug("column equilibrium index is {}", i);
				equiColumn++;
				//return i;
			}
		}
		log.debug("equiRow:{} * equiColumn:{}={}", equiRow, equiColumn, equiRow * equiColumn);
		return equiRow * equiColumn;
	}

}
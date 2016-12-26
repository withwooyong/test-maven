package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MaxSliceSum
 * Find a maximum sum of a compact subsequence of array elements.
 * 
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * A pair of integers (P, Q), 
 * such that 0 ≤ P ≤ Q < N, is called a slice of array A. 
 * The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
 * 
 * For example, given array A such that:
 * A[0] = 3  
 * A[1] = 2  
 * A[2] = -6
 * A[3] = 4  
 * A[4] = 0
 * the function should return 5 because:
 * 
 * (3, 4) is a slice of A that has sum 4,
 * (2, 2) is a slice of A that has sum −6,
 * (0, 1) is a slice of A that has sum 5,
 * no other slice of A has sum greater than (0, 1).
 * 
 * Assume that:
 * N is an integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000];
 * the result will be an integer within the range [−2,147,483,648..2,147,483,647].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class MaxSliceSum {
	
	private static Logger log = LoggerFactory.getLogger(MaxSliceSum.class);

	/*
	 * Find a maximum sum of a compact subsequence of array elements.
	 */
	// 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는 문제이다.
	/*
	 *  (3, 4) is a slice of A that has sum 4,
	 *  (2, 2) is a slice of A that has sum −6,
	 *  (0, 1) is a slice of A that has sum 5,
	 *  no other slice of A has sum greater than (0, 1).
	 *  return 5
	 */
	public static void main(String[] args) {
		int[] A = new int[] { 3, 2, -6, 4, 0 };
		//  { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		log.debug("{}", solution(A));
		log.debug("{}", solution2(A));
		log.debug("{}", max_subarray(A));
	}
	
	// Kadane's algorithm 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는
	public static int max_subarray(int[] A) {
		int max_ending_here = 0; 
		int max_so_far = 0;
		for (int i = 0; i < A.length; i++) {
			max_ending_here = Math.max(0, max_ending_here + A[i]);
			max_so_far = Math.max(max_so_far, max_ending_here);
			log.debug("{} {}", i, max_so_far);
		}
	    return max_so_far;
	}
	
	// 음수로만 구성되어 있을 경우 
	public static int solution2(int[] A) {
		int maxEndingHere = A[0];
		int maxSoFar = A[0];
		for (int i = 1; i < A.length; i++) {
			maxEndingHere = Math.max(A[i], maxEndingHere + A[i]);
			maxSoFar = Math.max(maxSoFar, maxEndingHere);
		}
		return maxSoFar;
	}
	
	public static int solution(int[] A) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; i++) {
			max = A[i] > max ? A[i] : max;
		}
		if (max <= 0)
			return max;

		int maxSliceSum = 0;
		int currentSum = 0;
		for (int i = 0; i < A.length; i++) {
			currentSum = (currentSum + A[i]) > 0 ? (currentSum + A[i]) : 0;
			maxSliceSum = currentSum > maxSliceSum ? currentSum : maxSliceSum;
		}
		return maxSliceSum;
	}
}

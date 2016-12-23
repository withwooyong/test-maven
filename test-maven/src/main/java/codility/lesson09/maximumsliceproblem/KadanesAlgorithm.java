package codility.lesson09.maximumsliceproblem;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KadanesAlgorithm {
	
	private static Logger log = LoggerFactory.getLogger(KadanesAlgorithm.class);

	public static void main(String[] args) {
		int[] A = { 2, -1, 2, 3, 4, -5 };
		//int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		log.debug("{}", max_subarray(A));

	}
	
	// Kadane's algorithm
	// 배열의 최고합 찾는 알고리즘. 
	// 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는 문제이다.
	public static int max_subarray(int[] A) {
		
		/*
		 * def max_subarray(A):
	    max_ending_here = max_so_far = A[0]
	    for x in A[1:]:
	        max_ending_here = max(x, max_ending_here + x)
	        max_so_far = max(max_so_far, max_ending_here)
	    return max_so_far
		 */
		int max_ending_here = A[0];
		int max_so_far = A[0];
		
		for (int i = 1; i < A.length; i++) {
			max_ending_here = Math.max(i, max_ending_here + A[i]);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}

}

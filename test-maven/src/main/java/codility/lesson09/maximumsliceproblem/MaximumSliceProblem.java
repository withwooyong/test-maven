package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumSliceProblem {

	private static Logger log = LoggerFactory.getLogger(MaximumSliceProblem.class);
	
	/*
	 * 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는 문제이다.
	 * 
	 * In computer science, 
	 * the maximum subarray problem is the task of finding the contiguous subarray within a one-dimensional array of numbers 
	 * which has the largest sum. 
	 * For example, 
	 * for the sequence of values −2, 1, −3, 4, −1, 2, 1, −5, 4; the contiguous subarray 
	 * with the largest sum is 4, −1, 2, 1, with sum 6.
	 */
	public static void main(String[] args) {
		//int[] A = { 5, -7, 3, 5, -2, 4, -1 };
		int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		log.debug("{}", slowMaxSlice(A)); // The sum of this slice equals 10
	}
	
	// O(n3)
	private static int slowMaxSlice(int[] A) {
		int result = 0;
		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				int sum = 0;
				for (int k = i; k < j + 1; k++) {
					//log.debug("### {} ### {}", k, j);
					sum += A[k];
				}
				//log.debug("i={} j={} result={} sum={}", i, j, result, sum);
				result = Math.max(result, sum);
			}
			log.debug("i={} A[i]={} result={} ", i, A[i], result);
		}
		return result;
	}
	
	private static int quadratic_max_slice(int[] A, int pref) {
		
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
//				int sum = pref[j + 1] - pref[i];
//				result = Math.max(result, sum);						
			}
		}
		return result;
	}
}



package codility.lesson04.countingelements;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * You are given N counters, initially set to 0, and you have two possible operations on them:
 * increase(X) − counter X is increased by 1, 
 * max counter − all counters are set to the maximum value of any counter.
 * 
 * A non-empty zero-indexed array A of M integers is given. 
 * 
 * This array represents consecutive operations:
 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X), 
 * if A[K] = N + 1 then operation K is max counter.
 * For example, given integer N = 5 and array A such that:
 *  A[0] = 3
 *  A[1] = 4
 *  A[2] = 4
 *  A[3] = 6
 *  A[4] = 1
 *  A[5] = 4
 *  A[6] = 4  
 *  the values of the counters after each consecutive operation will be:
 *  (0, 0, 1, 0, 0)
 *  (0, 0, 1, 1, 0)
 *  (0, 0, 1, 2, 0)
 *  (2, 2, 2, 2, 2)
 *  (3, 2, 2, 2, 2)
 *  (3, 2, 2, 3, 2)
 *  (3, 2, 2, 4, 2)
 *  
 *  The goal is to calculate the value of every counter after all operations.
 *  Write a function:
 *  class Solution { public int[] solution(int N, int[] A); }
 *  
 *  that, given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence of integers representing the values of the counters.
 *  
 *  For example, given:
 *  A[0] = 3
 *  A[1] = 4
 *  A[2] = 4
 *  A[3] = 6
 *  A[4] = 1
 *  A[5] = 4
 *  A[6] = 4
 *  the function should return [3, 2, 2, 4, 2], as explained above.
 *  
 *  Assume that:
 *  N and M are integers within the range [1..100,000];
 *  each element of array A is an integer within the range [1..N + 1].
 *  
 *  Complexity:
 *  expected worst-case time complexity is O(N+M);
 *  expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 *  Elements of input arrays can be modified.
 *  Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 */
public class MaxCounters {

	private static Logger log = LoggerFactory.getLogger(MaxCounters.class);
	
	public static void main(String[] args) {
		int N = 5;
		int[] A = { 3, 4, 4, 6, 1, 4, 4 };
//		log.debug("{}", Arrays.toString(solution(N, A)));
//		log.debug("{}", Arrays.toString(solution2(N, A)));
		log.debug("{}", Arrays.toString(mySolution(N, A)));
	}
	
	/*
	 * (0, 0, 1, 0, 0)
	 * (0, 0, 1, 1, 0)
	 * (0, 0, 1, 2, 0)
	 * (2, 2, 2, 2, 2)
	 * (3, 2, 2, 2, 2)
	 * (3, 2, 2, 3, 2)
	 * (3, 2, 2, 4, 2)
	 * 
	 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
	 * if A[K] = N + 1 then operation K is max counter.
	 * 
	 * For example, given integer N = 5 and array A such that:
	 * 
	 * You are given N counters, initially set to 0, and you have two possible operations on them:
	 * increase(X) − counter X is increased by 1,
	 * max counter − all counters are set to the maximum value of any counter.
 * 
 * A non-empty zero-indexed array A of M integers is given. 
 * 
 * This array represents consecutive operations:
 * if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X), 
 * if A[K] = N + 1 then operation K is max counter.
 * For example, given integer N = 5 and array A such that:
	 */
	// 이해안됨.
	public static int[] mySolution(int N, int[] A) {
			 
		// You are given N counters, initially set to 0
		int[] counters = new int[N];
		for (int i = 0; i < counters.length; i++) { 
			counters[i] = 0;
		}
		// two possible operations on them
		// if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
		// if A[K] = N + 1 then operation K is max counter.
		// increase(X) − counter X is increased by 1,
		// max counter − all counters are set to the maximum value of any counter.
		
		int increaseX = 0;
		int max_counter = 0;
		// int[] A = { 3, 4, 4, 6, 1, 4, 4 };
		for (int K = 0; K < A.length; K++) {
			int X = A[K];
			if (X >= 1 && X <= N) {
				// increase(X)
				log.debug("K={} X={} increase(X)={}", K, X, X + 1);
			} else if (X == N + 1) {
				// operation K is max counter
				max_counter = K;
				log.debug("K={}, max_counter={}", K, max_counter);
			}
		}
		return A;
	}
	
	
	public static int[] solution(int N, int[] A) {
		int M = A.length;
		int[] counters = new int[N];
		int maxValue = 0; // store the max value in the counters array
		int currMax = 0; // store the max value when there is a max counter
		for (int i = 0; i < M; i++) {
			if (A[i] >= 1 && A[i] <= N) {
				// max counter
				if (counters[A[i] - 1] < currMax) {
					counters[A[i] - 1] = currMax;
				}
				// increase (X)
				counters[A[i] - 1]++;
				if (counters[A[i] - 1] > maxValue) {
					maxValue = counters[A[i] - 1];
				}

				for (int j = 0; j < counters.length; j++) {
					System.out.print(" " + counters[j]);
				}
				System.out.println();
			}
			// update current max value when there is a max counter
			if (A[i] == N + 1) {
				currMax = maxValue;
			}
		}

		for (int i = 0; i < N; i++) {
			// update the rest elements of counters which aren't assigned above.
			if (counters[i] < currMax)
				counters[i] = currMax;
		}
		return counters;
	}

	public static int[] solution2(int N, int[] A) {
		int[] counters = new int[N];
		int currMax = 0;
		int currMin = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= N) { // increase(X)
				counters[A[i] - 1] = Math.max(currMin, counters[A[i] - 1]);
				counters[A[i] - 1]++;
				currMax = Math.max(currMax, counters[A[i] - 1]);
			} else if (A[i] == N + 1) { // max counter
				currMin = currMax;
			}
		}

		for (int i = 0; i < counters.length; i++) {
			counters[i] = Math.max(counters[i], currMin);
		}
		return counters;
	}

}

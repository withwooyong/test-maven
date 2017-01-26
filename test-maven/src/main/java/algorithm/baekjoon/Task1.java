package algorithm.baekjoon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Task1 {

	private static Logger log = LoggerFactory.getLogger(Task1.class);
	/*
	 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, these are arithmetic sequence:
1, 3, 5, 7, 9
7, 7, 7, 7
3, -1, -5, -9

The sequence [1, 1, 2, 5, 7] is not arithmetic.

A zero-indexed array A consisting of N numbers is given.
A slice of that array is any pair of integers(P, Q) such that 0 <= P < Q < N.
A slice (P, Q) of array A is called arithmetic if the sequence.
A[P], A[P+1], ..., A[Q-1], A[Q]
is arithmetic. In particular, this means that P + 1 < Q.

Write a function:
class Solution { public int solution(int[] A); }

that, given array A consisting of N numbers, returns the number of arithmetic slices in A.

For example, given array A suth that:
A[0] = -1
A[1] = 1
A[2] = 3
A[3] = 3
A[4] = 3
A[5] = 2
A[6] = 1
A[7] = 0

the function should return 5 because there are five arithmetic slices of that array, namely
(0, 2), (2, 4), (4, 6), (4, 7), (5, 7)
	 */
	public static void main(String[] args) {
		int[] B = {  0, 1, 2, 3, 4, 5, 6, 7 };
		int[] A = { -1, 1, 3, 3, 3, 2, 1, 0 };
		
		log.debug("{}", solution(A));
	}
	
	private static int solution(int[] A) {
		if (A.length < 3) {
			return 0;
		}
		int P = 0;
		int num = 0;

		// int[] A = { -1, 1, 3, 3, 3, 2, 1, 0 };
		while (P < A.length - 2) {
			int Q = P + 1;
			int diff = A[Q] - A[P]; // 차이
			
			// 0 <= P < Q < N
			// extend until arithmetic condition fails
			while ((Q < A.length - 1) && ((A[Q + 1] - A[Q]) == diff)) {
				Q++;
			}

			int len = Q - P + 1; // length of the found arightmetic slice
			
			// valid slice must have at least 3 number
			if (len >= 3) {
				// For an arithmetic progression with length n, where n>3, the
				// number of slices it can form is (n-2)*(n-1)/2.
				// For example, if n = 5, it has 3 slices with length 3, 2
				// slices with length 4, and 1 slice with length 5.
				num += ((len - 2) * (len - 1) / 2);
				log.debug("len={} num={}", len, (len - 2) * (len - 1) / 2);
				
			}
			// Arithmetic progression will not overlap, so move start to end
			P = Q;
		}
		return (num > 1000000000) ? -1 : num;
	}
}

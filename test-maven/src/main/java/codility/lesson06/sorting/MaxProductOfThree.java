package codility.lesson06.sorting;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
 * 
 * For example, array A such that:
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * contains the following example triplets:
 * 
 * (0, 1, 2), product is −3 * 1 * 2 = −6
 * (1, 2, 4), product is 1 * 2 * 5 = 10
 * (2, 4, 5), product is 2 * 5 * 6 = 60
 * 
 * Your goal is to find the maximal product of any triplet.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.
 * 
 * For example, given array A such that:
 * A[0] = -3
 * A[1] = 1
 * A[2] = 2
 * A[3] = -2
 * A[4] = 5
 * A[5] = 6
 * the function should return 60, as the product of triplet (2, 4, 5) is maximal.
 * 
 * Assume that:
 * N is an integer within the range [3..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class MaxProductOfThree {

	private static Logger log = LoggerFactory.getLogger(MaxProductOfThree.class);
	
	public static void main(String[] args) {
		int[] A = { -3, 1, 2, -2, 5, 6 };
		log.debug("{}", mySolution(A));
		//log.debug("{}", solution1(A));
		
		
	}
	
	// 배열중 3개 항목의 곲셈 중 최대값 구하는 문제
	// 3..100,000 개의 인수를 갖는 배열에서 3개의 인수를 곱하여 최대 값이 나올 수 있도록 하는 문제
	private static int mySolution(int[] A) {
		
		if (A.length <= 3) {
			log.debug("{}", A.length);
			return -1;
		}
		Arrays.sort(A); // 정렬하면 높은값이 뒤로 위치한다.
		for (int i = 0; i < A.length; i++) {
			if (A[i] < -1000 || A[i] > 1000 ) {
				log.debug("{}", A[i]);
				return -1;
			}
		}
		int maxProduct = A[A.length-1] * A[A.length-2] * A[A.length-3];
		log.debug("{}", maxProduct);
		if (A[0] < 0 && A[1] < 0) { // minus * minus = 양수가 됨.
			int minusProduct = A[0] * A[1] * A[A.length - 1];
			if (minusProduct > maxProduct) {
				maxProduct = minusProduct;
			}
		}
		return maxProduct;
	}
	
	private static int solution1(int[] A) {
		Arrays.sort(A);
		int len = A.length;

		int tmp1 = A[len - 1] * A[len - 2] * A[len - 3]; 
		int tmp2 = A[0] * A[1] * A[len - 1];

		if (tmp1 > tmp2) {
			return tmp1;
		}
		return tmp2;
	}
	
	// 이해안됨.
	private static int solution(int[] A) {
		int min1 = Integer.MAX_VALUE;
		int min2 = Integer.MAX_VALUE;
		int max1 = Integer.MIN_VALUE;
		int max2 = Integer.MIN_VALUE;
		int max3 = Integer.MIN_VALUE;

		for (int i = 0; i < A.length; i++) {
			if (A[i] > max1) {
				max3 = max2;
				max2 = max1;
				max1 = A[i];
			} else if (A[i] > max2) {
				max3 = max2;
				max2 = A[i];
			} else if (A[i] > max3) {
				max3 = A[i];
			}
			if (A[i] < min1) {
				min2 = min1;
				min1 = A[i];
			} else if (A[i] < min2) {
				min2 = A[i];
			}
		}
		int prod1 = min1 * min2 * max1;
		int prod2 = max1 * max2 * max3;
		return prod1 > prod2 ? prod1 : prod2;
	}
}

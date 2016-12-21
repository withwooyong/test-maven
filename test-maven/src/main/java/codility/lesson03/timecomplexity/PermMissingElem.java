package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A zero-indexed array A consisting of N different integers is given. 
 * The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
 * 
 * Your goal is to find that missing element.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a zero-indexed array A, returns the value of the missing element.
 * 
 * For example, given array A such that:
 * A[0] = 2
 * A[1] = 3
 * A[2] = 1
 * A[3] = 5
 * the function should return 4, as it is the missing element.
 * 
 * Assume that:
 * N is an integer within the range [0..100,000];
 * the elements of A are all distinct;
 * each element of array A is an integer within the range [1..(N + 1)].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class PermMissingElem {

	private static Logger log = LoggerFactory.getLogger(PermMissingElem.class);
	
	/*
	 * Find the missing element in a given permutation.
	 */
	// 없는 숫자를 찾아내는 함수
	public static void main(String[] args) {
		int[] A = { 2, 3, 1, 5 };
		log.debug("{}", mySolution(A));
		log.debug("{}", solution(A));
		log.debug("{}", solution2(A));
		log.debug("{}", test(A));
	}
	
	public static int test(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i < A.length; i++) {
			if (A[i] != (i + 1)) {
				return i + 1;
			}			
		}
		return 0;
	}
	
	public static int solution2(int[] A) {
		long N = A.length;
		long sum = ((N + 1) * (N + 2)) / 2; // 요넘 중요.
		for (int i = 0; i < N; i++) {
			sum -= A[i];
		}
		return (int) sum;
	}
	
	public static int mySolution(int[] A) {
		// N is an integer within the range [0..100,000];
		// the elements of A are all distinct;
		// each element of array A is an integer within the range [1..(N + 1)].
		Arrays.sort(A);		
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0 && A[i] <= 100000) {
				if (A[i] + 1 != A[i+1]) {
					result =  A[i] + 1;
					break;
				}
			} 
		}
		return result;
	}
	
	public static int solution(int[] A) {
		int result = 0;
		boolean occurred[] = new boolean[A.length + 2];
		for (int i = 0; i < A.length; i++) {
			int occurredNumber = A[i];
			occurred[occurredNumber] = true;
		}
		for (int i = 1; i < occurred.length; i++) {
			if (!occurred[i]) {
				result = i;
				break;
			}
		}
		return result;
	}
}

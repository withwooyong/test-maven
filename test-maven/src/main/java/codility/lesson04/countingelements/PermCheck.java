package codility.lesson04.countingelements;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A non-empty zero-indexed array A consisting of N integers is given.
 * A permutation is a sequence containing each element from 1 to N once, and only once.
 * 
 * For example, array A such that:
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * is a permutation, 
 * 
 * but array A such that:
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * is not a permutation, 
 * because value 2 is missing.
 * 
 * The goal is to check whether array A is a permutation.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
 * 
 * For example, given array A such that:
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * A[3] = 2
 * the function should return 1.
 * Given array A such that:
 * A[0] = 4
 * A[1] = 1
 * A[2] = 3
 * the function should return 0.
 * 
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [1..1,000,000,000].
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class PermCheck {

	private static Logger log = LoggerFactory.getLogger(PermCheck.class);
	
	public static void main(String[] args) {
		//int[] A = { 4, 1, 3, 2 };
		int[] A = { 4, 1, 3 };
		log.debug("{}", Arrays.toString(A));
		//log.debug("{}", solution(A));
		mySolution(A);
	}
	
	// 주어진 배열이 순열인지 체크 / 합의공식 n*(n+1)/2		
	public static void mySolution(int[] A) {
		//Arrays.sort(A);		
		// n*(n+1)/2		
		int sum = A.length * (A.length + 1) / 2;		
		for (int i = 0; i < A.length; i++) {
			sum -= A[i];
		}
		log.debug("{}", Math.abs(sum));
	}
	
	public static int solution(int[] A) {

		boolean checker[] = new boolean[A.length + 1];

		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			if (value < checker.length) {
				checker[value] = true;
			}
		}

		int result = 1;
		for (int i = 1; i < checker.length; i++) {
			if (checker[i] == false) {
				result = 0;
				break;
			}
		}

		return result;
	}
	
	public int solution2(int[] A) {
		int max = 0;
		HashSet<Integer> set = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			set.add(new Integer(A[i]));
			
			if (A[i] > max) {
				max = A[i];				
			}
		}
		
		if (set.size() != A.length) {
			return 0;			
		}
		int returnVal = 1;
		for (int i = 1; i < max; i++) {
			if (!set.contains(new Integer(i))) {
				returnVal = 0;
				break;
			}			
		}
		return returnVal;
	}
}

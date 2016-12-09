package codility.lesson06.sorting;


import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write a function
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.
 * 
 * Assume that:
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−1,000,000..1,000,000].
 * 
 * For example, given array A consisting of six elements such that:
 * A[0] = 2
 * A[1] = 1
 * A[2] = 1
 * A[3] = 2
 * A[4] = 3    
 * A[5] = 1
 * 
 * the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class Distinct {

	private static Logger log = LoggerFactory.getLogger(Distinct.class);
	
	public static void main(String[] args) {
		int[] A = { 2, 1, 1, 2, 3, 1 };
		//log.debug("{}", solution(A));
		log.debug("{}", mySolution(A));
	}
	
	// the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
	// 중복제거 후 유일한 값의 갯수
	public static int mySolution(int[] A) {
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			set.add(A[i]);
		} 
		return set.size();
	}

	public static int solution(int[] A) {
		// write your code in Java SE 8
		Set<Integer> set = new HashSet<>();
		int ret = 0;
		for (int item : A) {
			set.add(item);
		}
		ret = set.size();
		return ret;
	}

}

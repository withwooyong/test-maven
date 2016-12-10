package codility.lesson04.countingelements;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer (greater than 0) that does not occur in A.
 * 
 * For example, given:
 * A[0] = 1
 * A[1] = 3
 * A[2] = 6
 * A[3] = 4
 * A[4] = 1
 * A[5] = 2
 * the function should return 5.
 * 
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class MissingInteger {
	
	private static Logger log = LoggerFactory.getLogger(MissingInteger.class);

	/*
	 * Find the minimal positive integer not occurring in a given sequence.
	 */
	public static void main(String[] args) {
		int[] A = { 1, 3, 6, 4, 1, 2 };
		mySolution(A);
	}
	
	// 배열에 없는 최소양의 정수
	// the function should return 5.
	public static void mySolution(int[] A) {
		Arrays.sort(A);
		int min = 1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == min) {
				min++;
			} else {
				log.debug("{} {}", i, min);
			}
		}
		log.debug("{} ", min);
		
	}
	
	public int solution(int[] A) {
		boolean checker[] = new boolean[A.length + 1];
		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			if (value > 0 && value < checker.length) {
				checker[value] = true;
			}
		}
		for (int i = 1; i < checker.length; i++) {
			if (checker[i] == false) {
				return i;
			}
		}
		return checker.length;
	}
	
	public int solution3(int[] A) {
		HashSet<Integer> set = new HashSet<>();
		
		for (int i = 0; i < A.length; i++) {
			if (A[i] > 0) {
				set.add(A[i]);				
			}
		}
		
		for (int i = 1; i < Integer.MAX_VALUE; i++) {
			if (!set.contains(new Integer(i))) {
				return i;				
			}
		}
		return 1;
	}
	
	public static int solution2(int[] A) {
		Arrays.sort(A);
		int min = 1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == min)
				min++;
		}
		return min;
	}
}

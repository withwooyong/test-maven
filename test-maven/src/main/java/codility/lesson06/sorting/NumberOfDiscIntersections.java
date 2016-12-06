package codility.lesson06.sorting;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * We draw N discs on a plane. 
 * The discs are numbered from 0 to N − 1. 
 * A zero-indexed array A of N non-negative integers, specifying the radiuses of the discs, is given. 
 * The J-th disc is drawn with its center at (J, 0) and radius A[J].
 * 
 * We say that the J-th disc and K-th disc intersect if J ≠ K and the J-th and K-th discs have at least one common point (assuming that the discs contain their borders).
 * 
 * The figure below shows discs drawn for N = 6 and A as follows:
 * A[0] = 1
 * A[1] = 5
 * A[2] = 2
 * A[3] = 1
 * A[4] = 4
 * A[5] = 0
 * 
 * There are eleven (unordered) pairs of discs that intersect, namely:
 * discs 1 and 4 intersect, and both intersect with all the other discs;
 * disc 2 also intersects with discs 0 and 3.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * 
 * that, given an array A describing N discs as explained above, returns the number of (unordered) pairs of intersecting discs. 
 * The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
 * 
 * Given array A shown above, the function should return 11, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..2,147,483,647].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class NumberOfDiscIntersections {

	private static Logger log = LoggerFactory.getLogger(NumberOfDiscIntersections.class);
	
	// 이해못했음.
	public static void main(String[] args) {
		int[] A = { 1, 5, 2, 1, 4, 0 };
		log.debug("{}", solution(A));
		log.debug("{}", mySolution(A));	 
	}
	
	/**
	 * A[0] = 1 
	 * A[1] = 5 
	 * A[2] = 2 
	 * A[3] = 1 
	 * A[4] = 4 
	 * A[5] = 0
	 *  There are eleven (unordered) pairs of discs that intersect, namely:
	 *  discs 1 and 4 intersect, and both intersect with all the other discs;
	 *  disc 2 also intersects with discs 0 and 3.
	 *  이해안됨.
	 */
	public static int mySolution(int[] A) {
		int cnt = 0;
		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (i + A[i] >= j - A[j]) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	
	// J번째 디스크는 중앙이 (J, 0)이고 반지름 A[J] 으로 그려진다.
	// J ≠ K이고 J번째 디스크와 K번째 디스크가 적어도 하나의 공통 지점이 있다면(디스크에 경계선도 포함한다고 가정한다.)
	// J번째 디스크와 K번째 디스크가 '교차'(intersect)한다고 말한다.
	public static int solution(int[] A) {
		// 6개의 디스크
		long result = 0;

		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				long iMax = Math.min((long)i + A[i], A.length);
				long jMin = Math.max((long)j - A[j], 0);
				if (iMax >= jMin) {
					result++;
				}
			}
		}
		return (int) result;
	}
}

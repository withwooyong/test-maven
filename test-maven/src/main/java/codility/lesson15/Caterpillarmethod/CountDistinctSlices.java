package codility.lesson15.Caterpillarmethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Count the number of distinct slices (containing only unique numbers).
 * 
 * An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given. 
 * All integers in array A are less than or equal to M.
 * 
 * A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. 
 * The slice consists of the elements A[P], A[P + 1], ..., A[Q]. 
 * A distinct slice is a slice consisting of only unique numbers. 
 * That is, no individual number occurs more than once in the slice.
 * 
 * For example, consider integer M = 6 and array A such that:
 * A[0] = 3
 * A[1] = 4
 * A[2] = 5
 * A[3] = 5
 * A[4] = 2
 * 
 * There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
 * 
 * The goal is to calculate the number of distinct slices.
 * 
 * Write a function:
 * class Solution { public int solution(int M, int[] A); }
 * that, given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.
 * If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
 * 
 * the function should return 9, as explained above.
 * Assume that:
 * N is an integer within the range [1..100,000];
 * M is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [0..M].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 */
public class CountDistinctSlices {

	private static Logger log = LoggerFactory.getLogger(CountDistinctSlices.class);
	
	public static void main(String[] args) {
		int[] A = new int[] { 3, 4, 5, 5, 2 };
		int M = 6;
		log.debug("{}", solution(A, M)); // return 9
		log.debug("{}", solution2(A, M)); // return 9
		log.debug("{}", mySolution(A, M)); // return 9	
	}
	
	// https://codesays.com/2014/solution-to-count-distinct-slices-by-codility/
	private static int solution2(int[] A, int M) {
		long slices = 0;
		int begin = 0;
		int end = 0;
		HashSet<Integer> set = new HashSet<Integer>();

		while (end < A.length) {
			while (set.contains(A[end]) == false) {
				set.add(A[end]);
				end++;
				log.debug("{} {} {}", set.toString(), end, A.length);
				if (end == A.length) {
					break;
				}				
			}
			
			log.debug("begin={} end={} ", begin, end);
			// PS: the formula: 1+2+3+4+…+n-1+n = n*(n+1)/2
			slices += ((end - begin) * (end - begin + 1)) / 2;

			if (slices > 1000000000) {
				return 1000000000;
			}

			begin = end;
			set.clear();
		}
		log.debug("slices={}", slices);
		return (int) slices;
	}
	
	public static int mySolution(int[] A, int M) {
		// All integers in array A are less than or equal to M.
		// A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N,
		// The slice consists of the elements A[P], A[P + 1], ..., A[Q]. 
		// A distinct slice is a slice consisting of only unique numbers. 
		// That is, no individual number occurs more than once in the slice.
		// (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
		// (3, 3), (3, 4), (3, 5), (4, 4), (4, 5), (5, 5), (5, 5), (5, 2) and (2, 2).
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A.length; j++) {
				if (A[i] == A[j]) {
					continue;
				} else {
					count++;
				}
			}
		}
		return count;
	}

	// 중복제거된 슬라이스 갯수
	public static int solution(int[] A, int M) { // caterpillarMethod
		int res = 0;
		int front = 0;
		int back = 0;
		boolean[] seen = new boolean[M + 1];
		while (front < A.length && back < A.length) {
			while (front < A.length && !seen[A[front]]) {
				res += front - back + 1;
				seen[A[front]] = true;
				front++;
			}
			while (front < A.length && back < A.length && A[back] != A[front]) {
				seen[A[back]] = false;
				back++;
			}
			seen[A[back]] = false;
			back++;
		}
		return Math.min(res, 1000000000);
	}
	
	
	private static void permutation(int[] A, int n) {
		if (n == 1) {
			log.debug("{}", Arrays.toString(A));
			return;
		}
		for (int i = 0; i < n; i++) {
			swap(A, i, n - 1);
			permutation(A, n - 1);
			swap(A, i, n - 1);
		}
	}

	// swap the characters at indices i and j
	private static void swap(int[] a, int i, int j) {
		int c = a[i];
		a[i] = a[j];
		a[j] = c;
	}
}

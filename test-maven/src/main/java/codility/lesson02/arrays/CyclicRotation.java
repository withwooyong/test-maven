package codility.lesson02.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A zero-indexed array A consisting of N integers is given.
 * Rotation of the array means that each element is shifted right by one index, and the last element of the array is
 * also moved to the first place.
 * 
 * For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7].
 * The goal is to rotate array A K times; that is, each element of A will be shifted to the right by K indexes.
 * 
 * Write a function:
 * class Solution { public int[] solution(int[] A, int K); }
 * 
 * that, given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.
 * 
 * For example,
 * given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].
 * 
 * Assume that:
 * N and K are integers within the range [0..100];
 * each element of array A is an integer within the range [−1,000..1,000].
 * In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 * 
 * @author wooyong
 *
 */
public class CyclicRotation {

	public static void main(String[] args) {
		int[] A = { 3, 8, 9, 7, 6, 8 };
		int K = 3;
		// return [9, 7, 6, 3, 8]
		System.out.println(Arrays.toString(mySolution(A, K)));

	}

	public int[] solution(int[] A, int K) {

		return A;
	}

	public static int[] mySolution(int[] A, int K) {
		// N and K are integers within the range [0..100];
		// each element of array A is an integer within the range [−1,000..1,000].

		if ((A.length == 0) || (A.length > 100) || (K < 0) || (K > 100)) {
			return new int[] {};
		}

		int[] B = new int[A.length];

		for (int i = 0; i < A.length; i++) {
			if (A[i] >= -1000 || A[i] <= 1000) {
				System.out.println("(K + c) % A.length=" + (K + i) % A.length);
				B[(K + i) % A.length] = A[i]; // 요넘이 중요한데 ..무슨공식인지 이해안감.
			} else {
				return new int[] {};
			}
		}
		return B;
	}
}

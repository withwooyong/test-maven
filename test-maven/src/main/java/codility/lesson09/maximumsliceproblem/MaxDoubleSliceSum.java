package codility.lesson09.maximumsliceproblem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MaxDoubleSliceSum
 * Find the maximal sum of any double slice.
 * 
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.  
 * The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
 * 
 * For example, array A such that:
 * A[0] = 3
 * A[1] = 2
 * A[2] = 6
 * A[3] = -1
 * A[4] = 4
 * A[5] = 5
 * A[6] = -1
 * A[7] = 2
 * contains the following example double slices:
 * 
 * double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
 * double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
 * double slice (3, 4, 5), sum is 0.
 * 
 * The goal is to find the maximal sum of any double slice.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.
 * 
 * For example, given:
 * A[0] = 3
 * A[1] = 2
 * A[2] = 6
 * A[3] = -1
 * A[4] = 4
 * A[5] = 5
 * A[6] = -1
 * A[7] = 2
 * the function should return 17, because no double slice of array A has a sum of greater than 17.
 * 
 * Assume that:
 * N is an integer within the range [3..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class MaxDoubleSliceSum {
	
	private static Logger log = LoggerFactory.getLogger(MaxDoubleSliceSum.class);
	
	/*
	 * Find the maximal sum of any double slice.
	 * return 17
	 */
	public static void main(String[] args) {
		int[] A = new int[] { 3, 2, 6, -1, 4, 5, -1, 2 };
		log.debug("{}", solution(A));
		log.debug("{}", solution2(A));
		log.debug("{}", max_subarray()); // return 10
	}
	
	
	// Kadane's algorithm
	// 배열의 최고합 찾는 알고리즘. 
	// 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는 문제이다.
	public static int max_subarray() {
		int[] A = { 2, -1, 2, 3, 4, -5 };
		/*
		 * def max_subarray(A):
	    max_ending_here = max_so_far = A[0]
	    for x in A[1:]:
	        max_ending_here = max(x, max_ending_here + x)
	        max_so_far = max(max_so_far, max_ending_here)
	    return max_so_far
		 */
		int max_ending_here = A[0];
		int max_so_far = A[0];
		
		for (int i = 1; i < A.length; i++) {
			max_ending_here = Math.max(i, max_ending_here + A[i]);
			max_so_far = Math.max(max_so_far, max_ending_here);
		}
		return max_so_far;
	}	

	// return 17
	// A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
	// The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
	// double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
	// double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
	// double slice (3, 4, 5), sum is 0.
	/*
	 * 접근방법:
	 * MaxSliceSum문제와 유사한데 가운데 1개를 빼고 최대합계를 구한다고 생각한다.
	 * Kadane's algorithm에서 힌트를 얻어서 뺄 가운데 1개를 기준으로
	 * 순방향 진행 시 최대값과 역방향 진행 시 최대값을 각각 미리 구한다.
	 * 미리 구해놓은 값을 좌측에서 우측으로 가운데 1개씩 빼고 이동하면서 최대값을 구해 리턴한다.
	 * 합을 구할 때 좌측 또는 우측 최대값이 음수인 경우 0으로 치환
	 * ​(사유: 음수가 나타나는 경우 이전에 자르면 0이다.)
	 */
	// O(n)
	public static int solution(int[] A) {
		// int[] A = new int[] { 3, 2, 6, -1, 4, 5, -1, 2 };
		int max = 0;
		int[] A1 = new int[A.length];
		int[] A2 = new int[A.length];
		
		log.debug("{}", Arrays.toString(A));
		for (int i = 1; i < A.length - 1; i++) {
			A1[i] = Math.max(A1[i - 1] + A[i], 0);
		}
		log.debug("{}", Arrays.toString(A1));
		
		for (int i = A.length - 2; i >= 1; i--) {
			A2[i] = Math.max(A2[i + 1] + A[i], 0);
		}
		
		log.debug("{}", Arrays.toString(A2));

		for (int i = 1; i < A.length - 1; i++) {
			max = Math.max(max, A1[i - 1] + A2[i + 1]);
		}
		return max;
	}
	
	// https://rafal.io/posts/codility-max-double-slice-sum.html
	/*
	 * We can use Kadane’s algorithm from two directions. 
	 * First, we can safely ignore A[0] and A[N-1] since by definition they can’t be a part of any double-slice sum.
	 * Now, define K1[i] as the maximum sum contiguous subsequence ending at index ii, 
	 * and similarly, define K2[i] as the maximum sum contiguous subsequence starting with index ii.
	 * Then, iterate over ii, and find the maximum sum of K1[i-1]+K2[i+1]. This is the max double slice sum.
	 * The 100/100 Java code:
	 */
	public static int solution2(int[] A) {
		int N = A.length;
		int[] K1 = new int[N];
		int[] K2 = new int[N];

		for (int i = 1; i < N - 1; i++) {
			K1[i] = Math.max(K1[i - 1] + A[i], 0);
		}
		
		for (int i = N - 2; i > 0; i--) {
			K2[i] = Math.max(K2[i + 1] + A[i], 0);
		}

		int max = 0;

		for (int i = 1; i < N - 1; i++) {
			max = Math.max(max, K1[i - 1] + K2[i + 1]);
		}
		return max;
	}
}

package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumSubarrayProblem {

	private static Logger log = LoggerFactory.getLogger(MaximumSubarrayProblem.class);
	/*
	 * 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는 문제이다.
	 * 
	 * In computer science, 
	 * the maximum subarray problem is the task of finding the contiguous subarray within a one-dimensional array of numbers 
	 * which has the largest sum. 
	 * For example, 
	 * for the sequence of values -2, 1, -3, 4, -1, 2, 1, -5, 4; the contiguous subarray 
	 * with the largest sum is 4, −1, 2, 1, with sum 6.
	 */
	public static void main(String[] args) {
		int[] A = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		log.debug("{}", max_subarray(A));
		
	}
	
	// Kadane's algorithm 어떤 배열이 주어졌을 때, 배열에서 연속된 부분배열 중 가장 큰 합을 찾는
	public static int max_subarray(int[] A) {
		int max_ending_here = 0; 
		int max_so_far = 0;
		for (int i = 0; i < A.length; i++) {
			max_ending_here = Math.max(0, max_ending_here + A[i]);
			max_so_far = Math.max(max_so_far, max_ending_here);
			log.debug("{} {}", i, max_so_far);
		}
	    return max_so_far;
	}
	
	// Kadane's algorithm
	/*
	 * 1차원으로 이뤄진 배열에서 배열을 한번 스캔하면서, 각 포지션에서 가지는 최대값을 구하는 그런 알고리즘이다.
	 * Kadane's algorithm consists of a scan through the array values, 
	 * computing at each position the maximum (positive sum) subarray ending at that position. 
	 * This subarray is either empty (in which case its sum is zero) or consists of one more element 
	 * than the maximum subarray ending at the previous position. 
	 * The algorithm only needs to keep track of the ending position because the implied starting position is 
	 * just after the last position at which the sum went negative; 
	 * a higher sum can always be found by dropping any negative-sum prefix. 
	 * Thus, the problem can be solved with the following code, 
	 * 
	 * expressed here in Python:
	 * the entire array consists of negative and positive numbers
	 * 
def max_subarray(A):
    max_ending_here = max_so_far = 0
    for x in A:
        max_ending_here = max(0, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far
​
the entire array consists of negative numbers
def max_subarray(A):
    max_ending_here = max_so_far = A[0]
    for x in A[1:]:
        max_ending_here = max(x, max_ending_here + x)
        max_so_far = max(max_so_far, max_ending_here)
    return max_so_far​
	 */

}

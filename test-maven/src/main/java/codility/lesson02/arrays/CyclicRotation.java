package codility.lesson02.arrays;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	private static Logger log = LoggerFactory.getLogger(CyclicRotation.class);

	/*
	 * Rotate an array to the right by a given number of steps.
	 */
	// 배열의 순서를 오른쪽으로 K번 만큼 시프트 시키는 문제
	// System.arraycopy 사용하면 됨.
	public static void main(String[] args) {
		int[] A = { 3, 8, 9, 7, 6, 8 };
		int K = 3;
		// return [9, 7, 6, 3, 8]
		//log.debug(Arrays.toString(mySolution(A, K)));
		log.debug("{}", solution2(A, K));
	}
	
	public static int[] solution1(int[] A, int K) {
		int[] result = new int[A.length];
		System.arraycopy(A, A.length - K, result, 0, K);
		System.arraycopy(A, 0, result, K, A.length - K);
		return result;
	}
	
	public static int[] solution2(int[] A, int K) {
		int[] result = new int[A.length];
		
		if (A.length == 0) {
	    	return result;
	    }
	    
	    K %= A.length;
	    log.debug("{}", K);
	    System.arraycopy(A, A.length - K, result, 0, K);  // 7, 6, 8 복사
	    System.arraycopy(A, 0, result, K, A.length - K);  // 3, 8, 9 복사
	    
	    return result;
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

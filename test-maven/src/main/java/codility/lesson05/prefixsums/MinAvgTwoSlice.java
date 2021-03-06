package codility.lesson05.prefixsums;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A 
 * (notice that the slice contains at least two elements). 
 * 
 * The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. 
 * To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
 * 
 * For example, array A such that:
 * A[0] = 4
 * A[1] = 2
 * A[2] = 2
 * A[3] = 5
 * A[4] = 1
 * A[5] = 5
 * A[6] = 8
 * contains the following example slices: 
 * slice (1, 2), whose average is (2 + 2) / 2 = 2;
 * slice (3, 4), whose average is (5 + 1) / 2 = 3;
 * slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
 * 
 * The goal is to find the starting position of a slice whose average is minimal.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * 
 * that, given a non-empty zero-indexed array A consisting of N integers, 
 * returns the starting position of the slice with the minimal average. 
 * If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
 * For example, given array A such that:
 * A[0] = 4
 * A[1] = 2
 * A[2] = 2
 * A[3] = 5
 * A[4] = 1
 * A[5] = 5
 * A[6] = 8
 * the function should return 1, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−10,000..10,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class MinAvgTwoSlice {

	private static Logger log = LoggerFactory.getLogger(MinAvgTwoSlice.class);

	/*
	 * *** length 2 or 3의 슬라이스에서 최소 평균이 나온다는 증명을 활용
	 * (https://codesays.com/2014/solution-to-min-avg-two-slice-by-codility/)
	 */	
	/*
	 * Find the minimal average of any slice containing at least two elements.
	 * 배열 A에서 (0, 1), (0, 2)... 처럼 배열을 쪼개서 최소평균이 나오는 인덱스를 찾는 문제.
	 */
	public static void main(String[] args) {
		
		int[] A = { 4, 2, 2, 5, 1, 5, 8 };
		log.debug("{}", mySolution(A));
		log.debug("{}", solution(A)); // return 1
		
		log.debug("{}", solution2(A)); // return 1
		log.debug("{}", solution3(A)); // return 1
	}
	
	// length 2 or 3의 슬라이스에서 최소 평균이 나온다는 증명을 활용.
	// N is an integer within the range [2..100,000];
	// each element of array A is an integer within the range [−10,000..10,000].
	public static int mySolution(int[] A) {
		final int N = A.length;

		int minIndex = 0;
		double minAvg = Double.MAX_VALUE;

		for (int i = 0; i < N - 1; i++) {
			double average = (A[i] + A[i + 1]) / 2.0;

			if (i < N - 2) {
				double threeSliceAvg = (A[i] + A[i + 1] + A[i + 2]) / 3.0;
				average = Math.min(average, threeSliceAvg);
			}

			if (average < minAvg) {
				minAvg = average;
				minIndex = i;
			}
		}
		log.debug("{}", minIndex);
		return minIndex;
	}
	
	// 최소평균의 최대 슬라이스 수는 2개 아니면 3개.
	// http://egohim.blog.me/220626427578	
	private static int solution3(int A[]) {
	    int idxMin = 0;
	    int sum = 0;
	    float avrMin = 1e6f;
	    float avr = 0.f;
	    for (int i = 1; i < A.length; ++i) {
	    	sum = 0;
	    	for (int j = 0; j <= i && j < 3; ++j) {
	    		sum = sum + A[i - j];
	    		if (j > 0) {
	    			avr = (float)sum / ((float)j + 1.0f);
	    			if (avr < avrMin) {
	    				avrMin = avr;
	    				idxMin = i - j;
	    			}
	    		}
	    	}
	    }
	    return idxMin;
	}
	
	
	// http://blog.naver.com/souljo2/220829578846
	// 안되는데 100점 맞았다 함..
	public static int solution2(int[] A) {
		
		int[] minArr = new int[A.length - 1];
		int[] avgSum = new int[A.length - 1];
		int[] avgVal = new int[A.length - 1];		

		for (int i = 0; i < A.length - 1; i++) {
			int sum = A[i] + A[i + 1];
			avgSum[i] = sum;
			avgVal[i] = sum / 2;
		}

		for (int i = 0; i < avgSum.length; i++) {
			for (int j = 2 + i; j < A.length; j++) {
				if (avgVal[i] > A[j]) {
					avgSum[i] += A[j];
					avgVal[i] = avgSum[i] / (j - i + 1);
				} else {
					break;
				}
			}
			minArr[i] = avgVal[i];
		}
		
		int tmp = minArr[0];
		int minIndex = 0;
		log.debug("{}", Arrays.toString(minArr));
		for (int i = 0; i < minArr.length; i++) {
			if (tmp > minArr[i]) {
				tmp = minArr[i];
				minIndex = i;
			}
		}
		return minIndex;
	}
	
	// 접근방법:
	// the slice contains at least two elements.
	// The average of a slice (P, Q)
	// 0 ≤ P < Q < N
	// 적어도 2개 이상의 Elements로 연속된 Slice의 최소 평균을 구한다.
	// 예를들어 Elements가 7개인경우 6 + 5 = 총 11개의 경우의 수에 최소 평균이 포함되어 있다.
	// (0, 1) (1, 2) (2, 3) (3, 4), (4, 5), (5, 6)
	// (0, 1, 2) (1, 2, 3), (2, 3, 4), (3, 4, 5), (4, 5, 6) ※각 숫자는 인덱스 의미
	// Slice (1, 2), whose average is (2 + 2) / 2 = 2
	// Slice (3, 4), whose average is (5 + 1) / 2 = 3
	// Slice (1, 4), 이것의 평균은 (1, 2), (3, 4) Slice의 최소 평균과 같거나 크다. 이경우 2.5으로 크다.
	// ※같지 않은 2개의 수 중 1개는 평균값보다 작을 수 밖에 없다.
	public static int solution(int[] A) {
		
		if (A == null) {
			return -1;
		} else if (A.length <= 2) {
			return 0;
		}

		int smallest_starting_position = 0;

		int sliceSum = 0;
		double sliceAvg = 0.0;
		double sliceAvgMin = Integer.MAX_VALUE;

		for (int i = 0; i < A.length; i++) {
			sliceSum = 0;
			sliceAvg = 0.0;
			if (i < A.length - 1) {
				sliceSum = A[i] + A[i + 1];
				sliceAvg = (double) sliceSum / 2;
				if (sliceAvgMin > sliceAvg) {
					sliceAvgMin = sliceAvg;
					smallest_starting_position = i;
				}
			}
			if (i < A.length - 2 && sliceAvg > A[i + 2]) {
				sliceSum = sliceSum + A[i + 2];
				sliceAvg = (double) sliceSum / 3;
				if (sliceAvgMin > sliceAvg) {
					sliceAvgMin = sliceAvg;
					smallest_starting_position = i;
				}
			}
		}
		return smallest_starting_position;
	}
}



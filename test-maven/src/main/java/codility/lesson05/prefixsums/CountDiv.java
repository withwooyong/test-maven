package codility.lesson05.prefixsums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Write a function:
 * class Solution { public int solution(int A, int B, int K); }
 * 
 * that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, 
 * i.e.: { i : A ≤ i ≤ B, i mod K = 0 }
 * 
 * For example, for A = 6, B = 11 and K = 2, your function should return 3, 
 * because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
 * 
 * Assume that:
 * A and B are integers within the range [0..2,000,000,000];
 * K is an integer within the range [1..2,000,000,000];
 * A ≤ B.
 * 
 * Complexity:
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class CountDiv {

	private static Logger log = LoggerFactory.getLogger(CountDiv.class);
	
	/*
	 * Compute number of integers divisible by k in range [a..b].
	 */
	public static void main(String[] args) {
		int A = 6;
		int B = 11;
		int K = 2;
		log.debug("{}", mySolution(A, B, K)); // 3
		log.debug("{}", solution(A, B, K)); // 3
		log.debug("{}", solution2(A, B, K)); // 3
		
	}
	
	// For example, for A = 6, B = 11 and K = 2, your function should return 3,
	// because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
	// 주어진 숫자 사이에 나누어지는 갯수 
	public static int mySolution(int A, int B, int K) {
		//i.e.: { i : A ≤ i ≤ B, i mod K = 0 }
		int count = 0;
		for (int i = A; i <= B; i++) {
			//log.debug("{}", i);
			if ((i % K) == 0) {
				count++;
			}
		}
		log.debug("{}", count);
		return count;
	}
	
	// O(1)
	private static int solution2(int A, int B, int K) {
		int offsetForLeftRange = 0;
		if (A % K == 0) {
			++offsetForLeftRange;
		}
		return (B / K) - (A / K) + offsetForLeftRange;
	}
	
	// 요넘 이해안됨. 무슨 공식인듯 한데.
	// O(1)
	public static int solution(int A, int B, int K) {
		
		int result = B / K + 1;
		if (A != 0) {
			result -= ((A - 1) / K + 1);
		}
		log.debug("{}", result);
		return result;
	}
}

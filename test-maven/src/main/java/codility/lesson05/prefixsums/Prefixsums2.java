package codility.lesson05.prefixsums;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://jungmonster.tistory.com/126
 * http://book.algospot.com/estimation.html
 * 배열의 연속된 구간 최대 합을 구하는 알고리즘
 */
public class Prefixsums2 {
	
	private static Logger log = LoggerFactory.getLogger(Prefixsums2.class);

	public static void main(String[] args) {
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		int k = 4; // 0 <= k
		int m = 6; // m < n (A.length)
		//log.debug("{}", solution(A, k, m));
		log.debug("{}", mySolution(A, k, m));

//		int[] B = { -10, -7, 5, -7, 10, 5, -2, 17, -25, 1 };
//		log.debug("{}", inefficientMaxSum(B)); // 30 O(n3)
//		log.debug("{}", betterMaxSum(B)); // 30 O(n2) 
//		log.debug("{}", fastMaxSum(B, 0, B.length-1)); // 30 분할정복 O(nlogn)
//		log.debug("{}", fastestMaxSum(B)); // 30 동적계획법 O(n)
	}
	
	// 이해못했음.
	private static int mySolution(int[] A, int k, int m) {
		log.debug("A={} k={} m={}", Arrays.toString(A), k, m);
		int result = 0; // 2
	    int maxLeftShift = Math.min(k, m); // 4
	    
	    for(int i = 0; i < maxLeftShift; i++) { // 4
	        int first = k - i; // 5	        
	        int rightShift = Math.min(k + (m - i * 2), (A.length - 1)); // 6
	        int last = Math.max(k, rightShift); // 7
	        
	        log.debug("first={} maxLeftShift={} rightShift={} last={}", first, maxLeftShift, rightShift, last);
	        
	        int sum = 0;
	        for(int j = first; j <= last; ++j) { // 8
	        	sum += A[j];
	        }
	        result = Math.max(result, sum); // 9
	    }
	    /*
	     * int ret = Integer.MIN_VALUE;
	     * int psum = 0;
	     * for (int i = 0; i < A.length; ++i) {
	     * 		psum = Math.max(psum, 0) + A[i];
	     * 		ret = Math.max(psum, ret);
	     * }
	     */
	    return result;
	}
	
	// 구간합.
	// 나열된 수열의 개수 n개
	// 몇번 범위 합을 구해 볼지의 개수 m개
	// sum[i] = 1~i 의 합 
	// 구간 x 부터 y 까지의 합 = sum[y] - sum[x-1]
	private static int solution(int[] A, int k, int m) {
		int result = 0; // 2
	    int maxLeftShift = Math.min(k, m); // 4
	    log.debug("{}", maxLeftShift);
	    for(int curLeftShift = 0; curLeftShift < maxLeftShift; curLeftShift++) { // 4
	        int first = k - curLeftShift; // 5
	        //log.debug("{}", first);
	        int rightShift = Math.min(k + (m - curLeftShift * 2), (A.length - 1)); // 6
	        int last = Math.max(k, rightShift); // 7
	 
	        int tentative = 0;
	        for(int i = first; i <= last; ++i) { // 8
	            tentative += A[i];
	        }
	        result = Math.max(result, tentative); // 9
	    }
	    return result;
	}	

	// O(N3) 배열의 연속된 구간 최대 합을 구하는 알고리즘
	public static int inefficientMaxSum(int[] B) {
		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < B.length; i++) {
			for (int j = i; j < B.length; j++) {
				int sum = 0;
				for (int k = i; k < j; k++) {
					sum += B[k];
					ret = Math.max(ret, sum);
				}
			}
		}
		return ret;
	}

	// O(N2) 배열의 연속된 구간 최대 합을 구하는 알고리즘
	public static int betterMaxSum(int[] B) {
		int ret = Integer.MIN_VALUE;
		for (int i = 0; i < B.length; i++) {
			int sum = 0;
			for (int j = i; j < B.length; j++) {
				sum += B[j];
				ret = Math.max(ret, sum);
			}
		}

		return ret;
	}

	// 시간 복잡도: O(nlgn) 배열의 연속된 구간 최대 합을 구하는 알고리즘
	public static int fastMaxSum(int[] B, int lo, int hi) {
		
		if (lo == hi) { // 구간의 길이가 1일 경우
			return B[lo];
		}
		int mid = (lo + hi) / 2; // 두 구간으로 나눈다.
		
		log.debug("B={} lo={} mid={} hi={}", Arrays.toString(B), lo, mid, hi);
		
		int left = Integer.MIN_VALUE;
		int right = Integer.MIN_VALUE;
		int sum = 0;

		for (int i = mid; i >= lo; --i) { // 왼쪽구간
			sum += B[i];
			left = Math.max(left, sum);
		}

		sum = 0;
		for (int j = mid + 1; j < hi; ++j) { // 오른쪽구간
			sum += B[j];
			right = Math.max(right, sum);
		}

		// 최대 구간이 두 조각중 하나에만 속해 있는 경우의 답을 재귀호출로 찾는다.
		int single = Math.max(fastMaxSum(B, lo, mid), fastMaxSum(B, mid + 1, hi));

		return Math.max(left + right, single);
	}
	
	// 최대 연속 부분 구간 합 문제를 푸는 동적 계획법 알고리즘
	// A[] 의 연속된 부분 구간의 최대 합을 구한다. 시간 복잡도: O(n)
	public static int fastestMaxSum(int[] A) {
		int N = A.length;
		int ret = Integer.MIN_VALUE;
		int psum = 0;
		for (int i = 0; i < N; ++i) {
			psum = Math.max(psum, 0) + A[i];
			ret = Math.max(psum, ret);
		}
		return ret;
	}
}

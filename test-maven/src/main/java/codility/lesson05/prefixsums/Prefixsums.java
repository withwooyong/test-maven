package codility.lesson05.prefixsums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Problem: You are given a non-empty, zero-indexed array A of n (1 <= n <= 100 000) integers a0, a1, . . . , an−1 (0 <= ai <= 1 000). 
 * This array represents number of mushrooms growing on the consecutive spots along a road. 
 * You are also given integers k and m (0 <= k,m < n).
 * A mushroom picker is at spot number k on the road and should perform m moves. 
 * In one move she moves to an adjacent spot. 
 * She collects all the mushrooms growing on spots she visits. 
 * The goal is to calculate the maximum number of mushrooms that the mushroom picker can collect in m moves.
 * 
 * For example, consider array A such that:
 * 2 3 7 5 1 3 9
 * The mushroom picker starts at spot k = 4 and should perform m = 6 moves. 
 * She might move to spots 3, 2, 3, 4, 5, 6 and thereby collect 1+5+7+3+9 = 25 mushrooms. 
 * This is the maximal number of mushrooms she can collect.
 * 
 * @author wooyong
 *
 */
public class Prefixsums {
	
	private static Logger log = LoggerFactory.getLogger(Prefixsums.class);

	// pdf 문제 이해안됨.
	public static void main(String[] args) {
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		int k = 4; // 0 <= k
		int m = 6; // m < n (A.length)
		solution(A, k, m);
		//mySolution(A, k, m);
		test(A, k, m);
		test2(A, k, m);
	}
	
	// 나열된 수열의 개수 n개
	// 몇번 범위 합을 구해 볼지의 개수 m개
	// sum[i] = 1~i 의 합 
	// 구간 x 부터 y 까지의 합 = sum[y] - sum[x-1]
	private static void test(int[] A, int k, int m) {
		
//		int start = 3;
//		int end = 5;
		int sum = 0;
		//int m = 2; // 몇번 범위 합을 구해 볼지의 개수 m개
		for (int i = 0; i < m; i++) { // 몇번째부터 몇번째까지 더할지  
			for (int j = k - 1; j < A.length - k; j++) {
				//log.debug("{}", A[j]);
				sum = sum + A[j];
			}
			log.debug("{}", sum);
			sum = 0;
		}
	}
	
	private static void test2(int[] A, int k, int m) {
		int ans;
		//int n = 6; // 나열된 수열의 개수 n개
		//int m = 2; // 몇번 범위 합을 구해 볼지의 개수 m개
		int start = 1; // 1 2
		int end = 3; // 3 5
		int[] sum = new int[A.length]; // a[0]~a[n]까지 합을 넣어두는 배열

		for (int i = 0; i < A.length; i++) {
			if (i == 0) {
				sum[i] = A[i];
			} else {
				sum[i] = sum[i - 1] + A[i]; // a[0]부터 a[i-1]의 합을 넣어둔다.
			}
		}
		
		for (int i = 0; i < m; i++) { // 몇번째부터 몇번째까지 더할지
			if (start == 1) {
				ans = sum[end - 1];
				log.debug("{}", ans);
			} else {
				// sum[end] - sum[start-1]
				ans = sum[end - 1] - sum[start - 2]; /// #### 중요
				log.debug("{}", ans);
			}
			
		}
	}
	
	private static void solution(int[] A, int k, int m) {
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
	    log.debug("{}", result);
	}	
	
}

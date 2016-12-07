package codility.lesson05.prefixsums;

import java.util.Arrays;

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
 *http://wcipeg.com/wiki/Prefix_sum_array_and_difference_array
 */
public class Prefixsums {
	
	private static Logger log = LoggerFactory.getLogger(Prefixsums.class);

	// pdf 문제 이해안됨.
	public static void main(String[] args) {
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		//int[] A = { 3, 2, 3, 4, 5, 6 };
		int k = 4; // 0 <= k
		int m = 6; // m < n (A.length)
		log.debug("{}", solution(A, k, m));
//		mySolution(A, k, m);
//		test(A, k, m);
//		test2(A, k, m);
		prefixSum(A, k, m);
		prefixSumEx(A, m);
		prefixSumSuccess(A);
		log.debug("{}", maximumMushrooms(A, k, m));
	}
	
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
	
	// Time : O(n ^ 2)
    // Space: O(n * m)
	private static int maximumMushrooms(int[] A, int k, int m) {
		int maxSum = 0;
		for (int p = 0; p <= m; p++) {
			boolean[] visited = new boolean[A.length];
			int currentSum = A[k];
			visited[k] = true;
			int leftMoves = p;

			for (int i = k, count = 0; (i - 1) >= 0 && count < leftMoves; i--, count++) {
				currentSum += A[i - 1];
				visited[i - 1] = true;
			}

			int rightMoves = m - p;
			for (int i = k - p, count = 0; (i + 1) < A.length && count < rightMoves; i++, count++) {
				if (!visited[i + 1]) {
					currentSum += A[i + 1];
				}
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
		}
		return maxSum;
	}	
	
	// 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
	// 첫째 줄에 수의 개수 N (1 ≤ N ≤ 100,000), 합을 구해야 하는 횟수 M (1 ≤ M ≤ 100,000)이 주어진다. 둘째 줄에는 N개의 수가 주어진다. 수는 1,000보다 작거나 같은 자연수이다. 셋째 줄부터 M개의 줄에는 합을 구해야 하는 구간 i와 j가 주어진다.
	// 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
	// 5 3			12
	// 5 4 3 2 1	9
	// 1 3			1
	// 2 4			
	// 5 5
	
	// #####################################################
	// prefix sum 설명
	// sum[i] = 1~i 원소의 합이라고 할때
	// x 부터 y 까지의 합을 sum[y] - sum[x-1] 로 간단하게 구할 수 있다.
	// 중간에 수를 바꿀 일이 없고 구간합을 구할 경우
	// #####################################################
	private static void prefixSumSuccess(int[] A) {
		
		int[] sum = new int[A.length + 1];
		int x = 4;
		int y = 6;
		
		sum[0] = 0;
		for (int i = 0; i < A.length; i++) {
			sum[i+1] = A[i] + sum[i];
		}
		log.debug("{}", Arrays.toString(A));
		log.debug("{}", Arrays.toString(sum));
		log.debug("x={} y={} (sum[y]){}-(sum[x-1]){}={}", x, y, sum[y], sum[x-1], sum[y] - sum[x-1]);
	}
	
	/*
	 * For example, consider array A such that:
	 * int[] A = { 2, 3, 7, 5, 1, 3, 9 }
	 * The mushroom picker starts at spot k = 4 and should perform m = 6 moves.
	 * She might move to spots 3, 2, 3, 4, 5, 6 and thereby collect 1+5+7+3+9 = 25 mushrooms.
	 */
	private static void prefixSum(int[] A, int k, int m) {
		int[] prefixSum = new int[A.length + 1];
		
		prefixSum[0] = 0;
		for (int i = 0; i < A.length; i++) {
			prefixSum[i + 1] = prefixSum[i] + A[i];
		}
		log.debug("{}", Arrays.toString(A));
		// ans = sum[end - 1] - sum[start - 2]; /// #### 중요
		// public int sumRange(int i, int j) { return prefixSum[j + 1] - prefixSum[i];
	
		log.debug("{} {}", prefixSum[m] - prefixSum[k-1], Arrays.toString(prefixSum));
	}
	
private static void prefixSumEx(int[] A, int n) {
		
		
	}
	
	
	// 구간합.
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
	

}

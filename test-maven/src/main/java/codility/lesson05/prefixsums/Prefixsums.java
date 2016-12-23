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
//		log.debug("solution={}", solution(A, k, m));
		log.debug("prefixSum={}", prefixSum(A));
//		log.debug("maximumMushrooms={}", maximumMushrooms(A, k, m));
	}
	
	// 구간합.
	// 나열된 수열의 개수 n개
	// 몇번 범위 합을 구해 볼지의 개수 m개
	// sum[i] = 1~i 의 합 
	// 구간 x 부터 y 까지의 합 = sum[y] - sum[x-1]
	// spot number k 4
	// m moves 6
	private static int solution(int[] A, int k, int m) {
		int result = 0; // 2
		int maxLeftShift = Math.min(k, m); // 4
		log.debug("{}", maxLeftShift);
		for (int curLeftShift = 0; curLeftShift < maxLeftShift; curLeftShift++) { // 4
			int first = k - curLeftShift; // 5
			// log.debug("{}", first);
			int rightShift = Math.min(k + (m - curLeftShift * 2), (A.length - 1)); // 6
			int last = Math.max(k, rightShift); // 7

			int tentative = 0;
			for (int i = first; i <= last; ++i) { // 8
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
	
	// #####################################################
	// prefix sum 설명
	// sum[i] = 1~i 원소의 합이라고 할때
	// x 부터 y 까지의 합을 sum[y] - sum[x-1] 로 간단하게 구할 수 있다.
	// 중간에 수를 바꿀 일이 없고 구간합을 구할 경우
	// #####################################################
	// 구간 x 부터 y 까지의 합 = sum[y] - sum[x-1]
	private static int prefixSum(int[] A) {
		int[] sum = new int[A.length]; // 구간합 배열 선언
		int x = 4; // 4번째부터
		int y = 6; // 6번째까지 구간합

		sum[0] = A[0];
		for (int i = 1; i < A.length; i++) {
			sum[i] = sum[i - 1] + A[i];
		}
		log.debug("{}", Arrays.toString(A));
		log.debug("{}", Arrays.toString(sum));
		log.debug("sum[y]={} sum[x-1]={}", sum[y], sum[x - 1]);
		return sum[y] - sum[x - 1];
	}
}

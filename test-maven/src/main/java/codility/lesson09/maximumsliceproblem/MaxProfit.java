package codility.lesson09.maximumsliceproblem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MaxProfit
 * Given a log of stock prices compute the maximum possible earning
 * 
 * A zero-indexed array A consisting of N integers is given. 
 * It contains daily prices of a stock share for a period of N consecutive days. 
 * If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then the profit of 
 * such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. 
 * Otherwise, the transaction brings loss of A[P] − A[Q].
 * 
 * For example, consider the following array A consisting of six elements such that:
 * A[0] = 23171
 * A[1] = 21011
 * A[2] = 21123
 * A[3] = 21366
 * A[4] = 21013
 * A[5] = 21367
 * If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur 
 * because A[2] − A[0] = 21123 − 23171 = −2048. 
 * If a share was bought on day 4 and sold on day 5, 
 * a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. 
 * Maximum possible profit was 356. 
 * It would occur if a share was bought on day 1 and sold on day 5.
 * 
 * Write a function,
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A consisting of N integers containing daily prices of a stock share 
 * for a period of N consecutive days, returns the maximum possible profit from one transaction during this period. 
 * The function should return 0 if it was impossible to gain any profit.
 * 
 * For example, given array A consisting of six elements such that:
 * A[0] = 23171
 * A[1] = 21011
 * A[2] = 21123
 * A[3] = 21366
 * A[4] = 21013
 * A[5] = 21367
 * the function should return 356, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [0..400,000];
 * each element of array A is an integer within the range [0..200,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class MaxProfit {
	
	private static Logger log = LoggerFactory.getLogger(MaxProfit.class);
	
	// return 356
	public static void main(String[] args) {
		int[] A = new int[] { 23171, 21011, 21123, 21366, 21013, 21367 };
		log.debug("{}", solution(A));
	}
	
	public static int solution(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		int[] array = new int[A.length];
		array[0] = 0;
		for (int i = 1; i < A.length; i++) {
			array[i] = A[i] - A[i - 1];
		}
		return goldenMaxSlice(array);
	}

	public static int goldenMaxSlice(int[] A) {
		int arrMax = Arrays.stream(A).max().getAsInt();
		log.debug("{} {}", arrMax, Arrays.toString(A));
		if (arrMax < 0) {
			return arrMax;
		}
		int maxEnding = 0;
		int maxSlice = 0;
		for (int i = 0; i < A.length; i++) {
			maxEnding = (maxEnding + A[i]) > 0 ? (maxEnding + A[i]) : 0;
			maxSlice = maxSlice > maxEnding ? maxSlice : maxEnding;
		}
		return maxSlice;
	}
	
	/*
	 * http://blog.naver.com/wideeyed/220845529296
	 * 접근방법:
	 * NXN으로 모두 경우의 수를 탐색할 수도 있지만 중복되거나 생략가능한 포인트가 존재한다.
	 * 순방향(왼쪽에서 오른쪽)으로 진행하면서 이익최대값을 찾는다.
	 * 매도시점값(B) - 매수시점값(A) 계산할 때
	 * 진행시점까지 발견된 최소매수값(A)보다 큰 매수값들은 절대 최대 이익이 될 수 없다.
	 * 따라서 해당 부분의 연산을 생략하면 O(N)으로 처리 가능하다.
	 * 즉, 최소매수값만을 가지고 차례대로 진행하면서 매도시점값(B) 차이만 계산하여 최대값을 얻는다.
	 * ※ 단, 최소매수값이 더 낮은 것이 나타나면 해당 최소매수값(A)은 갱신되고 이후시점부터 사용된다.
	 */
	public int solution2(int[] A) {
		
		if (A == null || A.length <= 1) {
			return 0;
		}

		int min_forward_direction_price = 0;
		int max_profit = 0;

		min_forward_direction_price = A[0];
		for (int i = 1; i < A.length; i++) {
			min_forward_direction_price = Math.min(min_forward_direction_price, A[i]);
			max_profit = Math.max(max_profit, A[i] - min_forward_direction_price);
		}
		return max_profit;
	}

}

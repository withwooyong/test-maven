package algorithm.algosrc.problems.P2357;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2357 {
	
	private static Logger log = LoggerFactory.getLogger(P2357.class);
	private static int INT_MAX = 2147483647;
	/*
	 * 4
	 * 1
	 * 7
	 * 1024
	 * 940802360
	 */
	public static void main(String[] args) {
		int k = 4;
		int ret = solve(k);
		log.debug("ret={}", ret);
		log.debug("{}", ret % 2);
		log.debug("{}", ret % 3);
		log.debug("{}", ret % 5);
		log.debug("{}", ret % 7);
		// div == 1

	}

	// [1,n] 범위에서 2, 3, 5, 7 중 정확히 하나로만 나누어 떨어지는 수의 개수를 반환한다
	private static int count(int n) {
		return (n / 2 + n / 3 + n / 5 + n / 7) - 2 * (n / 6 + n / 10 + n / 14 + n / 15 + n / 21 + n / 35)
				+ 3 * (n / 30 + n / 42 + n / 70 + n / 105) - 4 * (n / 210);
	}

	// 2, 3, 5, 7 중 정확히 하나로만 나누어 떨어지는 수 중 k번째의 수를 반환한다
	private static int solve(int k) {
		// 루프 불변 조건: count(lo) < k <= count(hi)
		// 오버플로우를 방지하기 위해 64비트 정수를 사용한다
		long lo = 0;
		long hi = INT_MAX;
		while (lo + 1 < hi) {
			long mid = (lo + hi) / 2;
			if (count((int)mid) < k) {
				lo = mid;
			} else {
				hi = mid;
			}
		}
		return (int)hi;
	}
}

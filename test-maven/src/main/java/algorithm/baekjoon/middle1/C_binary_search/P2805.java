package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2805 {

	private static Logger log = LoggerFactory.getLogger(P2805.class);

	/*
	 * 나무자르기
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long m = sc.nextLong();
		long[] a = new long[n];
		long left = 0;
		long right = 0;
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextLong();
			right = Math.max(right, a[i]);
		}
		long ans = 0;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (check(a, mid, m)) {
				ans = Math.max(ans, mid);
				left = mid + 1; // 왼쪽  +1
			} else {
				right = mid - 1; // 가장 오른쪽 -1
			}
		}
		System.out.println(ans);
		sc.close();
	}
	
	public static boolean check(long[] a, long mid, long m) {
		long sum = 0;
		for (int i = 0; i < a.length; i++) {
			if (a[i] - mid > 0) {
				sum += a[i] - mid;
			}
		}
		return sum >= m;
	}
}

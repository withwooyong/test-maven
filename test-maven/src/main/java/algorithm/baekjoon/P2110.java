package algorithm.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2110 {

	private static Logger log = LoggerFactory.getLogger(P2110.class);
	/*
	 * 공유기 설치
	 */
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int c = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		int ans = 1;
		int left = 1;
		int right = a[n - 1] - a[0];
		while (left <= right) {
			int mid = (left + right) / 2;
			if (possible(a, c, mid)) {
				ans = Math.max(ans, mid);
				left = mid + 1; // 왼쪽 + 1
			} else {
				right = mid - 1; // 가장 오른쪽 -1
			}
		}
		System.out.println(ans);
		sc.close();
	}

	public static boolean possible(int[] a, int c, int mid) {
		int cnt = 1;
		int last = a[0];
		for (int house : a) {
			if (house - last >= mid) {
				cnt += 1;
				last = house;
			}
		}
		return cnt >= c;
	}
}

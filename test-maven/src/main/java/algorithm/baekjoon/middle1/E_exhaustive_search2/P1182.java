package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1182 {

	private static Logger log = LoggerFactory.getLogger(P1182.class);

	/*
	 * 부분집합의 합
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 4853 2191 1347 42.789%
	 * 문제
	 * N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤20, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절대값은 100,000을
	 * 넘지 않는다. 같은 수가 여러번 주어질 수도 있다.
	 * 
	 * 출력
	 * 첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 5 0
	 * -7 -3 -2 5 8
	 * 예제 출력 복사
	 * 1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/bddda372acf45d698817
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int s = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		int ans = 0;
		for (int i = 1; i < (1 << n); i++) {
			int sum = 0;
			for (int k = 0; k < n; k++) {
				if ((i & (1 << k)) != 0) {
					sum += a[k];
				}
			}
			if (sum == s) {
				ans += 1;
			}
		}
		System.out.println(ans);
		sc.close();
	}

	/*
	 * https://gist.github.com/Baekjoon/923eddd3d8d3bef43372433c83afb6cf
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		int ans = go(a, m, 0, 0);
		if (m == 0) {
			ans -= 1;
		}
		System.out.println(ans);
		sc.close();
	}

	public static int go(int[] a, int m, int i, int sum) {
		if (i == a.length) {
			if (sum == m) {
				return 1;
			} else {
				return 0;
			}
		}
		return go(a, m, i + 1, sum + a[i]) + go(a, m, i + 1, sum);
	}
}

package algorithm.baekjoon.middle1.D_exhaustive_search1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10972 {

	private static Logger log = LoggerFactory.getLogger(P10972.class);

	/*
	 * 다음 순열
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 256 MB 896 444 361 54.204%
	 * 문제
	 * 1부터 N까지의 수로 이루어진 순열이 있다. 이 때, 사전순으로 다음에 오는 순열을 구하는 프로그램을 작성하시오.
	 * 
	 * 사전 순으로 가장 앞서는 순열은 오름차순으로 이루어진 순열이고, 가장 마지막에 오는 순열은 내림차순으로 이루어진 순열이다.
	 * 
	 * N = 3인 경우에 사전순으로 순열을 나열하면 다음과 같다.
	 * 
	 * 1, 2, 3
	 * 1, 3, 2
	 * 2, 1, 3
	 * 2, 3, 1
	 * 3, 1, 2
	 * 3, 2, 1
	 * 입력
	 * 첫째 줄에 N(1 ≤ N ≤ 10,000)이 주어진다. 둘째 줄에 순열이 주어진다.
	 * 
	 * 출력
	 * 첫째 줄에 입력으로 주어진 순열의 다음에 오는 순열을 출력한다. 만약, 사전순으로 마지막에 오는 순열인 경우에는 -1을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 4
	 * 1 2 3 4
	 * 예제 출력 복사
	 * 1 2 4 3
	 * 예제 입력 2 복사
	 * 5
	 * 5 4 3 2 1
	 * 예제 출력 2 복사
	 * -1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/c307fc69373a74a730c0
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		if (next_permutation(a)) {
			for (int i = 0; i < n; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		} else {
			System.out.println("-1");
		}
		sc.close();
	}

	public static boolean next_permutation(int[] a) {
		int i = a.length - 1;
		while (i > 0 && a[i - 1] >= a[i]) {
			i -= 1;
		}

		// 마지막 순열
		if (i <= 0) {
			return false;
		}

		int j = a.length - 1;
		while (a[j] <= a[i - 1]) {
			j -= 1;
		}

		int temp = a[i - 1];
		a[i - 1] = a[j];
		a[j] = temp;

		j = a.length - 1;
		while (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			i += 1;
			j -= 1;
		}
		return true;
	}
}

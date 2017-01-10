package algorithm.baekjoon.middle1.D_exhaustive_search1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10974 {

	private static Logger log = LoggerFactory.getLogger(P10974.class);

	/*
	 * 모든 순열
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 256 MB 1909 1050 776 58.258%
	 * 문제
	 * N이 주어졌을 때, 1부터 N까지의 수로 이루어진 순열을 사전순으로 출력하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 N(1 ≤ N ≤ 8)이 주어진다.
	 * 
	 * 출력
	 * 첫째 줄부터 N!개의 줄에 걸쳐서 모든 순열을 사전순으로 출력한다.
	 * 
	 * 예제 입력 복사
	 * 3
	 * 예제 출력 복사
	 * 1 2 3
	 * 1 3 2
	 * 2 1 3
	 * 2 3 1
	 * 3 1 2
	 * 3 2 1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/bb4679d85dd726fd3456
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = i + 1;
		}
		do {
			for (int i = 0; i < n; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		} while (next_permutation(a));

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

package algorithm.baekjoon;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11727 {

	private static Logger log = LoggerFactory.getLogger(P11727.class);

	/* 
	 * 2×n 직사각형을 2×1과 2×2 타일로 채우는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 아래 그림은 2×17 직사각형을 채운 한가지 예이다.
	 * 첫째 줄에 n이 주어진다. (1 ≤ n ≤ 1,000)
	 * 첫째 줄에 2×n 크기의 직사각형을 채우는 방법의 수를 10,007로 나눈 나머지를 출력한다.
	 * 8
	 * 
	 * 171
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int N = 8;
		int[] A = new int[N + 1];
		A[0] = 1;
		A[1] = 1;

		for (int i = 2; i <= N; i++) {
			A[i] = (A[i - 1] + A[i - 2] * 2) % 10007; // * 2 했음
		}

		log.debug("{}", Arrays.toString(A));
		log.debug("{}", A[N] % 10007);
	}
}

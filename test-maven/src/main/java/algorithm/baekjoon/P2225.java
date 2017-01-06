package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2225 {

	private static Logger log = LoggerFactory.getLogger(P2225.class);

	/*
	 * 합분해
	 * 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수를 구하는 프로그램을 작성하시오.
	 * 덧셈의 순서가 바뀐 경우는 다른 경우로 센다(1+2와 2+1은 서로 다른 경우). 또한 한 개의 수를 여러 번 쓸 수도 있다.
	 * 
	 * 첫째 줄에 두 정수 N(1≤N≤200), K(1≤K≤200)가 주어진다.
	 * 첫째 줄에 답을 1,000,000,000으로 나눈 나머지를 출력한다.
	 * 20 2
	 * 21
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 5를 숫자 4개로 만드는 방법
	 * 5를 만들려면 (0, 5), (1, 4), (2, 3), (3, 2), (4, 1), (5, 0) 의 6가지가 있다.
	 * (0을 숫자 2개로 만드는 방법 + 5를 숫자2개로 만드는 방법) + 
	 * (1을 숫자 2개로 만드는 방법 + 4를 숫자2개로 만드는 방법) +
	 * (2을 숫자 2개로 만드는 방법 + 3를 숫자2개로 만드는 방법) +
	 * (3을 숫자 2개로 만드는 방법 + 2를 숫자2개로 만드는 방법) +
	 * (4을 숫자 2개로 만드는 방법 + 1를 숫자2개로 만드는 방법) +
	 * (5을 숫자 2개로 만드는 방법 + 0를 숫자2개로 만드는 방법)
	 * 의 총합이 5를 숫자 4개로 만드는 방법이다.
	 * 못풀었음.
	 */
	private static void solution() {
		// 0부터 N까지의 정수 K개를 더해서 그 합이 N이 되는 경우의 수
		Scanner sc = new Scanner(System.in);
//		int N = sc.nextInt(); // 5
//		int K = sc.nextInt(); // 4
		int N = 5; // 숫자
		int K = 4; // 개
		int mod = 1000000000;
		int[][] Dp = new int[K + 1][N + 2];

		for (int i = 2; i <= K; i++) {
			for (int j = 0; j <= N; j++) {
				Dp[i][j] = (Dp[i][j] + Dp[i - 1][j + 1]) % mod;
			}
		}
		System.out.println(Dp[K][N] % mod);
		sc.close();
	}
}

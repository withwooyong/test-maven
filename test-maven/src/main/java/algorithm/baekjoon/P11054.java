package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11054 {

	private static Logger log = LoggerFactory.getLogger(P11054.class);

	/*
	 * 바이토닉 수열
	 * 수열 S가 어떤 수 Sk를 기준으로 S1 < S2 < ... Sk-1 < Sk > Sk+1 > ... SN-1 > SN을 만족한다면, 
	 * 그 수열을 바이토닉 수열이라고 한다.
	 * 예를 들어, {10, 20, 30, 25, 20}과 {10, 20, 30, 40}, {50, 40, 25, 10} 은 바이토닉 수열이지만,  
	 * {1, 2, 3, 2, 1, 2, 3, 2, 1}과 {10, 20, 30, 40, 20, 30} 은 바이토닉 수열이 아니다.
	 * 수열 A가 주어졌을 때, 그 수열의 부분 수열 중 바이토닉 수열이면서 가장 긴 수열의 길이를 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 수열 A의 크기 N이 주어지고, 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ N ≤ 1,000, 1 ≤ Ai ≤ 1,000)
	 * 첫째 줄에 수열 A의 부분 수열 중에서 가장 긴 바이토닉 수열의 길이를 출력한다.
	 * 예제의 경우 {1 5 2 1 4 3 4 5 2 1}이 가장 긴 바이토닉 부분 수열이다.
	 * 
	 * 10
	 * 1 5 2 1 4 3 4 5 2 1
	 * 
	 * 7
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 바이토닉 수열
	 * 1. 이건 '가장 긴 증가하는 부분 수열'을 두 번 사용해서 풀면 된다.
	 * 2. -> 오른쪽 방향으로 진행을 한번 하고 <- 왼쪽 방향으로 진행을 하여서 그 두개의 Dp 배열의 합이 최대가 되는 곳이 
	 * 가장 긴 바이토닉 수열이 되는 것이다.
	 * 3. 하지만 자기자신을 둘다 가지고 있기 때문에 -1값을 해야한다.
	 * 출처: http://wootool.tistory.com/99 [우투리와툴툴]
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] A = new int[N + 1];
		int[][] Dp = new int[2][N + 1];
		
		int max = 0;

		for (int i = 1; i <= N; i++)
			A[i] = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			int maxA = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) {
					if (maxA < Dp[0][j])
						maxA = Dp[0][j];
				}
			}
			Dp[0][i] = maxA + 1;
		}

		for (int i = N; i >= 1; i--) {
			int maxA = 0;
			for (int j = N; j > i; j--) {
				if (A[i] > A[j]) {
					if (maxA < Dp[1][j])
						maxA = Dp[1][j];
				}
			}
			if (Dp[1][i] < maxA + 1)
				Dp[1][i] = maxA + 1;
		}

		for (int i = 1; i <= N; i++) {
			if (max < Dp[0][i] + Dp[1][i])
				max = Dp[0][i] + Dp[1][i];
		}
		System.out.println(max - 1);
		sc.close();
	}
}

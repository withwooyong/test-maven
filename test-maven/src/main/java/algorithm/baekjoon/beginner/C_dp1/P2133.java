package algorithm.baekjoon.beginner.C_dp1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2133 {

	private static Logger log = LoggerFactory.getLogger(P2133.class);

	/*
	 * 타일 채우기
	 * 3×N 크기의 벽을 2×1, 1×2 크기의 타일로 채우는 경우의 수를 구해보자.
	 * 첫째 줄에 N(1 ≤ N ≤ 30)이 주어진다.
	 * 첫째 줄에 경우의 수를 출력한다.
	 * 2
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/fe102cef6f7b7ebecdedef00ec811a7c
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] d = new long[n + 1];
		d[0] = 1;
		for (int i = 2; i <= n; i += 2) {
			d[i] = d[i - 2] * 3;
			for (int j = i - 4; j >= 0; j -= 2) {
				d[i] += d[j] * 2;
			}
		}
		System.out.println(d[n]);
		sc.close();
	}

	/*
	 * 1.  타일이 만들어 질 수 있는 경우는 짝 수 일때 뿐이다.
	 * 2.  타일이 추가될 때의 경우를 생각해 보자. 예를 들어
	 * -2 개일 때 : 3개이다.
	 * -4 개일 때 : 앞이 2개로 만들 수 있는 경우 * 4-2개로 만들 수 있는 경우 + 4개로만 만들 수 있는 경우 * 4-4개로 만들 수 있는 경우
	 * -6 개일 때 : 앞이 2개로 만들 수 있는 경우 * 6-2개로 만들 수 있는 경우 + 앞이 4개로만 만들 수 있는 경우 *  6-4개로 만들 수 있는 경우 + 앞이 6개로만 만들 수 있는 경우 * 6-6개로 만들 수 있는 경우
	 * 3. 위에 껄로 보면 과거의 것으로 새롭게 만드는 것과 자기 자신의 숫자만큼 유일하게 만들 수 있는 경우를 더하면 되는 것이다.
	 * 4. 그렇게 진행하면 마지막에 K에 들어오는 값이 만들 수 있는 타일의 수가 된다.
	 * 출처: http://wootool.tistory.com/84 [우투리와툴툴]
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Dp = new int[N + 2];
		int[] result = new int[N + 2]; // N(1 ≤ N ≤ 30)
		Dp[0] = 1;
		result[0] = 1;
		Dp[2] = 3;
		
		for (int i = 4; i <= N; i += 2)
			Dp[i] = 2;

		for (int i = 2; i <= N; i += 2) {
			for (int j = 2; j <= i; j += 2) {
				result[i] += Dp[j] * result[i - j];
			}
		}

		System.out.println(result[N]);
		sc.close();
	}
}

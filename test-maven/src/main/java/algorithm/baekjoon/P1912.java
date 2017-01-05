package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1912 {

	private static Logger log = LoggerFactory.getLogger(P1912.class);

	/*
	 * 연속합
	 * n개의 정수로 이루어진 임의의 수열이 주어진다. 
	 * 우리는 이 중 연속된 몇 개의 숫자를 선택해서 구할 수 있는 합 중 가장 큰 합을 구하려고 한다. 
	 * 단, 숫자는 한 개 이상 선택해야 한다.
	 * 예를 들어서 10, -4, 3, 1, 5, 6, -35, 12, 21, -1 이라는 수열이 주어졌다고 하자. 
	 * 여기서 정답은 12+21인 33이 정답이 된다.
	 * 
	 * 첫째 줄에 정수 n(1≤n≤100,000)이 주어지고 둘째 줄에는 n개의 정수로 이루어진 수열이 주어진다. 
	 * 수는 -1,000보다 크거나 같고, 1,000보다 작거나 같은 정수이다.
	 * 첫째 줄에 답을 출력한다.
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 연속합
	 * 1. 이 문제는 최대값을 구하기 위해서는 현재의 자신의 값을 선택하는 경우의 수가 두가지이다.
	 * 2. 첫 번째 경우는 이전의 값을 더한것과 지금의 값을 더한 값이 최대일 때랑 
	 *    이전의 값을 더하지 않고 현재의 값만이 최대일 때 이 두가지 경우만 따지면 된다.
	 * 출처: http://wootool.tistory.com/100 [우투리와툴툴]
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] number = new int[N + 1];
		int[] Dp = new int[N + 1];
		int max;

		for (int i = 1; i <= N; i++)
			number[i] = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			if (Dp[i - 1] + number[i] > number[i]) {
				Dp[i] = Dp[i - 1] + number[i];
			} else {
				Dp[i] = number[i];
			}
		}
		max = Dp[1];
		for (int i = 2; i <= N; i++)
			if (max < Dp[i])
				max = Dp[i];

		System.out.println(max);
		sc.close();
	}
}

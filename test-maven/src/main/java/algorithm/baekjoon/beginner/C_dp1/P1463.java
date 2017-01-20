package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1463 {

	private static Logger log = LoggerFactory.getLogger(P1463.class);

	/*
	 * 1로 만들기
	 * 정수 X에 사용할 수 있는 연산은 다음과 같이 세 가지 이다.
	 * X가 3으로 나누어 떨어지면, 3으로 나눈다.
	 * X가 2로 나누어 떨어지면, 2로 나눈다.
	 * 1을 뺀다.
	 * 정수 N이 주어졌을 때, 위와 같은 연산 세 개를 적절히 사용해서 1을 만드려고 한다. 
	 * 연산을 사용하는 횟수의 최소값을 출력하시오.
	 * 
	 * 첫째 줄에 1보다 크거나 같고, 106보다 작거나 같은 자연수 N이 주어진다.
	 * 첫째 줄에 연산을 하는 횟수의 최소값을 출력한다.
	 * 2
	 * 1
	 * 
	 * D[N] = N을 1로 만드는데 필요한 연산의 최소값
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/7b675fe68d3c2abfef40
	 */
	private static int[] d;

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		d = new int[n + 1];
		System.out.println(go(n));
		sc.close();
	}
	
	private static int go(int n) {
		if (n == 1) {
			return 0;
		}
		if (d[n] > 0) {
			return d[n];
		}
		d[n] = go(n - 1) + 1;
		if (n % 2 == 0) {
			int temp = go(n / 2) + 1;
			if (d[n] > temp) {
				d[n] = temp;
			}
		}
		if (n % 3 == 0) {
			int temp = go(n / 3) + 1;
			if (d[n] > temp) {
				d[n] = temp;
			}
		}
		return d[n];
	}

	private static int solution1(int[] Dp, int N) {
		if (N == 1) {
			return 0;
		}		
		if (Dp[N] > 0) {
			return Dp[N];
		}		
		Dp[N] = solution1(Dp, N - 1) + 1;
		if (N % 2 == 0) {
			Dp[N] = Math.min(Dp[N], solution1(Dp, N / 2) + 1);
		}
		if (N % 3 == 0) {
			Dp[N] = Math.min(Dp[N], solution1(Dp, N / 3) + 1);
		}
		return Dp[N];
	}
	
	/*
	 * 1. Dp 테이블에 최소값을 꾸준히 집어넣는다고 가정을 한다면
	 * 2. Dp[i]값에 최소값이 들어갈 경우는 Dp[i-1] +1, Dp[i/2] +1, Dp[i/3] +1 값을 비교해서 최소값을 넣어 주면 된다.
	 * 3. 쭉 진행해서 Dp[N]에 우리가 구하고자 하는 최소값이 들어오게 된다.
	 */
	private static int[] Dp = new int[100];
	private static int solution2(int N) {
		if (N == 1) {
			return 0;
		}
		if (Dp[N] > 0) {
			return Dp[N];
		}
		for (int i = 1; i < N; i++) {
			Dp[i] = Dp[i - 1] + 1;
	        if (i % 2 == 0) {
	        	Dp[i] = Math.min(Dp[i], Dp[i / 2] + 1);
	        }
	        if (i % 3 == 0) {
	        	Dp[i] = Math.min(Dp[i], Dp[i / 3] + 1);
	        }
	    }
		log.debug("{} {}", Arrays.toString(Dp), Dp[N - 1]);
		return Dp[N - 1];
	}
}

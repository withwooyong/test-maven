package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10844 {

	private static Logger log = LoggerFactory.getLogger(P10844.class);

	/*
	 * 쉬운 계단 수
	 * 45656이란 수를 보자.
	 * 이 수는 인접한 모든 자리수의 차이가 1이 난다. 이런 수를 계단 수라고 한다.
	 * 세준이는 수의 길이가 N인 계단 수가 몇 개 있는지 궁금해졌다.
	 * N이 주어질 때, 길이가 N인 계단 수가 총 몇 개 있는지 구하는 프로그램을 작성하시오. (0으로 시작하는 수는 없다.)
	 * 
	 * 첫째 줄에 N이 주어진다. N은 1보다 크거나 같고, 100보다 작거나 같은 자연수이다.
	 * 첫째 줄에 정답을 1,000,000,000으로 나눈 나머지를 출력한다.
	 * 1
	 * 9
	 */
	public static void main(String[] args) throws Exception {
		solution();
		bottom_up();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/7e4e12ce1b0aa740d5d1
	 */
	private static long mod = 1000000000L;

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt(); // 1
		long[][] d = new long[n + 1][10];
		for (int i = 1; i <= 9; i++) {
			d[1][i] = 1;
		}
		for (int i = 2; i <= n; i++) {
			// 1 <= L <= 8
			// D[N][L] = D[N-1][L-1] + D[N-1][L+1] 
			for (int j = 0; j <= 9; j++) {
				d[i][j] = 0;
				if (j - 1 >= 0) {
					d[i][j] += d[i - 1][j - 1];
				}
				if (j + 1 <= 9) {
					d[i][j] += d[i - 1][j + 1];
				}
				d[i][j] %= mod;
			}
		}
		long ans = 0;
		for (int i = 0; i <= 9; i++) {
			ans += d[n][i];
		}
		ans %= mod;
		System.out.println(ans);
		sc.close();
	}
	
	private static void bottom_up() {
		int[][] memo = new int[1001][10];
		int N = 2;
		
		for (int i = 1; i <= 9; i++) {
			memo[1][i] = 1;
		}
		
		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				memo[i][j] = 0;
				if (j - 1 >= 0) {
					memo[i][j] += memo[i - 1][j - 1];					
				}
				if (j + 1 <= 9) {
					memo[i][j] += memo[i -1][j + 1];
				}
				memo[i][j] %= 1000000000;
			}
		}
		
		int answer = 0; 
				
		for (int i = 0; i <= 9; i++) {
			answer += memo[N][i];
			answer %= 1000000000;
		}
		System.out.println(answer);
	}

	/*
	 * 1. 자릿수가 증가함에 따라서 경우의 수도 증가하는데 이 증가할때 법칙이 있다.
	 * 2. 이 법칙은 j 자릿수 i 수에 대해서 j-1 자릿수의 i-1과 i+1을 더한 수가 i 수에 대한 값이 되는것이다. 
	 * 예를들면 -  1 ,2 ,3 ,4 ,5 가 있다고 하면 10 자리수의 쉬운 계단 수를 만들려고 
	 * 앞의 자리를 1로 결정하는 순간 뒤의 자리수는 0 아니면 2 가 된다.
	 * - 10 ,12, 21, 23, 32, 34 에서 100의 자리수의 쉬운 계단 수를 만들려고하는데 
	 * 앞자리가 2라면 그 뒤에 10 ,12, 32, 34 의 수가 될 수 있다.
	 * 
	 * 3. 즉 이전 자리수의 앞자리 수의 경우의 수만 알면 지금 자리수에 경우의 수를 구할 수 있게 되는것이다.
	 * 
	 * 4. 이 것을 주욱 반복하면 정답을 구할 수 있다.
	 * 출처: http://wootool.tistory.com/90 [우투리와툴툴]
	 * 2
	 * 17
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int mod = 1000000000;
		int N = 2;
		int[][] A = new int[N + 1][10];
		
		Arrays.fill(A[1], 1);		

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				if (j == 0) {
					A[i][0] = A[i - 1][1] % mod; // 0 이면 뒷자리 1만 올수 있고
				} else if (j == 9) {
					A[i][9] = A[i - 1][8] % mod; // 9 이면 앞자리 8만 올수 있다.
				} else {
					A[i][j] = (A[i - 1][j - 1] + A[i - 1][j + 1]) % mod; // 앞자리 / 뒷자리 의 경우 가 올수 있다.
				}
			}
		}
		
		int sum = 0;
		for (int i = 1; i < 10; i++) {
			sum = (sum + A[N][i]) % mod;
		}
		log.debug("{}", Arrays.deepToString(A));
		log.debug("{}", sum % mod);
		sc.close();
	}
}

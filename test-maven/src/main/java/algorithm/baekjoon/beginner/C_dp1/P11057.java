package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11057 {

	private static Logger log = LoggerFactory.getLogger(P11057.class);

	/*
	 * 오르막 수
	 * 오르막 수는 수의 자리가 오름차순을 이루는 수를 말한다. 이 때, 인접한 수가 같아도 오름차순으로 친다.
	 * 예를 들어, 2234와 3678, 11119는 오르막 수이지만, 2232, 3676, 91111은 오르막 수가 아니다.
	 * 수의 길이 N이 주어졌을 때, 오르막 수의 개수를 구하는 프로그램을 작성하시오. 수는 0으로 시작할 수 있다.
	 * 
	 * 첫째 줄에 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 첫째 줄에 길이가 N인 오르막 수의 개수를 10,007로 나눈 나머지를 출력한다.
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	/*
	 * https://gist.github.com/Baekjoon/264f68b19e93cc9b46aa
	 */
	public static long mod = 10007;
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long[][] d = new long[n+1][10];
        for (int i=0; i<=9; i++) {
            d[1][i] = 1;
        }
        for (int i=2; i<=n; i++) {
            for (int j=0; j<=9; j++) {
                for (int k=0; k<=j; k++) {
                    d[i][j] += d[i-1][k];
                    d[i][j] %= mod;
                }
            }
        }
        long ans = 0;
        for (int i=0; i<10; i++) {
            ans += d[n][i];
        }
        ans %= mod;
        System.out.println(ans);
        sc.close();
	}

	/*
	 * 1. 이 문제는 한번에 우리가 원하는 자릿수를 구하는게 아니고 차근차근 자릿수를 올려가면서 구하면 될 거 같다.
	 *  마치 피보나치 수열을 구하는 것 처럼
	 * 2. 현재의 j 자릿수에 앞의 숫자가 i 라고 했을 때 이 숫자는 j-1 자릿수의 앞자리가 i 보다 크거나 같은 값으로 만들 수 있다.
	 * 3. 이를 우리가 원하는 N자리수 까지 반복한다면 우리는 정답을 구할 수 있다.
	 * 출처: http://wootool.tistory.com/91 [우투리와툴툴]
	 * 3
	 * 
	 * 220
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int mod = 10007;
		int N = 3;
		int[][] A = new int[N + 1][10];
		Arrays.fill(A[1], 1);

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = j; k < 10; k++) {
					A[i][j] = (A[i][j] + A[i - 1][k]) % mod;
				}
			}
		}

		int total = 0;
		for (int i = 0; i < 10; i++) {
			total = (total + A[N][i]) % mod;
		}

		log.debug("{}", total);
		sc.close();
	}
}

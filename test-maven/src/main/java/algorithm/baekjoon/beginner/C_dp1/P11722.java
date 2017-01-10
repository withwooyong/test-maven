package algorithm.baekjoon.beginner.C_dp1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11722 {

	private static Logger log = LoggerFactory.getLogger(P11722.class);

	/*
	 * 가장 긴 감소하는 부분 수열
	 * 수열 A가 주어졌을 때, 가장 긴 감소하는 부분 수열을 구하는 프로그램을 작성하시오.
	 * 예를 들어, 수열 A = {10, 30, 10, 20, 20, 10} 인 경우에 
	 * 가장 긴 감소하는 부분 수열은 A = {10, 30, 10, 20, 20, 10}  이고, 길이는 3이다.
	 * 
	 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
	 * 
	 * 첫째 줄에 수열 A의 가장 긴 감소하는 부분 수열의 길이를 출력한다.
	 * 6
	 * 10 30 10 20 20 10
	 * 
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n+1];
        int[] d = new int[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = sc.nextInt();
        }
        for (int i=n; i>=1; i--) {
            d[i] = 1;
            for (int j=i+1; j<=n; j++) {
                if (a[i] > a[j] && d[i] < d[j]+1) {
                    d[i] = d[j]+1;
                }
            }
        }
        int ans = d[1];
        for (int i=2; i<=n; i++) {
            if (ans < d[i]) {
                ans = d[i];
            }
        }
        System.out.println(ans);
        sc.close();
	}

	/*
	 * 가장 긴 감소하는 부분 수열
	 * 1. 이 문제야 말로 '가장 긴 증가 하는 부분 수열' 문제와 동일하다.
	 * 2. 다른 점은 증가하는 수열이 아닌 감소하는 수열이기 때문에 검색할 때의 조건이 자신보다 높은값만 찾으면 된다.
	 * 출처: http://wootool.tistory.com/98 [우투리와툴툴]
	 */
	private static void solution1() {		
		
		int[] A = { 10, 30, 10, 20, 20, 10 };
		int[] Dp = new int[A.length];
		int max = 0;		

		for (int i = 0; i < A.length; i++) {
			int min = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] < A[j]) {
					min = Math.max(min,  Dp[j]);
				}
			}
			Dp[i] = min + 1;
			max = Math.max(max,  Dp[i]);
		}
		log.debug("{}", max);
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Dp = new int[N + 1];
		int[] A = new int[N + 1];
		int max = 0;

		for (int i = 1; i <= N; i++)
			A[i] = sc.nextInt();

		for (int i = 1; i <= N; i++) {
			int min = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] < A[j]) {
					if (min < Dp[j])
						min = Dp[j];
				}
			}
			Dp[i] = min + 1;
			if (max < Dp[i])
				max = Dp[i];
		}
		System.out.println(max);
		sc.close();
	}
}

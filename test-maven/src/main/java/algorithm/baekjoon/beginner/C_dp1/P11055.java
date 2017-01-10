package algorithm.baekjoon.beginner.C_dp1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11055 {

	private static Logger log = LoggerFactory.getLogger(P11055.class);

	/*
	 * 가장 큰 증가 부분 수열
	 * 수열 A가 주어졌을 때, 그 수열의 증가 부분 수열 중에서 합이 가장 큰 것을 구하는 프로그램을 작성하시오.
	 * 예를 들어, 수열 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 인 경우에 
	 * 합이 가장 큰 증가 부분 수열은 A = {1, 100, 2, 50, 60, 3, 5, 6, 7, 8} 이고, 합은 113이다.
	 * 
	 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
	 * 첫째 줄에 수열 A의 합이 가장 큰 증가 부분 수열의 합을 출력한다.
	 * 10
	 * 1 100 2 50 60 3 5 6 7 8
	 * 
	 * 113
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/85045323277de97683d9
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int[] d = new int[n];
        for (int i=0; i<n; i++) {
            d[i] = a[i];
            for (int j=0; j<i; j++) {
                if (a[j] < a[i] && d[i] < d[j]+a[i]) {
                    d[i] = d[j]+a[i];
                }
            }
        }
        int ans = d[0];
        for (int i=0; i<n; i++) {
            if (ans < d[i]) {
                ans = d[i];
            }
        }
        System.out.println(ans);
        sc.close();
	}

	/*
	 * 1. 이 문제 같은 경우는 푸는 방식이 이전글인 '가장 긴 증가 부분 수열' 과 동일하다.
	 * 2. 그 문제와 차이점은 Dp[i] 배열에 넣는 값이 수열의 길이가 아닌 최대합을 집어 넣어야하기 때문에 
	 * 그 집어넣는 값을 A[i] 배열의 값을 더해서 주면 된다.
	 * 출처: http://wootool.tistory.com/97 [우투리와툴툴]
	 */
	private static void solution2() {
		int[] A = { 1, 100, 2, 50, 60, 3, 5, 6, 7, 8 };
		int[] Dp = new int[A.length];
		int max = 0;
		
		for (int i = 0; i < A.length; i++) {
			int maxDp = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) {					
					if (maxDp < Dp[j])
						maxDp = Dp[j];
				}
			}
			Dp[i] = maxDp + A[i];
			if (max < Dp[i])
				max = Dp[i];
		}
		System.out.println(max);
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
			int maxDp = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) {					
					if (maxDp < Dp[j])
						maxDp = Dp[j];
				}
			}
			Dp[i] = maxDp + A[i];
			if (max < Dp[i])
				max = Dp[i];
		}
		System.out.println(max);
		sc.close();
	}
}

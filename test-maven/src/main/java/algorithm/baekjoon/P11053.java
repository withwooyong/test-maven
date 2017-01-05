package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11053 {

	private static Logger log = LoggerFactory.getLogger(P11053.class);

	/*
	 * 가장 긴 증가하는 부분 수열
	 * 수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.
	 * 예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 
	 * 가장 긴 증가하는 부분 수열은 A = {10, 20, 10, 30, 20, 50} 이고, 길이는 4이다.
	 * 
	 * 첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
	 * 둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)
	 * 
	 * 첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.
	 * 
	 * 6
	 * 10 20 10 30 20 50
	 * 
	 * 4
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	/*
	 * 가장 긴 증가하는 부분 수열
	 * 1. 이 문제 같은 경우는 현재의 자신 값을 과거의 값들을 돌아봐서 그 중 최대의 값으로 자신의 최대 길이를 정하는 문제이다.
	 * 2. 그래서 각 부분마다 과거의 저장한 배열을 검색을 해야한다. 쉽게 설명하자면 아래 그림과 같다.
	 * 출처: http://wootool.tistory.com/96 [우투리와툴툴]
	 */
	private static void solution() {
		int[] A = { 10, 20, 10, 30, 20, 50 };
		int[] Dp = new int[A.length];
	    
	    int max = 0;
	 
		for (int i = 0; i < A.length; i++) {
			int min = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) { 
					if (min < Dp[j]) {
						min = Dp[j];
					}
				}
			}
			Dp[i] = min + 1;
			if (max < Dp[i]) {
				max = Dp[i];
			}
		}
	    log.debug("{}", max);		    
		
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
	    int[] Dp = new int[1010];
	    int[] A = new int[1010];
	    int max = 0;
	    
	    for (int i = 1; i <= N; i++) {
	        A[i] = sc.nextInt();
	    }
	 
		for (int i = 1; i <= N; i++) {
			int min = 0;
			for (int j = 0; j < i; j++) {
				if (A[i] > A[j]) { 
					if (min < Dp[j]) {
						min = Dp[j];
					}
				}
			}
			Dp[i] = min + 1;
			if (max < Dp[i]) {
				max = Dp[i];
			}
		}
	    System.out.println(max);		    
		sc.close();
	}
}

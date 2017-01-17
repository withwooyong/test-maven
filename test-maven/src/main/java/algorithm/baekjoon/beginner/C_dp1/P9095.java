package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9095 {

	private static Logger log = LoggerFactory.getLogger(P9095.class);

	/*
	 * 1, 2, 3 더하기
	 * 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 총 7가지가 있다.
	 * 1+1+1+1
	 * 1+1+2
	 * 1+2+1
	 * 2+1+1
	 * 2+2
	 * 1+3
	 * 3+1
	 * 정수 n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 첫쨰 줄에 테스트 케이스의 개수 T가 주어진다. 
	 * 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.
	 * 각 테스트 케이스마다, n을 1,2,3의 합으로 나타내는 방법의 수를 출력한다.
	 * 3
	 * 4
	 * 7
	 * 10
	 * 
	 * 7
	 * 44
	 * 274
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	private static int go(int count, int sum, int goal) {
		if (count > 10) {
			return 0;
		}
		if (sum > goal) {
			return 0;
		}
		
		return 0;
	}
	
	/*
	 * https://gist.github.com/Baekjoon/e019984a7c7f1ac6bd32
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int[] d = new int[11];
        d[0] = 1;
        for (int i=1; i<=10; i++) {
            for (int j=1; j<=3; j++) {
                if (i-j >= 0) {
                    d[i] += d[i-j];
                }
            }
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
        sc.close();
	}

	/*
	 * 1. 이 문제 같은경우는 1,2,3 만 고려해줘야하기 때문에 푸는 방법이 어렵지 않다.
	 * 2. 각 숫자에 대해서 어떤 경우의 수가 있을지만 생각하면 된다.
	 * 3. 간단하게 앞자리 수에 1, 2, 3 이 나올 경우를 생각해 보면 된다. 
	 * 즉 예를 들면 숫자 4 같은경우는 앞에 
	 * 1이 추가됬을때 -> 4-1 인 3을 만드는 모든 경우의 수가 되고 
	 * 2가 추가된다면 -> 4-2 인 2를 만드는 모든 경우의 수를 추가하고 
	 * 3을 앞에 추가하면 3-1인 1을 만드는 모든 경우의 수를 추가하면 된다.
	 * 4. 즉 Dp[i] += Dp[i-1]  , Dp[i] += Dp[i-2] , Dp[i] += Dp[i-3] 의 값을 꾸준히 더해주면서 구하면되는데 
	 * 계산을 쉽게 하기위해 Dp[0]의 경우를 1로 채워 넣는다.
	 * 출처: http://wootool.tistory.com/77 [우투리와툴툴]
	 */
	private static void solution1() {
		int K = 7;
		int[] A = new int[K + 1];
		A[0] = 1;
		// 1, 2, 3
		for (int i = 1; i <= K; i++) {
			if (i - 1 >= 0) {
				A[i] += A[i - 1];
			}
			if (i - 2 >= 0) {
				A[i] += A[i - 2];
			}
			if (i - 3 >= 0) {
				A[i] += A[i - 3];
			}
		}
		log.debug("{}", Arrays.toString(A));
		log.debug("{}", A[K]);
		
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = 1;
		while (N-- > 0) {
			int K = 4;
			int[] A = new int[K + 1];
			A[0] = 1;

			// n을 1,2,3의 합으로 나타내는 방법의 수
			for (int i = 1; i <= K; i++) {
				if (i - 1 >= 0) {
					A[i] += A[i - 1];
				}
				if (i - 2 >= 0) {
					A[i] += A[i - 2];
				}
				if (i - 3 >= 0) {
					A[i] += A[i - 3];
				}
			}
			log.debug("{}", A[K]);
		}
	}
}

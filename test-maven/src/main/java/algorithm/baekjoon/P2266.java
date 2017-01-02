package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2266 {

	private static Logger log = LoggerFactory.getLogger(P2266.class);

	/*
	 * N층 빌딩이 있다. 이 빌딩의 F층은 금고를 떨어뜨렸을 때에 부서지는 최소 층이다. 
	 * 다시 말하면, F층을 포함하여 그 위의 층에서 금고를 떨어뜨리면 무조건 부서지며, 
	 * F층의 아래층에서 금고를 떨어뜨릴 때에는 금고는 절대 부서지지 않는다. 
	 * N층에서도 부서지지 않을 수도 있으며, 1층에서도 부서질 수도 있다.
	 * 새로 개발한 금고의 견고함을 측정해보기 위해서 K개의 금고를 이용하여 이 빌딩의 F층을 구하려고 한다. 
	 * 이를 위해서 직접 금고를 떨어뜨려 보면서 그 결과를 확인하려 한다. 
	 * 금고가 부서진 경우에는 그 금고를 다시 사용할 수 없으며, 부서지지 않았다면 다시 사용할 수 있다.
	 * 이런 상황에서 K개의 금고를 가지고 F층이 몇 층이던지 간에 F층을 알아낼 수 있는 
	 * 최소한의 금고 낙하 회수를 E(N, K)라 하자. 
	 * 예를 들어 K=1이라면 F를 알아내기 전에 금고가 부서지면 안 되기 때문에 1층부터 차례로 올라가면서 금고를 떨어뜨려야 한다. 
	 * 따라서 E(N, 1)=N이 된다.
	 * 두 정수 N, K가 주어졌을 때 E(N, K)를 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 두 정수 N(1≤N≤500), K(1≤K≤N)가 주어진다.
	 * 첫째 줄에 E(N, K)를 출력한다.
	 * 4 2
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		int N = 4; // 층
		int K = 2; // 금고수
		int F; // 결과층
		
		if (K == 1) {
			
		} else {
			
		}
		
		//solution();
		solution2();
		solution3();
		// E(N, K) K 금고
	}
	
	private static void solution2() {
		int N = 10; // 층
		int K = 2; // 박스
		int count = 0;
		int half = N;
		while (1 < K && 1 < half) {
			half /= 2;
			K--;
			count++;
		}
		count += half;
		log.debug("{}", count);
	}
	
	/*
	 * Analysis:
	 * 어떤 층 f에서 금고를 떨어뜨렸다고 한다면, 결과는 둘중 하나입니다. 
	 * 금고가 부서지거나 부서지지 않거나. 만약 금고가 부서졌다면 k-1개의 금고로 총 (f-1)층을 조사하면 됩니다. 
	 * 만약 금고가 부서지지 않았다면 k개의 금고로 (n-f)층을 조사하면 됩니다. 
	 * 그렇다면 다음과 같은 다음과 같은 점화식을 세울 수 있겠죠:
	 * dp[i][j] := i개의 금고로 j층 빌딩에서 최악의 경우 떨어뜨려야하는 최소 횟수
	 * dp[i][j] = 1+dp[i-1][j-1]+dp[i][n-f], (1 <= f <= n, 2 <= i,j <= k)
	 * 그리고 기본적으로 i = 1일때 dp[i][j] = j 이고, j = 0일때 dp[i][j] = 0, j = 1일때 dp[i][j] = 1입니다.
	 */
	// k개의 금고와 n개의 층수 
	private static int[][] dp = new int[500][500]; // dp[i][j] := i개의 금고를 j개의 층에서 떨어뜨리는 최소한의 필요한 횟수
	
	private static void solution3() {
		int n = 10;
		int k = 2;

		for (int i = 1; i <= k; i++) {
			dp[i][1] = 1;
			dp[i][0] = 0;
		}
		
		for (int ii = 1; ii <= n; ii++) {
			dp[1][ii] = ii;
		}
		
		for (int iii = 2; iii <= k; iii++) {
			for (int j = 2; j <= n; j++) {
				dp[iii][j] = 987654321;
				for (int f = 1; f <= j; f++) {
					int res = 1 + Math.max(dp[iii - 1][f - 1], dp[iii][j - f]);
					dp[iii][j] = Math.min(dp[iii][j], res);
				}
			}
		}
		log.debug("{}", dp[k][n]);
	}	
}

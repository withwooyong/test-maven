package algorithm.baekjoon.beginner.C_dp1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2193 {

	private static Logger log = LoggerFactory.getLogger(P2193.class);

	/*
	 * 이친수
	 * 0과 1로만 이루어진 수를 이진수라 한다. 
	 * 이러한 이진수 들 특별한 성질을 갖는 것들이 있는데, 이들을 이친수(pinary number)라 한다. 
	 * 이친수는 다음의 성질을 만족한다.
	 * 이친수는 0으로 시작하지 않는다.
	 * 이친수에서는 1이 두 번 연속으로 나타나지 않는다. 즉, 11을 부분 문자열로 갖지 않는다.
	 * 예를 들면 1, 10, 100, 101, 1000, 1001 등이 이친수가 된다. 
	 * 하지만 0010101이나 101101은 각각 1, 2번 규칙에 위배되므로 이친수가 아니다.
	 * N(1≤N≤90)이 주어졌을 때, N자리 이친수의 개수를 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 N이 주어진다.
	 * 첫째 줄에 N자리 이친수의 개수를 출력한다.
	 * 3
	 * 2
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/7fbfd8d0963139d638de
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		long[] d = new long[n + 1];
		d[1] = 1;
		if (n >= 2) {
			d[2] = 1;
		}
		for (int i = 3; i <= n; i++) {
			d[i] = d[i - 1] + d[i - 2];
		}
		System.out.println(d[n]);
		sc.close();
	}
	
	private static void bottom_up() {
		long[][] memo = new long[95][2];
		int N = 3;
		long answer = 0;
		
		memo[1][1] = 1;
		for (int i = 2; i <= N; i++) {
			memo[i][0] += memo[i - 1][1] + memo[i - 1][0];
			memo[i][1] += memo[i - 1][0];
		}
		
		for (int i = 0; i <= 1; i++) {
			answer += memo[N][i];
		}
		System.out.println(answer);
	}

	/*
	 * 1. 이 문제는 앞에 첫 자리 숫자가 1이 꼭 나와야 한다. 하지만 여기서 0이 앞으로 나오는 경우도 카운트 해줘야 한다.
	 * 2. 그렇기에 현재 1이 앞으로 나오는 경우에 이전 자릿수에 앞에 0이 나오는 경우의 수랑 같다.
	 * 3. 현재 0이 앞으로 나오는 경우는 이전  자릿수에 앞에 0, 1 이 나오는 경우의 합과 같다.
	 * 4. 즉 이 규칙을 이용하여서 진행하면 A[N][1] 에 원하는 답을 구 할 수 있다.
	 * 출처: http://wootool.tistory.com/92 [우투리와툴툴]
	 * 
	 * 점화식을 세울 때 D[N]은 n자리 수의 이친수 개수이다.
	 * 즉 마지막 수는 0 또는 1이 올 수 있는데, 0이 오는 경우는 D[N-1]이지만 1이 오는 경우는 그 이전에 1이 올수 없으므로 강제로 0이 오게된다. 즉 D[N-2]이다.
	 * 따라서 점화식은 D[N] = D[N-1]+D[N-2] 인 것을 알 수 있는데, 이는 피보나치 수열의 점화식과 똑같은 것을 알 수 있다.
	 * 3
	 * 
	 * 2
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = 3;
		int[][] A = new int[N + 1][2];

		A[1][0] = 1;
		A[1][1] = 1;

		for (int i = 2; i <= N; i++) {
			A[i][0] += A[i - 1][0] + A[i - 1][1];
			A[i][1] += A[i - 1][0];
		}
		log.debug("{}", A[N][1]);
	}
}

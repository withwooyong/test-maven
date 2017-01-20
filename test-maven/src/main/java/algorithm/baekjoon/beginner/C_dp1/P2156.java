package algorithm.baekjoon.beginner.C_dp1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2156 {

	private static Logger log = LoggerFactory.getLogger(P2156.class);

	/*
	 * 포도주 시식
	 * 효주는 포도주 시식회에 갔다. 
	 * 그 곳에 갔더니, 테이블 위에 다양한 포도주가 들어있는 포도주 잔이 일렬로 놓여 있었다. 
	 * 효주는 포도주 시식을 하려고 하는데, 여기에는 다음과 같은 두 가지 규칙이 있다.
	 * 포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
	 * 연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
	 * 효주는 될 수 있는 대로 많은 양의 포도주를 맛보기 위해서 어떤 포도주 잔을 선택해야 할지 고민하고 있다. 
	 * 1부터 n까지의 번호가 붙어 있는 n개의 포도주 잔이 순서대로 테이블 위에 놓여 있고, 각 포도주 잔에 들어있는 포도주의 양이 주어졌을 때, 
	 * 효주를 도와 가장 많은 양의 포도주를 마실 수 있도록 하는 프로그램을 작성하시오.
	 * 예를 들어 6개의 포도주 잔이 있고, 각각의 잔에 순서대로 6, 10, 13, 9, 8, 1 만큼의 포도주가 들어 있을 때, 
	 * 첫 번째, 두 번째, 네 번째, 다섯 번째 포도주 잔을 선택하면 총 포도주 양이 33으로 최대로 마실 수 있다.
	 * 
	 * 첫째 줄에 포도주 잔의 개수 n이 주어진다. 
	 * (1≤n≤10,000) 둘째 줄부터 n+1번째 줄까지 포도주 잔에 들어있는 포도주의 양이 순서대로 주어진다. 포도주의 양은 1,000 이하의 정수이다.
	 * 첫째 줄에 최대로 마실 수 있는 포도주의 양을 출력한다.
	 * 6
	 * 6
	 * 10
	 * 13
	 * 9
	 * 8
	 * 1
	 * 
	 * 33
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/5a8d8b46c4b2c608f3dd
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		int[] d = new int[n + 1];
		d[1] = a[1];
		if (n >= 2) {
			d[2] = a[1] + a[2];
		}
		for (int i = 3; i <= n; i++) {
			d[i] = d[i - 1];
			d[i] = Math.max(d[i], d[i - 2] + a[i]);
			d[i] = Math.max(d[i], d[i - 3] + a[i - 1] + a[i]);
		}
		int ans = d[1];
		for (int i = 2; i <= n; i++) {
			ans = Math.max(ans, d[i]);
		}
		System.out.println(ans);
		sc.close();
	}

	/*
	 * N 위치까지 포도주를 최적으로 먹어온 결과를 저장하는데 연속으로 몇개 먹어온 상황인지도 알아야 함.
	 * 따라서 A[N][L] == N 위치까지 L만큼 연속으로 먹어온 최적의 값
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		long[][] A = new long[N + 1][3];
		long[] B = new long[N + 1];
		long answer;

		for (int i = 1; i <= N; i++) {
			B[i] = sc.nextInt();
		}

		A[1][0] = 0;
		A[1][1] = B[1];
		A[1][2] = B[1];

		for (int i = 2; i <= N; i++) {
			A[i][0] = Math.max(A[i - 1][0], Math.max(A[i - 1][1], A[i - 1][2]));
			A[i][1] = A[i - 1][0] + B[i];
			A[i][2] = A[i - 1][1] + B[i];
		}
		answer = Math.max(A[N][0], Math.max(A[N][1], A[N][2]));
		System.out.println(answer);
		sc.close();
	}
}

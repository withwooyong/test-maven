package algorithm.baekjoon.middle2.A_dp2;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10942 {	
	
	private static Logger log = LoggerFactory.getLogger(P10942.class); 

	/*
	 * 팰린드롬? (앞으로도 뒤로도 같은 문자열)
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	3851	1069	685	29.861%
문제
명우는 홍준이와 함께 팰린드롬 놀이를 해보려고 한다.

먼저, 홍준이는 자연수 N개를 칠판에 적는다. 그 다음, 명우에게 질문을 총 M번 한다.

각 질문은 두 정수 S와 E로 나타낼 수 있으며, S번째 수부터 E번째 까지 수가 팰린드롬을 이루는지를 물어보며, 
명우는 각 질문에 대해 팰린드롬이다 또는 아니다를 말해야 한다.

예를 들어, 홍준이가 칠판에 적은 수가 1, 2, 1, 3, 1, 2, 1라고 하자.

S = 1, E = 3인 경우 1, 2, 1은 팰린드롬이다.
S = 2, E = 5인 경우 2, 1, 3, 1은 팰린드롬이 아니다.
S = 3, E = 3인 경우 1은 팰린드롬이다.
S = 5, E = 7인 경우 1, 2, 1은 팰린드롬이다.
자연수 N개와 질문 M개가 모두 주어졌을 때, 명우의 대답을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 수열의 크기 N (1 ≤ N ≤ 2,000)이 주어진다.

둘째 줄에는 홍준이가 칠판에 적은 수 N개가 순서대로 주어진다. 칠판에 적은 수는 100,000보다 작거나 같은 자연수이다.

셋째 줄에는 홍준이가 한 질문의 개수 M (1 ≤ M ≤ 1,000,000)이 주어진다.

넷째 줄부터 M개의 줄에는 홍준이가 명우에게 한 질문 S와 E가 한 줄에 하나씩 주어진다.

출력
총 M개의 줄에 걸쳐 홍준이의 질문에 대한 명우의 답을 입력으로 주어진 순서에 따라서 출력한다. 팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력한다.

예제 입력  복사
7
1 2 1 3 1 2 1
4
1 3
2 5
3 3
5 7
예제 출력  복사
1
0
1
1
	 */
	public static void main(String[] args) throws Exception {
		solution1();
		log.debug("{}", "");
	}

	/*
	 * Bottom Up https://gist.github.com/Baekjoon/f1003fbe8651b961de45
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		boolean[][] d = new boolean[n][n];
		for (int i = 0; i < n; i++) {
			d[i][i] = true;
		}
		for (int i = 0; i < n - 1; i++) {
			if (a[i] == a[i + 1]) {
				d[i][i + 1] = true;
			}
		}
		for (int k = 2; k < n; k++) {
			for (int i = 0; i < n - k; i++) {
				int j = i + k;
				if (a[i] == a[j] && d[i + 1][j - 1]) {
					d[i][j] = true;
				}
			}
		}
		int m = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			int s = sc.nextInt();
			int e = sc.nextInt();
			if (d[s - 1][e - 1]) {
				sb.append(1);
			} else {
				sb.append(0);
			}
			sb.append('\n');
		}
		System.out.println(sb);
		sc.close();
	}

	/*
	 * Top Down https://gist.github.com/Baekjoon/d4c90eb580674a898972
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		// 0 : ?, 1 : not palin, 2 : palin
		int[] a = new int[n];
		int[][] d = new int[n][n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
			Arrays.fill(d[i], -1);
		}
		int m = sc.nextInt();
		StringBuilder sb = new StringBuilder();
		while (m-- > 0) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			sb.append(go(a, d, x - 1, y - 1));
			sb.append('\n');
		}
		System.out.println(sb);

		sc.close();
	}

	public static int go(int[] a, int[][] d, int x, int y) {
		if (x == y) {
			return 1;
		} else if (x + 1 == y) {
			if (a[x] == a[y]) {
				return 1;
			} else {
				return 0;
			}
		}
		if (d[x][y] != -1) {
			return d[x][y];
		}
		if (a[x] != a[y]) {
			return d[x][y] = 0;
		} else {
			return d[x][y] = go(a, d, x + 1, y - 1);
		}
	}

}

package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.io.SyndFeedOutput;

public class P1890 {	
	
	private static Logger log = LoggerFactory.getLogger(P1890.class);

	/*
	 * 점프
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	2624	753	565	27.642%
문제
N×N 게임판에 수가 적혀져 있다. 이 게임의 목표는 가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 점프를 해서 가는 것이다.

각 칸에 적혀있는 수는 현재 칸에서 갈 수 있는 거리를 의미한다. 반드시 오른쪽이나 아래쪽으로만 이동해야 한다. 
0은 더 이상 진행을 막는 종착점이며, 항상 현재 칸에 적혀있는 수만큼 오른쪽이나 아래로 가야 한다.

가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 규칙에 맞게 이동할 수 있는 경로의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 게임 판의 크기 N (4 ≤ N ≤ 100)이 주어진다. 그 다음 N개 줄에는 각 칸에 적혀져 있는 수가 N개씩 주어진다. 
칸에 적혀있는 수는 0보다 크거나 같고, 9보다 작거나 같은 정수이며, 가장 오른쪽 아래 칸에는 항상 0이 주어진다.

출력
가장 왼쪽 위 칸에서 가장 오른쪽 아래 칸으로 문제의 규칙에 맞게 갈 수 있는 경로의 개수를 출력한다. 경로의 개수는 263-1보다 작거나 같다.

예제 입력  복사
4
2 3 3 1
1 2 1 3
1 2 3 1
3 1 1 0
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution1();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/b5f8bae461a2e43a35b25d515a6b5946
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int[][] a = new int[100][100];
		long[][] d = new long[100][100];
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		d[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == 0 && j == 0)
					continue;
				// (i,k+a[i][k]) -> (i,j)
				for (int k = 0; k < j; k++) {
					if (k + a[i][k] == j) {
						d[i][j] += d[i][k];
					}
				}
				// (k+a[k][j],j) -> (i,j)
				for (int k = 0; k < i; k++) {
					if (k + a[k][j] == i) {
						d[i][j] += d[k][j];
					}
				}
			}
		}
		System.out.println(d[n - 1][n - 1]);
		sc.close();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/cdbfbfb7b4de890d766de1579529bee2
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int[][] a = new int[100][100];
		long[][] d = new long[100][100];
		int n = sc.nextInt();

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		d[0][0] = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] == 0)
					continue;
				// (i,j) -> (i,j+a[i][j])
				if (j + a[i][j] < n) {
					d[i][j + a[i][j]] += d[i][j];
				}
				// (i,j) -> (i+a[i][j],j)
				if (i + a[i][j] < n) {
					d[i + a[i][j]][j] += d[i][j];
				}
			}
		}
		System.out.println(d[n - 1][n - 1]);
		sc.close();
	}
}

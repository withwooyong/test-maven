package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11048 {	
	
	private static Logger log = LoggerFactory.getLogger(P11048.class); 

	/*
	 * 이동하기
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	3663	1958	1365	56.150%
문제
준규는 N×M 크기의 미로에 갇혀있다. 미로는 1×1크기의 방으로 나누어져 있고, 각 방에는 사탕이 놓여져 있다. 미로의 가장 왼쪽 윗 방은 (1, 1)이고, 가장 오른쪽 아랫 방은 (N, M)이다.

준규는 현재 (1, 1)에 있고, (N, M)으로 이동하려고 한다. 준규가 (i, j)에 있으면, (i+1, j), (i, j+1), (i+1, j+1)로 이동할 수 있고, 각 방을 방문할 때마다 방에 놓여져있는 사탕을 모두 가져갈 수 있다. 또, 미로 밖으로 나갈 수는 없다.

준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수의 최대값을 구하시오.

입력
첫째 줄에 미로의 크기 N, M이 주어진다. (1 ≤ N, M ≤ 1,000)

둘째 줄부터 N개 줄에는 총 M개의 숫자가 주어지며, i번째 줄의 j번째 숫자는 (i, j)에 놓여져 있는 사탕의 개수이다. 사탕의 개수는 0보다 크거나 같고, 100보다 작거나 같다.

출력
첫째 줄에 준규가 (N, M)으로 이동할 때, 가져올 수 있는 사탕 개수를 출력한다.

예제 입력  복사
3 4
1 2 3 4
0 0 0 5
9 8 7 6
예제 출력  복사
31
	 */
	public static void main(String[] args) throws Exception {
		solution1();
		log.debug("{}", "");
	}

	/*
	 * Bottom Up (for문)
	 * https://gist.github.com/Baekjoon/51fc0cd6a1b2db1a4d48
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n + 1][m + 1];
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		// (i+1, j) 밑으로 , (i, j+1) 오른쪽, (i+1, j+1) 대각선, 로 이동할 수 있고
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]);
				d[i][j] = Math.max(d[i - 1][j - 1], d[i][j]);
				d[i][j] += a[i][j];
			}
		}
		System.out.println(d[n][m]);

		sc.close();
	}

	/*
	 * Bottom Up (for문)
	 * https://gist.github.com/Baekjoon/df34e7d5daf341c8b76a
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n + 1][m + 1];
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		d[1][1] = a[1][1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (j + 1 <= m && d[i][j + 1] < d[i][j] + a[i][j + 1]) {
					d[i][j + 1] = d[i][j] + a[i][j + 1];
				}
				if (i + 1 <= n && d[i + 1][j] < d[i][j] + a[i + 1][j]) {
					d[i + 1][j] = d[i][j] + a[i + 1][j];
				}
				if (i + 1 <= n && j + 1 <= m && d[i + 1][j + 1] < d[i][j] + a[i + 1][j + 1]) {
					d[i + 1][j + 1] = d[i][j] + a[i + 1][j + 1];
				}
			}
		}
		System.out.println(d[n][m]);
		sc.close();
	}

	/* 
	 * https://gist.github.com/Baekjoon/6b580f31de918530a7e9
	 */
	private static void solution3() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n + 1][m + 1];
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				d[i][j] = Math.max(d[i - 1][j], d[i][j - 1]); // 대각선 이동을 뺀 방법
				d[i][j] += a[i][j];
			}
		}
		System.out.println(d[n][m]);
		sc.close();
	}

	/*
	 * 재귀 Top Down
	 * https://gist.github.com/Baekjoon/9cda32c0258dab2ce563
	 */
	private static void solution4() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n + 1][m + 1];
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		System.out.println(go4(a, d, n, m));
	}
	
	public static int go4(int[][] a, int[][] d, int x, int y) {
		if (x == 1 && y == 1) { // Base Value
			return a[1][1];
		}
		if (x < 1 || y < 1) { //  불가능한 경우 
			return 0;
		}
		if (d[x][y] > 0) { // if (d[x][y] >= 0) { // 메모이제이션
			return d[x][y];
		}
		d[x][y] = go4(a, d, x - 1, y) + a[x][y];
		int temp = go4(a, d, x, y - 1) + a[x][y];
		if (d[x][y] < temp) {
			d[x][y] = temp;
		}
		return d[x][y];
	}

	private static void solution5() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n + 1][m + 1];
		int[][] d = new int[n + 1][m + 1];
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		System.out.println(go5(a, d, 1, 1));
		sc.close();
	}
	
	public static int go5(int[][] a, int[][] d, int x, int y) {
		int n = a.length - 1;
		int m = a[0].length - 1;
		if (x > n || y > m) {
			return 0;
		}
		if (d[x][y] > 0) { // if (d[x][y] >= 0) { // 메모이제이션
			return d[x][y];
		}
		d[x][y] = go5(a, d, x + 1, y) + a[x][y];
		int temp = go5(a, d, x, y + 1) + a[x][y];
		if (d[x][y] < temp) {
			d[x][y] = temp;
		}
		return d[x][y];
	}
}

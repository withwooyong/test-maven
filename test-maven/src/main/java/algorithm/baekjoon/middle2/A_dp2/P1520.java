package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1520 {	
	
	private static Logger log = LoggerFactory.getLogger(P1520.class); 

	/*
	 * 내리막 길
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	5438	2228	1437	41.317%
문제
여행을 떠난 세준이는 지도를 하나 구하였다. 이 지도는 아래 그림과 같이 직사각형 모양이며 여러 칸으로 나뉘어져 있다. 
한 칸은 한 지점을 나타내는데 각 칸에는 그 지점의 높이가 쓰여 있으며, 각 지점 사이의 이동은 지도에서 상하좌우 이웃한 곳끼리만 가능하다.

현재 제일 왼쪽 위 칸이 나타내는 지점에 있는 세준이는 제일 오른쪽 아래 칸이 나타내는 지점으로 가려고 한다. 
그런데 가능한 힘을 적게 들이고 싶어 항상 높이가 더 낮은 지점으로만 이동하여 목표 지점까지 가고자 한다. 
위와 같은 지도에서는 다음과 같은 세 가지 경로가 가능하다.

지도가 주어질 때 이와 같이 제일 왼쪽 위 지점에서 출발하여 제일 오른쪽 아래 지점까지 
항상 내리막길로만 이동하는 경로의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에는 지도의 세로의 크기 M과 가로의 크기 N이 빈칸을 사이에 두고 주어진다. 
이어 다음 M개 줄에 걸쳐 한 줄에 N개씩 위에서부터 차례로 각 지점의 높이가 빈 칸을 사이에 두고 주어진다. 
M과 N은 각각 500이하의 자연수이고, 각 지점의 높이는 10000이하의 자연수이다.

출력
첫째 줄에 이동 가능한 경로의 수 H를 출력한다. 모든 입력에 대하여 H는 10억 이하의 음이 아닌 정수이다.

예제 입력  복사
4 5
50 45 37 32 30
35 50 40 20 25
30 30 25 17 28
27 24 22 15 10
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * Top Down
	 * https://gist.github.com/Baekjoon/a8466ac399855aec2258
	 * 
	 */
	public static int mod = 1000000;
	public static int[] dx = { 0, 0, 1, -1 };
	public static int[] dy = { 1, -1, 0, 0 };

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n][m];
		int[][] d = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		System.out.println(go(a, d, 0, 0));
		sc.close();
	}

	public static int go(int[][] a, int[][] d, int x, int y) {
		int n = a.length;
		int m = a[0].length;
		if (x == n - 1 && y == m - 1) {
			return 1;
		}
		if (d[x][y] > 0) {
			return d[x][y];
		}
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (0 <= nx && nx < n && 0 <= ny && ny < m) {
				if (a[x][y] > a[nx][ny]) {
					d[x][y] += go(a, d, nx, ny);
				}
			}
		}
		return d[x][y];
	}
}
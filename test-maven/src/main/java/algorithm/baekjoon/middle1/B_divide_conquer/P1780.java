package algorithm.baekjoon.middle1.B_divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1780 {

	private static Logger log = LoggerFactory.getLogger(P1780.class);

	/*
	 * 종이의 개수
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 1334 756 596 57.308%
	 * 문제
	 * N×N크기의 행렬로 표현되는 종이가 있다. 종이의 각 칸에는 -1, 0, 1의 세 값 중 하나가 저장되어 있다. 우리는 이 행렬을 적절한 크기로 자르려고 하는데, 이 때 다음의 규칙에 따라 자르려고
	 * 한다.
	 * 
	 * 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
	 * (1)이 아닌 경우에는 종이를 같은 크기의 9개의 종이로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을 반복한다.
	 * 이와 같이 종이를 잘랐을 때, -1로만 채워진 종이의 개수, 0으로만 채워진 종이의 개수, 1로만 채워진 종이의 개수를 구해내는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 N(1≤N≤3^7, N은 3^k 꼴)이 주어진다. 다음 N개의 줄에는 N개의 정수로 행렬이 주어진다.
	 * 
	 * 출력
	 * 첫째 줄에 -1로만 채워진 종이의 개수를, 둘째 줄에 0으로만 채워진 종이의 개수를, 셋째 줄에 1로만 채워진 종이의 개수를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 9
	 * 0 0 0 1 1 1 -1 -1 -1
	 * 0 0 0 1 1 1 -1 -1 -1
	 * 0 0 0 1 1 1 -1 -1 -1
	 * 1 1 1 0 0 0 0 0 0
	 * 1 1 1 0 0 0 0 0 0
	 * 1 1 1 0 0 0 0 0 0
	 * 0 1 -1 0 1 -1 0 1 -1
	 * 0 -1 1 0 1 -1 0 1 -1
	 * 0 1 -1 1 0 -1 0 1 -1
	 * 예제 출력 복사
	 * 10
	 * 12
	 * 11
	 */
	public static void main(String args[]) throws Exception {
		solution();
	}

	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[][] a = new int[n][n];
		int[] cnt = new int[3];
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.valueOf(line[j]);
			}
		}
		solve(a, cnt, 0, 0, n);
		for (int i = 0; i < 3; i++) {
			System.out.println(cnt[i]);
		}
	}

	public static boolean same(int[][] a, int x, int y, int n) {
		for (int i = x; i < x + n; i++) {
			for (int j = y; j < y + n; j++) {
				if (a[x][y] != a[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	public static void solve(int[][] a, int[] cnt, int x, int y, int n) {
		if (same(a, x, y, n)) {
			cnt[a[x][y] + 1] += 1;
			return;
		}
		int m = n / 3;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				solve(a, cnt, x + i * m, y + j * m, m);
			}
		}
	}

}

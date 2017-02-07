package algorithm.baekjoon.middle1.A_greedy;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2873 {	
	
	private static Logger log = LoggerFactory.getLogger(P2873.class); 

	/*
	 * 롤러코스터 스페셜 저지
	 * 문제
상근이는 우리나라에서 가장 유명한 놀이 공원을 운영하고 있다. 이 놀이 공원은 야외에 있고, 다양한 롤러코스터가 많이 있다.

어느날 벤치에 앉아있던 상근이는 커다란 황금을 발견한 기분이 들었다. 
자신의 눈 앞에 보이는 이 부지를 구매해서 롤러코스터를 만든다면, 세상에서 가장 재미있는 롤러코스터를 만들 수 있다고 생각했다.

이 부지는 직사각형 모양이고, 상근이는 R행 C열의 표 모양으로 나누었다. 
롤러코스터는 가장 왼쪽 위 칸에서 시작할 것이고, 가장 오른쪽 아래 칸에서 도착할 것이다. 
롤러코스터는 현재 있는 칸과 위, 아래, 왼쪽, 오른쪽으로 인접한 칸으로 이동할 수 있다. 각 칸은 한 번 방문할 수 있고, 방문하지 않은 칸이 있어도 된다.

각 칸에는 그 칸을 지나갈 때, 탑승자가 얻을 수 있는 기쁨을 나타낸 숫자가 적혀있다. 롤러코스터를 탄 사람이 얻을 수 있는 기쁨은 지나간 칸의 기쁨의 합이다. 가장 큰 기쁨을 주는 롤러코스터는 어떻게 움직여야 하는지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 R과 C가 주어진다. (2 ≤ R, C ≤ 1000) 둘째 줄부터 R개 줄에는 각 칸을 지나갈 때 얻을 수 있는 기쁨이 주어진다. 이 값은 1000보다 작은 양의 정수이다.

출력
첫째 줄에 가장 가장 큰 기쁨을 주는 롤러코스터는 가장 왼쪽 위 칸부터 가장 오른쪽 아래 칸으로 어떻게 움직이면 되는지를 출력한다. 위는 U, 오른쪽은 R, 왼쪽은 L, 아래는 D로 출력한다. 정답은 여러가지 일 수도 있다.

3 3
5 1 3
2 4 8
1 1 2

RRDLLDRR
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/52bac8c27c03181a1cdfcfc145428a8b
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[][] a = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				a[i][j] = sc.nextInt();
			}
		}
		StringBuilder s = new StringBuilder();
		if (n % 2 == 1) {
			for (int i = 0; i < n; i++) {
				if (i % 2 == 0) {
					append(s, 'R', m - 1);
					if (i != n - 1) {
						append(s, 'D', 1);
					}
				} else {
					append(s, 'L', m - 1);
					append(s, 'D', 1);
				}
			}
		} else if (m % 2 == 1) {
			for (int j = 0; j < m; j++) {
				if (j % 2 == 0) {
					append(s, 'D', n - 1);
					if (j != m - 1) {
						append(s, 'R', 1);
					}
				} else {
					append(s, 'U', n - 1);
					append(s, 'R', 1);
				}
			}
		} else {
			int x, y;
			x = 0;
			y = 1;
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if ((i + j) % 2 == 1) {
						if (a[x][y] > a[i][j]) {
							x = i;
							y = j;
						}
					}
				}
			}
			int x1 = 0;
			int y1 = 0;
			int x2 = n - 1;
			int y2 = m - 1;
			StringBuilder s2 = new StringBuilder();
			while (x2 - x1 > 1) {
				if (x1 / 2 < x / 2) {
					append(s, 'R', m - 1);
					append(s, 'D', 1);
					append(s, 'L', m - 1);
					append(s, 'D', 1);
					x1 += 2;
				}
				if (x / 2 < x2 / 2) {
					append(s2, 'R', m - 1);
					append(s2, 'D', 1);
					append(s2, 'L', m - 1);
					append(s2, 'D', 1);
					x2 -= 2;
				}
			}
			while (y2 - y1 > 1) {
				if (y1 / 2 < y / 2) {
					append(s, 'D', 1);
					append(s, 'R', 1);
					append(s, 'U', 1);
					append(s, 'R', 1);
					y1 += 2;
				}
				if (y / 2 < y2 / 2) {
					append(s2, 'D', 1);
					append(s2, 'R', 1);
					append(s2, 'U', 1);
					append(s2, 'R', 1);
					y2 -= 2;
				}
			}
			if (y == y1) {
				append(s, 'R', 1);
				append(s, 'D', 1);
			} else {
				append(s, 'D', 1);
				append(s, 'R', 1);
			}
			s2.reverse();
			s.append(s2);
		}

		System.out.println(s);

		sc.close();
	}

	public static void append(StringBuilder s, char c, int cnt) {
		for (int i = 0; i < cnt; i++) {
			s.append(c);
		}
	}
}

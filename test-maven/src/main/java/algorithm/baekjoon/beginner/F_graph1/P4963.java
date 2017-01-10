package algorithm.baekjoon.beginner.F_graph1;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P4963 {	
	
	private static Logger log = LoggerFactory.getLogger(P4963.class); 

	/*
	 * 섬의 개수
	 * 문제
정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다. 섬의 개수를 세는 프로그램을 작성하시오.



한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다. 

두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다. 지도는 바다로 둘러쌓여 있으며, 지도 밖으로 나갈 수 없다.

입력
입력은 여러 개의 테스트 케이스로 이루어져 있다. 각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다. w와 h는 50보다 작거나 같은 양의 정수이다.

둘째 줄부터 h개 줄에는 지도가 주어진다. 1은 땅, 0은 바다이다.

입력의 마지막 줄에는 0이 두 개 주어진다.

출력
각 테스트 케이스에 대해서, 섬의 개수를 출력한다.

1 1
0
2 2
0 1
1 0
3 2
1 1 1
1 1 1
5 4
1 0 1 0 0
1 0 0 0 0
1 0 1 0 1
1 0 0 1 0
5 4
1 1 1 0 1
1 0 1 0 1
1 0 1 0 1
1 0 1 1 1
5 5
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0 0 0 0
1 0 1 0 1
0 0

0
1
1
3
1
9
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/83f0eab9c0eba3e7e6796f04fa1d420b
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        while (true) {
            int m = sc.nextInt();
            int n = sc.nextInt();
            if (n == 0 && m == 0) {
                break;
            }
            int[][] a = new int[n][m];
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    a[i][j] = sc.nextInt();
                }
            }
            int cnt = 0;
            int[][] group = new int[n][m];
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (a[i][j] == 1 && group[i][j] == 0) {
                        bfs(a, group, i, j, ++cnt, n, m);
                    }
                }
            }
            System.out.println(cnt);
        }
		
		sc.close();
	}
	
	public static final int[] dx = { 0, 0, 1, -1, 1, 1, -1, -1 };
	public static final int[] dy = { 1, -1, 0, 0, 1, -1, 1, -1 };

	public static void bfs(int[][] a, int[][] group, int x, int y, int cnt, int n, int m) {
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(x, y));
		group[x][y] = cnt;
		while (!q.isEmpty()) {
			Pair p = q.remove();
			x = p.x;
			y = p.y;
			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (0 <= nx && nx < n && 0 <= ny && ny < m) {
					if (a[nx][ny] == 1 && group[nx][ny] == 0) {
						q.add(new Pair(nx, ny));
						group[nx][ny] = cnt;
					}
				}
			}
		}
	}
}

package algorithm.baekjoon.middle2.C_graph2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1504 {	
	
	private static Logger log = LoggerFactory.getLogger(P1504.class); 

	/*
	 * 특정한 최단 경로
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	4960	1154	696	21.142%
문제
방향성이 없는 그래프가 주어진다. 세준이는 1번 정점에서 N번 정점으로 최단 거리로 이동하려고 한다. 또한 세준이는 두 가지 조건을 만족하면서 이동하는 특정한 최단 경로를 구하고 싶은데, 그것은 바로 임의로 주어진 두 정점은 반드시 통과해야 한다는 것이다.

세준이는 한번 이동했던 정점은 물론, 한번 이동했던 간선도 다시 이동할 수 있다. 하지만 반드시 최단 경로로 이동해야 한다는 사실에 주의하라. 1번 정점에서 N번 정점으로 이동할 때, 주어진 두 정점을 반드시 거치면서 최단 경로로 이동하는 프로그램을 작성하시오.

입력
첫째 줄에 정점의 개수 N과 간선의 개수 E가 주어진다. (2<=N<=800, 0<=E<=200,000) 둘째 줄부터 E개의 줄에 걸쳐서 세 개의 정수 a, b, c가 주어지는데, a번 정점에서 b번 정점까지 양방향 길이 존재하며, 그 거리가 c라는 뜻이다. (1<=c<=1,000) 다음 줄에는 반드시 거쳐야 하는 두 개의 서로 다른 정점 번호가 주어진다.

출력
첫째 줄에 두 개의 정점을 지나는 최단 경로의 길이를 출력한다. 그러한 경로가 없을 때에는 -1을 출력한다.

예제 입력  복사
4 6
1 2 3
2 3 3
3 4 1
1 3 5
2 4 5
1 4 4
2 3
예제 출력  복사
7
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/b8c102094d8c00da95c9 
	 */
	static final int inf = 100000000;
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][]a = new int[n+1][n+1];
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                a[i][j] = inf;
            }
        }
        int m = sc.nextInt();
        for (int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            if (a[x][y] > z) {
                a[x][y] = z;
            }
            if (a[y][x] > z) {
                a[y][x] = z;
            }
        }
        int v1 = sc.nextInt();
        int v2 = sc.nextInt();
        int[] dstart = dijkstra(n,a,1);
        int[] d1 = dijkstra(n,a,v1);
        int[] d2 = dijkstra(n,a,v2);
        int ans = dstart[v1] + d1[v2] + d2[n];
        int ans2 = dstart[v2] + d2[v1] + d1[n];
        if (ans > ans2) {
            ans = ans2;
        }
        if (ans >= inf) {
            ans = -1;
        }
        System.out.println(ans);
		
		sc.close();
	}
	
	private static int[] dijkstra(int n, int[][] a, int start) {
        int[] d = new int[n+1];
        boolean[]c = new boolean[n+1];
        for (int i=1; i<=n; i++) {
            d[i] = inf;
            c[i] = false;
        }
        d[start] = 0;
        for (int k=0; k<n-1; k++) {
            int min = inf+1;
            int x = -1;
            for (int i=1; i<=n; i++) {
                if (c[i] == false && min > d[i]) {
                    min = d[i];
                    x = i;
                }
            }
            c[x] = true;
            for (int i=1; i<=n; i++) {
                if (d[i] > d[x] + a[x][i]) {
                    d[i] = d[x] + a[x][i];
                }
            }
        }
        return d;
    }
}

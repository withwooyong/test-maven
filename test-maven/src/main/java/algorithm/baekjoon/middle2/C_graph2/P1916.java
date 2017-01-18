package algorithm.baekjoon.middle2.C_graph2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1916 {	
	
	private static Logger log = LoggerFactory.getLogger(P1916.class); 

	/*
	 * 최소비용 구하기
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	6027	1936	1083	34.121%
문제
n(1≤n≤1,000)개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 m(1≤m≤100,000)개의 버스가 있다. 우리는 A번째 도시에서 B번째 도시까지 가는데 드는 버스 비용을 최소화 시키려고 한다. 그러면 A번째 도시에서 B번째 도시 까지 가는데 드는 최소비용을 출력하여라.

입력
첫째 줄에 도시의 개수 n(1≤n≤1,000)이 주어지고 둘째 줄에는 버스의 개수 m(1≤m≤100,000)이 주어진다. 그리고 셋째 줄부터 m+2줄까지 다음과 같은 버스의 정보가 주어진다. 먼저 처음에는 그 버스의 출발 도시의 번호가 주어진다. 그리고 그 다음에는 도착지의 도시 번호가 주어지고 또 그 버스 비용이 주어진다. 버스 비용은 0보다 크거나 같고, 100,000보다 작은 정수이다.

그리고 m+3째 줄에는 우리가 구하고자 하는 구간 출발점의 도시번호와 도착점의 도시번호가 주어진다.

출력
첫째 줄에 출발 도시에서 도착 도시까지 가는데 드는 최소 비용을 출력한다.

예제 입력  복사
5
8
1 2 2
1 3 3
1 4 1
1 5 10
2 4 2
3 4 1
3 5 1
4 5 3
1 5
예제 출력  복사
4
	 */
	public static void main(String[] args) throws Exception {
		solution1();
		log.debug("{}", "");
	}

	/*
	 * 인접행렬
	 * https://gist.github.com/Baekjoon/742dbb011c0a03fcaae4
	 */
	private static void solution1() {
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
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
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
        System.out.println(d[end]);
		
		sc.close();
	}
	
	/*
	 * 인접 리스트 
	 * https://gist.github.com/Baekjoon/dead4e8e294f79c95e1c
	 */
	static final int inf = 1000000000;
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Edge3>[] a = (List<Edge3>[]) new List[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = new ArrayList<Edge3>();
        }
        int m = sc.nextInt();
        for (int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            a[x].add(new Edge3(y,z));
        }
        int start = sc.nextInt();
        int end = sc.nextInt();
        int[] d = new int[n+1];
        boolean[]c = new boolean[n+1];
        for (int i=1; i<=n; i++) {
            d[i] = inf;
            c[i] = false;
        }
        d[start] = 0;
        for (int k=0; k<n-1; k++) {
            m = inf+1;
            int x = -1;
            for (int i=1; i<=n; i++) {
                if (c[i] == false && m > d[i]) {
                    m = d[i];
                    x = i;
                }
            }
            c[x] = true;
            for (Edge3 y : a[x]) {
                if (d[y.to] > d[x] + y.cost) {
                    d[y.to] = d[x] + y.cost;
                }
            }
        }
        System.out.println(d[end]);
		
		sc.close();
	}
}

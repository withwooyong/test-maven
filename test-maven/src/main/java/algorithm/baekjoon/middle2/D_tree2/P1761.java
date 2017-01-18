package algorithm.baekjoon.middle2.D_tree2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1761 {	
	
	private static Logger log = LoggerFactory.getLogger(P1761.class); 

	/*
	 * 정점들의 거리
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1016	328	207	33.173%
문제
N(2≤N≤40,000)개의 정점으로 이루어진 트리가 주어지고 M(M≤10,000)개의 두 노드 쌍을 입력받을 때 두 노드 사이의 거리를 출력하라.

입력
첫째 줄에 노드의 개수 N이 입력되고 다음 N-1개의 줄에 트리 상에 연결된 두 점과 거리를 입력받는다. 그 다음 줄에 거리를 알고 싶은 M개의 노드 쌍이 한 줄에 한 쌍씩 입력된다. 두 점 사이의 거리는 10,000보다 작거나 같은 자연수이다.

정점은 1번부터 N번까지 번호가 매겨져 있다.

출력
M개의 줄에 차례대로 입력받은 두 노드 사이의 거리를 출력한다.

예제 입력  복사
7
1 6 13
6 3 9
3 5 7
4 1 3
2 4 20
4 7 2
3
1 6
1 4
2 6
예제 출력  복사
13
3
36
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/b2c4b1b29f518d5d4427 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Edge>[] a = (List<Edge>[]) new List[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = new ArrayList<Edge>();
        }
        for (int i=1; i<=n-1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int z = sc.nextInt();
            a[x].add(new Edge(y,z));
            a[y].add(new Edge(x,z));
        }
        int[] depth = new int[n+1];
        boolean[] check = new boolean[n+1];
        int[] parent = new int[n+1];
        int[] dist = new int[n+1];
        int start = 1;
        Queue<Integer> q = new LinkedList<Integer>();
        check[start] = true;
        depth[start] = 0;
        parent[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int x = q.remove();
            for (Edge e : a[x]) {
                int y = e.to; 
                if (check[y] == false) {
                    dist[y] = e.cost;
                    depth[y] = depth[x] + 1;
                    check[y] = true;
                    parent[y] = x;
                    q.add(y);
                }
            }
        }
        int m = sc.nextInt();
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int ans = 0;
            if (depth[x] < depth[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            while (depth[x] != depth[y]) {
                ans += dist[x];
                x = parent[x];
            }
            while (x != y) {
                ans += dist[x] + dist[y];
                x = parent[x];
                y = parent[y];
            }
            System.out.println(ans);
        }
		
		sc.close();
	}
}

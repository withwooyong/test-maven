package algorithm.baekjoon.beginner.F_graph1;

import java.util.ArrayList;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1707 {	
	
	private static Logger log = LoggerFactory.getLogger(P1707.class); 

	/*
	 * 이분 그래프
	 * 문제
그래프의 정점의 집합을 둘로 분할하여, 각 집합에 속한 정점끼리는 서로 인접하지 않도록 분할할 수 있을 때, 그러한 그래프를 특별히 이분 그래프 (Bipartite Graph) 라 부른다.

그래프가 입력으로 주어졌을 때, 이 그래프가 이분 그래프인지 아닌지 판별하는 프로그램을 작성하시오. 입력으로 주어지는 그래프는 단순 그래프이다.

입력
입력은 여러 개의 테스트 케이스로 구성되어 있는데, 첫째 줄에 테스트 케이스의 개수 K(2≤K≤5)가 주어진다. 각 테스트 케이스의 첫째 줄에는 그래프의 정점의 개수 V(1≤V≤20,000)와 간선의 개수 E(1≤E≤200,000)가 빈 칸을 사이에 두고 순서대로 주어진다. 각 정점에는 1부터 N까지 차례로 번호가 붙어 있다. 이어서 둘째 줄부터 E개의 줄에 걸쳐 간선에 대한 정보가 주어지는데, 각 줄에 인접한 두 정점의 번호가 빈 칸을 사이에 두고 주어진다.

출력
K개의 줄에 걸쳐 입력으로 주어진 그래프가 이분 그래프이면 YES, 아니면 NO를 순서대로 출력한다.

2
3 2
1 3
2 3
4 4
1 2
2 3
3 4
4 2

YES
NO
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/50e0fdd4ccdc424c4a6b564fa2e2cae7
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            ArrayList<Integer>[] a = (ArrayList<Integer>[]) new ArrayList[n+1];
            for (int i=1; i<=n; i++) {
                a[i] = new ArrayList<Integer>();
            }
            for (int i=0; i<m; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                a[u].add(v);
                a[v].add(u);
            }
            int[] color = new int[n+1];
            boolean ok = true;
            for (int i=1; i<=n; i++) {
                if (color[i] == 0) {
                    dfs(a, color, i, 1);
                }
            }
            for (int i=1; i<=n; i++) {
                for (int j : a[i]) {
                    if (color[i] == color[j]) {
                        ok = false;
                    }
                }
            }
            if (ok) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
		
		sc.close();
	}
	
	public static void dfs(ArrayList<Integer>[] a, int[] color, int x, int c) {
        color[x] = c;
        for (int y : a[x]) {
            if (color[y] == 0) {
                dfs(a, color, y, 3-c);
            }
        }
    }
}

package algorithm.baekjoon.middle2.D_tree2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11437 {	
	
	private static Logger log = LoggerFactory.getLogger(P11437.class); 

	/*
	 * LCA
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	735	345	215	45.455%
문제
N(2 ≤ N ≤ 50,000)개의 정점으로 이루어진 트리가 주어진다. 트리의 각 정점은 1번부터 N번까지 번호가 매겨져 있으며, 루트는 1번이다.

두 노드의 쌍 M(1 ≤ M ≤ 10,000)개가 주어졌을 때, 두 노드의 가장 가까운 공통 조상이 몇 번인지 출력한다.

입력
첫째 줄에 노드의 개수 N이 주어지고, 다음 N-1개 줄에는 트리 상에서 연결된 두 정점이 주어진다. 그 다음 줄에는 가장 가까운 공통 조상을 알고싶은 쌍의 개수 M이 주어지고, 다음 M개 줄에는 정점 쌍이 주어진다.

출력
M개의 줄에 차례대로 입력받은 두 정점의 가장 가까운 공통 조상을 출력한다.

예제 입력  복사
15
1 2
1 3
2 4
3 7
6 2
3 8
4 9
2 5
5 11
7 13
10 4
11 15
12 5
14 7
6
6 11
10 9
2 6
7 6
8 13
8 15
예제 출력  복사
2
4
2
1
3
1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/09b029f637354a4a3582 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] a = (List<Integer>[]) new List[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = new ArrayList<Integer>();
        }
        for (int i=1; i<=n-1; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[x].add(y);
            a[y].add(x);
        }
        int[] depth = new int[n+1];
        boolean[] check = new boolean[n+1];
        int[] parent = new int[n+1];
        int start = 1;
        Queue<Integer> q = new LinkedList<Integer>();
        check[start] = true;
        depth[start] = 0;
        parent[start] = 0;
        q.add(start);
        while (!q.isEmpty()) {
            int x = q.remove();
            for (int y : a[x]) {
                if (check[y] == false) {
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
            if (depth[x] < depth[y]) {
                int temp = x;
                x = y;
                y = temp;
            }
            while (depth[x] != depth[y]) {
                x = parent[x];
            }
            while (x != y) {
                x = parent[x];
                y = parent[y];
            }
            System.out.println(x);
        }
		
		sc.close();
	}
}

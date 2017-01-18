package algorithm.baekjoon.middle2.C_graph2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1197 {	
	
	private static Logger log = LoggerFactory.getLogger(P1197.class); 

	/*
	 * 최소 스패닝 트리
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	4289	1681	866	33.947%
문제
그래프가 주어졌을 때, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.

최소 스패닝 트리는, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 그 가중치의 합이 최소인 트리를 말한다.

입력
첫째 줄에 정점의 개수 V(1≤V≤10,000)와 간선의 개수 E(1≤E≤100,000)가 주어진다. 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A, B, C가 주어진다. 이는 A번 정점과 B번 정점이 가중치 C인 간선으로 연결되어 있다는 의미이다. C는 음수일 수도 있으며, 절대값이 1,000,000을 넘지 않는다.

출력
첫째 줄에 최소 스패닝 트리의 가중치를 출력한다.

예제 입력  복사
3 3
1 2 1
2 3 2
1 3 3
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	static class Compare implements Comparator<Edge> {
        public int compare(Edge one, Edge two) {
            return Integer.compare(one.cost, two.cost);
        }
    }
    public static void disjoint_union(int [] p, int x, int y) {
        x = find(p, x);
        y = find(p, y);
        p[x] = y;
    }
    public static int find(int[] p, int x) {
        if (x == p[x]) {
            return x;
        } else {
            return (p[x] = find(p, p[x]));
        }
    }
    
	/*
	 * https://gist.github.com/Baekjoon/22d20713a18608a2fbd2
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] p = new int[n+1];
        for (int i=0; i<=n; i++) {
            p[i] = i;
        }
        ArrayList<Edge> a = new ArrayList<Edge>();
        for (int i=0; i<m; i++) {
            a.add(new Edge(sc.nextInt(), sc.nextInt(), sc.nextInt()));
        }
        Collections.sort(a, new Compare());
        int ans = 0;
        for (Edge e : a) {
            int x = find(p, e.start);
            int y = find(p, e.end);
            if (x != y) {
                disjoint_union(p, e.start, e.end);
                ans += e.cost;
            }
        }
        System.out.println(ans);
		
		sc.close();
	}
}

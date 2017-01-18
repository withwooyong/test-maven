package algorithm.baekjoon.middle2.C_graph2;

import java.util.Scanner;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1865 {	
	
	private static Logger log = LoggerFactory.getLogger(P1865.class); 

	/*
	 * 웜홀
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1469	480	291	30.957%
문제
때는 2020년, 백준이는 월드나라의 한 국민이다. 월드 나라에는 N개의 지점이 있고 N개의 지점 사이에는 M개의 도로와 W개의 웜홀이 있다. (단 도로는 방향이 없으며 웜홀은 방향이 있다.) 웜홀은 시작 위치에서 도착 위치로 가는 하나의 경로인데, 특이하게도 도착을 하게 되면 시작을 하였을 때 보다 시간이 뒤로 가게 된다. 웜홀 내에서는 시계가 거꾸로 간다고 생각하여도 좋다.

시간 여행을 매우 좋아하는 백준이는 한 가지 궁금점에 빠졌다. 어떤 지점에서 출발을 하여서 시간여행을 하기 시작하여 다시 출발을 하였던 위치로 돌아왔을 때, 출발을 하였을 때 보다 시간이 되돌아 가 있는 경우가 있는지 없는지 궁금해졌다. 여러분은 백준이를 도와 이런 일이 가능한지 불가능한지 구하는 프로그램을 작성하여라.

입력
첫번째 줄에는 테스트케이스의 개수 T(1≤T≤5)가 주어진다. 그리고 두 번째 줄부터 T개의 테스트케이스가 차례로 주어지는데 각 테스트케이스의 첫번째 줄에는 지점의 수 N(1≤N≤500), 도로의 개수 M(1≤M≤2500), 웜홀의 개수(1≤W≤200)이 주어진다. 그리고 두번째 줄부터 M+1개의 줄까지 도로의 정보가 주어지는데 각 도로의 정보는 S,E,T 세 정수로 주어진다. S와 E는 연결된 지점의 번호, T는 이 도로를 통해 이동하는데 걸리는 시간을 의미한다. 그리고 M+2~M+W+1번째 줄까지 웜홀의 정보가 S,E,T 세 정수로 주어지는데 S는 시작 지점, E는 도착 지점, T는 줄어드는 시간을 의미한다.

두 지점을 연결하는 도로가 한 개보다 많을 수도 있다.

출력
T개의 줄에 걸쳐서 만약에 시간이 줄어들면서 출발 위치로 돌아오는 것이 가능하면 YES, 불가능하면 NO를 출력한다.

예제 입력  복사
2
3 3 1
1 2 2
1 3 4
2 3 1
3 1 3
3 2 1
1 2 3
2 3 4
3 1 8
예제 출력  복사
NO
YES
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/e0fdb841ab360f806bc6
	 */
	static final int inf = 100000000;
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            int w = sc.nextInt();
            Vector<Edge2> a = new Vector<Edge2>();
            for (int i=0; i<m; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                a.add(new Edge2(x,y,z));
                a.add(new Edge2(y,x,z));
            }
            for (int i=2*m; i<2*m+w; i++) {
                int x = sc.nextInt();
                int y = sc.nextInt();
                int z = sc.nextInt();
                a.add(new Edge2(x,y,-z));
            }
            int[] d = new int[n+1];
            for (int i=1; i<=n; i++) {
                d[i] = inf;
            }
            d[1] = 0;
            m = 2*m+w;
            boolean ok = false;
            for (int i=1; i<=n; i++) {
                for (Edge2 e : a) {
                    int x = e.from;
                    int y = e.to;
                    int z = e.cost;
                    if (d[x] != inf && d[y] > d[x]+z) {
                        d[y] = d[x]+z;
                        if (i == n) {
                            ok = true;
                        }
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
}

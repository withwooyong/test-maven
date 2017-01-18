package algorithm.baekjoon.middle2.C_graph2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2056 {	
	
	private static Logger log = LoggerFactory.getLogger(P2056.class); 

	/*
	 * 작업
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1257	486	330	36.464%
문제
수행해야 할 작업 N개 (3 <= N <= 10000)가 있다. 각각의 작업마다 걸리는 시간(1 <= 시간 <= 100)이 정수로 주어진다.

몇몇 작업들 사이에는 선행 관계라는 게 있어서, 어떤 작업을 수행하기 위해 반드시 먼저 완료되어야 할 작업들이 있다. 이 작업들은 번호가 아주 예쁘게 매겨져 있어서, K번 작업에 대해 선행 관계에 있는(즉, K번 작업을 시작하기 전에 반드시 먼저 완료되어야 하는) 작업들의 번호는 모두 1 이상 (K-1) 이하이다. 작업들 중에는, 그것에 대해 선행 관계에 있는 작업이 하나도 없는 작업이 반드시 하나 이상 존재한다. (1번 작업이 항상 그러하다)

모든 작업을 완료하기 위해 필요한 최소 시간을 구하여라. 물론, 서로 선행 관계가 없는 작업들은 동시에 수행 가능하다.

입력
첫째 줄에 N이 주어진다.

두번째 줄부터 N+1번째 줄까지 N개의 줄이 주어진다. 2번째 줄은 1번 작업, 3번째 줄은 2번 작업, ..., N+1번째 줄은 N번 작업을 각각 나타낸다. 각 줄의 처음에는, 해당 작업에 걸리는 시간이 먼저 주이지고, 그 다음에 그 작업에 대해 선행 관계에 있는 작업들의 개수(0 <= 개수 <= 100)가 주어진다. 그리고 선행 관계에 있는 작업들의 번호가 주어진다.

출력
첫째 줄에 모든 작업을 완료하기 위한 최소 시간을 출력한다.

예제 입력  복사
7
5 0
1 1 1
3 1 2
6 1 1
1 2 2 4
8 2 2 4
4 3 3 5 6
예제 출력  복사
23
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/13769e8e0f06fa9ae383
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        List<Integer>[] a = (List<Integer>[]) new List[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = new ArrayList<Integer>();
        }
        int[] ind = new int[n+1];
        int[] work = new int[n+1];
        int[] d = new int[n+1];
        for (int i=1; i<=n; i++) {
            work[i] = sc.nextInt();
            int cnt = sc.nextInt();
            for (int j=0; j<cnt; j++) {
                int x = sc.nextInt();
                a[x].add(i);
                ind[i] += 1;
            }
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=1; i<=n; i++) {
            if (ind[i] == 0) {
                q.add(i);
                d[i] = work[i];
            }
        }
        for (int k=0; k<n; k++) {
            int x = q.remove();
            for (int y : a[x]) {
                ind[y] -= 1;
                if (d[y] < d[x]+work[y]) {
                    d[y] = d[x]+work[y];
                }
                if (ind[y] == 0) {
                    q.add(y);
                }
            }
        }
        int ans = 0;
        for (int i=1; i<=n; i++) {
            ans = Math.max(ans,d[i]);
        }
        System.out.println(ans);
		
		sc.close();
	}
}

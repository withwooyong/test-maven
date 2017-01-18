package algorithm.baekjoon.middle2.C_graph2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2252 {	
	
	private static Logger log = LoggerFactory.getLogger(P2252.class); 

	/*
	 * 줄 세우기 스페셜 저지
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1732	860	536	49.722%
문제
N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다. 그나마도 모든 학생들을 다 비교해 본 것이 아니고, 일부 학생들의 키만을 비교해 보았다.

일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤32,000), M(1≤M≤100,000)이 주어진다. M은 키를 비교한 회수이다. 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A, B가 주어진다. 이는 학생 A가 학생 B의 앞에 서야 한다는 의미이다.

학생들의 번호는 1번부터 N번이다.

출력
첫째 줄부터 앞에서부터 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.

예제 입력  복사
3 2
1 3
2 3
예제 출력  복사
1 2 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/f62c5a1d4324f28e0c14
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        List<Integer>[] a = (List<Integer>[]) new List[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = new ArrayList<Integer>();
        }
        int[] ind = new int[n+1];
        for (int i=0; i<m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            a[x].add(y);
            ind[y] += 1;
        }
        Queue<Integer> q = new LinkedList<Integer>();
        for (int i=1; i<=n; i++) {
            if (ind[i] == 0) {
                q.add(i);
            }
        }
        for (int k=0; k<n; k++) {
            int x = q.remove();
            System.out.print(x + " ");
            for (int y : a[x]) {
                ind[y] -= 1;
                if (ind[y] == 0) {
                    q.add(y);
                }
            }
        }
        System.out.println();
		
		sc.close();
	}
}

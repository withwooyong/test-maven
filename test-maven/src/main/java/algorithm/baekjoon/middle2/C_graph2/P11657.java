package algorithm.baekjoon.middle2.C_graph2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11657 {	
	
	private static Logger log = LoggerFactory.getLogger(P11657.class); 

	/*
	 * 타임머신
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	1919	798	435	39.654%
문제
N개의 도시가 있다. 그리고 한 도시에서 출발하여 다른 도시에 도착하는 버스가 M개 있다. 각 버스는 A, B, C로 나타낼 수 있는데, A는 시작도시, B는 도착도시, C는 버스를 타고 이동하는데 걸리는 시간이다. 시간 C가 양수가 아닌 경우가 있다. C = 0인 경우는 순간 이동을 하는 경우, C < 0인 경우는 타임머신으로 시간을 되돌아가는 경우이다.

1번 도시에서 출발해서 나머지 도시로 가는 가장 빠른 시간을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 도시의 개수 N (1 ≤ N ≤ 500), 버스 노선의 개수 M (1 ≤ M ≤ 6,000)이 주어진다. 둘째 줄부터 M개의 줄에는 버스 노선의 정보 A, B, C (1 ≤ A, B ≤ N, -10,000 ≤ C ≤ 10,000)가 주어진다. 

출력
첫째 줄에는 2번 도시까지 가는 가장 빠른 시간, ..., N-1번째 줄에는 N번 도시까지 가는 가장 빠른 시간을 출력한다. 어떤 도시로 가는 가장 빠른 시간이 없는 경우에는 -1을 출력한다.

만약, 시작점에서 도달 가능한 타임머신으로 되어있는 사이클이 존재해 가장 빠른 시간이 존재하지 않는 경우에는 -1을 출력한다.

예제 입력  복사
3 4
1 2 4
1 3 3
2 3 -1
3 1 -2
예제 출력  복사
4
3
예제 입력 2  복사
3 4
1 2 4
1 3 3
2 3 -4
3 1 -2
예제 출력 2  복사
-1
예제 입력 3  복사
3 2
1 2 4
1 2 3
예제 출력 3  복사
3
-1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/2376cd9ee0e01a284ad1
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

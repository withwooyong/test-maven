package algorithm.baekjoon.middle2.D_tree2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P3176 {	
	
	private static Logger log = LoggerFactory.getLogger(P3176.class); 

	/*
	 * 도로 네트워크
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	420	138	85	31.599%
문제
N개의 도시와 그 도시를 연결하는 N-1개의 도로로 이루어진 도로 네트워크가 있다. 

모든 도시의 쌍에는 그 도시를 연결하는 유일한 경로가 있고, 각 도로의 길이는 입력으로 주어진다.

총 K개의 도시 쌍이 주어진다. 이 때, 두 도시를 연결하는 경로 상에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (2 ≤ N ≤ 100,000)

다음 N-1개 줄에는 도로를 나타내는 세 정수 A, B, C가 주어진다. A와 B사이에 길이가 C인 도로가 있다는 뜻이다. 도로의 길이는 1,000,000보다 작거나 같은 양의 정수이다.

다음 줄에는 K가 주어진다. (1 ≤ K ≤ 100,000)

다음 K개 줄에는 서로 다른 두 자연수 D와 E가 주어진다. D와 E를 연결하는 경로에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 구해서 출력하면 된다.

출력
총 K개 줄에 D와 E를 연결하는 경로에서 가장 짧은 도로의 길이와 가장 긴 도로의 길이를 출력한다.

예제 입력  복사
5
2 3 100
4 3 200
1 5 150
1 3 50
3
2 4
3 5
1 2
예제 출력  복사
100 200
50 150
50 100
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/4bf3caafe1bdd5fbb8b3 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

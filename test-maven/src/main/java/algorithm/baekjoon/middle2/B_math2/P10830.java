package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10830 {	
	
	private static Logger log = LoggerFactory.getLogger(P10830.class); 

	/*
	 * 행렬 제곱
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	488	178	156	40.838%
문제
크기가 N*N인 행렬 A가 주어진다. 이 때, A의 B제곱을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으니, A^B의 각 원소를 1,000으로 나눈 나머지를 출력한다.

입력
첫째 줄에 행렬의 크기 N과 B가 주어진다. (2 ≤ N ≤  5, 1 ≤ B ≤ 100,000,000,000)

둘째 줄부터 N개의 줄에 행렬의 각 원소가 주어진다. 행렬의 각 원소는 1,000보다 작거나 같은 자연수 또는 0이다.

출력
첫째 줄부터 N개의 줄에 걸쳐 행렬 A를 B제곱한 결과를 출력한다.

예제 입력  복사
2 5
1 2
3 4
예제 출력  복사
69 558
337 406
예제 입력 2  복사
3 3
1 2 3
4 5 6
7 8 9
예제 출력 2  복사
468 576 684
62 305 548
656 34 412
예제 입력 3  복사
5 10
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
1 0 0 0 1
예제 출력 3  복사
512 0 0 0 512
512 0 0 0 512
512 0 0 0 512
512 0 0 0 512
512 0 0 0 512

	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/53da6550ac5ca0c7608d
	 * 연산자 오버로딩 https://gist.github.com/Baekjoon/a5d855c9f20ad45ef6c9
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

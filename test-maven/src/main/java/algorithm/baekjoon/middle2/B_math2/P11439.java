package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11439 {	
	
	private static Logger log = LoggerFactory.getLogger(P11439.class); 

	/*
	 * 이항 계수 5
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	99	27	21	35.000%
문제
자연수 N과 정수 K가 주어졌을 때 이항 계수 (NK)를 M으로 나눈 나머지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N, K와 M이 주어진다. (1 ≤ N ≤ 4×106, 0 ≤ K ≤ N, 2 ≤ M ≤ 4×106)

출력
 (NK)를 M으로 나눈 나머지를 출력한다.

예제 입력  복사
5 2 3
예제 출력  복사
1
예제 입력 2  복사
30 3 3
예제 출력 2  복사
1
예제 입력 3  복사
100 45 13
예제 출력 3  복사
2
예제 입력 4  복사
100 46 72
예제 출력 4  복사
48
예제 입력 5  복사
50 23 9
예제 출력 5  복사
1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/237269bdf7fa2ea98520
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

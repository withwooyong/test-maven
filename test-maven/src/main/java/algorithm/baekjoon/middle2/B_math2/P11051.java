package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11051 {	
	
	private static Logger log = LoggerFactory.getLogger(P11051.class); 

	/*
	 * 이항 계수 2
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	2024	778	632	42.359%
문제
자연수 N과 정수 K가 주어졌을 때 이항 계수 (NK)를 10,007로 나눈 나머지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 1,000, 0 ≤ K ≤ N)

출력
 (NK)를 10,007로 나눈 나머지를 출력한다.

예제 입력  복사
5 2
예제 출력  복사
10
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/318692441d185275e5a3
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

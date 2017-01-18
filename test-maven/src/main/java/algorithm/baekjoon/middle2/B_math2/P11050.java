package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11050 {	
	
	private static Logger log = LoggerFactory.getLogger(P11050.class); 

	/*
	 * 이항 계수 1
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	1226	829	723	70.262%
문제
자연수 과 정수 가 주어졌을 때 이항 계수 를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 과 가 주어진다. (1 ≤  ≤ 10, 0 ≤  ≤ )

출력
 를 출력한다.

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
	 * https://gist.github.com/Baekjoon/28960ad31bc2c134a6e4
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

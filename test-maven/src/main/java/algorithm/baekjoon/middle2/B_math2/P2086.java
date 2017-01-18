package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2086 {	
	
	private static Logger log = LoggerFactory.getLogger(P2086.class); 

	/*
	 * 피보나치 수의 합
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	497	140	112	33.837%
문제
제 1항과 제 2항을 1이라 하고, 제 3항부터는 앞의 두 항의 합을 취하는 수열을 피보나치(fibonacci) 수열이라고 한다. 예를 들어 제 3항은 2이며, 제 4항은 3이다.

피보나치 수열의 a번째 항부터 b번째 항까지의 합을 구하는 프로그램을 작성하시오. 수가 매우 커질 수 있으므로 마지막 아홉 자리만을 구하도록 한다. 즉 1,000,000,000으로 나눈 나머지를 구하면 된다.

입력
첫째 줄에 a와 b(1≤a≤b≤9,000,000,000,000,000,000)이 주어진다.

출력
첫째 줄에 구한 합을 출력한다.

예제 입력  복사
4 10
예제 출력  복사
139
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

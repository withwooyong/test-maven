package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11689 {	
	
	private static Logger log = LoggerFactory.getLogger(P11689.class); 

	/*
	 * GCD(n, k) = 1
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	225	84	63	44.366%
문제
자연수 n이 주어졌을 때, GCD(n, k) = 1을 만족하는 자연수 1 ≤ k ≤ n 의 개수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 n (1 ≤ n ≤ 1012)이 주어진다.

출력
GCD(n, k) = 1을 만족하는 자연수 1 ≤ k ≤ n 의 개수를 출력한다.

예제 입력  복사
1
예제 출력  복사
1
예제 입력 2  복사
5
예제 출력 2  복사
4
예제 입력 3  복사
10
예제 출력 3  복사
4
예제 입력 4  복사
45
예제 출력 4  복사
24
예제 입력 5  복사
99
예제 출력 5  복사
60
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/47e8d9b2a2e60253e22f
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

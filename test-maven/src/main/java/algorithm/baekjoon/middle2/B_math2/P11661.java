package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11661 {	
	
	private static Logger log = LoggerFactory.getLogger(P11661.class); 

	/*
	 * 해의 개수
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	38	4	3	10.714%
문제
방정식 Ax + By + C = 0의 해의 개수를 구하는 프로그램을 작성하시오.

A, B, C, x, y는 모두 정수이고, x1 ≤ x ≤ x2, y1 ≤ y ≤ y2인 해의 개수를 구해야 한다.

입력
첫째 줄에 A, B, C, x1, x2, y1, y2가 주어진다. 모든 정수는 -108보다 크거나 같고, 108보다 작거나 같은 정수이다.

출력
첫째 줄에 입력으로 주어진 방정식의 해의 개수를 출력한다.

예제 입력  복사
1 1 1 -20 20 -30 30
예제 출력  복사
41
예제 입력 2  복사
20 30 40 50 60 70 80
예제 출력 2  복사
0
예제 입력 3  복사
-100 20 300 -100 200 -2000 3000
예제 출력 3  복사
301
예제 입력 4  복사
-10 8 4 -100 100 -200 300
예제 출력 4  복사
50
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

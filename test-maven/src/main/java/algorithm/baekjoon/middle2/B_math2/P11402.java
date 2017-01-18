package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11402 {	
	
	private static Logger log = LoggerFactory.getLogger(P11402.class); 

	/*
	 * 이항 계수 4
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	202	80	60	41.958%
문제
자연수 N과 정수 K가 주어졌을 때 이항 계수 (NK)를 M으로 나눈 나머지를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N, K와 M이 주어진다. (1 ≤ N ≤ 1018, 0 ≤ K ≤ N, 2 ≤ M ≤ 2,000, M은 소수)

출력
 (NK)를 M으로 나눈 나머지를 출력한다.

예제 입력  복사
5 2 3
예제 출력  복사
1
예제 입력 2  복사
30 10 3
예제 출력 2  복사
0
예제 입력 3  복사
30 3 3
예제 출력 3  복사
1
예제 입력 4  복사
100 45 7
예제 출력 4  복사
0
예제 입력 5  복사
100 45 13
예제 출력 5  복사
2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/69fb9c3f786e0e1855da
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

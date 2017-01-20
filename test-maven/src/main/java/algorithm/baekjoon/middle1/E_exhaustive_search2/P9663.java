package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9663 {	
	
	private static Logger log = LoggerFactory.getLogger(P9663.class); 

	/*
	 * N-Queen
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
10 초	128 MB	3230	1632	1224	51.646%
문제
N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제이다.

N이 주어졌을 때, 퀸을 놓는 방법의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N이 주어진다. (1 ≤ N < 15)

출력
첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력한다.

예제 입력  복사
8
예제 출력  복사
92
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/7ce9963ec6d292ad9cfd2aeb3face717
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

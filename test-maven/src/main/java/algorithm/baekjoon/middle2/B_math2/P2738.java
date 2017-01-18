package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2738 {	
	
	private static Logger log = LoggerFactory.getLogger(P2738.class); 

	/*
	 * 행렬 덧셈
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	680	538	477	79.899%
문제
N*M크기의 두 행렬 A와 B가 주어졌을 때, 두 행렬을 더하는 프로그램을 작성하시오.

입력
첫째 줄에 행렬의 크기 N 과 M이 주어진다. 둘째줄부터 N개의 줄에 행렬 A의 원소 M개가 차레대로 주어진다. 이어서 N개의 줄에 행렬 B의 원소 M개가 차례대로 주어진다. N과 M은 100보다 작거나 같고, 행렬의 원소는 절대값이 100보다 작거나 같은 정수이다.

출력
첫째줄부터 N개의 줄에 행렬 A와 B를 더한 행렬을 출력한다. 행렬의 각 원소는 공백으로 구분한다.

예제 입력  복사
3 3
1 1 1
2 2 2
0 1 0
3 3 3
4 4 4
5 5 100
예제 출력  복사
4 4 4
6 6 6
5 6 100
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

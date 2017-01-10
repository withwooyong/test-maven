package algorithm.baekjoon.middle1.C_binary_search;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2022 {	
	
	private static Logger log = LoggerFactory.getLogger(P2022.class); 

	/*
	 * 사다리
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	249	84	57	31.148%
문제
아래의 그림과 같이 높은 빌딩 사이를 따라 좁은 길이 나있다. 두 개의 사다리가 있는데 길이가 x인 사다리는 오른쪽 빌딩의 아래를 받침대로 하여 왼쪽 빌딩에 기대져 있고 길이가 y인 사다리는 왼쪽 빌딩의 아래를 받침대로 하여 오른쪽 빌딩에 기대져 있다. 그리고 두 사다리는 땅에서부터 정확하게 c인 지점에서 서로 교차한다. 그렇다면 두 빌딩은 얼마나 떨어져 있는 걸까?



입력
첫째 줄에 차례대로 x, y, c에 해당하는 양의 실수 세 개가 입력된다.

출력
두 빌딩사이에 너비가 되는 수치를 소수점 4째 자리에서 반올림 하여 소수점 3째 자리까지 출력한다.

예제 입력  복사
30 40 10
12.619429 8.163332 3
10 10 3
10 10 1
예제 출력  복사
26.033
7.000
8.000
9.798
	 * 자바 소스 업음
	 * https://gist.github.com/Baekjoon/5a2bd2a33eb855a0144ee38708431fe5
	 * https://gist.github.com/Baekjoon/3472fd75d793be0140a99a232593d1d8
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

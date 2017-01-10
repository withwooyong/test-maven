package algorithm.baekjoon.middle1.B_divide_conquer;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2261 {

	private static Logger log = LoggerFactory.getLogger(P2261.class);

	/*
	 * 가장 가까운 두 점
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 128 MB 2667 401 205 14.664%
	 * 문제
	 * 2차원 평면상에 n개의 점이 주어졌을 때, 이 점들 중 가장 가까운 두 점을 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 자연수 n(2≤n≤100,000)이 주어진다. 다음 n개의 줄에는 차례로 각 점의 x, y좌표가 주어진다. 각각의 좌표는 절댓값이 10,000을 넘지 않는 정수이다.
	 * 
	 * 출력
	 * 첫째 줄에 가장 가까운 두 점의 거리의 제곱을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 4
	 * 0 0
	 * 10 10
	 * 0 10
	 * 10 0
	 * 예제 출력 복사
	 * 100
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/4d240e06d2ec28fc8b78
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}
}

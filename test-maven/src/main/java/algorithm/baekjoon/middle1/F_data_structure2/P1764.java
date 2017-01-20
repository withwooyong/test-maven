package algorithm.baekjoon.middle1.F_data_structure2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1764 {

	private static Logger log = LoggerFactory.getLogger(P1764.class);

	/*
	 * 듣보잡
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 1951 790 581 42.224%
	 * 문제
	 * 김진영이 듣도 못한 사람의 명단과, 보도 못한 사람의 명단이 주어질 때, 듣도 보도 못한 사람의 명단을 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 듣도 못한 사람의 수 N, 보도 못한 사람의 수 M이 주어진다. 이어서 둘째 줄부터 N개의 줄에 걸쳐 듣도 못한 사람의 이름과, N+2째 줄부터 보도 못한 사람의 이름이 순서대로 주어진다.
	 * 이름은 띄어쓰기 없이 영어 소문자로만 이루어지며, 그 길이는 20 이하이다. N, M은 500,000 이하의 자연수이다.
	 * 
	 * 출력
	 * 듣보잡의 수와 그 명단을 사전순으로 출력한다.
	 * 
	 * 예제 입력 복사
	 * 3 4
	 * ohhenrie
	 * charlie
	 * baesangwook
	 * obama
	 * baesangwook
	 * ohhenrie
	 * clinton
	 * 예제 출력 복사
	 * 2
	 * baesangwook
	 * ohhenrie
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * map https://gist.github.com/Baekjoon/eff5f116fbbb07231675
	 * set https://gist.github.com/Baekjoon/2f5caeca4fd25b8c4021
	 * merge sort https://gist.github.com/Baekjoon/385f7d8129d46dd353d7
	 * set_intersection https://gist.github.com/Baekjoon/130329076d21f97f76cc
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}
}

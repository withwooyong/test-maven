package algorithm.baekjoon.middle1.F_data_structure2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P7785 {

	private static Logger log = LoggerFactory.getLogger(P7785.class);

	/*
	 * 회사에 있는 사람
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 128 MB 679 330 253 50.000%
	 * 문제
	 * 상근이는 세계적인 소프트웨어 회사 기글에서 일한다. 이 회사의 가장 큰 특징은 자유로운 출퇴근 시간이다. 따라서, 직원들은 반드시 9시부터 6시까지 회사에 있지 않아도 된다.
	 * 
	 * 각 직원은 자기가 원할 때 출근할 수 있고, 아무때나 퇴근할 수 있다.
	 * 
	 * 상근이는 모든 사람의 출입카드 시스템의 로그를 가지고 있다. 이 로그는 어떤 사람이 회사에 들어왔는지, 나갔는지가 기록되어져 있다. 로그가 주어졌을 때, 현재 회사에 있는 모든 사람을 구하는 프로그램을
	 * 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 로그에 기록된 출입 기록의 수 n이 주어진다. (2 ≤ n ≤ 106) 다음 n개의 줄에는 출입 기록이 순서대로 주어지며, 각 사람의 이름이 주어지고 "enter"나 "leave"가 주어진다.
	 * "enter"인 경우는 출근, "leave"인 경우는 퇴근이다.
	 * 
	 * 출력
	 * 현재 회사에 있는 사람의 이름을 사전 순의 역순으로 한 줄에 한 명씩 출력한다.
	 * 
	 * 예제 입력 복사
	 * 4
	 * Baha enter
	 * Askar enter
	 * Baha leave
	 * Artem enter
	 * 예제 출력 복사
	 * Askar
	 * Artem
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/9ea0da3cf46c394dc1a5
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}
}

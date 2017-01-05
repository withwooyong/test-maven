package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10430 {

	private static Logger log = LoggerFactory.getLogger(P10430.class);

	/*
	 * (A+B)%C는 (A%C + B%C)%C 와 같을까?
	 * (A×B)%C는 (A%C × B%C)%C 와 같을까?
	 * 세 수 A, B, C가 주어졌을 때, 위의 네가지 값을 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 A, B, C가 순서대로 주어진다. (2 ≤ A, B, C ≤ 10000)
	 * 첫째 줄에 (A+B)%C, 둘째 줄에 (A%C + B%C)%C, 셋째 줄에 (A×B)%C, 넷째 줄에 (A%C × B%C)%C를 출력한다.
	 * 5 8 4
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int A = sc.nextInt();
		int B = sc.nextInt();
		int C = sc.nextInt();
		log.debug("{}", (A + B) % C);
		log.debug("{}", (A % C + B % C) % C);
		log.debug("{}", (A * B) % C);
		log.debug("{}", (A % C * B % C) % C);
		sc.close();
	}
}

package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11653 {	
	
	private static Logger log = LoggerFactory.getLogger(P11653.class); 

	/*
	 * 소인수분해
	 * 문제
	 * 정수 N이 주어졌을 때, 소인수분해하는 프로그램을 작성하시오.
	 * 입력
	 * 첫째 줄에 정수 N (1 ≤ N ≤ 10,000,000)이 주어진다.
	 * 출력
	 * N의 인수를 한 줄에 하나씩 증가하는 순서대로 출력한다.
	 * 
	 * 72
	 * 
	 * 2
	 * 2
	 * 2
	 * 3
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/6fc3d75e2751500bf18ea62cc7a59531
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		for (int i = 2; i * i <= n; i++) {
			while (n % i == 0) {
				System.out.println(i);
				n /= i;
			}
		}
		if (n > 1) {
			System.out.println(n);
		}

		sc.close();
	}
}

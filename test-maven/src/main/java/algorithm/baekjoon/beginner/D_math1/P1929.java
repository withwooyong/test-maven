package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1929 {	
	
	private static Logger log = LoggerFactory.getLogger(P1929.class); 

	/*
	 * 소수 구하기
	 * M이상 N이하의 소수를 모두 출력하는 프로그램을 작성하시오.
	 * 첫째 줄에 자연수 M과 N이 빈 칸을 사이에 두고 주어진다. (1≤M≤N≤1,000,000)
	 * 한 줄에 하나씩, 증가하는 순서대로 소수를 출력한다.
	 * 3 16
	 * 
	 * 3
	 * 5
	 * 7
	 * 11
	 * 13
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/ed716b579054862b599d14fef8a70f1b
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] check = new boolean[m + 1];
		check[0] = check[1] = true;
		for (int i = 2; i * i <= m; i++) {
			if (check[i] == true) {
				continue;
			}
			for (int j = i + i; j <= m; j += i) {
				check[j] = true;
			}
		}
		for (int i = n; i <= m; i++) {
			if (check[i] == false) {
				System.out.println(i);
			}
		}

		sc.close();
	}
}

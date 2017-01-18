package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1509 {	
	
	private static Logger log = LoggerFactory.getLogger(P1509.class); 

	/*
	 * 팰린드롬 분할
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1397	584	380	45.564%
문제
세준이는 어떤 문자열을 팰린드롬으로 분할하려고 한다.

예를 들어, ABACABA를 팰린드롬 분할하면, {A, B, A, C, A, B, A}, {A, BACAB, A}, {ABA, C, ABA}, {ABACABA}가 된다.

분할의 개수의 최소값을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 문자열이 주어진다. 이 문자열의 최대길이는 2,500이다.

출력
첫째 줄에 팰린드롬 분할의 개수의 최소값을 출력한다

예제 입력  복사
BBCDDECAECBDABADDCEBACCCBDCAABDBADD
예제 출력  복사
22

	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/5fd1bfa24a7a4c8de393
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine().trim();
		int n = s.length();
		s = " " + s;
		boolean[][] c = new boolean[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			c[i][i] = true;
		}
		for (int i = 1; i <= n - 1; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				c[i][i + 1] = true;
			}
		}
		for (int k = 2; k < n; k++) {
			for (int i = 1; i <= n - k; i++) {
				int j = i + k;
				c[i][j] = (s.charAt(i) == s.charAt(j) && c[i + 1][j - 1]);
			}
		}
		int[] d = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			d[i] = -1;
			for (int j = 1; j <= i; j++) {
				if (c[j][i]) {
					if (d[i] == -1 || d[i] > d[j - 1] + 1) {
						d[i] = d[j - 1] + 1;
					}
				}
			}
		}
		System.out.println(d[n]);

		sc.close();
	}
}

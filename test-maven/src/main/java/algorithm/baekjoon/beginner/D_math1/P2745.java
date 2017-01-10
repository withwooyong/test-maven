package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2745 {	
	
	private static Logger log = LoggerFactory.getLogger(P2745.class); 

	/*
	 * 진법 변환
	 * B진법 수 N이 주어진다. 이 수를 10진법으로 바꿔 출력하는 프로그램을 작성하시오.
	 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
	 * A: 10, B: 11, ..., F: 16, ..., Y: 34, Z: 35
	 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36)
	 * B진법 수 N을 10진법으로 바꾸면, 항상 10억보다 작거나 같다.
	 * 첫째 줄에 B진법 수 N을 10진법으로 출력한다.
	 * 
	 * ZZZZZ 36
	 * 
	 * 60466175
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/2abda395e1c625842be6f273255bc5ea
	 */
	private static void solution() {

		Scanner sc = new Scanner(System.in);
		String[] line = sc.nextLine().split(" ");
		String s = line[0];
		int b = Integer.valueOf(line[1]);
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if ('0' <= c && c <= '9') {
				ans = ans * b + (c - '0');
			} else {
				ans = ans * b + (c - 'A' + 10);
			}
		}
		System.out.println(ans);
		sc.close();
	}
}

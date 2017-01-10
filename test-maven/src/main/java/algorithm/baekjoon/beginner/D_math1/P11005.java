package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11005 {	
	
	private static Logger log = LoggerFactory.getLogger(P11005.class); 

	/*
	 * 진법 변환 2
	 * 10진법 수 N이 주어진다. 이 수를 B진법으로 바꿔 출력하는 프로그램을 작성하시오.
	 * 10진법을 넘어가는 진법은 숫자로 표시할 수 없는 자리가 있다. 이런 경우에는 다음과 같이 알파벳 대문자를 사용한다.
	 * A: 10, B: 11, ..., F: 16, ..., Y: 34, Z: 35
	 * 
	 * 첫째 줄에 N과 B가 주어진다. (2 ≤ B ≤ 36) N은 10억보다 작거나 같은 자연수이다.
	 * 첫째 줄에 10진법 수 N을 B진법으로 출력한다.
	 * 60466175 36
	 * 
	 * ZZZZZ
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	/*
	 * 10진법을 다른 진법(A)으로 나타내려면, 
	 * A로 계속나눠주면서 마지막 몫과 연산해왔던 나머지들을 순서에 맞게 출력하면 진법변환이 완료 된다.
	 * https://gist.github.com/Baekjoon/3b55b5bb398e7b4922ba769337544ee2
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int b = sc.nextInt();
		StringBuilder ans = new StringBuilder();
		while (n > 0) {
			int r = n % b;
			if (r < 10) {
				ans.append((char) (r + '0'));
			} else {
				ans.append((char) (r - 10 + 'A'));
			}
			n /= b;
		}
		System.out.println(ans.reverse());
		sc.close();
	}
}

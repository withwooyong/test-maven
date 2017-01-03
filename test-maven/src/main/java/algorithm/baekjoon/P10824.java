package algorithm.baekjoon;


import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10824 {

	private static Logger log = LoggerFactory.getLogger(P10824.class);

	/*
	 * 네 자연수 A, B, C, D가 주어진다. 이 때, A와 B를 붙인 수와 C와 D를 붙인 수의 합을 구하는 프로그램을 작성하시오.
	 * 두 수를 A와 B를 합치는 것은 A의 뒤에 B를 붙이는 것을 의미한다. 
	 * 즉, 20과 30을 붙이면 2030이 된다.
	 * 첫째 줄에 네 자연수 A, B, C, D가 주어진다. (1 ≤ A, B, C, D ≤ 1,000,000)
	 * A와 B를 붙인 수와 C와 D를 붙인 수의 합을 출력한다.
	 * 10 20 30 40
	 * 4060
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		if ((1 <= A && A <= 1000000) && (1 <= B && B <= 1000000) && (1 <= C && C <= 1000000) && (1 <= D && D <= 1000000)) {
			StringBuilder build1 = new StringBuilder();
			build1.append(A).append(B);		
			StringBuilder build2 = new StringBuilder();
			build2.append(C).append(D);
			System.out.println(Math.round(Double.parseDouble(build1.toString()) + Double.parseDouble(build2.toString())));
		}     
	}
}

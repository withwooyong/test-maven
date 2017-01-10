package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1212 {	
	
	private static Logger log = LoggerFactory.getLogger(P1212.class); 

	/*
	 * 8진수 2진수
	 * 8진수가 주어졌을 때, 2진수로 변환하는 프로그램을 작성하시오.
	 * 첫째 줄에 8진수가 주어진다. 주어지는 수의 길이는 333,334을 넘지 않는다.
	 * 첫째 줄에 주어진 수를 2진수로 변환하여 출력한다. 수가 0인 경우를 제외하고는 반드시 1로 시작해야 한다.
	 * 
	 * 314
	 * 
	 * 11001100
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/1fd4ea6807e8e7faeb0439c036f20911
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		String[] eight = { "000", "001", "010", "011", "100", "101", "110", "111" };
		String s = sc.nextLine();
		boolean start = false;
		if (s.length() == 1 && s.charAt(0) == '0') {
			System.out.print(0);
		}
		for (int i = 0; i < s.length(); i++) {
			int n = s.charAt(i) - '0';
			if (!start && n < 4) {
				if (n == 0)
					continue;
				else if (n == 1)
					System.out.print("1");
				else if (n == 2)
					System.out.print("10");
				else if (n == 3)
					System.out.print("11");
				start = true;
			} else {
				System.out.print(eight[n]);
				start = true;
			}
		}
		System.out.println();

		sc.close();
	}
}

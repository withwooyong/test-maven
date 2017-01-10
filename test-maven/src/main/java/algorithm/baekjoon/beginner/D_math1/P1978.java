package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1978 {	
	
	private static Logger log = LoggerFactory.getLogger(P1978.class); 

	/*
	 * 소수 찾기
	 * 주어진 숫자들 중 소수가 몇 개인지 찾아서 출력하는 프로그램을 작성하시오.
	 * 첫 줄에 수의 개수 N이 주어진다. N은 100이하이다. 다음으로 N개의 수가 주어지는데 수는 1,000 이하의 자연수이다.
	 * 주어진 수들 중 소수의 개수를 출력한다.
	 * 
	 * 4
	 * 1 3 5 7
	 * 
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/4132a4e944d689e9119b6d081ebcab2b
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int ans = 0;
		while (n-- > 0) {
			if (is_prime(sc.nextInt())) {
				ans += 1;
			}
		}
		System.out.println(ans);

		sc.close();
	}

	public static boolean is_prime(int x) {
		if (x <= 1) {
			return false;
		} else if (x == 2) {
			return true;
		}
		for (int i = 2; i * i <= x; i++) {
			if (x % i == 0) {
				return false;
			}
		}
		return true;
	}
}

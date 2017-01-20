package algorithm.baekjoon.beginner.D_math1;

import java.math.BigInteger;
import java.util.Scanner;

import org.hibernate.type.BigIntegerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9613 {	
	
	private static Logger log = LoggerFactory.getLogger(P9613.class); 

	/*
	 * GCD 합
	 * 양의 정수 n개가 주어졌을 때, 가능한 모든 쌍의 GCD의 합을 구하는 프로그램을 작성하시오.
	 * 첫째 줄에 테스트 케이스의 개수 t (1 < t < 100)이 주어진다. 
	 * 각 테스트 케이스는 한 줄로 이루어져 있고, 첫째 줄에는 수의 개수 n (1 < n < 100)가 주어지고, 
	 * 다음에는 n개의 수가 주어진다. 입력으로 주어지는 수는 1000000을 넘지 않는다.
	 * 
	 * 각 테스트 케이스마다 가능한 모든 쌍의 GCD의 합을 출력한다.
	 * 
	 * 3
	 * 4 10 20 30 40
	 * 3 7 5 12
	 * 3 125 15 25
	 * 
	 * 70
	 * 3
	 * 35
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/2dcfe756827e505f1f1cf1f6552ef305
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int[] a = new int[n];
			for (int i = 0; i < n; i++) {
				a[i] = sc.nextInt();
			}
			// BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)));
			int ans = 0;
			for (int i = 0; i < n - 1; i++) {
				for (int j = i + 1; j < n; j++) {					
					ans += gcd(a[i], a[j]);
				}
			}
			System.out.println(ans);
		}
		sc.close();
	}

	public static int gcd(int x, int y) {
		if (y == 0) {
			return x;
		} else {
			return gcd(y, x % y);
		}
	}

	/*
	 * 출처: http://ksj14.tistory.com/entry/BackJoon9613-GCD-합 [:: ENJOY ::]
	 */
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		int n;
		int i, j;
		long sum;
		int[] num = new int[101];

		while (t-- > 0) {
			sum = 0;
			n = sc.nextInt();

			for (i = 0; i < n; i++) {
				num[i] = sc.nextInt();
			}

			for (i = 0; i < n - 1; i++) {
				for (j = i + 1; j < n; j++) {
					sum += gcd2(num[i], num[j]);
				}
			}
			System.out.println(sum);
		}

		sc.close();
	}

	private static int gcd2(int n, int m) {
		if (m == 0)
			return n;
		return gcd(m, n % m);
	}	
}

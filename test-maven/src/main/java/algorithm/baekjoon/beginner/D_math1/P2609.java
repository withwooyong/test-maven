package algorithm.baekjoon.beginner.D_math1;

import java.math.BigInteger;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2609 {

	private static Logger log = LoggerFactory.getLogger(P2609.class);

	/*
	 * 두 개의 자연수를 입력받아 최대 공약수와 최소 공배수를 출력하는 프로그램을 작성하시오.
	 * 첫째 줄에는 두 개의 자연수가 주어진다. 이 둘은 10,000이하의 자연수이며 사이에 한 칸의 공백이 주어진다.
	 * 첫째 줄에는 입력으로 주어진 두 수의 최대공약수를,둘째 줄에는 입력으로 주어진 두 수의 최소 공배수를 출력한다.
	 * 24 18
	 * 
	 * 6
	 * 72
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/68dd288070993c73d0e9f8711316ea5c
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int a = sc.nextInt();
		int b = sc.nextInt();
		int g = gcd(a, b); // 최대공약수
		int l = a * b / g; // 최소공배수
		System.out.println(g);
		System.out.println(l);
		sc.close();
	}
	
	private static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        } else {
            return gcd(y, x%y);
        }
    }

	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		log.debug("gcd {}", BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)));
		log.debug("lcm {}", a * (b / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue()));
		System.out.println(BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)));
		System.out.println(a * (b / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue()));
		sc.close();
	}
	
	
}


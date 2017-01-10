package algorithm.baekjoon.beginner.D_math1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1934 {

	private static Logger log = LoggerFactory.getLogger(P1934.class);

	/*
	 * 최소공배수
	 * 두 자연수 A와 B에 대해서, A의 배수이면서 B의 배수인 자연수를 A와 B의 공배수라고 한다. 
	 * 이런 공배수 중에서 가장 작은 수를 최소공배수라고 한다. 
	 * 예를 들어, 6과 15의 공배수는 30, 60, 90등이 있으며, 최소 공배수는 30이다.
	 * 두 자연수 A와 B가 주어졌을 때, A와 B의 최소공배수를 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 테스트 케이스의 개수 T(1 ≤ T ≤ 1,000)가 주어진다. 둘째 줄부터 T개의 줄에 걸쳐서 A와 B가 주어진다. (1 ≤ A, B ≤ 45,000)
	 * 첫째 줄부터 T개의 줄에 A와 B의 최소공배수를 입력받은 순서대로 한 줄에 하나씩 출력한다.
	 * 3
	 * 1 45000
	 * 6 10
	 * 13 17
	 * 
	 * 45000
	 * 30
	 * 221
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/91651d952bf2ae09b30dbeec8d0ea0ac
	 */
	private static void solution() throws Exception, IOException {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int g = gcd(a, b);
			int l = a * b / g;
			System.out.println(l);
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
	
	
	private static void solution2() throws Exception, IOException {
		// 버퍼를 이용해서 입력 값을 받아옴
		// (이유 : Scanner를 반복적으로 이용 할 경우 매우 느림)
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		int[] resArr = new int[T];
		
		for (int i = 0; i < T; i++) {
			String str = br.readLine();
			StringTokenizer st = new StringTokenizer(str, " ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int multiplyAB = a * b;

			// 유클리드 호제법을 이용하여 두 수의 최대공약수를 구한 후
			while (true) {
				if (b == 0) {
					// 두 수의 곱에서 나누면 두 수의 최소공배수를 구할 수 있음
					resArr[i] = multiplyAB / a;
					break;
				}

				int tmp = b;
				b = a % b;
				a = tmp;
			}
		}
		br.close();

		// 버퍼를 이용하여 결과 값을 만듬
		// (이유 : System.out.println을 여러번 호출 할 경우 매우 느림)
		StringBuilder sb = new StringBuilder();

		for (int res : resArr) {
			sb.append(res).append("\n");
		}

		// 결과 값을 한꺼번에 출력
		System.out.println(sb.toString());
	}
}

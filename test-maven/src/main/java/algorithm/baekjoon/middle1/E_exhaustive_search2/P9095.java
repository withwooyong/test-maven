package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9095 {

	private static Logger log = LoggerFactory.getLogger(P9095.class);

	/*
	 * 1, 2, 3 더하기 성공
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 128 MB 5332 3633 2561 66.107%
	 * 문제
	 * 정수 4를 1, 2, 3의 조합으로 나타내는 방법은 총 7가지가 있다.
	 * 
	 * 1+1+1+1
	 * 1+1+2
	 * 1+2+1
	 * 2+1+1
	 * 2+2
	 * 1+3
	 * 3+1
	 * 정수 n이 주어졌을 때, n을 1,2,3의 합으로 나타내는 방법의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫쨰 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, 정수 n이 주어진다. n은 양수이며 11보다 작다.
	 * 
	 * 출력
	 * 각 테스트 케이스마다, n을 1,2,3의 합으로 나타내는 방법의 수를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 3
	 * 4
	 * 7
	 * 10
	 * 예제 출력 복사
	 * 7
	 * 44
	 * 274
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/bdeba307e9e6d1e80fc7
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			System.out.println(go(0, 0, n));
		}
	}

	public static int go(int count, int sum, int goal) {
		if (count > 10) {
			return 0;
		}
		if (sum > goal) {
			return 0;
		}
		if (sum == goal) {
			return 1;
		}
		int now = 0;
		for (int i = 1; i <= 3; i++) {
			now += go(count + 1, sum + i, goal);
		}
		return now;
	}

	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int t = sc.nextInt();
		while (t-- > 0) {
			int n = sc.nextInt();
			int ans = 0;
			for (int l1 = 1; l1 <= 3; l1++) {
				if (l1 == n) {
					ans += 1;
				}
				for (int l2 = 1; l2 <= 3; l2++) {
					if (l1 + l2 == n) {
						ans += 1;
					}
					for (int l3 = 1; l3 <= 3; l3++) {
						if (l1 + l2 + l3 == n) {
							ans += 1;
						}
						for (int l4 = 1; l4 <= 3; l4++) {
							if (l1 + l2 + l3 + l4 == n) {
								ans += 1;
							}
							for (int l5 = 1; l5 <= 3; l5++) {
								if (l1 + l2 + l3 + l4 + l5 == n) {
									ans += 1;
								}
								for (int l6 = 1; l6 <= 3; l6++) {
									if (l1 + l2 + l3 + l4 + l5 + l6 == n) {
										ans += 1;
									}
									for (int l7 = 1; l7 <= 3; l7++) {
										if (l1 + l2 + l3 + l4 + l5 + l6 + l7 == n) {
											ans += 1;
										}
										for (int l8 = 1; l8 <= 3; l8++) {
											if (l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 == n) {
												ans += 1;
											}
											for (int l9 = 1; l9 <= 3; l9++) {
												if (l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 == n) {
													ans += 1;
												}
												for (int l0 = 1; l0 <= 3; l0++) {
													if (l1 + l2 + l3 + l4 + l5 + l6 + l7 + l8 + l9 + l0 == n) {
														ans += 1;
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
			System.out.println(ans);
		}

		sc.close();
	}
}

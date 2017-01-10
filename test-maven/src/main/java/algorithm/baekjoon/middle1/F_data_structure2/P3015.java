package algorithm.baekjoon.middle1.F_data_structure2;

import java.util.Scanner;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P3015 {

	private static Logger log = LoggerFactory.getLogger(P3015.class);

	/*
	 * 오아시스 재결합
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 128 MB 1648 299 216 19.512%
	 * 문제
	 * 오아시스의 재결합 공연에 N명이 한 줄로 서서 기다리고 있다.
	 * 
	 * 이 역사적인 순간을 맞이하기 위해 줄에서서 기다리고 있던 백준이는 갑자기 자기가 볼 수 있는 사람의 수가 궁금해 졌다.
	 * 
	 * 두 사람 A와 B가 서로 볼 수 있으려면, 두 사람 사이에 A 또는 B보다 키가 큰 사람이 없어야 한다.
	 * 
	 * 줄에 서있는 사람의 키가 주어졌을 때, 서로 볼 수 있는 쌍의 수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 줄에서 기다리고 있는 사람의 수 N이 주어진다. (1 ≤ N ≤ 500,000)
	 * 
	 * 둘째 줄부터 N개의 줄에는 각 사람의 키가 나노미터 단위로 주어진다. 모든 사람의 키는 231 나노미터 보다 작다.
	 * 
	 * 사람들이 서 있는 순서대로 입력이 주어진다.
	 * 
	 * 출력
	 * 서로 볼 수 있는 쌍의 수를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 7
	 * 2
	 * 4
	 * 1
	 * 2
	 * 2
	 * 5
	 * 1
	 * 예제 출력 복사
	 * 10
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/6557d554cf1400002a86
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		Stack<Integer> s = new Stack<Integer>();
		Stack<Integer> c = new Stack<Integer>();
		long ans = 0;
		for (int i = 0; i < n; i++) {
			int h = a[i];
			int cnt = 1;
			while (!s.empty()) {
				if (s.peek() <= a[i]) {
					ans += (long) c.peek();
					if (s.peek() == a[i]) {
						cnt += c.peek();
					}
					s.pop();
					c.pop();
				} else {
					break;
				}
			}
			if (!s.empty()) {
				ans += 1L;
			}
			s.push(h);
			c.push(cnt);
		}
		System.out.println(ans);

		sc.close();
	}
}

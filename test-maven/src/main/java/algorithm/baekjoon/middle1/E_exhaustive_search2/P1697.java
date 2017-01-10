package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1697 {

	private static Logger log = LoggerFactory.getLogger(P1697.class);

	/*
	 * 숨바꼭질
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 13161 3610 2448 25.785%
	 * 문제
	 * 수빈이는 동생과 숨바꼭질을 하고 있다. 수빈이는 현재 점 N(0 ≤ N ≤ 100,000)에 있고, 동생은 점 K(0 ≤ K ≤ 100,000)에 있다. 수빈이는 걷거나 순간이동을 할 수 있다. 만약,
	 * 수빈이의 위치가 X일 때 걷는다면 1초 후에 X-1 또는 X+1로 이동하게 된다. 순간이동을 하는 경우에는 1초 후에 2*X의 위치로 이동하게 된다.
	 * 
	 * 수빈이와 동생의 위치가 주어졌을 때, 수빈이가 동생을 찾을 수 있는 가장 빠른 시간이 몇 초 후인지 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫 번째 줄에 수빈이가 있는 위치 N과 동생이 있는 위치 K가 주어진다.
	 * 
	 * 출력
	 * 수빈이가 동생을 찾는 가장 빠른 시간을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 5 17
	 * 예제 출력 복사
	 * 4
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/e1abcf6deb6796c1282a
	 */
	public static final int MAX = 1000000;

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		boolean[] c = new boolean[MAX];
		int[] d = new int[MAX];
		c[n] = true;
		d[n] = 0;
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(n);
		while (!q.isEmpty()) {
			int now = q.remove();
			if (now - 1 >= 0) {
				if (c[now - 1] == false) {
					q.add(now - 1);
					c[now - 1] = true;
					d[now - 1] = d[now] + 1;
				}
			}
			if (now + 1 < MAX) {
				if (c[now + 1] == false) {
					q.add(now + 1);
					c[now + 1] = true;
					d[now + 1] = d[now] + 1;
				}
			}
			if (now * 2 < MAX) {
				if (c[now * 2] == false) {
					q.add(now * 2);
					c[now * 2] = true;
					d[now * 2] = d[now] + 1;
				}
			}
		}
		System.out.println(d[m]);

		sc.close();
	}
}

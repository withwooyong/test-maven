package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2251 {

	private static Logger log = LoggerFactory.getLogger(P2251.class);

	/*
	 * 물통
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 1026 462 309 45.982%
	 * 문제
	 * 각각 부피가 A, B, C(1≤A, B, C≤200) 리터인 세 개의 물통이 있다. 처음에는 앞의 두 물통은 비어 있고, 세 번째 물통은 가득(C 리터) 차 있다. 이제 어떤 물통에 들어있는 물을 다른
	 * 물통으로 쏟아 부을 수 있는데, 이 때에는 한 물통이 비거나, 다른 한 물통이 가득 찰 때까지 물을 부을 수 있다. 이 과정에서 손실되는 물은 없다고 가정한다.
	 * 
	 * 이와 같은 과정을 거치다보면 세 번째 물통(용량이 C인)에 담겨있는 물의 양이 변할 수도 있다. 첫 번째 물통(용량이 A인)이 비어 있을 때, 세 번째 물통(용량이 C인)에 담겨있을 수 있는 물의 양을
	 * 모두 구해내는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 세 정수 A, B, C가 주어진다.
	 * 
	 * 출력
	 * 첫째 줄에 공백으로 구분하여 답을 출력한다. 각 용량은 오름차순으로 정렬한다.
	 * 
	 * 예제 입력 복사
	 * 8 9 10
	 * 예제 출력 복사
	 * 1 2 8 9 10
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 
	 */
	public static final int[] from = { 0, 0, 1, 1, 2, 2 };
	public static final int[] to = { 1, 2, 0, 2, 0, 1 };

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int[] cap = new int[3];
		for (int i = 0; i < 3; i++) {
			cap[i] = sc.nextInt();
		}
		int sum = cap[2];
		boolean[][] check = new boolean[201][201];
		boolean[] ans = new boolean[201];
		Queue<Pair> q = new LinkedList<Pair>();
		q.add(new Pair(0, 0));
		check[0][0] = true;
		ans[cap[2]] = true;
		while (!q.isEmpty()) {
			int[] cur = new int[3];
			Pair p = q.peek();
			cur[0] = p.first;
			cur[1] = p.second;
			cur[2] = sum - cur[0] - cur[1];
			q.remove();
			for (int k = 0; k < 6; k++) {
				int[] next = { cur[0], cur[1], cur[2] };
				next[to[k]] += next[from[k]];
				next[from[k]] = 0;
				if (next[to[k]] >= cap[to[k]]) {
					next[from[k]] = next[to[k]] - cap[to[k]];
					next[to[k]] = cap[to[k]];
				}
				if (!check[next[0]][next[1]]) {
					check[next[0]][next[1]] = true;
					q.add(new Pair(next[0], next[1]));
					if (next[0] == 0) {
						ans[next[2]] = true;
					}
				}
			}
		}
		for (int i = 0; i <= cap[2]; i++) {
			if (ans[i]) {
				System.out.print(i + " ");
			}
		}
		System.out.println();

		sc.close();
	}
}

class Pair implements Comparable<Pair> {
	final int first;
	final int second;

	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public int compareTo(Pair pair) {
		if (this.first < pair.first) {
			return -1;
		}
		if (this.first > pair.first) {
			return 1;
		}
		if (this.second < pair.second) {
			return -1;
		}
		if (this.second > pair.second) {
			return 1;
		}
		return 0;
	}

	public boolean equals(Object object) {
		if (object instanceof Pair) {
			Pair pair = (Pair) object;
			return this.first == pair.first && this.second == pair.second;
		}
		return false;
	}

	public int hashCode() {
		int n = 3;
		n = 19 * n + this.first;
		n = 19 * n + this.second;
		return n;
	}
}
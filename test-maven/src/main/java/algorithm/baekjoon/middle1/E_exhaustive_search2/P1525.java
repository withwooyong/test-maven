package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1525 {

	private static Logger log = LoggerFactory.getLogger(P1525.class);

	/*
	 * 퍼즐
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 16 MB 1123 329 205 30.873%
	 * 문제
	 * 3*3 표에 다음과 같이 수가 채워져 있다. 오른쪽 아래 가장 끝 칸은 비어 있는 칸이다.
	 * 
	 * 1 2 3
	 * 4 5 6
	 * 7 8
	 * 어떤 수와 인접해 있는 네 개의 칸 중에 하나가 비어 있으면, 수를 그 칸으로 이동시킬 수가 있다. 물론 표 바깥으로 나가는 경우는 불가능하다. 우리의 목표는 초기 상태가 주어졌을 때, 최소의 이동으로
	 * 위와 같은 정리된 상태를 만드는 것이다. 다음의 예를 보자.
	 * 
	 * 1 3
	 * 4 2 5
	 * 7 8 6
	 * 1 2 3
	 * 4 5
	 * 7 8 6
	 * 1 2 3
	 * 4 5
	 * 7 8 6
	 * 1 2 3
	 * 4 5 6
	 * 7 8
	 * 가장 윗 상태에서 세 번의 이동을 통해 정리된 상태를 만들 수 있다. 이와 같이 최소 이동 횟수를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 세 줄에 걸쳐서 표에 채워져 있는 아홉 개의 수가 주어진다. 한 줄에 세 개의 수가 주어지며, 빈 칸은 0으로 나타낸다.
	 * 
	 * 출력
	 * 첫째 줄에 최소의 이동 횟수를 출력한다. 이동이 불가능한 경우 -1을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 1 0 3
	 * 4 2 5
	 * 7 8 6
	 * 예제 출력 복사
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/1b496dcf92f128468aca
	 */
	public static final int[] dx = { 0, 0, 1, -1 };
	public static final int[] dy = { 1, -1, 0, 0 };

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = 3;
		int start = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				int temp = sc.nextInt();
				if (temp == 0) {
					temp = 9;
				}
				start = start * 10 + temp;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		HashMap<Integer, Integer> d = new HashMap<Integer, Integer>();
		d.put(start, 0);
		q.add(start);
		while (!q.isEmpty()) {
			int now_num = q.remove();
			String now = Integer.toString(now_num);
			int z = now.indexOf('9');
			int x = z / 3;
			int y = z % 3;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					StringBuilder next = new StringBuilder(now);
					char temp = next.charAt(x * 3 + y);
					next.setCharAt(x * 3 + y, next.charAt(nx * 3 + ny));
					next.setCharAt(nx * 3 + ny, temp);
					int num = Integer.parseInt(next.toString());
					if (!d.containsKey(num)) {
						d.put(num, d.get(now_num) + 1);
						q.add(num);
					}
				}
			}
		}
		if (d.containsKey(123456789)) {
			System.out.println(d.get(123456789));
		} else {
			System.out.println("-1");
		}

		sc.close();
	}
}

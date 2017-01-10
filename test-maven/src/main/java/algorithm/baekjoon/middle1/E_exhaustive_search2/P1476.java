package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1476 {

	private static Logger log = LoggerFactory.getLogger(P1476.class);

	/*
	 * 날짜 계산
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 4 MB 2031 1387 1188 70.213%
	 * 문제
	 * 준규가 사는 나라는 우리가 사용하는 연도와 다른 방식을 이용한다. 준규가 사는 나라에서는 수 3개를 이용해서 연도를 나타낸다. 각각의 수는 지구, 태양, 그리고 달을 나타낸다.
	 * 
	 * 지구를 나타내는 숫자를 E, 태양을 나타내는 숫자를 S, 달을 나타내는 숫자를 M이라고 했을 때, 이 세 수는 서로 다른 범위를 가진다. (1 ≤ E ≤ 15, 1 ≤ S ≤ 28, 1 ≤ M ≤ 19)
	 * 
	 * 우리가 알고있는 1년은 준규가 살고있는 나라에서는 1 1 1로 나타낼 수 있다. 1년이 지날 때마다, 세 수는 모두 1씩 증가한다. 만약, 어떤 수가 범위를 넘어가는 경우에는 1이 된다.
	 * 
	 * 예를 들어, 15년은 15 15 15로 나타낼 수 있다. 하지만, 1년이 지나서 16년이 지나면 16 16 16이 아니라 1 16 16이 된다. 이유는 1 ≤ E ≤ 15 라서 범위를 넘어가기 때문이다.
	 * 
	 * E, S, M이 주어졌고, 1년이 준규가 사는 나라에서 1 1 1일때, 준규가 사는 나라에서 E S M이 우리가 알고 있는 연도로 몇 년인지 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 세 수 E, S, M이 주어진다. 문제에 나와있는 범위를 지키는 입력만 주어진다.
	 * 
	 * 출력
	 * 첫째 줄에 E S M으로 표시되는 가장 빠른 연도를 출력한다. 1 1 1은 항상 1이기 때문에, 정답이 음수가 나오는 경우는 없다.
	 * 
	 * 예제 입력 복사
	 * 1 16 16
	 * 예제 출력 복사
	 * 16
	 * 예제 입력 2 복사
	 * 1 1 1
	 * 예제 출력 2 복사
	 * 1
	 * 예제 입력 3 복사
	 * 1 2 3
	 * 예제 출력 3 복사
	 * 5266
	 * 예제 입력 4 복사
	 * 15 28 19
	 * 예제 출력 4 복사
	 * 7980
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/9c1b1a4e7245cb8858879234fa96fe4a
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int E = sc.nextInt();
		int S = sc.nextInt();
		int M = sc.nextInt();
		int e = 1, s = 1, m = 1;
		for (int i = 1;; i++) {
			if (e == E && s == S && m == M) {
				System.out.println(i);
				break;
			}
			e += 1;
			s += 1;
			m += 1;
			if (e == 16) {
				e = 1;
			}
			if (s == 29) {
				s = 1;
			}
			if (m == 20) {
				m = 1;
			}
		}

		sc.close();
	}
}

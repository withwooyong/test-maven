package algorithm.baekjoon.middle1.F_data_structure2;

import java.util.Scanner;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9935 {

	private static Logger log = LoggerFactory.getLogger(P9935.class);

	/*
	 * 문자열 폭발
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 128 MB 6210 948 632 19.175%
	 * 문제
	 * 상근이는 문자열에 폭발 문자열을 심어 놓았다. 폭발 문자열이 폭발하면 그 문자는 문자열에서 사라지며, 남은 문자열은 합쳐지게 된다.
	 * 
	 * 폭발은 다음과 같은 과정으로 진행된다.
	 * 
	 * 문자열이 폭발 문자열을 포함하고 있는 경우에, 모든 폭발 문자열이 폭발하게 된다. 남은 문자열을 순서대로 이어 붙여 새로운 문자열을 만든다.
	 * 새로 생긴 문자열에 폭발 문자열이 포함되어 있을 수도 있다.
	 * 폭발은 폭발 문자열이 문자열에 없을 때까지 계속된다.
	 * 상근이는 모든 폭발이 끝난 후에 어떤 문자열이 남는지 구해보려고 한다. 남아있는 문자가 없는 경우가 있다. 이 때는 "FRULA"를 출력한다.
	 * 
	 * 폭발 문자열은 같은 문자를 두 개 이상 포함하지 않는다.
	 * 
	 * 입력
	 * 첫째 줄에 문자열이 주어진다. 문자열의 길이는 1보다 크거나 같고, 1,000,000보다 작거나 같다.
	 * 
	 * 둘째 줄에 폭발 문자열이 주어진다. 길이는 1보다 크거나 같고, 36보다 작거나 같다.
	 * 
	 * 두 문자열은 모두 알파벳 소문자와 대문자, 숫자 0, 1, ..., 9로만 이루어져 있다.
	 * 
	 * 출력
	 * 첫째 줄에 모든 폭발이 끝난 후 남은 문자열을 출력한다.
	 * 
	 * 예제 입력 복사
	 * mirkovC4nizCC44
	 * C4
	 * 예제 출력 복사
	 * mirkovniz
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/4368405c6c974ee61f74
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		String a = sc.nextLine();
		String b = sc.nextLine();
		int n = a.length();
		int m = b.length();
		boolean[] erased = new boolean[n];
		if (m == 1) {
			for (int i = 0; i < n; i++) {
				if (a.charAt(i) == b.charAt(0)) {
					erased[i] = true;
				}
			}
		} else {
			Stack<Pair> s = new Stack<Pair>();
			for (int i = 0; i < n; i++) {
				if (a.charAt(i) == b.charAt(0)) {
					s.push(new Pair(i, 0));
				} else {
					if (!s.empty()) {
						Pair p = s.peek();
						if (a.charAt(i) == b.charAt(p.second + 1)) {
							s.push(new Pair(i, p.second + 1));
							if (p.second + 1 == m - 1) {
								for (int k = 0; k < m; k++) {
									Pair top = s.pop();
									erased[top.first] = true;
								}
							}
						} else {
							while (!s.empty()) {
								s.pop();
							}
						}
					}
				}
			}
		}
		boolean printed = false;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			if (erased[i]) {
				continue;
			}
			sb.append(a.charAt(i));
			printed = true;
		}
		if (printed) {
			System.out.println(sb);
		} else {
			System.out.println("FRULA");
		}

		sc.close();
	}
}

class Pair {
	int first, second;

	Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

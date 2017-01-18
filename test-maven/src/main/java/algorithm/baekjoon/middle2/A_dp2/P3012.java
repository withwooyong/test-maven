package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P3012 {	
	
	private static Logger log = LoggerFactory.getLogger(P3012.class); 

	/*
	 * 올바른 괄호 문자열
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	352	78	47	20.614%
문제
여는 괄호와 닫는 괄호로만 이루어져 있으면서, 다음 규칙을 지키는 문자열을 올바른 괄호 문자열이라고 한다.

빈 문자열은 올바른 괄호 문자열이다.
A가 올바른 괄호 문자열이라면, (A), [A], {A}도 올바른 괄호 문자열이다.
A와 B가 올바른 괄호 문자열이라면, AB도 올바른 괄호 문자열이다.
예를 들어, [({})], [](){}, [{}]()[{}]는 올바른 괄호 문자열이다. 하지만, [({{)[, []({)}, [{}])([{}]는 올바른 괄호 문자열이 아니다.

상근이는 책상 서럽에서 올바른 괄호 문자열을 적은 종이를 찾았다. 이 종이는 오래되었기 때문에, 일부 문자는 알아 볼 수 없었다.

알아볼 수 없는 문자를 적절히 괄호로 바꿔서 만들 수 있는 올바른 괄호 문자열의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 문자열의 길이 N이 주어진다. (2 ≤ N ≤ 200)

둘째 줄에는 문자열이 주어진다. 알아볼 수 없는 문자는 '?'로 표시되어 있다.

출력
첫째 줄에 만들 수 있는 올바른 괄호 문자열의 수를 출력한다. 개수가 다섯 자리를 넘어가는 경우에는 마지막 다섯 자리만 출력한다.

예제 입력  복사
10
(?([?)]?}?
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/8ba00d0064e8ca9c65f9ce8d64be5626
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

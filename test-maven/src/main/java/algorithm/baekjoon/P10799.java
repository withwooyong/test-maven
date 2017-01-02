package algorithm.baekjoon;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class P10799 {

	private static Logger log = LoggerFactory.getLogger(P10799.class);

	// http://hsp1116.tistory.com/29
	public static void main(String[] args) throws Exception {
		String A = "()(((()())(())()))(())";
		log.debug("{}", solution(A));
	}

	private static int solution(String A) {
//		Scanner sc = new Scanner(System.in);
//		String A = sc.nextLine();
		Stack<Character> stack = new Stack<>();
		int result = 0;

		for (int i = 0; i < A.length(); i++) {
			// 여는 괄호이면 스택에 넣는다.
			if (A.charAt(i) == '(') {
				stack.push(A.charAt(i));
			} else { // )
				// 닫는 괄호면 이 괄호가 레이저인지, 파이프 끝인지 알아본다.
				stack.pop();
				// 레이저면
				if (A.charAt(i - 1) == '(') {
					result += stack.size(); // 잘린 갯수 추가
					log.debug("{}", result);
				} else { // 파이프 끝이면
					result++; // 닫혀서 잘려진 갯수 추가
					log.debug("{}", result);
				}
			}
		}
		return result;
	}
}

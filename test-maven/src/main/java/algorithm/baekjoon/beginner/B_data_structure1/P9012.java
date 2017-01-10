package algorithm.baekjoon.beginner.B_data_structure1;

import java.util.Scanner;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9012 {	
	
	private static Logger log = LoggerFactory.getLogger(P9012.class); 

	/*
	 * 괄호 문자열(Parenthesis String, PS)은 두 개의 괄호 기호인 ‘(’ 와 ‘)’ 만으로 구성되어 있는 문자열이다. 
	 * 그 중에서 괄호의 모양이 바르게 구성된 문자열을 올바른 괄호 문자열(Valid PS, VPS)이라고 부른다. 
	 * 한 쌍의 괄호 기호로 된 “( )” 문자열은 기본 VPS 이라고 부른다. 
	 * 만일 x 가 VPS 라면 이것을 하나의 괄호에 넣은 새로운 문자열 “(x)”도 VPS 가 된다. 
	 * 그리고 두 VPS x 와 y를 접합(concatenation)시킨 새로운 문자열 xy도 VPS 가 된다. 
	 * 예를 들어 “(())()”와 “((()))” 는 VPS 이지만 “(()(”, “(())()))” , 그리고 “(()” 는 모두 VPS 가 아닌 문자열이다.
	 * 여러분은 입력으로 주어진 괄호 문자열이 VPS 인지 아닌지를 판단해서 그 결과를 YES 와 NO 로 나타내어야 한다.
	 * 
	 * 입력 데이터는 표준 입력을 사용한다. 
	 * 입력은 T개의 테스트 데이터로 주어진다. 
	 * 입력의 첫 번째 줄에는 입력 데이터의 수를 나타내는 정수 T가 주어진다. 
	 * 각 테스트 데이터의 첫째 줄에는 괄호 문자열이 한 줄에 주어진다. 
	 * 하나의 괄호 문자열의 길이는 2 이상 50 이하이다.
	 * 
	 * 출력은 표준 출력을 사용한다. 
	 * 만일 입력 괄호 문자열이 올바른 괄호 문자열(VPS)이면 “YES”, 아니면 “NO”를 한 줄에 하나씩 차례대로 출력해야 한다.
	 * (())())
	 * 
	 * NO
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/c5052cc44c9ccc15733b
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.nextLine();
        while (n-- > 0) {
            System.out.println(isValid(sc.nextLine()));
        }
        sc.close();
	}
	
	private static String isValid(String s) {
        s = s.trim();
        int n = s.length();
        int cnt = 0;
        for (int i=0; i<n; i++) {
            if (s.charAt(i) == '(') {
                cnt += 1;
            } else {
                cnt -= 1;
            }
            if (cnt < 0) {
                return "NO";
            }
        }
        if (cnt == 0) {
            return "YES";
        } else {
            return "NO";
        }
    }

	/*
	 * 
	 * 출처: http://pooh-explorer.tistory.com/16 [푸의 개발 이야기]
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int i = 0; i < T; i++) {
			String input = sc.nextLine();
			if (solve(input)) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
		sc.close();
	}
	
	public static boolean solve(String inputs) {
		boolean isCorrect = true;
		Stack<Character> resultStack = new Stack<Character>();

		char[] insertArray = inputs.toCharArray();
		for (int i = 0; i < insertArray.length; i++) {
			if (insertArray[i] == '(') {
				resultStack.push(insertArray[i]);
			} else {
				if (resultStack.peek() == null) {
					isCorrect = false;
					break;
				} else {
					resultStack.pop();
				}
			}
		}
		if (isCorrect && resultStack.peek() != null) {
			isCorrect = false;
		}
		return isCorrect;
	}
}

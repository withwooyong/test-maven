package codility.lesson07.stacksandqueues;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Brackets
 * Determine whether a given string of parentheses is properly nested.
 * 
 * A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
 * S is empty;
 * S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * 
 * For example, the string "{[()()]}" is properly nested but "([)()]" is not.
 * 
 * Write a function:
 * class Solution { public int solution(String S); }
 * that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
 * 
 * For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [0..200,000];
 * string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
 * @author wooyong
 *
 */
public class Brackets {
	
	private static Logger log = LoggerFactory.getLogger(Brackets.class);

	/*
	 * Determine whether a given string of parentheses is properly nested.
	 */
	public static void main(String[] args) {
		String S = "{[()()]}"; // true;
		//String S = "([)()]"; // false;
		log.debug("{}", mySolution(S));

	}
	
	// string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
	// 주어진 문자열이 쌍이 맞는지 확인 Stack 사용
	public static int mySolution(String S) {
		
		Stack<Character> stack = new Stack<>();
		log.debug("{}", stack.size());
		for (int i = 0; i < S.length(); i++) {
			char item = S.charAt(i);
			log.debug("{}", item);
			if (stack.isEmpty()) { // 첫번째 데이터 등록
				stack.push(item);
			}			
			if ((item == ')' && stack.peek() == '(') || (item == '}' && stack.peek() == '{') || (item == ']' && stack.peek() == '[')) {
				log.debug("{}", stack.pop());
			} else {
				log.debug("{}", item);
				stack.push(item);
			}
		}
		log.debug("{}", stack.pop());
		return stack.size();
	}
	
	public static int solution(String S) {
		
		Stack<Character> chars = new Stack<Character>();
		
		for (int i = 0; i < S.length(); i++) {
			if (chars.size() == 0) {
				chars.push(S.charAt(i));
			} else {
				if (isMatch(chars.peek(), S.charAt(i))) {
					chars.pop();
				} else {
					chars.push(S.charAt(i));
				}
			}
		}
		return chars.size() == 0 ? 1 : 0;
	}

	private static boolean isMatch(char a, char b) {
		switch (a) {
		case '{':
			return b == '}';
		case '(':
			return b == ')';
		case '[':
			return b == ']';
		default:
			return false;
		}
	}

}

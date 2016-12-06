package codility.lesson07.stacksandqueues;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Nesting
 * Determine whether given string of parentheses is properly nested.
 * 
 * A string S consisting of N characters is called properly nested if:
 * 
 * S is empty;
 * S has the form "(U)" where U is a properly nested string;
 * S has the form "VW" where V and W are properly nested strings.
 * For example, string "(()(())())" is properly nested but string "())" isn't.
 * 
 * Write a function:
 * class Solution { public int solution(String S); }
 * 
 * that, given a string S consisting of N characters, returns 1 if string S is properly nested and 0 otherwise.
 * 
 * For example, given S = "(()(())())", the function should return 1 and given S = "())", the function should return 0, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [0..1,000,000];
 * string S consists only of the characters "(" and/or ")".
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1) (not counting the storage required for input arguments).
 * @author wooyong
 *
 */
public class Nesting {

	private static Logger log = LoggerFactory.getLogger(Nesting.class);
	
	// (()(())())
	// 주어진 문자열이 쌍이 맞는지 확인 Stack 사용
	// LIFO [Last In First Out]을 구현하기 위해 Stack 이용
	// Exeption없이 완전히 짝을 이루는 경우 1, 이외 0
	public static void main(String[] args) {
		String S = "(()(())())"; // return 1;
		String SS = "())"; // return 0
		log.debug("{}", solution(S));
		log.debug("{}", mySolution(S));
	}
	
	public static int mySolution(String S) {
		Stack<Character> stack = new Stack<>();
		
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			//log.debug("{}", c);
			if (c == '(') {
				stack.push(c);
			} else if (c == ')') {
				stack.pop();
			}
		}
		return stack.size() == 0 ? 1 : 0;
	}
	
	// O(n)
	public static int solution(String S) {
		
		if (S == null || S.length() == 0) {
			return 1; // Properly nested
		}	
		
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < S.length(); i++) {
			char c = S.charAt(i);
			if (c == '(') {
				stack.push('*');
			} else if (c == ')') {
				try {
					Character character = stack.pop();
					if (character == '*') {
						continue;
					} else {
						return 0;
					}
				} catch (Exception e) {
					return 0;
				}
			}
		}

		if (stack.size() == 0) {
			return 1;// Properly nested
		} else {
			return 0;
		}
	}
} 
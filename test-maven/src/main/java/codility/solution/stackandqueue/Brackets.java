/*
	A string S consisting of N characters is considered to be properly nested if any of the following conditions is true:
	S is empty;
	S has the form "(U)" or "[U]" or "{U}" where U is a properly nested string;
	S has the form "VW" where V and W are properly nested strings.
	For example, the string "{[()()]}" is properly nested but "([)()]" is not.
	Write a function:
	class Solution { public int solution(String S); }
	that, given a string S consisting of N characters, returns 1 if S is properly nested and 0 otherwise.
	For example, given S = "{[()()]}", the function should return 1 and given S = "([)()]", the function should return 0, as explained above.
	Assume that:
	N is an integer within the range [0..200,000];
	string S consists only of the following characters: "(", "{", "[", "]", "}" and/or ")".
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N) (not counting the storage required for input arguments).
	
	다음 조건 중 하나라도 해당되면 N 문자로 구성된 문자열 S가 올바르게 중첩 된 것으로 간주됩니다.
	S는 비어 있습니다.
	S는 "(U)"또는 "[U]"또는 "{U}"형식을가집니다. 
	여기서 U는 올바르게 중첩 된 문자열입니다.
	S는 "VW"형식을 가지고 있으며 여기서 V와 W는 제대로 중첩 된 문자열입니다.
	예를 들어 "{[() ()]"문자열은 제대로 중첩되어 있지만 "([) ()]"은 중첩되지 않습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (String S); }
	N 개의 문자로 구성된 문자열 S가 주어지면 S가 제대로 중첩되면 1을 반환하고 그렇지 않으면 0을 반환합니다.
	
	예를 들어 S = "{[() ()]}"이면 함수는 1을 반환하고 S = "([) ()]를 제공해야 함수는 위에 설명 된 것처럼 0을 반환해야합니다.
	
	가정하자면 :
	N은 [0..200,000] 범위의 정수입니다.
	문자열 S는 "(", "{", "[", "]", "}"및 / 또는 ")"문자로만 구성됩니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상되는 최악의 경우의 공간 복잡도는 O (N)입니다 (입력 인수에 필요한 저장소를 계산하지 않음).
 */

//SCORE:100/100
package codility.solution.stackandqueue;

import java.util.Stack;

public class Brackets {
	public static void main(String[] args) {
		String S = "{[()()]}";
		System.out.println(solution(S));
	}

	public static int solution(String S) {
		Stack<Character> chars = new Stack<Character>();
		for (int i = 0; i < S.length(); i++) {
			if (chars.size() == 0)
				chars.push(S.charAt(i));
			else {
				if (isMatch(chars.peek(), S.charAt(i)))
					chars.pop();
				else
					chars.push(S.charAt(i));
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

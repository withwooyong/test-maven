package algorithm.baekjoon;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1406 {

	private static Logger log = LoggerFactory.getLogger(P1406.class);

	/*
	 * 에디터
	 */
	public static void main(String[] args) throws Exception {
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine(); // abcd
		int N = sc.nextInt(); // 3
		
		LinkedList<String> text = new LinkedList<>();	    
	    for (int i = 0; i < str.length(); i++) {
	    	text.add(""+ str.charAt(i));
		}
	    
	    char[] A = { 'P', 'L', 'P' };
	    char[] B = { 'x', ' ', 'y' };
	 
	    int position = text.size();
	    // 명령에 맞게 함수를 실행함
		for (int i = 0; i < N; i++) {
			String command = sc.nextLine();
			StringTokenizer st = new StringTokenizer(command, " ");
			
			switch ((Character)st.nextElement()) {
			case 'L':
				if (position > 0) {
					position--;
				}
				break;
			case 'D':
				if (position < text.size()) {
					position++;
				}
				break;
			case 'B':
				text.remove(position--);
				break;
			case 'P':
				text.add(position++, "" + B[i]);
			}
		}
	    // 결과 출력
		log.debug("{}", text.toString());
	    	
		//solution(A, N);
	}

	private static void solution(String A, int N) {
		Stack<Character> left1 = new Stack<>();
		Stack<Character> left2 = new Stack<>();
		
		    
	}
}

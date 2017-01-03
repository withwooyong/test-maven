package algorithm.baekjoon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11656 {

	private static Logger log = LoggerFactory.getLogger(P11656.class);

	/*
	 * 접미사 배열은 문자열 S의 모든 접미사를 사전순으로 정렬해 놓은 배열이다.
	 * baekjoon의 접미사는 baekjoon, aekjoon, ekjoon, kjoon, joon, oon, on, n 으로 총 8가지가 있고, 
	 * 이를 사전순으로 정렬하면, aekjoon, baekjoon, ekjoon, joon, kjoon, n, on, oon이 된다.
	 * 문자열 S가 주어졌을 때, 모든 접미사를 사전순으로 정렬한 다음 출력하는 프로그램을 작성하시오.
	 * 첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000보다 작거나 같다.
	 * 첫째 줄부터 S의 접미사를 사전순으로 한 줄에 하나씩 출력한다.
	 * baekjoon
	 * 
	 * aekjoon
	 * baekjoon
	 * ekjoon
	 * joon
	 * kjoon
	 * n
	 * on
	 * oon
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		String str = "baekjoon";
		// 첫째 줄에 문자열 S가 주어진다. S는 알파벳 소문자로만 이루어져 있고, 길이는 1,000보다 작거나 같다.
		boolean condition = true;
		if (str.length() <= 1000) {
			for (int i = 0; i < str.length(); i++) {
				if (Character.isUpperCase((str.charAt(i)))) {
					condition = false;
					return;
				} 
			}
		} else {
			condition = false;
			return;
		}
		
		if (condition) {
			ArrayList<String> list = new ArrayList<>();
			for (int i = 0; i < str.length(); i++) {
				list.add(str.substring(i));
			}
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}			
			log.debug("{}", list.toString());   
		}  
	}
}

package algorithm.baekjoon.beginner.B_data_structure1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11655 {

	private static Logger log = LoggerFactory.getLogger(P11655.class);

	/*
	 * ROT13은 카이사르 암호의 일종으로 영어 알파벳을 13글자씩 밀어서 만든다.
	 * 예를 들어, "Baekjoon Online Judge"를 ROT13으로 암호화하면 "Onrxwbba Bayvar Whqtr"가 된다. 
	 * ROT13으로 암호화한 내용을 원래 내용으로 바꾸려면 암호화한 문자열을 다시 ROT13하면 된다. 
	 * 앞에서 암호화한 문자열 "Onrxwbba Bayvar Whqtr"에 다시 ROT13을 적용하면 "Baekjoon Online Judge"가 된다.
	 * ROT13은 알파벳 대문자와 소문자에만 적용할 수 있다. 
	 * 알파벳이 아닌 글자는 원래 글자 그대로 남아 있어야 한다. 
	 * 예를 들어, "One is 1"을 ROT13으로 암호화하면 "Bar vf 1"이 된다.
	 * 문자열이 주어졌을 때, "ROT13"으로 암호화한 다음 출력하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 알파벳 대문자, 소문자, 공백, 숫자로만 이루어진 문자열 S가 주어진다. S의 길이는 100을 넘지 않는다.
	 * 첫째 줄에 S를 ROT13으로 암호화한 내용을 출력한다.
	 * 
	 * Baekjoon Online Judge
	 * Onrxwbba Bayvar Whqtr
	 * 
	 * One is 1
	 * Bar vf 1
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/529eda569f98d62d8e36d9e81d57c97e
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        System.out.println(rot13(s));
        sc.close();
	}
	
	private static String rot13(String s) {
		String ans = "";
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 'a' && c <= 'm') {
				c += 13;
			} else if (c >= 'n' && c <= 'z') {
				c -= 13;
			} else if (c >= 'A' && c <= 'M') {
				c += 13;
			} else if (c >= 'N' && c <= 'Z') {
				c -= 13;
			}
			ans += c;
		}
		return ans;
	}

	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		//String str = "Baekjoon Online Judge";
		//String str = "One is 1";
		StringBuilder builder = new StringBuilder();
		// 알파벳 대문자와 소문자에만 적용
		// 소문자(97~122), 대문자(65~90), 숫자(48~57), 공백(32)
		while (sc.hasNextLine()) {
			String str = sc.nextLine();
			for (int i = 0; i < str.length(); i++) {
				int temp = (int)str.charAt(i);
				if (temp >= 97 && temp <= 122) {
					temp += 13;
					if (temp > 122) {
						temp = temp - 122 + 96;
					}
				} else if (temp >= 65 && temp <= 90) {
					temp += 13;
					if (temp > 90) {
						temp = temp - 90 + 64;
					}
				}
				builder.append((char)temp);
			}
			System.out.println(builder.toString());
		}
		log.debug("{}", builder.toString());
	}
}

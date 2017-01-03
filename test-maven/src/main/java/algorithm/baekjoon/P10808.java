package algorithm.baekjoon;


import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10808 {

	private static Logger log = LoggerFactory.getLogger(P10808.class);

	/*
	 * 알파벳 소문자로만 이루어진 단어 S가 주어진다. 이 때, 각 알파벳이 단어에 몇 개가 포함되어 있는지 구하는 프로그램을 작성하시오.
	 * 첫째 줄에 단어 S가 주어진다. 단어의 길이는 100을 넘지 않으며, 알파벳 소문자로만 이루어져 있다.
	 * 단어에 포함되어 있는 a의 개수, b의 개수, …, z의 개수를 공백으로 구분해서 출력한다.
	 * baekjoon
	 * 1 1 0 0 1 0 0 0 0 1 1 0 0 1 2 0 0 0 0 0 0 0 0 0 0 0
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		//String str = "baekjoon";
		String str = sc.nextLine();
		log.debug("{}",  'b' -97);
		int[] A = new int[26];
		for (int i = 0; i < str.length(); i++) {
			A[str.charAt(i)-97]++;
		}
		log.debug("{}", Arrays.toString(A));
		System.out.println(Arrays.toString(A));
		
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
		
		
		
		    
	}
}

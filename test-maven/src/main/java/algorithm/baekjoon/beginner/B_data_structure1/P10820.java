package algorithm.baekjoon.beginner.B_data_structure1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10820 {

	private static Logger log = LoggerFactory.getLogger(P10820.class);

	/*
	 * 문자열 분석
	 * 문자열 N개가 주어진다. 이 때, 문자열에 포함되어 있는 소문자, 대문자, 숫자, 공백의 개수를 구하는 프로그램을 작성하시오.
	 * 각 문자열은 알파벳 소문자, 대문자, 숫자, 공백으로만 이루어져 있다.
	 * 
	 * 첫째 줄부터 N번째 줄까지 문자열이 주어진다. (1 ≤ N ≤ 100) 문자열의 길이는 100을 넘지 않는다.
	 * 첫째 줄부터 N번째 줄까지 각각의 문자열에 대해서 소문자, 대문자, 숫자, 공백의 개수를 공백으로 구분해 출력한다.
	 * 
	 * This is String
	 * SPACE    1    SPACE
	 *  S a M p L e I n P u T     
	 *  0L1A2S3T4L5I6N7E8
	 *  
	 * 10 2 0 2
	 * 0 10 1 8
	 * 5 6 0 16
	 * 0 8 9 0 
	 */
	public static void main(String[] args) throws Exception {
		solution();
		
	}
	
	/*
	 * https://gist.github.com/Baekjoon/fb2f6c0e2eaff1ea6909fbae8c9fc022
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            int lower = 0;
            int upper = 0;
            int digit = 0;
            int space = 0;
            for (int i=0; i<s.length(); i++) {
                char c = s.charAt(i);
                if ('A' <= c && c <= 'Z') {
                    upper += 1;
                } else if ('a' <= c && c <= 'z') {
                    lower += 1;
                } else if ('0' <= c && c <= '9') {
                    digit += 1;
                } else if (c == ' ') {
                    space += 1;
                }
            }
            System.out.println(lower + " " + upper + " " + digit + " " + space);
        }
        sc.close();
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		// 소문자(97~122), 대문자(65~90), 숫자(48~57), 공백(32)
		//String str = "This is String";
		//String str = "SPACE    1    SPACE";
		//String str = " S a M p L e I n P u T     ";
		//String str = "0L1A2S3T4L5I6N7E8";
		int[] A = new int[4];
		
		while (sc.hasNextLine()) {
			A = new int[4];
			String str = (String) sc.nextLine();
			for (int i = 0; i < str.length(); i++) {
				int temp = (int)str.charAt(i);
				log.debug("{} {}", temp, str.charAt(i));
				if (temp >= 97 && temp <= 122) {
					A[0]++;
				} else if (temp >= 65 && temp <= 90) {
					A[1]++;
				} else if (temp >= 48 && temp <= 57) {
					A[2]++;
				} else if (temp == 32) {
					A[3]++;
				} 
			}
			
			for (int i = 0; i < A.length; i++) {
				System.out.print(A[i] + " ");
			}
			System.out.println();
		}
	}

	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		// 소문자(97~122), 대문자(65~90), 숫자(48~57), 공백(32)
		//String str = "This is String";
		//String str = "SPACE    1    SPACE";
		//String str = " S a M p L e I n P u T     ";
		String str = "0L1A2S3T4L5I6N7E8";
		int[] A = new int[4];
		
		if (str.length() > 100) {
			return;
		}
		
		for (int i = 0; i < str.length(); i++) {
			int temp = (int)str.charAt(i);
			log.debug("{} {}", temp, str.charAt(i));
			if (temp >= 97 && temp <= 122) {
				A[0]++;
			} else if (temp >= 65 && temp <= 90) {
				A[1]++;
			} else if (temp >= 48 && temp <= 57) {
				A[2]++;
			} else if (temp == 32) {
				A[3]++;
			} 
		}
		
		for (int i = 0; i < A.length; i++) {
			System.out.print(A[i] + " ");
		}
		System.out.println();
	}
}

package algorithm.baekjoon.middle1.A_greedy;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10610 {	
	
	private static Logger log = LoggerFactory.getLogger(P10610.class); 

	/*
	 * 30
	 * 어느날, 미르코는 우연히 길거리에서 양수 N을 보았다. 
	 * 미르코는 30이란 수를 존경하기 때문에, 그는 길거리에서 찾은 수에 포함된 숫자들을 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶어한다.
	 * 미르코를 도와 그가 만들고 싶어하는 수를 계산하는 프로그램을 작성하라. (그 수가 존재한다면)
	 * 
	 * N을 입력받는다. N는 최대 10^5개의 숫자로 구성되어 있다.
	 * 미르코가 만들고 싶어하는 수가 존재한다면 그 수를 출력하라. 그 수가 존재하지 않는다면, -1을 출력하라.
	 * 30
	 * 30
	 * 
	 * 102
	 * 210
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/635ebbefe004d0f22dbdf862932793d2
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toCharArray();
        int sum = 0;
        for (int i=0; i<s.length; i++) {
            sum += s[i] - '0';
        }
        Arrays.sort(s);
        if (s[0] == '0' && sum%3 == 0) {
            for (int i=s.length-1; i>=0; i--) {
                System.out.print(s[i]);
            }
            System.out.println();
        } else {
            System.out.println(-1);
        }
        sc.close();
	}

	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		char[] s = sc.nextLine().toCharArray();
		int sum = 0;
		for (int i = 0; i < s.length; i++) {
			sum += s[i] - '0';
		}
		Arrays.sort(s);
		if (s[0] == '0' && sum % 3 == 0) {
			for (int i = s.length - 1; i >= 0; i--) {
				System.out.print(s[i]);
			}
			System.out.println();
		} else {
			System.out.println(-1);
		}
		sc.close();
	}
}

package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2089 {	
	
	private static Logger log = LoggerFactory.getLogger(P2089.class); 

	/*
	 * -2진수
	 * -2진법은 부호 없는 2진수로 표현이 된다. 
	 * 2진법에서는 20, 21, 22, 23이 표현 되지만 -2진법에서는 (-2)0 = 1, (-2)1 = -2, (-2)2 = 4, (-2)3 = -8을 표현한다. 
	 * 10진수로 1부터 표현하자면 1, 110, 111, 100, 101, 11010, 11011, 11000, 11001 등이다.
	 * 10진법의 수를 입력 받아서 -2진수를 출력하는 프로그램을 작성하시오.
	 * 첫 줄에 10진법으로 표현된 수 N(-2,000,000,000≤N≤2,000,000,000)이 주어진다.
	 * -2진법 수를 출력한다.
	 * 
	 * -13
	 * 
	 * 110111
	 */
	public static void main(String args[]) {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/be672cbe7a6062ca6c16b011150ef3e7
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		if (n == 0) {
			System.out.println(0);
		} else {
			go(n);
			System.out.println();
		}
		sc.close();
	}

	private static void go(int n) {
		if (n == 0) {
			return;
		}
		if (n % 2 == 0) {
			go(-(n / 2));
			System.out.print(0);
		} else {
			if (n > 0) {
				go(-(n / 2));
			} else {
				go((-n + 1) / 2);
			}
			System.out.print(1);
		}
	}
    
}

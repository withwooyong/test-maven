package algorithm.baekjoon.middle1.B_divide_conquer;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10816 {

	private static Logger log = LoggerFactory.getLogger(P10816.class);

	/*
	 * 숫자 카드 2
	 * 문제
	 * 숫자 카드는 정수 하나가 적혀져 있는 카드이다. 상근이는 숫자 카드 N개를 가지고 있다. 숫자 M개가 주어졌을 때, 이 숫자가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을
	 * 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N (1 ≤ N ≤ 500,000)이가 주어진다. 둘째 줄에는 숫자 카드에 적혀있는 숫자가 주어진다. 숫자 카드에 적혀있는 숫자는
	 * -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
	 * 
	 * 셋째 줄에는 M (1 ≤ M ≤ 500,000)이 주어진다. 넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 숫자가 주어지며, 이 숫자는 공백으로 구분되어져 있다. 이숫자도
	 * -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.
	 * 
	 * 출력
	 * 첫째 줄에 입력으로 주어진 M개의 숫자에 대해서, 각 숫자가 적현 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.
	 * 
	 * 10
	 * 6 3 2 10 10 10 -10 -10 7 3
	 * 8
	 * 10 9 -5 2 3 4 5 -10
	 * 
	 * 3 0 0 1 2 0 0 2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/896c0b7c7bfb1c49bc08
	 * https://gist.github.com/Baekjoon/5d8e1fcdf1ed3f240201
	 * https://gist.github.com/Baekjoon/98a2993e3d1258221f69
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}
}

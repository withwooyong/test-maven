package algorithm.baekjoon;

import java.util.Scanner;

public class P10819 {

	/*
	 * N개의 정수로 이루어진 배열 A가 주어진다. 
	 * 이 때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최대값을 구하는 프로그램을 작성하시오.
	 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
	 * 
	 * 첫째 줄에 N (3 ≤ N ≤ 8)이 주어진다. 
	 * 둘째 줄에는 배열 A에 들어있는 정수가 주어진다. 
	 * 배열에 들어있는 정수는 -100보다 크거나 같고, 100보다 작거나 같다.
	 * 
	 * 첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.
	 */
	public static void main(String[] args) {
		// 6
		// 20 1 15 8 4 10
		Scanner sc = new Scanner(System.in);
		int N = 6;
		int[] A = { 20, 1, 15, 8, 4, 10 };
		// |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
		
		for (int i = 0; i < A.length; i+=2) {
			
		}
		

	}

}

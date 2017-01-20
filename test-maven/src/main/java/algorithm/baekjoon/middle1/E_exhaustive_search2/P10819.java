package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Arrays;
import java.util.Scanner;

public class P10819 {

	/*
	 * 차이를 최대로
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 256 MB 1994 1168 939 59.923%
	 * 문제
	 * N개의 정수로 이루어진 배열 A가 주어진다. 이 때, 배열에 들어있는 정수의 순서를 적절히 바꿔서 다음 식의 최대값을 구하는 프로그램을 작성하시오.
	 * 
	 * |A[0] - A[1]| + |A[1] - A[2]| + ... + |A[N-2] - A[N-1]|
	 * 
	 * 입력
	 * 첫째 줄에 N (3 ≤ N ≤ 8)이 주어진다. 둘째 줄에는 배열 A에 들어있는 정수가 주어진다. 배열에 들어있는 정수는 -100보다 크거나 같고, 100보다 작거나 같다.
	 * 
	 * 출력
	 * 첫째 줄에 배열에 들어있는 수의 순서를 적절히 바꿔서 얻을 수 있는 식의 최댓값을 출력한다.
	 * 
	 * 예제 입력 복사
	 * 6
	 * 20 1 15 8 4 10
	 * 예제 출력 복사
	 * 62
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		Arrays.sort(a);
		int ans = 0;
		do {
			int temp = calculate(a);
			ans = Math.max(ans, temp);
		} while (next_permutation(a));

		System.out.println(ans);
		sc.close();
	}

	public static boolean next_permutation(int[] a) {
		int i = a.length - 1;
		while (i > 0 && a[i - 1] >= a[i]) {
			i -= 1;
		}

		// 마지막 순열
		if (i <= 0) {
			return false;
		}

		int j = a.length - 1;
		while (a[j] <= a[i - 1]) {
			j -= 1;
		}

		int temp = a[i - 1];
		a[i - 1] = a[j];
		a[j] = temp;

		j = a.length - 1;
		while (i < j) {
			temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			i += 1;
			j -= 1;
		}
		return true;
	}

	public static int calculate(int a[]) {
		int sum = 0;
		for (int i = 1; i < a.length; i++) {
			sum += Math.abs(a[i] - a[i - 1]);
		}
		return sum;
	}

}

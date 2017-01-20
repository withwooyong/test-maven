package algorithm.baekjoon.middle1.B_divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11728 {

	private static Logger log = LoggerFactory.getLogger(P11728.class);

	/*
	 * 배열 합치기
	 * 문제
	 * 정렬되어있는 두 배열 A와 B가 주어진다. 두 배열을 합친 다음 정렬해서 출력하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 배열 A의 크기 N, 배열 B의 크기 M이 주어진다. (1 ≤ N, M ≤ 1,000,000)
	 * 
	 * 둘째 줄에는 배열 A의 내용이, 셋째 줄에는 배열 B의 내용이 주어진다. 배열에 들어있는 수는 절대값이 109보다 작거나 같은 정수이다.
	 * 
	 * 출력
	 * 첫째 줄에 두 배열을 합친 후 정렬한 결과를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 2 2
	 * 3 5
	 * 2 9
	 * 예제 출력 복사
	 * 2 3 5 9
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/c949446ce64f03a4f8727203066f2331
	 */
	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.valueOf(line[0]);
		int m = Integer.valueOf(line[1]);
		int[] a = new int[n];
		line = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.valueOf(line[i]);
		}
		int[] b = new int[m];
		line = br.readLine().split(" ");
		for (int i = 0; i < m; i++) {
			b[i] = Integer.valueOf(line[i]);
		}
		int[] c = new int[n + m];
		{
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < n || j < m) {
				if (j >= m || (i < n && a[i] <= b[j])) {
					c[k++] = a[i++];
				} else {
					c[k++] = b[j++];
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n + m; i++) {
			sb.append(c[i] + " ");
		}
		System.out.println(sb);
	}
}

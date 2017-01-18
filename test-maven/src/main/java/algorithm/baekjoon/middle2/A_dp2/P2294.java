package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2294 {	
	
	private static Logger log = LoggerFactory.getLogger(P2294.class); 

	/*
	 * 동전 2
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	9541	2250	1524	24.545%
문제
n가지 종류의 동전이 있다. 각각의 동전이 나타내는 가치는 다르다. 이 동전들을 적당히 사용해서, 그 가치의 합이 k원이 되도록 하고 싶다. 
그러면서 동전의 개수가 최소가 되도록 하려고 한다. (각각의 동전은 몇개라도 사용할 수 있다.)

입력
첫째줄에 n, k가 주어진다. (1 ≤ n ≤ 100, 1 ≤ k ≤ 10,000) 다음 n개의 줄에는 각각의 동전의 가치가 주어진다.

출력
첫째줄에 사용한 동전의 최소 개수를 출력한다. 불가능한 경우에는 -1을 출력한다.

예제 입력  복사
3 15
1
5
12
예제 출력  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/3db9cc732a3234efe680
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		int[] d = new int[m + 1];
		for (int i = 0; i <= m; i++) {
			d[i] = -1;
		}
		d[0] = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= m; j++) {
				if (j - a[i] >= 0 && d[j - a[i]] != -1) {
					if (d[j] == -1 || d[j] > d[j - a[i]] + 1) {
						d[j] = d[j - a[i]] + 1;
					}
				}
			}
		}
		System.out.println(d[m]);

		sc.close();
	}
}

package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11049 {	
	
	private static Logger log = LoggerFactory.getLogger(P11049.class); 

	/*
	 * 행렬 곱셈 순서
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	1393	628	417	42.813%
문제
크기가 N×M인 행렬 A와 M×K인 B를 곱할 때 필요한 곱셈 연산의 수는 총 N×M×K번이다. 
행렬 N개를 곱하는데 필요한 곱셈 연산의 수는 행렬을 곱하는 순서에 따라 달라지게 된다.

예를 들어, A의 크기가 5×3이고, B의 크기가 3×2, C의 크기가 2×6인 경우에 행렬의 곱 ABC를 구하는 경우를 생각해보자.

AB를 먼저 곱하고 C를 곱하는 경우 (AB)C에 필요한 곱셈 연산의 수는 5×3×2 + 5×2×6 = 30 + 60 = 90번이다.
BC를 먼저 곱하고 A를 곱하는 경우 A(BC)에 필요한 곱셈 연산의 수는 3×2×6 + 5×3×6 = 36 + 90 = 126번이다.
같은 곱셈이지만, 곱셈을 하는 순서에 따라서 곱셈 연산의 수가 달라진다.

행렬 N개의 크기가 주어졌을 때, 모든 행렬을 곱하는데 필요한 곱셈 연산 횟수의 최소값을 구하는 프로그램을 작성하시오. 
입력으로 주어진 행렬의 순서를 바꾸면 안된다. 

입력
첫째 줄에 행렬의 개수 N(1 ≤ N ≤ 500)이 주어진다.

둘째 줄부터 N개 줄에는 행렬의 크기 r과 c가 주어진다. (1 ≤ r, c ≤ 500)

항상 순서대로 곱셈을 할 수 있는 크기만 입력으로 주어진다.

출력
첫째 줄에 입력으로 주어진 행렬을 곱하는데 필요한 곱셈 연산의 최소값을 출력한다. 정답은 231-1 보다 작거나 같은 자연수이다.

예제 입력  복사
3
5 3
3 2
2 6
예제 출력  복사
90

	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/f9995c7e91eea4b300b6
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] a = new int[n][2];
		for (int i = 0; i < n; i++) {
			a[i][0] = sc.nextInt();
			a[i][1] = sc.nextInt();
		}
		int[][] d = new int[n][n];
		System.out.println(go(a, d, 0, n - 1));

		sc.close();
	}

	public static int go(int[][] a, int[][] d, int i, int j) {
		if (d[i][j] > 0) {
			return d[i][j];
		}
		if (i == j) {
			return 0;
		}
		if (i + 1 == j) {
			return a[i][0] * a[i][1] * a[j][1];
		}
		d[i][j] = -1;
		for (int k = i; k < j; k++) {
			int t1 = go(a, d, i, k);
			int t2 = go(a, d, k + 1, j);
			int t3 = a[i][0] * a[k][1] * a[j][1];
			if (d[i][j] == -1 || d[i][j] > t1 + t2 + t3) {
				d[i][j] = t1 + t2 + t3;
			}
		}
		return d[i][j];
	}
}

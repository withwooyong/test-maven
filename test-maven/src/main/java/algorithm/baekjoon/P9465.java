package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9465 {

	private static Logger log = LoggerFactory.getLogger(P9465.class);

	/*
	 * 스티커의 점수의 최대값을 구하는 프로그램을 작성하시오. 
	 * 즉, 2n개의 스티커 중에서 점수의 합이 최대가 되면서 서로 변을 공유 하지 않는 스티커 집합을 구해야 한다.
	 * 위의 그림의 경우에 점수가 50, 50, 100, 60인 스티커를 고르면, 점수는 260이 되고 이 것이 최대 점수이다. 
	 * 가장 높은 점수를 가지는 두 스티커 (100과 70)은 변을 공유하기 때문에, 동시에 뗄 수 없다.
	 * 
	 * 첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스의 첫째 줄에는 n (1 ≤ n ≤ 100,000)이 주어진다. 
	 * 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수이다. 
	 * 연속하는 두 정수 사이에는 빈 칸이 하나 있다. 점수는 0보다 크거나 같고, 100보다 작거나 같은 정수이다.
	 * 
	 * 각 테스트 케이스 마다, 2n개의 스티커 중에서 두 변을 공유하지 않는 스티커 점수의 최대값을 출력한다.
	 * 
	 * 2
	 * 5
	 * 50 10 100 20 40
	 * 30 50 70 10 60
	 * 7
	 * 10 30 10 50 100 20 40
	 * 20 40 30 50 60 20 80
	 * 
	 * 260
	 * 290
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	/*
	 * 1. 이 문제는 스티커를 붙일경우와 붙이지 않을경우의 최대값을 비교해주면 된다.
	 * 2. 각 포인트를 비교해줘야 할 요소는 3가지이다. 그 요소는 아래 그림과 같다. 
	 *    즉 스티커를 선택 안했을 경우는 이전의 값과 스티커를 선택했을 때 대각선의 값 그리고 마지막으로 스티커를 선택 할 경우 이전 이전의 대각선 값을 비교해야 한다.
	 * 3. 2번에서 비교한 값중 최대값을 넣어준다. 이 방식을 반복하면서 채워나가면 마지막에 채워진 값중에서 최대값이 최대 스티커 값이 되는 것이다.
	 * 출처: http://wootool.tistory.com/82 [우투리와툴툴]
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		while (T-- > 0) {
			int N = sc.nextInt();
			int[][] A = new int[2][N + 2];
			int[][] B = new int[2][N + 2];
			for (int i = 0; i < 2; i++) {
				for (int j = 2; j <= N + 1; j++) {
					A[i][j] = sc.nextInt();
				}
			}

			for (int i = 2; i <= N + 1; i++) {
				B[0][i] = Math.max(Math.max(B[0][i - 1], B[1][i - 1] + A[0][i]), B[1][i - 2] + A[0][i]);
				B[1][i] = Math.max(Math.max(B[1][i - 1], B[0][i - 1] + A[1][i]), B[0][i - 2] + A[1][i]);
			}

			if (B[0][N + 1] > B[1][N + 1]) {
				log.debug("{}", B[0][N + 1]);
			} else {
				log.debug("{}", B[1][N + 1]);
			}
		}
		sc.close();
	}
}

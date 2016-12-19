package algorithm.algosrc.src.morse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Morse2 {
	
	private static Logger log = LoggerFactory.getLogger(Morse2.class);
	
	private static int[][] bino = new int[201][201];

	public static void main(String[] args) {
		// K 의 최대값 +100. 오버플로우를 막기 위해 이보다 큰 값은 구하지 않는다
		int M = Integer.MAX_VALUE;

		for (int i = 0; i <= 200; ++i) {
			bino[i][0] = 1;
			bino[i][i] = 1;
			for (int j = 1; j < i; ++j) {
				bino[i][j] = Math.min(M, bino[i - 1][j - 1] + bino[i - 1][j]);
			}
		}

		for (int i = 0; i < 10; ++i) {
			getKthSignal(2, 3, i);
		}
	}

	public static String getKthSignal(int n, int m, int k) {
		if (n == 0 && m == 0) {
			return "";
		}

		// - 로 시작하는 신호의 수
		int dashes = bino[n + m - 1][n - 1];
		if (n > 0 && k < dashes) {
			return "-" + getKthSignal(n - 1, m, k);
		}
		return "o" + getKthSignal(n, m - 1, k - dashes);
	}
}

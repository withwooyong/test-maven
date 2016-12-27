package algorithm.hanbit.hanbit15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code15_01 {

	private static Logger log = LoggerFactory.getLogger(code15_01.class);

	private static int m;
	private static int n;

	private static int[][] a = new int[21][301];
	private static int[][] t = new int[21][301];
	private static int[][] Benefit = new int[21][301];

	public static void main(String[] args) {
		input();
		process();
		output();
	}

	private static void input() {
		int z;
		for (int i = 1; i <= m; i++) {
			// scanf("%d", &z); // ���� �ݾ��� z�� �о�´�
			for (int j = 0; j < n; j++) {
				// scanf("%d", &a[j][z]);
			}
		}
	}

	private static void process() {
		int i, j, k;
		for (i = 1; i <= n; i++) {
			for (j = 0; j <= m; j++) {
				for (k = 0; k <= j; k++) {
					// a[i][k]�� ������ �߰��� ������ ���� ���� t[i][j]���� ū ���
					if (t[i - 1][j - k] + a[i][k] > t[i][j]) {
						t[i][j] = t[i - 1][j - k] + a[i][k];
						Benefit[i][j] = k;
					}
				}
			}
		}
	}

	private static void output() {
		int[] g = new int[21];
		int i, j;

		j = m;

		for (i = n; i >= 0; i--) {
			g[i] = Benefit[i][j];
			j -= g[i];
		}

		for (i = 0; i < n; i++) {
			log.debug("{}", g[i]);
		}

		log.debug("{}", t[n][m]);
	}

}

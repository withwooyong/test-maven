package algorithm.hanbit.hanbit13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code13_02 {

	private static Logger log = LoggerFactory.getLogger(code13_02.class);
	
	private static int[][] prime = new int[8][1000];

	public static void main(String[] args) {

		int testCases = 2;
		int baseNumber = 2;

		for (int tc = 0; tc < testCases; ++tc) {
			extendPrime(baseNumber);
			for (int i = 0; prime[baseNumber - 1][i] > 0; i++) {
				log.debug("Prime number : {}", prime[baseNumber - 1][i]);
			}
		}
	}

	private static int extendPrime(int N) {
		int num;
		int x;

		for (int i = 0; i < N; i++) {
			if (i == 0) {
				if (prime[0][0] == 0) {
					prime[0][0] = 2;
					prime[0][1] = 3;
					prime[0][2] = 5;
					prime[0][3] = 7;
				}
			} else {
				x = 0;
				for (int j = 0; prime[i - 1][j] > 0; j++) {
					for (int k = 0; k < 5; k++) {
						num = prime[i - 1][j] * 10 + 2 * k + 1;
						log.debug("candidated num : {}", num);

						if ((prime[i][x] == 0) && (checkPrime(num))) {
							prime[i][x] = num;
							x++;
						}
					}
				}
			}
		}

		return 0;
	}

	private static boolean checkPrime(int num) {
		for (int i = 2; i * i <= num; i++) {
			if (num % i == 0) {
				return false;
			}
		}
		return true;
	}
}

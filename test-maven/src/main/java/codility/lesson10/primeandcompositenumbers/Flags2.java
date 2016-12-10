package codility.lesson10.primeandcompositenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flags2 {
	
	private static Logger log = LoggerFactory.getLogger(Flags2.class);

	public static void main(String[] args) {
		
		int[] N = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
	}

	/*
	 * 접근방법: (에라토스테네스의) 체처럼 숫자들을 모두 체 위에 놓고 소수가 아닌 수들을 걸러내면 소수만 남는다.
	 * 11 - Sieve of Eratosthenes - CountSemiprimes Solution Java - 100%
	 * 
	 */
	public int[] solution(int N, int[] P, int[] Q) {
		int[] result = new int[P.length];
		int[] flags = new int[N + 1]; // 0 : default
		int[] presum = new int[N + 1];

		// 0 : prime
		// 1 : no prime
		// 2 : semi prime

		flags[0] = 1; // 1 : no prime
		flags[1] = 1; // 1 : no prime

		for (int i = 2; i * i <= N; i++) {
			if (flags[i] == 1)
				continue;
			int k = i * i;
			while (k <= N) {
				flags[k] = 1; // 1 : no prime
				k = k + i; // next multiple
			}
		}

		for (int i = 2; i * i <= N; i++) {
			if (flags[i] == 1)
				continue;
			int k = i * i;
			while (k <= N) {
				if (flags[i] == 0 && flags[k / i] == 0) {
					flags[k] = 2; // 2 : semi prime
				}
				k = k + i; // next multiple
			}
		}

		int semi_prime_counter = 0;
		for (int i = 2; i <= N; i++) {
			if (flags[i] == 2)
				semi_prime_counter++;
			presum[i] = semi_prime_counter;
		}

		for (int i = 0; i < P.length; i++) {
			result[i] = presum[Q[i]] - presum[P[i] - 1];
		}
		return result;
	}
}

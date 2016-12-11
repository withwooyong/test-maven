package codility.lesson11.sieveoferatosthenes;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://cafe.naver.com/mathget/57
 * 반소수, 세미소수(semiprime, biprime, 2-almost prime, pq number)는 두 소수의 곱으로 나타낼 수 있는 수
 * 이 수의 양의 약수의 개수는 3개 또는 4개
 * 하지만 어떤 자연수의 양의 약수의 개수가 3개 또는 4개라고 해서 반드시 semiprime 인 것은 아닙니다.
 * 예를 들어 8은 양의 약수의 개수가 4개 (1, 2, 4, 8) 이지만 8은 두 소수의 곱으로 나타낼 수 없습니다.
 */
public class Semiprimes {
	
	private static Logger log = LoggerFactory.getLogger(Semiprimes.class);

	public static void main(String[] args) {
		int small = 2;
		int large = 10;
		fillSieve(large).toString();
		divisors(large).toString();
		semiPrimes(small, large).toString();
	}
	
	// 소수: 1과 자기 자신만으로 나누어 떨어지는 1보다 큰 양의 정수.
	public static ArrayList<Integer> fillSieve(int N) { // prime
		ArrayList<Integer> arrPrimes = new ArrayList<>();
		boolean[] primes = new boolean[N];
		Arrays.fill(primes,  true);
		primes[0] = false;
		primes[1] = false;
		for (int i = 2; i < primes.length; i++) {
			if (primes[i] != false) {
				for (int j = i; j * i < primes.length; j++) {
					primes[i * j] = false;
				}
			}
		}
		
		for (int i = 2; i < primes.length; i++) {
			if (primes[i] == true) {
				arrPrimes.add(i);
			}
		}
		log.debug("소수:{}", arrPrimes.toString());
		return arrPrimes;
	}
	
	public static boolean isPrime(int n) {
		for (int i = 2; i < n; i++) {
			if (n % i == 0)
				return false;
		}
		return true;
	}
	
	public static ArrayList<Integer> divisors(int N) {
		ArrayList<Integer> arrDivisors = new ArrayList<>();
		for (int i = 1; i < N; i++) {
			if (N % i == 0) {
				arrDivisors.add(i);
			}
		}
		log.debug("약수:{}", arrDivisors.toString());
		return arrDivisors;
	}
	
	
	/*
	 * 반소수, 세미소수(semiprime, biprime, 2-almost prime, pq number)는 두 소수의 곱으로 나타낼 수 있는 수
	 * 이 수의 양의 약수의 개수는 3개 또는 4개
	 * 하지만 어떤 자연수의 양의 약수의 개수가 3개 또는 4개라고 해서 반드시 semiprime 인 것은 아닙니다.
	 * 예를 들어 8은 양의 약수의 개수가 4개 (1, 2, 4, 8) 이지만 8은 두 소수의 곱으로 나타낼 수 없습니다.
	 */
	public static ArrayList<Integer> semiPrimes(int small, int large) {
		ArrayList<Integer> semiPrimes = new ArrayList<>();
		int factorNumber;
		boolean semiPrime;
		for (int num = small; num < large; num++) {
			factorNumber = 0; // 양의 약수의 개수 
			semiPrime = true;
			int[] factorNumbers = new int[2];
			for (int i = 2; i < num; i++) {
				if (num % i == 0) { // 2 부터 n까지의 약수 divisor
					factorNumber++;
					if (factorNumber == 3) {
						break;
					}
					factorNumbers[factorNumber - 1] = i;
				}
			}
			if (factorNumber == 1) {
				semiPrimes.add(num);
				//log.debug("{}", num);
			} else if (factorNumber == 2) {
				for (int j = 0; j < 2; j++) {
					for (int k = 2; k < factorNumbers[j]; k++) {
						if (factorNumbers[j] % k == 0) {
							semiPrime = false;
							break;
						}
					}
					if (!semiPrime)
						break;
				}
				if (semiPrime) {
					semiPrimes.add(num);
					//log.debug("{}", num);
				}
			}
		}
		log.debug("반소수:{}", semiPrimes.toString());
		return semiPrimes;
	}
}

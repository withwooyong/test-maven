package codility.lesson10.primeandcompositenumbers;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Primeandcompositenumbers {
	
	private static Logger log = LoggerFactory.getLogger(Primeandcompositenumbers.class);

	public static void main(String[] args) {
		//int[] A = { 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		int[] A = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int n = 20; // 1, 2, 7, 14, 49, 98, are divisors of 98
		log.debug("{}", solution(n));
		log.debug("{}", countingdivisors(n));
		log.debug("{}", primalityTest(n));
		log.debug("{}", reversingCoins(A));

	}
	
	// 약수(約數, divisor)는 어떤 수를 나누었을 때 나머지가 0인 수
	// 98의 약수는 1, 2, 7, 14, 49, 98, are divisors of 98
	private static int solution(int n) {
		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			// If remainder is 0 when 'n' is divided by 'i',
			if (n % i == 0) { // divisor
				arr.add(i); 
			}
		}
		log.debug("{}", arr.toString());
		return arr.size();
	}
	
	// 약수 갯수 세기
	public static int countingdivisors(int n) {
		int i = 1;
		int result = 0;		
		while (i * i < n) {
			if (n % i == 0) {
				result += 2;
			}
			i += 1;
		}
		if (i * i == n) {
			result += 1;
		}
		log.debug("result={}", result);
		return result;
	}
	
	// 약수인지 아닌지 확인
	public static boolean primalityTest(int n) {
		
		int i = 2;
		while (i * i <= n) {
			if (n % i == 0) {
				return false;
			}
			i += 1;
		}
		return true;
	}
	
	// O(n log n): We can simulate the results of each person reversing coins.
	public static int reversingCoins(int[] A) {
		int n = A.length;
		int result = 0;
		// int coin = A[0] * (n + 1);
		int[] coin = new int[A.length+1];
		log.debug("result={}", result);
		for (int i = 1; i < n + 1; i++) {
			int k = i;
			while (k <= n) {
				coin[k] = (coin[k] + 1) % 2;
				k += i;
			}
			result += coin[i];
			log.debug("result={} {}", result, coin[i]);
		}
		log.debug("result={}", result);
		return result;
	}
}

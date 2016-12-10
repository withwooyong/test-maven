package codility.lesson10.primeandcompositenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CountFactors
 * Count factors of given number n.
 * 
 * A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
 * For example, 
 * 6 is a factor of 24, 
 * because M = 4 satisfies the above condition (24 = 6 * 4).
 * 
 * Write a function:
 * class Solution { public int solution(int N); }
 * that, given a positive integer N, returns the number of its factors.
 * 
 * For example, 
 * given N = 24, 
 * the function should return 8, 
 * because 24 has 8 factors, 
 * namely 1, 2, 3, 4, 6, 8, 12, 24. 
 * There are no other factors of 24.
 * 
 * Assume that:
 * N is an integer within the range [1..2,147,483,647].
 * 
 * Complexity:
 * expected worst-case time complexity is O(sqrt(N));
 * expected worst-case space complexity is O(1).
 * @author wooyong
 *
 */
public class CountFactors {
	
	private static Logger log = LoggerFactory.getLogger(CountFactors.class);
	
	/*
	 * Count factors of given number n.
	 */
	// factor 약수
	// 약수(約數, divisor)는 어떤 수를 나누었을 때 나머지가 0인 수를 말하며, 배수 관계와 서로 반대되는 개념이다.
	// N = D * M. (24 = 6 * 4).
	public static void main(String[] args) {
		log.debug("{}", solution(24));
		log.debug("{}", divisorsCount(24));
		log.debug("{}", divisorsSum(24));
	}

	// return 8
	public static int solution(int N) {
		int res = 0;
		for (int i = 1; (long) i * i <= N; i++) {
			if (i * i == N) {
				log.debug("{}", res);
				return ++res;
			} else if (N % i == 0) {
				res += 2;
			}
			log.debug("{}", res);
		}
		return res;
	}
	
	// Most Efficient way to Compute the sum of divisors of N (1 ≤ N ≤ 1 000 000 000)
	// 약수들의 합
	public static int divisorsSum(int N) {

		int maxD = (int) Math.sqrt(N); // 어떤 수 x를 제곱하여 a가 되었을 때에, x를 a의 제곱근이라고 한다.
		log.debug("N={}, maxD={} sqrt={}", N, maxD, Math.sqrt(24));
		int sum = 1;
		for (int i = 2; i <= maxD; i++) {
			if (N % i == 0) { // 약수(divisor) 어떤 수를 나누었을 때 나머지가 0인 수
				sum += i;
				int d = N / i;
				if (d != i) {
					log.debug("{}", "=============");
					sum += d;
				}
			}
		}
		return sum;
	}
	
	// 약수 갯수
	public static int divisorsCount(int N) {
		int count = 0;
		int sum = 0;
		for (int i = 1; i <= N; i++) {
			if (N % i == 0) {
				log.debug("{}", i);
				count++;
				sum += i;
			}
		}
		log.debug("sum={} count={}", sum, count);
		return count;
	}
}

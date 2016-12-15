package codility.lesson12.euclideanalgorithm;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ChocolatesByNumbers
 * There are N chocolates in a circle. 
 * Count the number of chocolates you will eat.
 * 
 * Two positive integers N and M are given. 
 * Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
 * 
 * You start to eat the chocolates. 
 * After eating a chocolate you leave only a wrapper.
 * You begin with eating chocolate number 0. 
 * Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
 * More precisely, if you ate chocolate number X, 
 * then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
 * 
 * You stop eating when you encounter an empty wrapper.
 * 
 * For example, 
 * given integers N = 10 and M = 4. 
 * You will eat the following chocolates: 0, 4, 8, 2, 6.
 * The goal is to count the number of chocolates that you will eat, following the above rules.
 * 
 * Write a function:
 * class Solution { public int solution(int N, int M); }
 * that, given two positive integers N and M, returns the number of chocolates that you will eat.
 * 
 * For example, given integers N = 10 and M = 4. 
 * the function should return 5, as explained above.
 * 
 * Assume that:
 * N and M are integers within the range [1..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(log(N+M));
 * expected worst-case space complexity is O(log(N+M)).
 * @author wooyong
 */
public class ChocolatesByNumbers {
	
	private static Logger log = LoggerFactory.getLogger(ChocolatesByNumbers.class);

	// https://www.martinkysel.com/codility-chocolatesbynumbers-solution/

	/*
	 * There are N chocolates in a circle. Count the number of chocolates you will eat.
	 */
	public static void main(String[] args) {
		int N = 10;
		int M = 4;
		log.debug("{}", native1(N, M));  //return 5
		log.debug("{}", solution(N, M));
		log.debug("{}", solution2(N, M));
	}
	
	private static int solution(int N, int M) {
		return lcm(N, M) / M;
	}
	
	private static int lcm(int p, int q) {
		return p * (q / gcd(p, q));
	}
	
	private static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}		
		return gcd(q, p % q);
	}
	
	private static int native1(int N, int M) {
		int[] eaten = new int[N]; // 0으로 초기화 되었음.
		log.debug("{}", eaten);
		int at = 0;
		int cnt = 0;
		
		while (eaten[at] == 0) {
			log.debug("{}", at);
			eaten[at] = 1;
			at = (at + M) % N;
			cnt += 1;
		}
		return cnt;
	}

	// 이건아님.
	private static int solution2(int N, int M) {
		BigInteger a = BigInteger.valueOf(N);
		BigInteger b = BigInteger.valueOf(M);
		int gcd = a.gcd(b).intValue();
		if (gcd == 0) {
			return N;
		} else {
			return solution(M, N % M);
		}
	}	
}

package codility.lesson12.euclideanalgorithm;


import java.math.BigInteger;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCD_LCM {
	
	private static Logger log = LoggerFactory.getLogger(GCD_LCM.class);

	public static void main(String[] args) {
		long a = 100;
		long b = 200;
		long[] input = { 100, 200, 300 }; 
		log.debug("gcd {}", gcd(a, b));
		log.debug("gcd {}", gcd2(a, b));
		log.debug("gcd {}", gcd(input));
		log.debug("gcd {}", BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)));
		log.debug("lcm {}", lcm(a, b));
		log.debug("lcm {}", lcm(input));
		//  a * (b / gcd(a, b));
		//BigInteger c = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b));
		log.debug("lcm {}", a * (b / BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue()));
	}

	private static long gcd2(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}
	
	// 최대공약수 Greatest Common Divisor
	// https://ko.wikipedia.org/wiki/%EC%B5%9C%EB%8C%80%EA%B3%B5%EC%95%BD%EC%88%98
	private static long gcd(long a, long b) {
		// 10,000이하의 자연수
		if (b == 0) {
			return a;
		}
		return gcd(b, a % b);
	}
	
	private static long gcd(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++) {
			result = gcd(result, input[i]);
		}
		return result;
	}

	/*
	 * 최소공배수(最小公倍數)는 n개의 정수 또는 다항식에 대해 모두의 배수가 되는 최소의 자연수 또는 다항식을 말한다. 이중 하나가 0일
	 * 때에 최소공배수는 0으로 한다. 최소공배수는 LCM(least common multiple)이라고 자주 줄여쓰며, 정수 a, b의
	 * 최소공배수는 lcm(a, b)로 표기한다.
	 */
	private static long lcm(long a, long b) {
		return a * (b / gcd(a, b));
	}

	private static long lcm(long[] input) {
		log.debug("{}", Arrays.toString(input));
		long result = input[0];
		for (int i = 1; i < input.length; i++) {
			result = lcm(result, input[i]);
		}
		return result;
	}
}

package codility.lesson12.euclideanalgorithm;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 최대공약수
 * [ greatest common divisor , GCD , GCF ]
 * 최대공약수(最大公約數)란, 0 이 아닌 두 개 이상의 정수의 공통되는 약수 중에서 가장 큰 수이다. 
 * 따라서 두 정수 a와 b의 최대공약수는 a의 약수인 동시에 b의 약수인 수, 즉 두 정수 a, b의 공약수 중에서 가장 큰 수를 의미한다.
 */
public class GCD {
	
	private static Logger log = LoggerFactory.getLogger(GCD.class);

	public static void main(String[] args) {
		long a = 100;
		long b = 200;
		log.debug("{}", gcd(a, b));
		log.debug("{}", BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)));
	}
	
	// 최대공약수 Greatest Common Divisor
	// https://ko.wikipedia.org/wiki/%EC%B5%9C%EB%8C%80%EA%B3%B5%EC%95%BD%EC%88%98
	private static long gcd(long A, long B) {
		// 10,000이하의 자연수
		if (B == 0) {
			return A;
		}
		return gcd(B, A % B);
	}
	
	private static long gcd2(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}
	
	// 
	private static long gcd(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++) {
			result = gcd(result, input[i]);
		}
		return result;
	}
	
	//LCM(least common multiple)
	private static long lcm(long a, long b)
	{
	    return a * (b / gcd(a, b));
	}

	private static long lcm(long[] input)
	{
	    long result = input[0];
	    for(int i = 1; i < input.length; i++) result = lcm(result, input[i]);
	    return result;
	}
	
	
}

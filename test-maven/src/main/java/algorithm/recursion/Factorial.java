package algorithm.recursion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Factorial {
	
	private static Logger log = LoggerFactory.getLogger(Factorial.class);

	// Factorial : n!
	public static void main(String[] args) {
		int n = 10;
		log.debug("{}", multiply(n));
		log.debug("{}", factorial(n));
		log.debug("{}", power(2,  3));
	}
	
	// 곱하기
	private static int multiply(int n) {
		int sum = 1;
		for (int i = 1; i <= n; i++) {
			sum *= i;
		}
		return sum;
	}
	
	private static int factorial(int n) {
		if (n == 0) {
			return 1;
		} else {
			return n * (factorial(n-1));
		}
	}
	
	// 제곱
	private static double power(double x, int n) {
		if (n == 0) {
			return 1;
		} else {
			return x * power(x, n - 1);
		}
	}
}

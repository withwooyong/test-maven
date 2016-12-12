package codility.lesson13.fibonaccinumbers;


import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 1. 피보나치(Fibonacci)란?
 * 피보나치는 수열 종류 중 하나이다.
 * 1 1 2 3 5 8 13 21 34....
 * 앞에 있는 숫자와 그 앞에 있는 숫자와 더한 것을 나열하는 것이다.
 * 1+1 = 2
 * 2+3 = 5
 * 3+5 = 8
 * 5+8 = 13
 */
public class Fibonacci {
	
	private static Logger log = LoggerFactory.getLogger(Fibonacci.class);

	public static void main(String[] args) {
		int n = 3;
		log.debug("{}", fibonacci(n));
		log.debug("{}", fibonacci2(n));
	}
	
	private static int fibonacci(int n) {
		if (n <= 1) {
			return n;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}
	
	public static int fibonacci2(int n) {
		ArrayList<Integer> list = new ArrayList<>();
		int a = 0; 
		int b = 1;
		int c = 0;
		while (a <= n) {
			list.add(a);
			c = a + b;
			a = b;
			b = c;
		}
		log.debug("{} ", list.toString());
		return c;
	}

}

package codingdojang;
/*
 * http://marobiana.tistory.com/80
 * 1. 피보나치(Fibonacci)란?
 * 피보나치는 수열 종류 중 하나이다.
 * 1 1 2 3 5 8 13 21 34....
 * 앞에 있는 숫자와 그 앞에 있는 숫자와 더한 것을 나열하는 것이다.
 * 1+1 = 2
 * 2+3 = 5
 * 3+5 = 8
 * 5+8 = 13
 * ...
 * 2. 재귀 예제
 * 문제 : 피보나치 수열을 입력 받은 숫자 개수만큼 출력하라
 */
public class Fibonacci {

	public static void main(String[] args) {
		int input = 10; 
		for (int i = 1; i <= input; i++) {
			System.out.println("i=" + i + ":" + fibonacci(i));
			System.out.println("count=" + count);
		}
	}
	
	public static int count = 0;

	public static int fibonacci(int n) {
		count++;
		if (n <= 1) {
			return n;
		} else {
			return fibonacci(n - 2) + fibonacci(n - 1);
		}
	}
}

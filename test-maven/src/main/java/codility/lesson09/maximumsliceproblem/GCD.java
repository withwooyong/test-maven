package codility.lesson09.maximumsliceproblem;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GCD {
	
	private static Logger log = LoggerFactory.getLogger(GCD.class);

	public static void main(String[] args) {
		long[] C = { 12, 8 };
		int A = 12;
		int B = 8;
		euclid(A, B);
		gcdThing(A, B);
		log.debug("최대공약수={}", gcd1(A, B));
		log.debug("최대공약수={}", gcd2(C));
	}
	
	/*
	 * P 문제
	 * 유클리드 호제법 (두 개의 정수의 최대공약수를 구하는 알고리즘.)
	 * 두 자연수 A, B에 대하여 A를 B로 나눈 나머지를 R이라 하면, 
	 * A와 B의 최대공약수와 B와 R의 최대공약수는 같다는 성질을 이용하여, 
	 * B를 R로 나눈 나머지 R`1`을 구하고, 또 R을 R`1`로 나눈 나머지R`2`를 구하는 것을 반복하면 최대공약수를 구할 수 있게 된다.
	 */
	private static void euclid(int A, int B) {
		log.debug("{} {}", A, B);
		if (A < B) { // 큰값 작은값 순으로 들어와야 함.
			return;
		}
		int R = 1;
		// 최소공배수
		int LCM = 0;		
		int C = A;
		int D = B;
		while (R > 0) {
			R = A % B;
			A = B;
			B = R;
		}
		LCM = C * D / A;
		log.debug("최소공배수 LCM={}", LCM); // 24
		/*
		 * 최대공약수(最大公約數)란, 0이 아닌 두 정수나 다항식의 공통되는 약수 중에서 가장 큰 수를 말한다.
		 * 두 정수 a와 b의 최대공약수를 기호로 gcd(a,b)로 표기한다.
		 */
		log.debug("최대공약수 GCD={}", A);   // 4
	}
	
	private static int gcdThing(int A, int B) {
		BigInteger b1 = BigInteger.valueOf(A);
		BigInteger b2 = BigInteger.valueOf(B);
		BigInteger gcd = b1.gcd(b2);
		log.debug("최대공약수 GCD={}", gcd.intValue());
		return gcd.intValue();
	}
	
	// 최대공약수
	private static long gcd1(long a, long b) {
		while (b > 0) {
			long temp = b;
			b = a % b; // % is remainder
			a = temp;
		}
		return a;
	}

	// 최대공약수
	private static long gcd2(long[] input) {
		long result = input[0];
		for (int i = 1; i < input.length; i++)
			result = gcd1(result, input[i]);
		return result;
	}

	

}

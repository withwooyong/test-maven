package codility.lesson13.fibonaccinumbers;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Ladder2 {

	private static Logger log = LoggerFactory.getLogger(Ladder2.class);

	int number = 0;
	
	public static void main(String[] args) {
		
		int[] A = new int[] { 4, 4, 5, 5, 1 };
		int[] B = new int[] { 3, 2, 4, 3, 1 };
		
		log.debug("{}", Arrays.toString(solution(A, B)));
	}

	/*
	 * 접근방법:
	 * 사다리 가로대에 따른 경우의 수는 파보나치 수열과 같다.
	 * 피보나치 수열의 값은 그때 그때 다시 계산하지 않고 미리 계산해놓고 재사용한다.
	 * 피보나치 수열이 정수 최대값 2,147,483,647 초과하지 않도록 주의한다.
	 * 초과하지 않도록 2^30(=1,073,741,824)의 나머지를 구해서 저장한다.
	 * 요구조건 "each element of array B is an integer within the range [1..30]"
	 * 설정한 이유이다.
	 */
	public static int[] solution(int[] A, int[] B) {
		int numberOfRequest = A.length;

		int maxBaseValue = 0;
		for (int m = 0; m < numberOfRequest; m++) {
			maxBaseValue = (maxBaseValue < A[m]) ? A[m] : maxBaseValue;
		}

		int[] fibonacci = new int[maxBaseValue + 1];
		fibonacci[0] = 1;
		fibonacci[1] = 1;
		for (int i = 2; i <= maxBaseValue; i++) {
			fibonacci[i] = (fibonacci[i - 1] + fibonacci[i - 2]) % (1 << 30);
			// 2^30 = 1,073,741,824 (is under Integer.MAX_VALUE 2,147,483,647)
		}

		// N = 2, you have 2 different ways of climbing = fib[2]
		// N = 3, you have 3 different ways of climbing = fib[3]
		// N = 4, you have 5 different ways of climbing = fib[4]
		// N = 5, you have 8 different ways of climbing = fib[5]

		int[] result = new int[numberOfRequest];
		for (int i = 0; i < numberOfRequest; i++) {
			result[i] = fibonacci[A[i]] % (1 << B[i]);
		}
		return result;
	}

}

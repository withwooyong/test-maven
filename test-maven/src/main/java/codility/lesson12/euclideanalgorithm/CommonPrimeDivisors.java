package codility.lesson12.euclideanalgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CommonPrimeDivisors
 * Check whether two numbers have the same prime divisors.
 * 
 * A prime is a positive integer X that has exactly two distinct divisors: 1 and X. 
 * The first few prime integers are 2, 3, 5, 7, 11 and 13.
 * 
 * A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. 
 * For example, 2 and 5 are prime divisors of 20.
 * 
 * You are given two positive integers N and M. 
 * The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
 * 
 * For example, given:
 * N = 15 and M = 75, the prime divisors are the same: {3, 5};
 * N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
 * N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A, int[] B); }
 * that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
 *
 * For example, given:
 * A[0] = 15   B[0] = 75
 * A[1] = 10   B[1] = 30
 * A[2] = 3    B[2] = 5
 * the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
 * 
 * Assume that:
 * Z is an integer within the range [1..6,000];
 * each element of arrays A, B is an integer within the range [1..2,147,483,647].
 * 
 * Complexity:
 * expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class CommonPrimeDivisors {

	private static Logger log = LoggerFactory.getLogger(CommonPrimeDivisors.class);
	/*
	 * Check whether two numbers have the same prime divisors.
	 */
	public static void main(String[] args) {
		int[] A = new int[] { 15, 10, 3 };
		int[] B = new int[] { 75, 30, 5 };
		log.debug("{}", solution(A, B));

	}

	/*
	 * 접근방법:
	 * - 유클리드 호제법을 이용하여 최대공약수(가)를 구한다.
	 * - 최대공약수는 두개의 수 공약수 중에서 최대인 수 이다. 즉, 모두 나누어지는 최대 수이다.
	 * - 먼저 두개 수(a,b)의 최대공약수(가)를 구한다.
	 * - 수(a)와 최대공약수(가)의 최대공약수(나)를 구한다.
	 * - 최대공약수(나)가 존재한다면 수(a)를 최대공약수(나)로 나눠 몫을 구한다.
	 *    (공약수이므로 나누어질 것이다.)
	 * - 계속 반복해서 몫이 1에 도달한 경우 최대공약수에 나타난 소수이외에는 없다는 의미
	 * - 최대공약수가 1인 경우는 최대공약수가 없다는 의미로 추가 소수가 존재한다는 의미
	 * - 위 4개를 반복하며 알아낸다.
	 * - 수(b)도 위 단락 과정을 반복한다.
	 * - 수(a)와 수(b) 모두 결국 몫이 1이 되면 최대공약수(가)의 factor 이외에는 없는 경우이다.
	 */
	private static int solution(int[] A, int[] B) {
		int res = 0;
		for (int i = 0; i < A.length; i++) {
			int x = A[i];
			int y = B[i];
			int gcd = gcd(x, y, 1);
			int gcdTmp = 0;
			while (x != 1) {
				gcdTmp = gcd(x, gcd, 1);
				if (gcdTmp == 1)
					break;
				x /= gcdTmp;
			}
			if (x != 1)
				continue;

			while (y != 1) {
				gcdTmp = gcd(y, gcd, 1);
				if (gcdTmp == 1)
					break;
				y /= gcdTmp;
			}
			if (y != 1)
				continue;
			res++;
		}
		return res;
	}

	private static int gcd(int a, int b, int res) {
		if (a == b)
			return res * a;
		else if (a % 2 == 0 && b % 2 == 0)
			return gcd(a / 2, b / 2, res * 2);
		else if (a % 2 == 0)
			return gcd(a / 2, b, res);
		else if (b % 2 == 0)
			return gcd(a, b / 2, res);
		else if (a > b)
			return gcd(a - b, b, res);
		else
			return gcd(a, b - a, res);
	}
}

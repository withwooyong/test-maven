package codility.lesson10.primeandcompositenumbers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MinPerimeterRectangle
 * Find the minimal perimeter of any rectangle whose area equals N.
 * 
 * An integer N is given, representing the area of some rectangle.
 * The area of a rectangle whose sides are of length A and B is A * B, 
 * and the perimeter is 2 * (A + B).
 * 
 * The goal is to find the minimal perimeter of any rectangle whose area equals N. 
 * 
 * The sides of this rectangle should be only integers.
 * 
 * For example, given integer N = 30, rectangles of area 30 are:
 * (1, 30), with a perimeter of 62,
 * (2, 15), with a perimeter of 34,
 * (3, 10), with a perimeter of 26,
 * (5, 6), with a perimeter of 22.
 * 
 * Write a function:
 * class Solution { public int solution(int N); }
 * that, given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.
 * 
 * For example, given an integer N = 30, the function should return 22, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(sqrt(N));
 * expected worst-case space complexity is O(1).
 * @author wooyong
 *
 */
public class MinPerimeterRectangle {
	
	private static Logger log = LoggerFactory.getLogger(MinPerimeterRectangle.class);

	/*
	 * Find the minimal perimeter of any rectangle whose area equals N.
	 */
	public static void main(String[] args) {
		log.debug("{}", solution(30));
		log.debug("{}", solution2(30));
	}

	// The goal is to find the minimal perimeter of any rectangle whose area equals N.
	// integer N = 30, the function should return 22
	
	public static int solution(int N) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i * i <= N; i++) {
			if (N % i == 0) {
				min = 2 * (i + N / i) < min ? 2 * (i + N / i) : min;
			}
		}
		return min;
	}
	
	/*
	 * 접근방법:
	 * 제곱근까지만 검색한다.
	 * 제곱근이전 숫자(A) 중 나눴을때 나머지가 0인 경우는 제곱근이후 Pair Number(B)​가 있다는 의미다.
	 * 가로 (A)와 세로 (B)로 가정했을 때 둘레가 최소인 값을 구한다.
	 * The area of a rectangle whose sides are of length A and B
	 * is A * B, and the perimeter is 2 * (A + B).
	 * rectangle 직사각형 perimeter 둘레 
	 */
	public static int solution2(int N) {
		// 제곱 square 제곱근 square root		
		int sqrt_num = (int) Math.sqrt(N);
		log.debug("sqrt_num={}", sqrt_num);
		int min_perimeter = Integer.MAX_VALUE;
		for (int i = 1; i <= sqrt_num; i++) {
			if (N % i == 0) {
				int A = i;
				int B = (int) (N / i);
				int perimeter = 2 * (A + B); // the perimeter is 2 * (A + B).
				log.debug("{} {} {}", A, B, perimeter);
				min_perimeter = Math.min(min_perimeter, perimeter);
			}
		}
		return min_perimeter;
	}
	
}

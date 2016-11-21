/*
	An integer N is given, representing the area of some rectangle.
	The area of a rectangle whose sides are of length A and B is A * B, and the perimeter is 2 * (A + B).
	
	The goal is to find the minimal perimeter of any rectangle whose area equals N. 
	
	The sides of this rectangle should be only integers.
	
	For example, given integer N = 30, rectangles of area 30 are:
	(1, 30), with a perimeter of 62,
	(2, 15), with a perimeter of 34,
	(3, 10), with a perimeter of 26,
	(5, 6), with a perimeter of 22.
	
	Write a function:
	class Solution { public int solution(int N); }
	
	that, given an integer N, returns the minimal perimeter of any rectangle whose area is exactly equal to N.
	
	For example, given an integer N = 30, the function should return 22, as explained above.
	
	Assume that:
	N is an integer within the range [1..1,000,000,000].
	
	Complexity:
	expected worst-case time complexity is O(sqrt(N));
	expected worst-case space complexity is O(1).
	
	일부 직사각형의 영역을 나타내는 정수 N이 주어집니다.
	변의 길이가 A와 B 인 사각형의 면적은 A * B이고 둘레는 2 * (A + B)입니다.
	목표는 면적이 N 인 모든 직사각형의 최소 둘레를 찾는 것입니다.이 직사각형의 변은 정수 여야합니다.
	
	예를 들어 정수 N = 30 인 경우 영역 30의 직사각형은 다음과 같습니다.
	(1,30), 둘레는 62,
	(2, 15), 둘레는 34,
	(3, 10), 둘레는 26,
	(5, 6), 둘레는 22입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int N); }
	정수 N이 주어지면 면적이 N과 정확히 일치하는 사각형의 최소 둘레를 반환합니다.
	예를 들어, 정수 N = 30 인 경우 함수는 위에서 설명한대로 22를 반환해야합니다.
	
	가정하자면 :
	N은 [1..1,000,000,000] 범위의 정수입니다.
	
	복잡성:
	예상 최악의 경우의 시간 복잡도는 O (sqrt (N))입니다.
	예상 최악의 공간 복잡도는 O (1)입니다.
 */

//Score: 100/100
package codility.solution.primeandcompositenumbers;

public class MinPerimeterRectangle {

	public static void main(String[] args) {
		System.out.println(solution(30));
	}

	public static int solution(int N) {
		int min = Integer.MAX_VALUE;
		for (int i = 1; i * i <= N; i++) {
			if (N % i == 0)
				min = 2 * (i + N / i) < min ? 2 * (i + N / i) : min;
		}
		return min;
	}
}

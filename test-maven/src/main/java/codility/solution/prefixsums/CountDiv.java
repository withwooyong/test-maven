/*
	Write a function:
	class Solution { public int solution(int A, int B, int K); }
	that, given three integers A, B and K, returns the number of integers within the range [A..B] that are divisible by K, i.e.:
	{ i : A ≤ i ≤ B, i mod K = 0 }
	For example, for A = 6, B = 11 and K = 2, your function should return 3, because there are three numbers divisible by 2 within the range [6..11], namely 6, 8 and 10.
	Assume that:
	A and B are integers within the range [0..2,000,000,000];
	K is an integer within the range [1..2,000,000,000];
	A ≤ B.
	Complexity:
	expected worst-case time complexity is O(1);
	expected worst-case space complexity is O(1).
	
	함수 작성 :
클래스 솔루션 {public int solution (int A, int B, int K); }
3 개의 정수 A, B, K가 주어진 경우, K로 나눌 수있는 범위 [A..B] 내의 정수의 수를 반환합니다. 즉,
{i : A ≤ i ≤ B, i mod K = 0}
예를 들어 A = 6, B = 11 및 K = 2 인 경우 함수는 [6..11], 즉 6, 8 및 10 범위에서 2로 나눌 수있는 세 개의 숫자가 있으므로 3을 반환해야합니다.
가정하자면 :
A와 B는 [0..2,000,000,000] 범위의 정수입니다.
K는 [1..2, 000,000,000] 범위의 정수이고;
A ≤ B.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (1)이다.
예상 최악의 공간 복잡도는 O (1)입니다.
 */

//SCORE: 100/100
package codility.solution.prefixsums;

public class CountDiv {
	public static void main(String[] args) {
		int A = 6;
		int B = 11;
		int K = 2;
		System.out.println(solution(A, B, K));
	}

	public static int solution(int A, int B, int K) {
		if (A % K == 0) {
			System.out.println("= " + (B / K));
			System.out.println("= " + (A / K));
			return (B / K) - (A / K) + 1;
		}
		return (B / K) - (A / K);
	}
}

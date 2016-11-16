/*
	A positive integer D is a factor of a positive integer N if there exists an integer M such that N = D * M.
	For example, 6 is a factor of 24, because M = 4 satisfies the above condition (24 = 6 * 4).
	Write a function:
	class Solution { public int solution(int N); }
	that, given a positive integer N, returns the number of its factors.
	For example, given N = 24, the function should return 8, because 24 has 8 factors, namely 1, 2, 3, 4, 6, 8, 12, 24. There are no other factors of 24.
	Assume that:
	N is an integer within the range [1..2,147,483,647].
	Complexity:
	expected worst-case time complexity is O(sqrt(N));
	expected worst-case space complexity is O(1).
	
	양의 정수 D는 N = D * M 인 정수 M이 존재할 경우 양의 정수 N의 인수입니다.
예를 들어 M = 4가 위의 조건 (24 = 6 * 4)을 충족하므로 6은 24의 인수입니다.
함수 작성 :
클래스 솔루션 {public int solution (int N); }
양의 정수 N이 주어지면 그 요소의 수를 반환합니다.
예를 들어, N = 24 일 때 함수는 8을 반환해야합니다. 24는 8, 1, 2, 3, 4, 6, 8, 12, 24 등의 요소를 갖기 때문입니다.
가정하자면 :
N은 [1..2,147,483,647] 범위의 정수입니다.
복잡성:
예상 최악의 경우의 시간 복잡도는 O (sqrt (N))입니다.
예상 최악의 공간 복잡도는 O (1)입니다.
 */

//Score: 100/100
package codility.solution.primeandcompositenumbers;

public class CountFactors {

	public static void main(String[] args) {
		System.out.println(solution(24));
	}
	
	public static int solution(int N) {
		int res = 0;
		for (int i = 1; (long)i*i <=N ; i++) {
			if (i*i == N)
				return ++res;
			else if(N%i == 0)
				res+=2;
		}
		return res;
	}
}

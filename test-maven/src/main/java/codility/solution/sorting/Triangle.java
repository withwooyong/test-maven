/*
	A zero-indexed array A consisting of N integers is given. 
	A triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
	A[P] + A[Q] > A[R],
	A[Q] + A[R] > A[P],
	A[R] + A[P] > A[Q].
	
	For example, consider array A such that:
	  A[0] = 10    A[1] = 2    A[2] = 5
	  A[3] = 1     A[4] = 8    A[5] = 20
	Triplet (0, 2, 4) is triangular.
	
	Write a function:
	class Solution { public int solution(int[] A); }
	
	that, given a zero-indexed array A consisting of N integers, returns 1 if there exists a triangular triplet for this array and returns 0 otherwise. 
	For example, given array A such that:
	  A[0] = 10    A[1] = 2    A[2] = 5
	  A[3] = 1     A[4] = 8    A[5] = 20
	the function should return 1, as explained above. 
	Given array A such that:
	  A[0] = 10    A[1] = 50    A[2] = 5
	  A[3] = 1
	the function should return 0.
	
	Assume that:
	N is an integer within the range [0..1,000,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 삼중 항 (P, Q, R)은 0 ≤ P <Q <R <N이면 삼각형이며,
	A [P] + A [Q]> A [R],
	A [Q] + A [R]> A [P],
	A [R] + A [P]> A [Q]이다.
	
	예를 들어 다음과 같은 배열 A를 고려하십시오.
	A [0] = 10A [1] = 2A [2] = 5
	A [3] = 1A [4] = 8A [5] = 20
	삼중 항 (0, 2, 4)은 삼각형입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	즉, N 개의 정수로 구성된 인덱스가없는 배열 A가 주어지면이 배열에 대해 삼각형 삼중 항이 있으면 1을 반환하고 그렇지 않으면 0을 반환합니다. 예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = 10A [1] = 2A [2] = 5
	A [3] = 1A [4] = 8A [5] = 20
	함수는 위에서 설명한대로 1을 반환해야합니다. 
	
	주어진 배열 A :
	A [0] = 10 A [1] = 50 A [2] = 5
	A [3] = 1
	함수는 0을 반환해야합니다.
	
	가정하자면 :
	N은 [0..1,000,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [-2,147,483,648.2,147,483,647] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.sorting;

import java.util.Arrays;

public class Triangle {
	public static void main(String[] args) {
		int[] A = new int[] { 10, 2, 5, 1, 8, 20 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		if (A.length < 3)
			return 0;
		Arrays.sort(A);
		for (int i = 2; i < A.length; i++) {
			if ((long) A[i - 2] + (long) A[i - 1] > (long) A[i])
				return 1;
		}
		return 0;
	}
}

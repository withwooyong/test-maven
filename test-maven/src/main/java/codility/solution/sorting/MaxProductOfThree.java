/*
	A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).
	For example, array A such that:
	  A[0] = -3
	  A[1] = 1
	  A[2] = 2
	  A[3] = -2
	  A[4] = 5
	  A[5] = 6
	contains the following example triplets:
	(0, 1, 2), product is −3 * 1 * 2 = −6
	(1, 2, 4), product is 1 * 2 * 5 = 10
	(2, 4, 5), product is 2 * 5 * 6 = 60
	Your goal is to find the maximal product of any triplet.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.
	For example, given array A such that:
	  A[0] = -3
	  A[1] = 1
	  A[2] = 2
	  A[3] = -2
	  A[4] = 5
	  A[5] = 6
	the function should return 60, as the product of triplet (2, 4, 5) is maximal.
	Assume that:
	N is an integer within the range [3..100,000];
	each element of array A is an integer within the range [−1,000..1,000].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다. 삼중 항 (P, Q, R)의 곱은 A [P] * A [Q] * A [R] (0 ≤ P <Q <R <N)와 동일하다.
	예를 들어, 배열 A는 다음과 같습니다.
	A [0] = -3
	A [1] = 1
	A [2] = 2
	A [3] = -2
	A [4] = 5
	A [5] = 6이다.
	
	다음 예제 트리플렛이 포함되어 있습니다.
	(0, 1, 2)이고, 곱은 -3 * 1 * 2 = -6
	(1, 2, 4), 곱이 1 * 2 * 5 = 10
	(2, 4, 5), 곱이 2 * 5 * 6 = 60
	당신의 목표는 어떤 트리플렛의 최대 생산물을 찾는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	비어 있지 않은 인덱스가없는 배열 A가 주어진다면, 어떤 트리플렛의 최대 곱의 값을 리턴한다.
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = -3
	A [1] = 1
	A [2] = 2
	A [3] = -2
	A [4] = 5
	A [5] = 6
	이 함수는 삼중 항 (2, 4, 5)의 곱이 최대 일 때 60을 반환해야합니다.
	
	가정하자면 :
	N은 [3..100,000] 범위의 정수입니다.
	배열 A의 각 요소는 [-1,000..1,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.sorting;

import java.util.Arrays;

public class MaxProductOfThree {
	public static void main(String[] args) {
		int[] A = new int[] { -3, 1, 2, -2, 5, 6 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		Arrays.sort(A);
		System.out.println(Arrays.toString(A));
		int max1 = A[A.length - 1] * A[A.length - 2] * A[A.length - 3];
		int max2 = A[A.length - 1] * A[0] * A[1];
		return max1 > max2 ? max1 : max2;
	}
}

/*
	A non-empty zero-indexed array A consisting of N integers is given.
	A permutation is a sequence containing each element from 1 to N once, and only once.
	For example, array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	    A[3] = 2
	is a permutation, but array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	is not a permutation, because value 2 is missing.
	The goal is to check whether array A is a permutation.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A, returns 1 if array A is a permutation and 0 if it is not.
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	    A[3] = 2
	the function should return 1.
	Given array A such that:
	    A[0] = 4
	    A[1] = 1
	    A[2] = 3
	the function should return 0.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [1..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다.
순열은 1에서 N까지의 각 요소를 한 번만 포함하는 시퀀스입니다.
예를 들어, 배열 A는 다음과 같습니다.
A [0] = 4
A [1] = 1
A [2] = 3
A [3] = 2
순열이지만 배열 A는 다음과 같습니다.
A [0] = 4
A [1] = 1
A [2] = 3
값 2가 없기 때문에 순열이 아닙니다.
목표는 배열 A가 순열인지 확인하는 것입니다.
함수 작성 :
클래스 솔루션 {public int solution (int [] A); }
인덱스가 0 인 배열 A가 주어진 경우, 배열 A가 순열이면 1을, 그렇지 않으면 0을 반환합니다.
예를 들어, 주어진 배열 A는 다음과 같습니다.
A [0] = 4
A [1] = 1
A [2] = 3
A [3] = 2
함수는 1을 반환해야합니다.
주어진 배열 A :
A [0] = 4
A [1] = 1
A [2] = 3
함수는 0을 반환해야합니다.
가정하자면 :
N은 [1..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [1..1,000,000,000] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.countingelements;

import java.util.Arrays;

public class PermCheck {

	public static void main(String[] args) {
		int[] A = new int[]{5,3,4,1,2,2,6};
		//int[] A = new int[]{ 4, 1, 3, 2 }; // 순열 permutation 1 리턴
		//int[] A = new int[]{ 4, 1, 3 }; // 비순열 not permutation 0 리턴
		System.out.println(solution(A));
	}
	public static int solution(int[] A) {
		Arrays.sort(A);
		for (int i = 0; i < A.length; i++) {
			if (A[i] != i+1)
				return 0;
		}
		return 1;
	}
}

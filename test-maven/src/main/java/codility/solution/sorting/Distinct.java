/*
	Write a function
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns the number of distinct values in array A.
	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [−1,000,000..1,000,000].
	For example, given array A consisting of six elements such that:
	A[0] = 2    A[1] = 1    A[2] = 1
	A[3] = 2    A[4] = 3    A[5] = 1
	the function should return 3, because there are 3 distinct values appearing in array A, namely 1, 2 and 3.
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	함수 작성
클래스 솔루션 {public int solution (int [] A); }
즉, N 개의 정수로 구성된 0 인덱스 배열 A가 주어지면 배열 A에있는 고유 한 값의 수를 반환합니다.
가정하자면 :
N은 [0..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [-1,000,000. 1,000,000] 범위의 정수입니다.
예를 들어 주어진 배열 A는 다음과 같은 6 개의 요소로 구성됩니다.
A [0] = 2A [1] = 1A [2] = 1
A [3] = 2A [4] = 3A [5] = 1
배열 A에 나타나는 3 개의 별개의 값, 즉 1, 2 및 3이 있기 때문에 함수는 3을 반환해야합니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE:100/100
package codility.solution.sorting;

import java.util.Arrays;

public class Distinct {
	public static void main (String[] args) {
		int[] A = new int[] {2,1,1,2,3,1};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		Arrays.sort(A);
		int dupl=0;
		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i-1])
				dupl++;
		}
		return A.length - dupl;
	}
}

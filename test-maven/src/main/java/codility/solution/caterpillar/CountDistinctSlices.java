/*
	An integer M and a non-empty zero-indexed array A consisting of N non-negative integers are given. All integers in array A are less than or equal to M.
	A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. The slice consists of the elements A[P], A[P + 1], ..., A[Q]. A distinct slice is a slice consisting of only unique numbers. That is, no individual number occurs more than once in the slice.
	For example, consider integer M = 6 and array A such that:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 5
	    A[3] = 5
	    A[4] = 2
	There are exactly nine distinct slices: (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3, 3), (3, 4) and (4, 4).
	The goal is to calculate the number of distinct slices.
	Write a function:
	class Solution { public int solution(int M, int[] A); }
	that, given an integer M and a non-empty zero-indexed array A consisting of N integers, returns the number of distinct slices.
	If the number of distinct slices is greater than 1,000,000,000, the function should return 1,000,000,000.
	For example, given integer M = 6 and array A such that:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 5
	    A[3] = 5
	    A[4] = 2
	the function should return 9, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	M is an integer within the range [0..100,000];
	each element of array A is an integer within the range [0..M].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	정수 M과 N이 음수가 아닌 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어집니다. 배열 A의 모든 정수는 M보다 작거나 같습니다.
0 ≤ P ≤ Q <N이되도록 한 쌍의 정수 (P, Q)를 배열 A의 슬라이스라고 부릅니다. 슬라이스는 요소 A [P], A [P + 1], ..., A [Q]. 고유 슬라이스는 고유 번호로만 구성된 슬라이스입니다. 즉, 슬라이스에서 개별 번호가 두 번 이상 나타나지 않습니다.
예를 들어 정수 M = 6 및 배열 A를 다음과 같이 고려하십시오.
A [0] = 3
A [1] = 4
A [2] = 5
A [3] = 5
A [4] = 2
(0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2), (3,3), 3, 4) 및 (4, 4).
목표는 고유 한 조각 수를 계산하는 것입니다.
함수 작성 :
클래스 솔루션 {public int solution (int M, int [] A); }
정수 M과 N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어지면 별개의 조각 수를 반환합니다.
별개의 조각 수가 1,000,000,000보다 큰 경우이 함수는 1,000,000,000을 반환해야합니다.
예를 들어, 정수 M = 6이고 배열 A는 다음과 같습니다.
A [0] = 3
A [1] = 4
A [2] = 5
A [3] = 5
A [4] = 2
함수는 위에서 설명한대로 9를 반환해야합니다.
가정하자면 :
N은 [1..100,000] 범위 내의 정수입니다.
M은 [0..100,000] 범위의 정수입니다.
배열 A의 각 요소는 [0..M] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음)를 초과하는 O (M)입니다.
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.caterpillar;

public class CountDistinctSlices {
	
	public static void main(String[] args) {
		int[] A = new int[]{3, 4, 5, 5, 2};
		int M = 6;
		System.out.println(solution(A, M));
	}
	
	public static int solution(int[] A, int M) {
		return caterpillarMethod(A, M);

	}
	
	public static int caterpillarMethod(int[] A, int M) {	
		int res = 0; 
		int front = 0; 
		int back =0;
		boolean[] seen = new boolean [M+1];
		while(front<A.length && back<A.length) {
			while(front<A.length && !seen[A[front]]) {
				res += front - back + 1;
				seen[A[front]] = true;
				front++;
			}
			while(front<A.length && back<A.length && A[back] != A[front]) {
				seen[A[back]] = false;
				back++;
			}
			seen[A[back]] = false;
			back++;
		}
		return Math.min(res, 1000000000);
	}
}

/*
	A non-empty zero-indexed array A consisting of N integers is given.
	A triplet (X, Y, Z), such that 0 ≤ X < Y < Z < N, is called a double slice.
	The sum of double slice (X, Y, Z) is the total of A[X + 1] + A[X + 2] + ... + A[Y − 1] + A[Y + 1] + A[Y + 2] + ... + A[Z − 1].
	For example, array A such that:
	    A[0] = 3
	    A[1] = 2
	    A[2] = 6
	    A[3] = -1
	    A[4] = 4
	    A[5] = 5
	    A[6] = -1
	    A[7] = 2
	contains the following example double slices:
	double slice (0, 3, 6), sum is 2 + 6 + 4 + 5 = 17,
	double slice (0, 3, 7), sum is 2 + 6 + 4 + 5 − 1 = 16,
	double slice (3, 4, 5), sum is 0.
	The goal is to find the maximal sum of any double slice.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the maximal sum of any double slice.
	For example, given:
	    A[0] = 3
	    A[1] = 2
	    A[2] = 6
	    A[3] = -1
	    A[4] = 4
	    A[5] = 5
	    A[6] = -1
	    A[7] = 2
	the function should return 17, because no double slice of array A has a sum of greater than 17.
	Assume that:
	N is an integer within the range [3..100,000];
	each element of array A is an integer within the range [−10,000..10,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다.
	0 ≤ X <Y <Z <N 인 삼중 항 (X, Y, Z)을 이중 슬라이스라고합니다.
	이중 슬라이스 (X, Y, Z)의 합은 A [X + 1] + A [X + 2] + ... + A [Y - 1] + A [Y + 1] + A [ Y + 2] + ... + A [Z-1]이다.
	
	예를 들어, 배열 A는 다음과 같습니다.
	A [0] = 3
	A [1] = 2
	A [2] = 6
	A [3] = -1
	A [4] = 4
	A [5] = 5
	A [6] = -1
	A [7] = 2
	다음 예제 이중 슬라이스를 포함합니다.
	더블 슬라이스 (0, 3, 6)이고, 합계는 2 + 6 + 4 + 5 = 17이고,
	더블 슬라이스 (0, 3, 7)이고, 합계는 2 + 6 + 4 + 5-1 = 16이며,
	더블 슬라이스 (3, 4, 5), 합계는 0입니다.
	목표는 이중 슬라이스의 최대 합을 찾는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 모든 이중 슬라이스의 최대 합계를 반환합니다.
	
	예를 들어, 주어진 :
	A [0] = 3
	A [1] = 2
	A [2] = 6
	A [3] = -1
	A [4] = 4
	A [5] = 5
	A [6] = -1
	A [7] = 2
	배열 A의 이중 슬라이스가 17보다 큰 합계를 가지지 않으므로이 함수는 17을 반환해야합니다.
	
	가정하자면 :
	N은 [3..100,000] 범위의 정수입니다.
	배열 A의 각 요소는 [-10,000..10,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.maximumslice;

public class MaxDoubleSliceSum {
	public static void main(String[] args) {
		int[] A = new int[] { 3, 2, 6, -1, 4, 5, -1, 2 };
		System.out.println(solution(A));

	}

	public static int solution(int[] A) {
		int max = 0;
		int[] A1 = new int[A.length];
		int[] A2 = new int[A.length];
		for (int i = 1; i < A.length - 1; i++) {
			A1[i] = Math.max(A1[i - 1] + A[i], 0);
		}
		for (int i = A.length - 2; i >= 1; i--) {
			A2[i] = Math.max(A2[i + 1] + A[i], 0);
		}

		for (int i = 1; i < A.length - 1; i++) {
			max = Math.max(max, A1[i - 1] + A2[i + 1]);
		}
		return max;
	}
}

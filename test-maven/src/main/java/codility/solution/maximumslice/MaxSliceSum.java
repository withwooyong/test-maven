/*
	A non-empty zero-indexed array A consisting of N integers is given. 
	A pair of integers (P, Q), such that 0 ≤ P ≤ Q < N, is called a slice of array A. 
	The sum of a slice (P, Q) is the total of A[P] + A[P+1] + ... + A[Q].
	
	Write a function:
	int solution(int A[], int N);
	that, given an array A consisting of N integers, returns the maximum sum of any slice of A.
	
	For example, given array A such that:
	A[0] = 3  A[1] = 2  A[2] = -6
	A[3] = 4  A[4] = 0
	the function should return 5 because:
	(3, 4) is a slice of A that has sum 4,
	(2, 2) is a slice of A that has sum −6,
	(0, 1) is a slice of A that has sum 5,
	no other slice of A has sum greater than (0, 1).
	
	Assume that:
	N is an integer within the range [1..1,000,000];
	each element of array A is an integer within the range [−1,000,000..1,000,000];
	the result will be an integer within the range [−2,147,483,648..2,147,483,647].
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다. 
	0 ≤ P ≤ Q <N이되도록 한 쌍의 정수 (P, Q)를 배열 A의 슬라이스 라 부른다. 
	슬라이스 (P, Q)의 합은 A [P] + A [P +1] + ... + A [Q]이다.
	
	함수 작성 :
	int 솔루션 (int A [], int N);
	N 개의 정수로 구성된 배열 A가 주어지면 A의 모든 슬라이스의 최대 합계를 반환합니다.
	
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = 3 A [1] = 2A [2] = -6
	A [3] = 4A [4] = 0
	이 함수는 다음과 같은 이유로 5를 반환해야합니다.
	
	(3, 4)는 합이 4 인 슬라이스이고,
	(2, 2)는 A의 슬라이스이고,
	(0, 1)은 합이 5이고,
	A의 다른 슬라이스는 (0, 1)보다 큰 합을 갖지 않습니다.
	
	가정하자면 :
	N은 [1..1,000,000] 범위의 정수입니다.
	배열 A의 각 요소는 [-1,000,000. 1,000,000] 범위의 정수입니다.
	결과는 [-2,147,483,648.2,147,483,647] 범위의 정수가됩니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.maximumslice;

public class MaxSliceSum {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] { 3, 2, -6, 4, 0 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < A.length; i++) {
			max = A[i] > max ? A[i] : max;
		}
		if (max <= 0)
			return max;

		int maxSliceSum = 0;
		int currentSum = 0;
		for (int i = 0; i < A.length; i++) {
			currentSum = (currentSum + A[i]) > 0 ? (currentSum + A[i]) : 0;
			maxSliceSum = currentSum > maxSliceSum ? currentSum : maxSliceSum;
		}
		return maxSliceSum;
	}

}

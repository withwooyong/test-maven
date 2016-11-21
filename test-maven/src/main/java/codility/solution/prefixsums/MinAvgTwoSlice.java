/*
	A non-empty zero-indexed array A consisting of N integers is given. 
	A pair of integers (P, Q), such that 0 ≤ P < Q < N, is called a slice of array A (notice that the slice contains at least two elements). 
	The average of a slice (P, Q) is the sum of A[P] + A[P + 1] + ... + A[Q] divided by the length of the slice. 
	To be precise, the average equals (A[P] + A[P + 1] + ... + A[Q]) / (Q − P + 1).
	For example, array A such that:
	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8
	contains the following example slices:
	slice (1, 2), whose average is (2 + 2) / 2 = 2;
	slice (3, 4), whose average is (5 + 1) / 2 = 3;
	slice (1, 4), whose average is (2 + 2 + 5 + 1) / 4 = 2.5.
	
	The goal is to find the starting position of a slice whose average is minimal.
	
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the starting position of the slice with the minimal average. 
	If there is more than one slice with a minimal average, you should return the smallest starting position of such a slice.
	
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 2
	    A[2] = 2
	    A[3] = 5
	    A[4] = 1
	    A[5] = 5
	    A[6] = 8
	the function should return 1, as explained above.
	
	Assume that:
	N is an integer within the range [2..100,000];
	each element of array A is an integer within the range [−10,000..10,000].
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다. 
	0 ≤ P <Q <N과 같은 한 쌍의 정수 (P, Q)를 배열 A의 조각이라 부릅니다 (슬라이스에 적어도 두 개의 요소가 있음을 알 수 있음). 
	슬라이스 (P, Q)의 평균은 A [P] + A [P + 1] + ... + A [Q]의 합을 슬라이스의 길이로 나눈 값입니다. 
	정확히 말하면 평균은 (A [P] + A [P + 1] + ... + A [Q]) / (Q-P + 1)와 같습니다.
	예를 들어, 배열 A는 다음과 같습니다.
	A [0] = 4
	A [1] = 2
	A [2] = 2
	A [3] = 5
	A [4] = 1
	A [5] = 5
	A [6] = 8
	다음 예제 슬라이스가 포함되어 있습니다.
	슬라이스 (1, 2), 평균은 (2 + 2) / 2 = 2;
	슬라이스 (3, 4), 평균은 (5 + 1) / 2 = 3;
	슬라이스 (1, 4), 평균은 (2 + 2 + 5 + 1) / 4 = 2.5입니다.
	목표는 평균이 최소 인 슬라이스의 시작 위치를 찾는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 최소 평균을 가진 슬라이스의 시작 위치를 반환합니다. 
	평균이 최소 인 슬라이스가 둘 이상인 경우 슬라이스의 최소 시작 위치를 반환해야합니다.
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = 4
	A [1] = 2
	A [2] = 2
	A [3] = 5
	A [4] = 1
	A [5] = 5
	A [6] = 8
	함수는 위에서 설명한대로 1을 반환해야합니다.
	
	가정하자면 :
	N은 [2..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [-10,000..10,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100 (both solutions)
package codility.solution.prefixsums;


public class MinAvgTwoSlice {
	public static void main(String[] args) {
		int[] A = new int[] { 4, 2, 2, 5, 1, 5, 8 };
		System.out.println(solution1(A));
	}
	
	public static int solution1(int[] A) {
		int res = 0;
		double min = (double) (A[0] + A[1]) / 2;

		for (int i = 0; i < A.length - 2; i++) {
			
			if ((double) (A[i] + A[i + 1]) / 2 < min) {
				min = (double) (A[i] + A[i + 1]) / 2;
				res = i;
			}
			if ((double) (A[i] + A[i + 1] + A[i + 2]) / 3 < min) {
				min = (double) (A[i] + A[i + 1] + A[i + 2]) / 3;
				res = i;
			}
		}

		if ((double) (A[A.length - 1] + A[A.length - 2]) / 2 < min) {
			System.out.println("========");
			return A.length - 2;
		}
		return res;
	}

	// using caterpillar method (min value will be inside 2 or 3 element slices)
	public static int solution(int[] A) {
		int front = 1;
		int back = 0;
		int res = 0;
		int curr = A[0] + A[1];
		double min = (double) curr / 2;
		double tmpMin = min;

		while (true) {
			if (front - back == 1) {
				front++;
				if (front == A.length)
					return res;
				curr += A[front];
			} else {
				curr -= A[back];
				back++;
			}

			tmpMin = (double) curr / (front - back + 1);
			if (tmpMin < min) {
				res = back;
				min = tmpMin;
			}
		}
	}
}

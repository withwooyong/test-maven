/*
	Let A be a non-empty zero-indexed array consisting of N integers.
	The abs sum of two for a pair of indices (P, Q) is the absolute value |A[P] + A[Q]|, for 0 ≤ P ≤ Q < N.
	
	For example, the following array A:
	  A[0] =  1  
	  A[1] =  4 
	  A[2] = -3
	has pairs of indices (0, 0), (0, 1), (0, 2), (1, 1), (1, 2), (2, 2). 
	The abs sum of two for the pair (0, 0) is A[0] + A[0] = |1 + 1| = 2. 
	The abs sum of two for the pair (0, 1) is A[0] + A[1] = |1 + 4| = 5. 
	The abs sum of two for the pair (0, 2) is A[0] + A[2] = |1 + (−3)| = 2. 
	The abs sum of two for the pair (1, 1) is A[1] + A[1] = |4 + 4| = 8. 
	The abs sum of two for the pair (1, 2) is A[1] + A[2] = |4 + (−3)| = 1. 
	The abs sum of two for the pair (2, 2) is A[2] + A[2] = |(−3) + (−3)| = 6. 
	
	Write a function:
	int solution(int A[], int N);
	that, given a non-empty zero-indexed array A consisting of N integers, returns the minimal abs sum of two for any pair of indices in this array.
	
	For example, given the following array A:
	  A[0] =  1 
	  A[1] =  4  
	  A[2] = -3
	
	the function should return 1, as explained above.
	Given array A:
	  A[0] = -8  
	  A[1] =  4  
	  A[2] =  5   
	  A[3] =-10  
	  A[4] =  3
	the function should return |(−8) + 5| = 3.
	
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
	
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	A를 N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열이라고합시다.
	한 쌍의 인덱스 (P, Q)에 대한 2의 abs sum은 0 ≤ P ≤ Q <N에 대한 절대 값 | A [P] + A [Q] |입니다.
	
	예를 들어, 다음 배열 A :
	A [0] = 1
	A [1] = 4
	A [2] = -3
	(0, 0), (0, 1), (0, 2), (1,1), (1, 2), (2, 2)의 쌍을가집니다.
	쌍 (0, 0)에 대한 2의 abs 합은 A [0] + A [0] = | 1 + 1 | = 2이다.
	쌍 (0, 1)에 대한 2의 abs 합은 A [0] + A [1] = | 1 + 4 | = 5.
	쌍 (0, 2)에 대한 2의 abs 합은 A [0] + A [2] = | 1 + (-3) | = 2이다.
	쌍 (1, 1)에 대한 2의 abs sum은 A [1] + A [1] = | 4 + 4 | = 8.
	쌍 (1, 2)에 대한 2의 abs 합은 A [1] + A [2] = | 4 + (-3) | = 1.
	쌍 (2, 2)에 대한 2의 abs 합은 A [2] + A [2] = | (-3) + (-3) | = 6.
	
	함수 작성 :
	int 솔루션 (int A [], int N);
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면이 배열의 모든 인덱스 쌍에 대해 2의 최소 abs 합을 반환합니다.
	예를 들어, 다음 배열 A가 주어진 경우 :
	A [0] = 1
	A [1] = 4
	A [2] = -3
	함수는 위에서 설명한대로 1을 반환해야합니다.
	
	주어진 배열 A :
	A [0] = -8
	A [1] = 4
	A [2] = 5
	A [3] = -10
	A [4] = 3
	함수는 | (-8) + 5 | = 3.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [-1,000,000,000,000,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
*/

//SCORE: 100/100 (both solutions, caterpillar and binary search)
package codility.solution.caterpillar;

import java.util.Arrays;

public class MinAbsSumOfTwo {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 4, -3 };
		System.out.println(solution1(A));
	}

	public static int solution(int[] A) {
		Arrays.sort(A);
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < A.length; i++) {
			min = Math.min(min, Math.abs(A[i] + findBestMatch(-A[i], A)));
		}
		return min;
	}

	public static int solution1(int[] A) {
		Arrays.sort(A);
		return getMinSum(A);
	}

	public static int findBestMatch(int target, int[] A) {
		if (A.length == 1) {
			return A[0];
		}
		
		int beg = 0;
		int end = A.length - 1;
		
		while (beg <= end) {
			int middle = (beg + end) / 2;
			
			if (A[middle] == target) {
				return A[middle];
			}
			
			if (end - beg == 1) {
				return Math.abs(A[end] - target) < Math.abs(A[beg] - target) ? A[end] : A[beg];
			}
			
			if (A[middle] > target) {
				end = middle;
			} else {
				beg = middle;
			}
		}
		return A[0];
	}

	public static int getMinSum(int[] A) {
		// all positives
		if (A[0] >= 0) {
			return A[0] * 2;
		}
		
		// all negatives
		if (A[A.length - 1] <= 0) {
			return -A[A.length - 1] * 2;
		}
		
		int front = A.length - 1;
		int back = 0;
		int min = Math.abs(A[back] + A[front]);
		
		while (back <= front) {
			int tmp = Math.abs(A[back] + A[front]);
			min = Math.min(min, tmp);
			
			if (Math.abs(A[back + 1] + A[front]) <= tmp) {
				back++;
			} else if (Math.abs(A[back] + A[front - 1]) <= tmp) {
				front--;
			} else {
				back++;
				front--;
			}
		}
		return min;
	}
}

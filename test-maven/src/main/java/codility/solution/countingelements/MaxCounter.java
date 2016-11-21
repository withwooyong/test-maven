/*
	You are given N counters, initially set to 0, and you have two possible operations on them:
	increase(X) − counter X is increased by 1,
	max counter − all counters are set to the maximum value of any counter.
	A non-empty zero-indexed array A of M integers is given. This array represents consecutive operations:
	if A[K] = X, such that 1 ≤ X ≤ N, then operation K is increase(X),
	if A[K] = N + 1 then operation K is max counter.
	For example, given integer N = 5 and array A such that:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 4
	    A[3] = 6
	    A[4] = 1
	    A[5] = 4
	    A[6] = 4
	the values of the counters after each consecutive operation will be:
	    (0, 0, 1, 0, 0)
	    (0, 0, 1, 1, 0)
	    (0, 0, 1, 2, 0)
	    (2, 2, 2, 2, 2)
	    (3, 2, 2, 2, 2)
	    (3, 2, 2, 3, 2)
	    (3, 2, 2, 4, 2)
	The goal is to calculate the value of every counter after all operations.
	
	Write a function:
	class Solution { public int[] solution(int N, int[] A); }
	that, given an integer N and a non-empty zero-indexed array A consisting of M integers, returns a sequence of integers representing the values of the counters.
	The sequence should be returned as:
	a structure Results (in C), or
	a vector of integers (in C++), or
	a record Results (in Pascal), or
	an array of integers (in any other programming language).
	For example, given:
	    A[0] = 3
	    A[1] = 4
	    A[2] = 4
	    A[3] = 6
	    A[4] = 1
	    A[5] = 4
	    A[6] = 4
	the function should return [3, 2, 2, 4, 2], as explained above.
	Assume that:
	N and M are integers within the range [1..100,000];
	each element of array A is an integer within the range [1..N + 1].
	Complexity:
	expected worst-case time complexity is O(N+M);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	처음에 0으로 설정된 N 개의 카운터가 제공되며 두 개의 가능한 연산이 있습니다.
	증가 (X) - 카운터 X가 1 씩 증가하고,
	최대 카운터 - 모든 카운터는 모든 카운터의 최대 값으로 설정됩니다.
	M 개의 정수 중 비어 있지 않은 제로 색인 된 배열 A가 주어진다. 이 배열은 연속 된 연산을 나타냅니다.
	1 ≤ X ≤ N이되는 A [K] = X이면, 연산 K는 증가 (X)이고,
	A [K] = N + 1이면 연산 K는 최대 카운터이다.
	
	예를 들어, 주어진 정수 N = 5와 배열 A는 다음과 같습니다.
	A [0] = 3
	A [1] = 4
	A [2] = 4
	A [3] = 6
	A [4] = 1
	A [5] = 4
	A [6] = 4
	
	각 연속 작업 후 카운터의 값은 다음과 같습니다.
	(0, 0, 1, 0, 0)
	(0, 0, 1, 1, 0)
	(0, 0, 1, 2, 0)
	(2, 2, 2, 2, 2)
	(3, 2, 2, 2, 2)
	(3, 2, 2, 3, 2)
	(3, 2, 2, 4, 2)
	목표는 모든 작업 후에 모든 카운터의 값을 계산하는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int [] solution (int N, int [] A); }
	정수 N과 M 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어지면 카운터의 값을 나타내는 정수 시퀀스를 반환합니다.
	시퀀스는 다음과 같이 반환되어야합니다.
	구조 결과 (C), 또는
	정수 벡터 (C ++에서) 또는
	기록 결과 (파스칼), 또는
	정수 배열 (다른 프로그래밍 언어에서)
	예를 들어, 주어진 :
	A [0] = 3
	A [1] = 4
	A [2] = 4
	A [3] = 6
	A [4] = 1
	A [5] = 4
	A [6] = 4
	함수는 위에서 설명한대로 [3, 2, 2, 4, 2]를 반환해야합니다.
	
	가정하자면 :
	N과 M은 [1..100,000] 범위의 정수입니다.
	배열 A의 각 요소는 [1..N + 1] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N + M)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.countingelements;

import java.util.Arrays;

public class MaxCounter {

	public static void main(String[] args) {
		int[] A = new int[] { 3, 4, 4, 6, 1, 4, 4 };
		int N = 5;
		System.out.println(Arrays.toString(solution(N, A)));
	}

	public static int[] solution(int N, int[] A) {
		int[] counters = new int[N];
		int currMax = 0;
		int currMin = 0;
		
		/*
		M 개의 정수 중 비어 있지 않은 제로 색인 된 배열 A가 주어진다. 이 배열은 연속 된 연산을 나타냅니다.
		1 ≤ X ≤ N이되는 A [K] = X이면, 연산 K는 증가 (X)이고,
		A [K] = N + 1이면 연산 K는 최대 카운터이다.
		예를 들어, 주어진 정수 N = 5와 배열 A는 다음과 같습니다.
		int[] A = new int[] { 3, 4, 4, 6, 1, 4, 4 };
		(0, 0, 1, 0, 0)
		(0, 0, 1, 1, 0)
		(0, 0, 1, 2, 0)
		(2, 2, 2, 2, 2)
		(3, 2, 2, 2, 2)
		(3, 2, 2, 3, 2)
		(3, 2, 2, 4, 2)
		*/
		for (int i = 0; i < A.length; i++) {
			if (A[i] <= N) {
				counters[A[i] - 1] = Math.max(currMin, counters[A[i] - 1]);
				System.out.println(i + ":" + counters[A[i] - 1]);
				counters[A[i] - 1]++;
				System.out.println(i + ":" + counters[A[i] - 1]);
				currMax = Math.max(currMax, counters[A[i] - 1]);
			} else if (A[i] == N + 1) { // A [K] = N + 1이면 연산 K는 최대 카운터이다. 
				currMin = currMax;
			}
		}

		for (int i = 0; i < counters.length; i++) {
			counters[i] = Math.max(counters[i], currMin);
		}
		return counters;
	}
}

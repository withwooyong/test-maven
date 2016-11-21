/*
	There are N ropes numbered from 0 to N − 1, whose lengths are given in a zero-indexed array A, lying on the floor in a line. 
	For each I (0 ≤ I < N), the length of rope I on the line is A[I].
	We say that two ropes I and I + 1 are adjacent. 
	Two adjacent ropes can be tied together with a knot, and the length of the tied rope is the sum of lengths of both ropes. 
	The resulting new rope can then be tied again.
	For a given integer K, 
	the goal is to tie the ropes in such a way that the number of ropes whose length is greater than or equal to K is maximal.
	
	For example, consider K = 4 and array A such that:
	    A[0] = 1
	    A[1] = 2
	    A[2] = 3
	    A[3] = 4
	    A[4] = 1
	    A[5] = 1
	    A[6] = 3
	The ropes are shown in the figure below.
	
	We can tie:
	rope 1 with rope 2 to produce a rope of length A[1] + A[2] = 5;
	rope 4 with rope 5 with rope 6 to produce a rope of length A[4] + A[5] + A[6] = 5.
	After that, there will be three ropes whose lengths are greater than or equal to K = 4. It is not possible to produce four such ropes.
	
	Write a function:
	int solution(int K, int A[], int N);
	that, given an integer K and a non-empty zero-indexed array A of N integers, returns the maximum number of ropes of length greater than or equal to K that can be created.
	For example, given K = 4 and array A such that:
	    A[0] = 1
	    A[1] = 2
	    A[2] = 3
	    A[3] = 4
	    A[4] = 1
	    A[5] = 1
	    A[6] = 3
	the function should return 3, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	K is an integer within the range [1..1,000,000,000];
	each element of array A is an integer within the range [1..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	0에서 N-1까지 번호가 매겨진 N 개의 밧줄이 있으며 길이는 줄의 바닥에 놓이는 인덱스가없는 배열 A에 있습니다. 
	각 I (0 ≤ I <N)에 대해 선상에있는 로프 I의 길이는 A [I]입니다.
	우리는 두 개의 로프 I와 I + 1이 인접 해 있다고 말합니다. 인접한 두 개의 로프는 매듭과 함께 묶을 수 있으며 묶인 로프의 길이는 두 개의 로프 길이의 합입니다. 그런 다음 새로운 로프를 다시 묶을 수 있습니다.
	주어진 정수 K에있어서, 목표는 길이가 K보다 크거나 같은 로프의 수가 최대가되는 방식으로 로프를 묶는 것입니다.
	
	예를 들어, K = 4 및 배열 A를 다음과 같이 고려하십시오.
	A [0] = 1
	A [1] = 2
	A [2] = 3
	A [3] = 4
	A [4] = 1
	A [5] = 1
	A [6] = 3
	로프는 아래 그림과 같습니다.
	
	우리는 넥타이 할 수있다 :
	로프 1을 로프 2와 결합시켜 길이 A [1] + A [2] = 5의 로프를 생산하는 단계;
	로프 (4)와 로프 (5)를 로프 (6)로 연결하여 길이 A [4] + A [5] + A [6] = 5의 로프를 생산한다.
	그 후에, 길이가 K = 4보다 크거나 같은 3 개의 로프가있게 될 것입니다. 4 개의 로프를 생산하는 것은 불가능합니다.
	
	함수 작성 :
	int 솔루션 (int K, int A [], int N);
	정수 K와 N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 생성 될 수있는 K 이상의 길이의 로프의 최대 수를 반환합니다.
	
	예를 들어 K = 4이고 배열 A는 다음과 같습니다.
	A [0] = 1
	A [1] = 2
	A [2] = 3
	A [3] = 4
	A [4] = 1
	A [5] = 1
	A [6] = 3
	함수는 위에서 설명한대로 3을 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	K는 [1..1,000,000,000] 범위의 정수입니다.
	배열 A의 각 요소는 [1..1,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.greedyalgorithms;

public class TieRope {
	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 4, 1, 1, 3 };
		int K = 4;
		System.out.println(solution(A, K));
	}

	public static int solution(int[] A, int K) {
		int res = 0;
		int tmp = 0;
		for (int i = 0; i < A.length; i++) {
			tmp += A[i];
			if (tmp >= K) {
				res++;
				tmp = 0;
			}
		}
		return res;
	}
}

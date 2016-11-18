/*
	You are given two non-empty zero-indexed arrays A and B consisting of N integers. 
	These arrays represent N planks. 
	More precisely, A[K] is the start and B[K] the end of the K−th plank.
	Next, you are given a non-empty zero-indexed array C consisting of M integers. 
	This array represents M nails. 
	More precisely, C[I] is the position where you can hammer in the I−th nail.
	We say that a plank (A[K], B[K]) is nailed if there exists a nail C[I] such that A[K] ≤ C[I] ≤ B[K].
	The goal is to find the minimum number of nails that must be used until all the planks are nailed. 
	In other words, you should find a value J such that all planks will be nailed after using only the first J nails. 
	More precisely, for every plank (A[K], B[K]) such that 0 ≤ K < N, there should exist a nail C[I] such that I < J and A[K] ≤ C[I] ≤ B[K].
	For example, given arrays A, B such that:
	    A[0] = 1    B[0] = 4
	    A[1] = 4    B[1] = 5
	    A[2] = 5    B[2] = 9
	    A[3] = 8    B[3] = 10
	four planks are represented: [1, 4], [4, 5], [5, 9] and [8, 10].
	Given array C such that:
	    C[0] = 4
	    C[1] = 6
	    C[2] = 7
	    C[3] = 10
	    C[4] = 2
	if we use the following nails:
	0, then planks [1, 4] and [4, 5] will both be nailed.
	0, 1, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
	0, 1, 2, then planks [1, 4], [4, 5] and [5, 9] will be nailed.
	0, 1, 2, 3, then all the planks will be nailed.
	Thus, four is the minimum number of nails that, used sequentially, allow all the planks to be nailed.
	
	Write a function:
	class Solution { public int solution(int[] A, int[] B, int[] C); }
	
	that, given two non-empty zero-indexed arrays A and B consisting of N integers and a non-empty zero-indexed array C consisting of M integers, 
	returns the minimum number of nails that, used sequentially, allow all the planks to be nailed.
	If it is not possible to nail all the planks, the function should return −1.
	For example, given arrays A, B, C such that:
	    A[0] = 1    B[0] = 4
	    A[1] = 4    B[1] = 5
	    A[2] = 5    B[2] = 9
	    A[3] = 8    B[3] = 10
	    
	    C[0] = 4
	    C[1] = 6
	    C[2] = 7
	    C[3] = 10
	    C[4] = 2
	the function should return 4, as explained above.
	Assume that:
	N and M are integers within the range [1..30,000];
	each element of arrays A, B, C is an integer within the range [1..2*M];
	A[K] ≤ B[K].
	Complexity:
	expected worst-case time complexity is O((N+M)*log(M));
	expected worst-case space complexity is O(M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 두 개의 비어 있지 않은 제로 색인 배열 A와 B가 제공됩니다. 
	이 배열은 N 널빤지를 나타냅니다. 
	보다 정확하게 A [K]는 K 번째 판자의 시작이고 B [K]는 K 번째 판자의 끝입니다.
	다음에는 M 개의 정수로 구성된 비어 있지 않은 0 인덱스 배열 C가 주어집니다. 
	이 배열은 M 개의 못을 나타냅니다. 보다 정확하게, C [I]는 I-th 못에서 망치질 수있는 위치입니다.
	A [K] ≤ C [I] ≤ B [K]가되도록 손톱 C [I]가 존재한다면 판자 (A [K], B [K])가 못 박히게된다.
	목표는 모든 판자가 못 박힐 때까지 사용해야하는 최소 개수의 못을 찾는 것입니다. 
	다른 말로하면 첫 번째 J 손톱 만 사용한 후에 모든 널빤지가 못을 박을 수 있도록 J 값을 찾아야합니다. 
	보다 정확하게, 0 ≤ K <N이되는 모든 널빤지 (A [K], B [K])에 대해 I <J이고 A [K] ≤ C [I] ≤ 인 못 C [I] B [K].
	
	예를 들어 주어진 배열 A, B는 다음과 같습니다.
	A [0] = 1 B [0] = 4
	A [1] = 4 B [1] = 5
	A [2] = 5 B [2] = 9
	A [3] = 8 B [3] = 10
	[1, 4], [4,5], [5,9], [8,10]의 네 개의 널빤지가 나타납니다.
	
	주어진 배열 C는 다음과 같습니다.
	C [0] = 4
	C [1] = 6
	C [2] = 7
	C [3] = 10
	C [4] = 2
	
	우리가 다음 손톱을 사용한다면 :
	0이면 판자 [1, 4]와 [4, 5]가 모두 못 박히게됩니다.
	0, 1, 그 다음 판자 [1, 4], [4, 5]와 [5, 9]가 못 박히게됩니다.
	0, 1, 2이면 판자 [1, 4], [4,5], [5,9]가 못 박히게됩니다.
	0, 1, 2, 3이면 모든 판자가 못 박히게됩니다.
	따라서 4 개는 순차적으로 사용되는 최소 개수의 못으로 모든 널판을 못 박는 수 있습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A, int [] B, int [] C); }
	N 개의 정수로 구성된 두 개의 비어 있지 않은 Z- 색인 배열 A와 B와 M 개의 정수로 이루어진 비어 있지 않은 Z- 색인 배열 C가 주어진다면 순차적으로 사용되는 모든 널빤지를 허용하는 최소 수의 못을 반환합니다. 못 박았다.
	모든 판자를 못 박는 것이 불가능한 경우 함수는 -1을 반환해야합니다.
	예를 들어, 주어진 배열 A, B, C는 다음과 같습니다.
	A [0] = 1 B [0] = 4
	A [1] = 4 B [1] = 5
	A [2] = 5 B [2] = 9
	A [3] = 8 B [3] = 10
	
	C [0] = 4
	C [1] = 6
	C [2] = 7
	C [3] = 10
	C [4] = 2
	위에서 설명한 것처럼 함수는 4를 반환해야합니다.
	가정하자면 :
	N과 M은 [1.30,000] 범위의 정수입니다.
	배열 A, B, C의 각 요소는 [1..2 * M] 범위의 정수입니다.
	A [K] ≤ B [K].
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O ((N + M) * log (M))이다.
	예상 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음)를 초과하는 O (M)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.binarysearch;

import java.util.Arrays;

public class NailingPlanks {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 4, 5, 8 };
		int[] B = new int[] { 4, 5, 9, 10 };
		int[] C = new int[] { 4, 6, 7, 10, 2 };

		System.out.println(solution(A, B, C));
	}

	public static int solution(int[] A, int[] B, int[] C) {
		int N = A.length;
		int M = C.length;
		int[][] sortedNails = new int[M][2];
		for (int i = 0; i < M; i++) {
			sortedNails[i][0] = C[i];
			sortedNails[i][1] = i;
		}
		Arrays.sort(sortedNails, (int[] x, int[] y) -> (Integer.compare(x[0], y[0])));

		int res = 0;
		for (int i = 0; i < N; i++) {
			res = minIndex(A[i], B[i], sortedNails, res);
			if (res == -1)
				return -1;
		}
		return res + 1;
	}

	public static int minIndex(int pStart, int pEnd, int[][] nails, int oldRes) {
		int beg = 0;
		int end = nails.length - 1;
		int res = -1;
		while (beg <= end) {
			int middle = (beg + end) / 2;
			if (nails[middle][0] < pStart) {
				beg = middle + 1;

			} else if (nails[middle][0] > pEnd) {
				end = middle - 1;
			} else {
				end = middle - 1;
				res = middle;
			}
		}
		if (res == -1 || nails[res][0] > pEnd)
			return -1;
		int min = nails[res][1];
		while (res < nails.length && nails[res][0] <= pEnd) {
			min = Math.min(min, nails[res][1]);
			if (min <= oldRes)
				return oldRes;
			res++;
		}

		return min;
	}

}

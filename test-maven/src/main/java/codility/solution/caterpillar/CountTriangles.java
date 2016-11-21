/*
	A zero-indexed array A consisting of N integers is given. 
	A triplet (P, Q, R) is triangular if it is possible to build a triangle with sides of lengths A[P], A[Q] and A[R]. 
	In other words, triplet (P, Q, R) is triangular if 0 ≤ P < Q < R < N and:
	A[P] + A[Q] > A[R],
	A[Q] + A[R] > A[P],
	A[R] + A[P] > A[Q].
	
	For example, consider array A such that:
	  A[0] = 10    A[1] = 2    A[2] = 5
	  A[3] = 1     A[4] = 8    A[5] = 12
	There are four triangular triplets that can be constructed from elements of this array, namely (0, 2, 4), (0, 2, 5), (0, 4, 5), and (2, 4, 5).
	
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns the number of triangular triplets in this array.
	
	For example, given array A such that:
	  A[0] = 10    A[1] = 2    A[2] = 5
	  A[3] = 1     A[4] = 8    A[5] = 12
	the function should return 4, as explained above.
	
	Assume that:
	N is an integer within the range [0..1,000];
	each element of array A is an integer within the range [1..1,000,000,000].
	
	Complexity:
	expected worst-case time complexity is O(N2);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 
	삼각형 (P, Q, R)은 길이 A [P], A [Q] 및 A [R]의 변을 갖는 삼각형을 만들 수 있다면 삼각형이다. 
	즉, 0 ≤ P <Q <R <N이면 삼중 항 (P, Q, R)은 삼각형이며,
	A [P] + A [Q]> A [R],
	A [Q] + A [R]> A [P],
	A [R] + A [P]> A [Q]이다.
	
	예를 들어 다음과 같은 배열 A를 고려하십시오.
	A [0] = 10
	A [1] = 2
	A [2] = 5
	A [3] = 1 
	A [4] = 8 
	A [5] = 12
	이 배열의 요소, 즉 (0, 2, 4), (0, 2, 5), (0, 4, 5) 및 (2, 4, 5)로 구성 할 수있는 4 개의 삼각형 쌍둥이가 있습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	즉, N 개의 정수로 구성된 0 인덱스 배열 A가 주어지면이 배열에있는 삼각형 쌍곡선의 수를 반환합니다.
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = 10
	A [1] = 2
	A [2] = 5
	A [3] = 1 
	A [4] = 8 
	A [5] = 12
	위에서 설명한 것처럼 함수는 4를 반환해야합니다.
	
	가정하자면 :
	N은 [0..1,000] 범위의 정수입니다.
	배열 A의 각 요소는 [1..1,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N2)이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.caterpillar;

import java.util.Arrays;

public class CountTriangles {
	
	public static void main(String[] args) {
		int[] A = new int[] {10, 2, 5, 1, 8, 12};
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		return caterpillarMethod(A);
	}
	
	public static int caterpillarMethod(int[] A) {
		int N = A.length;
		int res=0;
		
		if (N < 3) {
			return 0;
		}
		
		int front;		
		Arrays.sort(A);
		
		for (int i = 0; i < N-2; i++) {
			front = i+2;
			for (int j = i+1; j < N-1; j++) {
				while(front < N && A[i] + A[j] > A[front]){
					front++;
				}
				res+=front-j-1;
			}
		}
		return res;
	}
}

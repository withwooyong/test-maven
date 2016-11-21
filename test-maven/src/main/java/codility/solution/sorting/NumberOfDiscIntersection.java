/*
	Given an array A of N integers, we draw N discs in a 2D plane such that the I-th disc is centered on (0,I) and has a radius of A[I]. 
	We say that the J-th disc and K-th disc intersect if J ≠ K and J-th and K-th discs have at least one common point.
	
	Write a function:
	int solution(int A[], int N);
	
	that, given an array A describing N discs as explained above, returns the number of pairs of intersecting discs. For example, given N=6 and:
	A[0] = 1  A[1] = 5  A[2] = 2 
	A[3] = 1  A[4] = 4  A[5] = 0  
	intersecting discs appear in eleven pairs of elements:
	0 and 1,
	0 and 2,
	0 and 4,
	1 and 2,
	1 and 3,
	1 and 4,
	1 and 5,
	2 and 3,
	2 and 4,
	3 and 4,
	4 and 5.
	so the function should return 11.
	The function should return −1 if the number of intersecting pairs exceeds 10,000,000.
	
	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [0..2147483647].
	
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수 배열을 감안할 때, I 번째 디스크는 (0, I)에 중심을두고 A [I]의 반경을 갖도록 2D 평면에 N 개의 디스크를 그립니다. 
	J 번째 디스크와 K 번째 디스크는 J ≠ K이고 J 번째와 K 번째 디스크는 적어도 하나의 공통점을 가지고 있다고 가정합니다.
	
	함수 작성 :
	int 솔루션 (int A [], int N);
	위에서 설명한대로 N 개의 디스크를 설명하는 배열 A가 주어진다면 교차하는 디스크 쌍의 수를 반환합니다. 
	
	예를 들어, 주어진 N = 6 및 :
	A [0] = 1A [1] = 5A [2] = 2
	A [3] = 1A [4] = 4A [5] = 0
	교차하는 디스크는 11 쌍의 요소로 나타납니다.
	0 및 1,
	0 및 2,
	0 및 4,
	도 1 및도 2에서,
	도 1 및도 3을 참조하면,
	도 1 및도 4를 참조하면,
	도 1 및도 5를 참조하면,
	도 2 및도 3을 참조하면,
	도 2 및도 4를 참조하면,
	도 3 및도 4를 참조하면,
	4 및 5.
	함수는 11을 반환해야합니다.
	교차 쌍이 10,000,000을 초과하면 함수는 -1을 반환해야합니다.
	
	가정하자면 :
	N은 [0..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [0..2147483647] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/62
package codility.solution.sorting;

public class NumberOfDiscIntersection {
	public static void main(String[] args) {
		//int[] A = new int[] { 1, 5, 2, 1, 4, 0 };
		int[] A = new int[] { 1, 2, 6, 5, 5, 8, 9 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		int x = 0;
		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if ((long) A[i] + i >= j - (long) A[j]) {
					x++;
					if (x > 10000000)
						return -1;
				}
			}
		}
		return x;
	}
}

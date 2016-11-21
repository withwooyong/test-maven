package codility.solution;

/*
 * A zero-indexed array A consisting of N integers is given. 
 * An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, 
 * i.e.
 * A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 * Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N−1.
 * 
 * For example, consider the following array A consisting of N = 8 elements:
 *   int[] A = new int[] { -1, 3, -4, 5, 1, -6, 2, 1 };
 *
 * P = 1 is an equilibrium index of this array, 
 * because:  A[0] = −1 = A[2] + A[3] + A[4] + A[5] + A[6] + A[7]
 * P = 3 is an equilibrium index of this array, 
 * because: A[0] + A[1] + A[2] = −2 = A[4] + A[5] + A[6] + A[7]
 * P = 7 is also an equilibrium index, because:
 * 
 * A[0] + A[1] + A[2] + A[3] + A[4] + A[5] + A[6] = 0
 * and there are no elements with indices greater than 7.

P = 8 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.

Write a function:

class Solution { public int solution(int[] A); }

that, given a zero-indexed array A consisting of N integers, returns any of its equilibrium indices. The function should return −1 if no equilibrium index exists.

For example, given array A shown above, the function may return 1, 3 or 7, as explained above.

Assume that:

N is an integer within the range [0..100,000];
each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
Complexity:
expected worst-case time complexity is O(N);
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.

N 개의 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 이 배열의 평형 지수는 0 ≤ P <N이고 더 낮은 지수의 원소의 합이 더 높은 지수의 원소의 합, 즉
A [0] + A [1] + ... + A [P-1] = A [P + 1] + ... + A [N-2] + A [N-1]
0 요소의 합은 0과 같다고 가정합니다. 이것은 P = 0이거나 P = N-1 인 경우에 발생할 수 있습니다.

예를 들어, N = 8 요소로 구성된 다음 배열 A를 생각해보십시오.

  A [0] = -1
  A [1] = 3
  A [2] = -4
  A [3] = 5
  A [4] = 1
  A [5] = -6
  A [6] = 2
  A [7] = 1
P = 1은이 배열의 평형 지수입니다. 이유는 다음과 같습니다.
A [0] = -1 = A [2] + A [3] + A [4] + A [5] + A [6] + A [7]

P = 3은이 배열의 평형 지수입니다. 왜냐하면 :
A [0] + A [1] + A [2] = -2 = A [4] + A [5] + A [6] + A [7]
P = 7은 평형 지수이기도합니다. 왜냐하면 :

A [0] + A [1] + A [2] + A [3] + A [4] + A [5] + A [6] = 0
인덱스가 7보다 큰 요소는 없습니다.

P = 8은 0≤ P <N의 조건을 만족시키지 않기 때문에 평형 지수가 아니다.

함수 작성 :

클래스 솔루션 {public int solution (int [] A); }

즉, N 개의 정수로 구성된 인덱스가 0 인 인덱스 A가 주어지면 평형 인덱스 중 하나를 반환합니다. 평형 인덱스가없는 경우 함수는 -1을 반환해야합니다.

예를 들어 위에 표시된 배열 A에서 주어진 함수는 위에서 설명한대로 1, 3 또는 7을 반환 할 수 있습니다.

가정하자면 :

N은 [0..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [-2,147,483,648.2,147,483,647] 범위의 정수입니다.
복잡성:

기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */
public class Test {

	public static void main(String[] args) {
		int[] A = new int[] { -1, 3, -4, 5, 1, -6, 2, 1 };
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		int equilibrium = -1;
		// 배열값 확인
		if (A == null || A.length == 0) {
			return equilibrium;
		} else {
			// 변수 초기화 
			long sumLeft = 0;
			long sumRight = 0;
			for (int i = 0; i < A.length; i++) {
				sumRight += A[i];
			}
			// 첫 번째 평형을 찾기 위해 배열 탐색
			for (int i = 0; i < A.length; i++) {
				long tempRight = sumRight - A[i];
				if (sumLeft == tempRight) {
					equilibrium = i;
					break;
				} else {
					sumLeft += A[i];
					sumRight = tempRight;
				}
			}
		}
		return equilibrium;
	}
}

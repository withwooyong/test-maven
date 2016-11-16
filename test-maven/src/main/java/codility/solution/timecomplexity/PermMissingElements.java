/*
	A zero-indexed array A consisting of N different integers is given. The array contains integers in the range [1..(N + 1)], which means that exactly one element is missing.
	Your goal is to find that missing element.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A, returns the value of the missing element.
	For example, given array A such that:
	  A[0] = 2
	  A[1] = 3
	  A[2] = 1
	  A[3] = 5
	the function should return 4, as it is the missing element.
	Assume that:
	N is an integer within the range [0..100,000];
	the elements of A are all distinct;
	each element of array A is an integer within the range [1..(N + 1)].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 서로 다른 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 이 배열에는 [1 .. (N + 1)] 범위의 정수가 포함되어 있는데 이는 정확히 하나의 요소가 누락되었음을 의미합니다.
귀하의 목표는 누락 된 요소를 찾는 것입니다.
함수 작성 :
클래스 솔루션 {public int solution (int [] A); }
인덱스가 0 인 배열 A가 주어진 경우 누락 된 요소의 값을 반환합니다.
예를 들어, 주어진 배열 A는 다음과 같습니다.
A [0] = 2
A [1] = 3
A [2] = 1
A [3] = 5
누락 된 요소이므로 함수는 4를 반환해야합니다.
가정하자면 :
N은 [0..100,000] 범위 내의 정수입니다.
A의 요소들은 모두 구별된다;
배열 A의 각 요소는 [1 .. (N + 1)] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.timecomplexity;

public class PermMissingElements {

	public static void main(String[] args) {
		int[] A = new int[]{ 2, 3, 1, 5 };
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		int[] counters = new int[A.length + 2]; // 배열 초기화 0
		
		for (int i = 0; i < A.length; i++) {
			counters[A[i]] = 1; // 배열에 1값 담음
			System.out.println(A[i] + " " + counters[A[i]] + " " + counters.length);
		}
		for (int i = 1; i < counters.length; i++) {
			if (counters[i] == 0) { // 배열 N 번째 값이 0이면 누락된 값
				return i;
			}
		}
		//no element is missing
		return -1;
	}
}

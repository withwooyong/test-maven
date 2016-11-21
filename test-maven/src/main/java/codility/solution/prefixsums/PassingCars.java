/*
	A non-empty zero-indexed array A consisting of N integers is given. 
	The consecutive elements of array A represent consecutive cars on a road.
	Array A contains only 0s and/or 1s:
	0 represents a car traveling east,
	1 represents a car traveling west.
	
	The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.
	
	For example, consider array A such that:
	  A[0] = 0
	  A[1] = 1
	  A[2] = 0
	  A[3] = 1
	  A[4] = 1
	We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).
	
	Write a function:
	class Solution { public int solution(int[] A); }
	
	that, given a non-empty zero-indexed array A of N integers, returns the number of passing cars.
	The function should return −1 if the number of passing cars exceeds 1,000,000,000.
	
	For example, given:
	  A[0] = 0
	  A[1] = 1
	  A[2] = 0
	  A[3] = 1
	  A[4] = 1
	the function should return 5, as explained above.
	
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer that can have one of the following values: 0, 1.
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다. 배열 A의 연속 요소는 도로상의 연속적인 자동차를 나타냅니다.
	배열 A는 0 및 / 또는 1 만 포함합니다.
	0은 동쪽으로 주행하는 자동차를 나타내며,
	1은 서쪽으로 여행하는 자동차를 나타냅니다.
	목표는 지나가는 차량 수를 세는 것입니다. P가 동쪽으로 가고 Q가 서쪽으로 여행 할 때 0 ≤ P <Q <N 인 한 쌍의 자동차 (P, Q)가 지나가고 있다고합니다.
	
	예를 들어 다음과 같은 배열 A를 고려하십시오.
	A [0] = 0
	A [1] = 1
	A [2] = 0
	A [3] = 1
	A [4] = 1
	우리는 (0, 1), (0, 3), (0, 4), (2, 3), (2, 4)의 다섯 쌍의 지나가는 자동차를 가지고 있습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 전달되는 차량의 수를 반환합니다.
	지나가는 자동차 수가 1,000,000,000을 초과하면이 함수는 -1을 반환해야합니다.
	
	예를 들어, 주어진 :
	A [0] = 0
	A [1] = 1
	A [2] = 0
	A [3] = 1
	A [4] = 1
	함수는 위에서 설명한대로 5를 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 0, 1 중 하나의 값을 가질 수있는 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.prefixsums;

public class PassingCars {
	public static void main(String[] args) {
		int[] A = new int[] { 0, 1, 0, 1, 1 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		int res = 0;
		int ones = 0;
		for (int i = A.length - 1; i >= 0; i--) {
			if (A[i] == 1)
				ones++;
			else {
				res += ones;
				if (res > 1000000000)
					return -1;
			}
		}
		return res;
	}
}

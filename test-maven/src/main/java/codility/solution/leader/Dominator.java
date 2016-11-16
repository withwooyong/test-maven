/*
	A zero-indexed array A consisting of N integers is given. The dominator of array A is the value that occurs in more than half of the elements of A.
	For example, consider array A such that
	A[0] = 3    A[1] = 4    A[2] =  3
	A[3] = 2    A[4] = 3    A[5] = -1
	A[6] = 3    A[7] = 3
	The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.
	Write a function
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. The function should return −1 if array A does not have a dominator.
	Assume that:
	N is an integer within the range [0..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	For example, given array A such that
	A[0] = 3    A[1] = 4    A[2] =  3
	A[3] = 2    A[4] = 3    A[5] = -1
	A[6] = 3    A[7] = 3
	the function may return 0, 2, 4, 6 or 7, as explained above.
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 배열 A의 지배자는 A의 요소의 절반 이상에서 발생하는 값입니다.
예를 들어, 배열 A를 다음과 같이 고려하십시오.
A [0] = 3A [1] = 4A [2] = 3
A [3] = 2A [4] = 3A [5] = -1
A [6] = 3A [7] = 3
A의 지배자는 A의 8 개 요소 중 5 개 (즉, 지표 0, 2, 4, 6 및 7 인 요소)에서 발생하고 5는 8의 절반 이상이므로 발생합니다.
함수 작성
클래스 솔루션 {public int solution (int [] A); }
즉, N 개의 정수로 구성된 인덱스가 0 인 배열 A가 주어지면 A의 지배자가 발생하는 배열 A의 모든 요소의 인덱스를 반환합니다. 배열 A에 dominator가없는 경우 함수는 -1을 반환해야합니다.
가정하자면 :
N은 [0..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [-2,147,483,648.2,147,483,647] 범위의 정수입니다.
예를 들어, 주어진 배열 A는
A [0] = 3A [1] = 4A [2] = 3
A [3] = 2A [4] = 3A [5] = -1
A [6] = 3A [7] = 3
함수는 위에서 설명한대로 0, 2, 4, 6 또는 7을 반환 할 수 있습니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.leader;

import java.util.Stack;

public class Dominator {
	public static void main (String[] args) {
		int[] A = new int[]{3, 4, 3, 2, 3, -1, 3, 3};
		System.out.println(solution(A));
		
	}
	
	public static int solution(int[] A) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty()) {
				stack.push(A[i]);
				continue;
			}
			if (stack.peek() == A[i])
				stack.push(A[i]);
			else
				stack.pop();
		}
		if (stack.isEmpty())
			return -1;
		int domCandidate = stack.peek();
		int occurances = 0;
		int randomIndex=-1;
		for (int i = 0; i < A.length; i++) {
			if(A[i] == domCandidate) {
				occurances++;
				randomIndex = i;
			}
		}
		
		return occurances>A.length/2?randomIndex:-1;
	}
}

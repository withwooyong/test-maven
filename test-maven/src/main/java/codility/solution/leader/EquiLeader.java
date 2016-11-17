/*
	A non-empty zero-indexed array A consisting of N integers is given.
	The leader of this array is the value that occurs in more than half of the elements of A.
	An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
	For example, given array A such that:
	    A[0] = 4
	    A[1] = 3
	    A[2] = 4
	    A[3] = 4
	    A[4] = 4
	    A[5] = 2
	we can find two equi leaders:
	0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
	2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
	The goal is to count the number of equi leaders. Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
	For example, given:
	    A[0] = 4
	    A[1] = 3
	    A[2] = 4
	    A[3] = 4
	    A[4] = 4
	    A[5] = 2
	the function should return 2, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다.
	이 배열의 리더는 A의 요소 중 절반 이상에서 발생하는 값입니다.
	등 지시선은 0≤S <N-1이고 두 개의 시퀀스 A [0], A [1], ..., A [S]와 A [S + 1], A [S + 2] ], ..., A [N - 1]은 같은 가치의 리더를 가진다.
	
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = 4
	A [1] = 3
	A [2] = 4
	A [3] = 4
	A [4] = 4
	A [5] = 2
	
	우리는 동등한 지도자 두 명을 찾을 수 있습니다 :
	0인데, 왜냐하면 시퀀스 : (4)와 (3, 4, 4, 2)는 동일한 리더를 가지며 그 값은 4입니다.
	2는 시퀀스 : (4, 3, 4)와 (4, 4, 2)가 동일한 리더를 가지므로 그 값은 4입니다.
	목표는 동등한 지도자의 수를 세는 것입니다. 함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 equi 지시선의 수를 반환합니다.
	
	예를 들어, 주어진 :
	A [0] = 4
	A [1] = 3
	A [2] = 4
	A [3] = 4
	A [4] = 4
	A [5] = 2
	위에서 설명한대로 함수는 2를 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [-1,000,000,000,000,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.leader;

import java.util.Stack;

public class EquiLeader {
	public static void main(String[] args) {
		int[] A = new int[] { 4, 3, 4, 4, 4, 2 };
		System.out.println(solution(A));

	}

	public static int solution(int[] A) {

		// check if it is dominator at all
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
			return 0; // there's no dominator
		int dominator = stack.peek();
		int domOccurances = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == dominator)
				domOccurances++;
		}
		if (domOccurances <= A.length / 2)
			return 0;// not dominator
		int nonDomOccurances = A.length - domOccurances;
		stack.clear();
		int dom = 0;
		int nonDom = 0;
		int equiLeaders = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == dominator)
				dom++;
			else
				nonDom++;
			if (dom > nonDom && (domOccurances - dom) > (nonDomOccurances - nonDom))
				equiLeaders++;
		}
		return equiLeaders;
	}
}

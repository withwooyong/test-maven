/*
	You are given two non-empty zero-indexed arrays A and B consisting of N integers. 
	Arrays A and B represent N voracious fish in a river, ordered downstream along the flow of the river.
	The fish are numbered from 0 to N − 1. If P and Q are two fish and P < Q, then fish P is initially upstream of fish Q. 
	Initially, each fish has a unique position.
	Fish number P is represented by A[P] and B[P]. 
	Array A contains the sizes of the fish. 
	All its elements are unique. 
	Array B contains the directions of the fish. 
	It contains only 0s and/or 1s, where:
	0 represents a fish flowing upstream,
	1 represents a fish flowing downstream.
	If two fish move in opposite directions and there are no other (living) fish between them, they will eventually meet each other. 
	Then only one fish can stay alive − the larger fish eats the smaller one. More precisely, we say that two fish P and Q meet each other when P < Q, B[P] = 1 and B[Q] = 0, and there are no living fish between them. After they meet:
	If A[P] > A[Q] then P eats Q, and P will still be flowing downstream,
	If A[Q] > A[P] then Q eats P, and Q will still be flowing upstream.	
	We assume that all the fish are flowing at the same speed. 
	That is, fish moving in the same direction never meet. 
	
	The goal is to calculate the number of fish that will stay alive.
	
	For example, consider arrays A and B such that:
	  A[0] = 4    B[0] = 0
	  A[1] = 3    B[1] = 1
	  A[2] = 2    B[2] = 0
	  A[3] = 1    B[3] = 0
	  A[4] = 5    B[4] = 0
	Initially all the fish are alive and all except fish number 1 are moving upstream. Fish number 1 meets fish number 2 and eats it, then it meets fish number 3 and eats it too. Finally, it meets fish number 4 and is eaten by it. The remaining two fish, number 0 and 4, never meet and therefore stay alive.
	
	Write a function:
	class Solution { public int solution(int[] A, int[] B); }
	
	that, given two non-empty zero-indexed arrays A and B consisting of N integers, returns the number of fish that will stay alive.
	For example, given the arrays shown above, the function should return 2, as explained above.
	
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [0..1,000,000,000];
	each element of array B is an integer that can have one of the following values: 0, 1;
	the elements of A are all distinct.
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 두 개의 비어 있지 않은 제로 색인 배열 A와 B가 제공됩니다. 
	배열 A와 B는 강의 흐름을 따라 하류로 향한 강가에 서식하는 N 개의 방대한 물고기를 나타냅니다.
	물고기는 0에서 N - 1까지 번호가 매겨집니다. 
	P와 Q가 두 개의 물고기이고 P <Q이면 물고기 P는 물고기의 상류에 있습니다. 
	처음에는 각 물고기가 독특한 위치에 있습니다.
	어류 번호 P는 A [P]와 B [P]로 표시됩니다. 
	배열 A는 물고기의 크기를 포함합니다. 
	모든 요소는 고유합니다. 
	배열 B는 물고기의 방향을 포함합니다. 
	여기에는 0 및 / 또는 1 만 포함됩니다.
	0은 상류로 흐르는 물고기를 나타내며,
	1은 하류로 흐르는 물고기를 나타낸다.
	두 물고기가 반대 방향으로 움직이고 그 사이에 다른 (살아있는) 물고기가 없다면, 그들은 결국 서로 만날 것입니다. 
	그러면 오직 하나의 물고기 만이 살아있을 수 있습니다 - 큰 물고기는 더 작은 물고기를 먹습니다. 
	P <Q, B [P] = 1, B [Q] = 0 일 때 두 물고기 P와 Q가 서로 만났고 그들 사이에는 생선이 없다고합니다. 
	
	그들이 만난 후 :
	A [P]> A [Q]이면 P는 Q를 얻고, P는 여전히 하류로 흐르게된다.
	A [Q]> A [P]라면 Q는 P를 먹고, Q는 여전히 상류로 흐를 것입니다.
	우리는 모든 물고기가 같은 속도로 흐르고 있다고 가정합니다. 
	즉, 같은 방향으로 움직이는 물고기는 절대 만나지 않습니다. 
	목표는 살아 남을 물고기의 수를 계산하는 것입니다.
	
	예를 들어 다음과 같이 배열 A와 B를 고려하십시오.
	A [0] = 4 B [0] = 0
	A [1] = 3 B [1] = 1
	A [2] = 2 B [2] = 0
	A [3] = 1 B [3] = 0
	A [4] = 5 B [4] = 0
	처음에는 모든 물고기가 살아 있으며 물고기 번호 1을 제외한 모든 물고기가 상류로 이동합니다. 
	물고기 번호 1은 물고기 번호 2를 만나 먹고, 물고기 번호 3을 만나 먹는다. 
	마지막으로, 그것은 물고기 번호 4를 충족하고 그것에 의해 먹습니다. 
	나머지 2 마리의 물고기, 0 번과 4 번은 만날 수 없으므로 살아남습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A, int [] B); }
	두 개의 비어 있지 않은 제로 색인 배열 A와 B가 N 개의 정수로 이루어진다면, 살아 남을 물고기의 수를 반환합니다.
	예를 들어, 위의 배열을 보면 위에서 설명한 것처럼 함수는 2를 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [0..1,000,000,000] 범위의 정수입니다.
	배열 B의 각 요소는 다음 값 중 하나를 가질 수있는 정수입니다 : 0, 1;
	A의 요소는 모두 뚜렷합니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.stackandqueue;

import java.util.Stack;

public class Fish {

	public static void main(String[] args) {
		int[] A = new int[] { 4, 3, 2, 1, 5 };
		int[] B = new int[] { 0, 1, 0, 0, 0 };
		System.out.println(solution(A, B));
	}

	public static int solution(int[] A, int[] B) {
		Stack<Integer> stack = new Stack<Integer>();
		int duels = 0;
		for (int i = 0; i < A.length; i++) {
			if (B[i] == 0) {
				while (!stack.isEmpty()) {
					duels++;
					if (A[i] < A[stack.peek()])
						break;
					stack.pop();
				}
			} else
				stack.push(B[i]);
		}
		return A.length - duels;
	}
}

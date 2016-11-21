/*
	You are going to build a stone wall. 
	The wall should be straight and N meters long, and its thickness should be constant; however, it should have different heights in different places. 
	The height of the wall is specified by a zero-indexed array H of N positive integers. 
	H[I] is the height of the wall from I to I+1 meters to the right of its left end. 
	In particular, H[0] is the height of the wall's left end and H[N−1] is the height of the wall's right end.
	The wall should be built of cuboid stone blocks (that is, all sides of such blocks are rectangular). 
	Your task is to compute the minimum number of blocks needed to build the wall.
	
	Write a function:
	class Solution { public int solution(int[] H); }
	
	that, given a zero-indexed array H of N positive integers specifying the height of the wall, returns the minimum number of blocks needed to build it.
	For example, given array H containing N = 9 integers:
	  H[0] = 8    H[1] = 8    H[2] = 5    
	  H[3] = 7    H[4] = 9    H[5] = 8    
	  H[6] = 7    H[7] = 4    H[8] = 8    
	the function should return 7. The figure shows one possible arrangement of seven blocks.
	
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array H is an integer within the range [1..1,000,000,000].
	
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	돌담을 세울 것입니다. 
	벽은 직선이어야하며 길이 Nm이고 두께는 일정해야합니다. 
	그러나 그것은 다른 장소에서 다른 높이를 가져야합니다. 
	벽의 높이는 N 개의 양의 정수로 이루어진 제로 색인 배열 H에 의해 지정됩니다. 
	H [I]는 I에서 I + 1 미터까지의 왼쪽 끝의 오른쪽 벽까지의 높이입니다. 
	특히 H [0]은 벽의 왼쪽 끝의 높이이고, H [N-1]은 벽의 오른쪽 끝의 높이입니다.
	벽은 입방 형 석재 블록으로 만들어야합니다 (즉, 그러한 블록의 모든면은 직사각형입니다). 
	귀하의 임무는 벽을 구축하는 데 필요한 최소 블록 수를 계산하는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] H); }
	벽의 높이를 지정하는 N 개의 양의 정수로 구성된 인덱스가없는 배열 H가 주어지면이를 구축하는 데 필요한 최소 블록 수를 반환합니다.
	
	예를 들어, N = 9 정수를 포함하는 주어진 배열 H :
	H [0] = 8H [1] = 8H [2] = 5
	H [3] = 7 H [4] = 9 H [5] = 8
	H [6] = 7 H [7] = 4 H [8] = 8
	함수는 7을 반환해야합니다.이 그림은 7 개의 블록 중 하나의 가능한 배열을 보여줍니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 H의 각 요소는 [1..1,000,000,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.stackandqueue;

import java.util.Stack;

public class StoneWall {

	public static void main(String[] args) {
		int[] A = new int[] { 8, 8, 5, 7, 9, 8, 7, 4, 8 };
		System.out.println(solution(A));
	}

	public static int solution(int[] H) {
		Stack<Integer> stack = new Stack<Integer>();
		int blocks = 1;
		stack.push(H[0]);
		for (int i = 1; i < H.length; i++) {
			if (stack.peek() == H[i])
				continue;
			else if (H[i] > stack.peek()) {
				stack.push(H[i]);
				blocks++;
			} else {
				while (stack.size() > 0 && stack.peek() > H[i])
					stack.pop();
				if (stack.size() == 0 || stack.peek() != H[i]) {
					stack.push(H[i]);
					blocks++;
				}
			}
		}
		return blocks;
	}
}

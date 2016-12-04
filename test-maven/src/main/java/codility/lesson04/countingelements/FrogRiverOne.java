package codility.lesson04.countingelements;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A small frog wants to get to the other side of a river. 
 * The frog is initially located on one bank of the river (position 0) and wants to get to the opposite bank (position X+1). 
 * Leaves fall from a tree onto the surface of the river.
 * 
 * You are given a zero-indexed array A consisting of N integers representing the falling leaves. 
 * A[K] represents the position where one leaf falls at time K, measured in seconds.
 * 
 * The goal is to find the earliest time when the frog can jump to the other side of the river.
 *  
 * The frog can cross only when leaves appear at every position across the river from 1 to X 
 * (that is, we want to find the earliest moment when all the positions from 1 to X are covered by leaves). 
 * You may assume that the speed of the current in the river is negligibly small, 
 * i.e. the leaves do not change their positions once they fall in the river.
 * 
 * For example, you are given integer X = 5 and array A such that:
 *   A[0] = 1
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 4
 *   A[4] = 2
 *   A[5] = 3
 *   A[6] = 5
 *   A[7] = 4
 * In second 6, a leaf falls into position 5. 
 * This is the earliest time when leaves appear in every position across the river.
 * 
 * Write a function:
 * class Solution { public int solution(int X, int[] A); }
 * 
 * that, given a non-empty zero-indexed array A consisting of N integers and integer X, 
 * returns the earliest time when the frog can jump to the other side of the river.
 * 
 * If the frog is never able to jump to the other side of the river, the function should return −1.
 * For example, given X = 5 and array A such that:
 *   A[0] = 1
 *   A[1] = 3
 *   A[2] = 1
 *   A[3] = 4
 *   A[4] = 2
 *   A[5] = 3
 *   A[6] = 5
 *   A[7] = 4
 * the function should return 6, as explained above.
 * 
 * Assume that:
 * N and X are integers within the range [1..100,000];
 * each element of array A is an integer within the range [1..X].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(X), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * 작은 개구리가 강 저편에 도착하기를 원합니다.
 * 개구리는 처음에 강 하나의 은행 (위치 0)에 위치하고 반대 은행 (위치 X + 1)에 도착하려고합니다.
 * 잎은 나무에서 강 표면으로 떨어집니다.
 *
 * 떨어지는 잎을 나타내는 N 개의 정수로 구성된 0 인덱스 배열 A가 주어집니다.
 * A [K]는 시간 K에서 한 잎이 떨어지는 위치를 초 단위로 나타냅니다.
 *
 * 목표는 개구리가 강 저편으로 점프 할 수있는 가장 빠른 시간을 찾는 것입니다.
 *
 * 개구리는 잎이 1에서 X까지 강 건너편에 나타날 때만 교차 할 수 있습니다.
 * (즉, 1에서 X까지의 모든 위치가 나뭇잎으로 덮여있는 가장 빠른 순간을 찾고 싶습니다.)
 * 강의 흐름 속도가 무시할 정도로 작다고 가정 할 수 있습니다.
 즉 잎이 강물에 떨어지면 나뭇잎의 위치가 바뀌지 않습니다.
 *
 * 예를 들어, 정수 X = 5와 배열 A는 다음과 같습니다.
 * A [0] = 1
 * A [1] = 3
 * A [2] = 1
 * A [3] = 4
 * A [4] = 2
 * A [5] = 3
 * A [6] = 5
 * A [7] = 4
 * 두 번째 6에서는 잎이 5 위치에 떨어진다.
 * 강이 강을 건너서 모든 위치에 나뭇잎이 나타나는 가장 빠른 시간입니다.
 *
 * 함수 작성 :
 * 클래스 솔루션 {public int solution (int X, int [] A); }
 *
 * N 개의 정수와 정수 X로 구성된 비어 있지 않은 제로 색인 배열 A가 주어진다면,
 * 개구리가 강 저편으로 점프 할 수있는 가장 빠른 시간을 반환합니다.
 *
 * 개구리가 결코 강 저편으로 점프 할 수없는 경우, 함수는 -1을 리턴해야합니다.
 * 예를 들어, X = 5이고 배열 A는 다음과 같습니다.
 * A [0] = 1
 * A [1] = 3
 * A [2] = 1
 * A [3] = 4
 * A [4] = 2
 * A [5] = 3
 * A [6] = 5
 * A [7] = 4
 * 위에서 설명한 것처럼 함수는 6을 반환해야합니다.
 *
 * 다음과 같이 가정하십시오.
 * N과 X는 [1..100,000] 범위의 정수입니다.
 * 배열 A의 각 요소는 [1..X] 범위의 정수입니다.
 *
 * 복잡성 :
 예상 된 최악의 경우의 시간 복잡도는 O (N)이다.
 * 예상 최악의 경우의 공간 복잡도는 입력 저장 공간을 초월한 O (X)입니다 (입력 인수에 필요한 저장 장치는 제외).
 입력 배열의 요소를 수정할 수 있습니다.
 * @author wooyong
 *
 */
public class FrogRiverOne {
		
	private static Logger log = LoggerFactory.getLogger(FrogRiverOne.class);
	
	public static void main(String[] args) {
		int X = 5;
		int[] A = { '1', '3', '1', '4', '2', '3', '5', '4' };
		//System.out.println(solution2(X, A));
		//System.out.println(solution3(X, A));
		//System.out.println(solution4(X, A));
		mySolution(X, A);
	}
	
	public static void mySolution(int X, int[]A) {
		Set<Integer> zbior = new HashSet<Integer>();		
		for (int i = 1; i <= X; i++) {
			zbior.add(i); 
		}
		for (int i = 0; i < A.length; i++) {
			if (zbior.contains(A[i])) {
				zbior.remove(A[i]);
			}
			if (zbior.isEmpty()) {
				log.debug("{}", i);
				return;
			}
		}
		log.debug("{}", -1);
	}
	
	public static int solution4(int X, int[] A) {
		int[] B = new int[X];
		
		for (int i = 0; i < B.length; i++) { // X까지만 건너면 되므로 X크기의 배열에 0번에는 1 1번에는 2 ... 식으로 채워간다
			B[i] = i + 1;
		}
		
		int size = X; // 배열의 사이즈를 체크할 변수선언
		
		for (int i = 0; i < A.length; i++) { // 들어온 A배열 만큼의 수에 따라서 for문을 돌리면서
			if (A[i] <= X && B[A[i] - 1] == 0) {
				// A의 값이 X보다 작을 때, 이미 이전에 같은 B배열을 참조하지 않았을 때
			} else {
				B[A[i] - 1] = 0; // 한번도 참조하지 않은 배열은 값을 0으로 바꾸어준다.
				size--; // 그 뒤사이즈를 --한다.
			}
			if (size == 0) { // 결국 size가 0이 되는 시점이 개구리가 길을 건널 수 있는 시점이다.
				return i;
			}
		}
		return -1; // 개구리가 못 건넌다면 -1리턴
	}
	
	public static int solution3(int X, int[] A) {

		int[] leafs = new int[100];
		int N = A.length;
		int sum = 0;
		for (int i = 0; i < N; i++) {
			leafs[i] = 0;
		}

		for (int T = 0; T < N; T++) {
			if (leafs[A[T] - 1] == 0) {
				leafs[A[T] - 1]++;
				sum = sum + A[T];
			}
			if (T >= (X - 1)) {
				if (sum == (X * (X + 1) / 2)) {
					return T;
				}
			}
		}
		return -1;
	}
	
	public static int solution(int X, int[] A) {
		// write your code in Java SE 8
		int leafList[] = new int[X + 1];
		for (int i = 0; i < leafList.length; i++) {
			leafList[i] = -1;
		}

		for (int i = 0; i < A.length; i++) {
			int leaf = A[i];
			if (leaf <= X) {
				if (leafList[leaf] == -1) {
					leafList[leaf] = i;
				} else {
					leafList[leaf] = Math.min(i, leafList[leaf]);
				}
			}
		}

		int result = 0;
		for (int i = 1; i < leafList.length; i++) {
			if (leafList[i] == -1) {
				result = -1;
				break;
			}
			result = Math.max(result, leafList[i]);
		}

		return result;
	}
	
	public static int solution2(int X, int[] A) {
		Set<Integer> zbior = new HashSet<Integer>();
		
		for (int i = 1; i <= X; i++) {
			zbior.add(i);
		}

		for (int i = 0; i < A.length; i++) {
			if (zbior.contains(A[i])) {
				zbior.remove(A[i]);
			}
			if (zbior.isEmpty()) {
				return i;
			}
		}
		return -1;
	}	
}

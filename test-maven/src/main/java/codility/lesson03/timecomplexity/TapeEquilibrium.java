package codility.lesson03.timecomplexity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * Array A represents numbers on a tape.
 * 
 * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: 
 * A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
 * 
 * The difference between the two parts is the value of: 
 * |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
 * 
 * In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
 * 
 * For example, consider array A such that:
 *   A[0] = 3
 *   A[1] = 1
 *   A[2] = 2
 *   A[3] = 4
 *   A[4] = 3
 * We can split this tape in four places:
 * P = 1, difference = |3 − 10| = 7
 * P = 2, difference = |4 − 9| = 5
 * P = 3, difference = |6 − 7| = 1
 * P = 4, difference = |10 − 3| = 7
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A of N integers, returns the minimal difference that can be achieved.
 * For example, given:
 *   A[0] = 3
 *   A[1] = 1
 *   A[2] = 2
 *   A[3] = 4
 *   A[4] = 3
 * the function should return 1, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * N 개의 정수로 구성된 비어 있지 않은 제로 색인 배열 A가 제공됩니다.
 * 배열 A는 테이프의 숫자를 나타냅니다.
 * 0 P <N 인 모든 정수 P는이 테이프를 두 개의 비어 있지 않은 부분으로 나눕니다. A [0], A [1], ..., A [P - 1] 및 A [P], A [P + 1], ..., A [N-1]이다.
 * 두 부분의 차이는 | (A [0] + A [1] + ... + A [P - 1]) - (A [P] + A [P + 1] +)의 값입니다. .. + A [N-1]) |
 * 즉, 첫 번째 부분의 합계와 두 번째 부분의 합계 사이의 절대 차이입니다.
 * 예를 들어, 다음과 같은 배열 A를 고려하십시오.
 * A [0] = 3
 * A [1] = 1
 * A [2] = 2
 * A [3] = 4
 * A [4] = 3
 *이 테이프는 네 군데로 나눌 수 있습니다.
 * P = 1, 차이 = | 3 - 10 | = 7
 * P = 2, 차이 = | 4 - 9 | = 5
 * P = 3, 차이 = | 6 - 7 | = 1
 * P = 4, 차이 = | 10-3 | = 7
 * 함수 작성 :
 * 클래스 솔루션 {public int solution (int [] A); }
 * N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 달성 할 수있는 최소의 차이를 반환합니다.
 * 예를 들어, 주어진 :
 * A [0] = 3
 * A [1] = 1
 * A [2] = 2
 * A [3] = 4
 * A [4] = 3
 * 위에서 설명한 것처럼 함수는 1을 반환해야합니다.
 * 다음과 같이 가정하십시오.
 * N은 [2..100,000] 범위의 정수입니다.
 * 배열 A의 각 요소는 [-1,000..1,000] 범위의 정수입니다.
 * 복잡성 :
 *  예상 된 최악의 경우의 시간 복잡도는 O(N)이다.
 *  예상 최악의 경우의 공간 복잡도는 입력 저장 공간을 초월한 O (N)입니다 (입력 인수에 필요한 저장 장치는 계산하지 않음).
 *  입력 배열의 요소를 수정할 수 있습니다.
 * @author wooyong
 */
public class TapeEquilibrium {
	
	private static Logger log = LoggerFactory.getLogger(TapeEquilibrium.class);
	
	/*
	 * Minimize the value |(A[0] + ... + A[P-1]) - (A[P] + ... + A[N-1])|.
	 */
	// N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 달성 할 수있는 최소의 차이를 반환
	public static void main(String[] args) {
		int[] A = { 3, 1, 2, 4, 3 };
		log.debug("{}", solution(A));
//		log.debug("{}", solution2(A));
		log.debug("{}", mySolution(A));
	}
	
	public static int mySolution(int[] A) {
		// 0 < P < N
		// N is an integer within the range [2..100,000];
		// each element of array A is an integer within the range [−1,000..1,000].
		// O(N)
		
		int front = 0;
		int back = 0;
		int minimal = Integer.MAX_VALUE;
		
		for (int i = 0; i < A.length; i++) {
			back += A[i];
		}
		
		if (A.length >= 2 && A.length <= 100000) {
			for (int P = 1; P < A.length; P++) {
				front += A[P - 1];
				back -= A[P - 1];
				minimal = Math.min(minimal, Math.abs(front - back));
			}
		}
		return minimal;
	}
		
	// O(N)
	/*
	 * int[] A = { 3, 1, 2, 4, 3 };
	 * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts:
	 * A[0], A[1], ..., A[P − 1]
	 * A[P], A[P + 1], ..., A[N − 1].
	 * The difference between the two parts is the value of:
	 * |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
	 * i) 복잡하게 풀어보기
	 * P를 0부터 N-1까지 늘려보면서, 각 경우에 앞부분의 합 - 뒷부분의 합을 구해볼 수 있겠습니다.
	 * 이 경우에는 0~N-1까지 N번의 탐색,
	 * 그리고 각 탐색마다 앞 부분의 합과 뒷부분의 합을 구하며 N번의 탐색이 이루어집니다.
	 * 때문에 N^2의 시간 복잡도를 갖게 됩니다.
	 * 
	 * ii) 시간 복잡도 줄이기
	 * 합을 매 번 구할 필요없이 P의 위치를 변경할 때 마다, 각 부분의 합을 갱신할 수 있습니다.
	 * 뒷부분 가장 앞의 원소를 떼서, 앞부분으로 붙여준다고 생각하시면 될 것 같습니다.
	 * 이렇게 처리하면, P를 옮길 때 마다 앞,뒤부분의 합을 N번의 탐색을 통해 새로 구하지 않아도 됩니다.
	 * 그렇게 해서 구해진 합을 서로 빼서, 최소값과 비교하여 더 작은 값이 나오면 그 값을 최소값으로 저장합니다.
	 */
	public static int solution(int[] A) {
		int front = 0;
		int back = 0;
		for (int i = 0; i < A.length; i++) {
			back += A[i]; // 총합을 구한다.
		}
		int minDiff = Integer.MAX_VALUE;
		for (int i = 1; i < A.length; i++) {
			front += A[i - 1]; // 앞자리 합
			back -= A[i - 1]; //  뒷자리 합
			minDiff = Math.min(minDiff, Math.abs(front - back));
		}
		return minDiff;
	}

	public static int solution2(int[] A) {

		int minVal = 0;
		int arrayLength = A.length;

		if (arrayLength == 2) {
			return Math.abs(A[1] - A[0]);
		}

		for (int split = 1; split < arrayLength - 1; split++) {
			A[split] = Math.abs(A[split] + A[split - 1]);
		}

		int total = Math.abs(A[arrayLength - 2] + A[arrayLength - 1]);
		minVal = total;

		for (int split = 0; split < arrayLength - 1; split++) {
			int rSum = Math.abs(A[split] - total);
			if (Math.abs(A[split] - rSum) < minVal) {
				minVal = Math.abs(A[split] - rSum);
			}
		}
		return minVal;
	}

}

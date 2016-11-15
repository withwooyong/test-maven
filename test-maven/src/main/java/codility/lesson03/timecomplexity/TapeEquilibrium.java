package codility.lesson03.timecomplexity;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * Array A represents numbers on a tape.
 * Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
 * The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
 * In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
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
 * Assume that:
 * N is an integer within the range [2..100,000];
 * each element of array A is an integer within the range [−1,000..1,000].
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
 예상 된 최악의 경우의 시간 복잡도는 O (N)이다.
 * 예상 최악의 경우의 공간 복잡도는 입력 저장 공간을 초월한 O (N)입니다 (입력 인수에 필요한 저장 장치는 계산하지 않음).
 입력 배열의 요소를 수정할 수 있습니다.
 * @author wooyong
 */
public class TapeEquilibrium {
	
	public static void main(String[] args) {
		int[] input = { 3, 1, 2, 4, 3 };
		System.out.println(solution(input));
		
//		for (int i = 0; i < input.length; i++) {
//			System.out.println(Math.abs(input[i]));
//		}
	}
	
	public static int solution(int[] A) {
        // write your code in Java SE 8
        
        int front = 0;
        int back = 0;
        for(int i = 0 ; i < A.length ; i++){
            back += A[i];   
        }
        
        int minDiff = Integer.MAX_VALUE;
        int p = 1;
        for(int i = 1 ; i < A.length ; i++){
            front += A[i-1];
            back -= A[i-1];
            p = i;
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

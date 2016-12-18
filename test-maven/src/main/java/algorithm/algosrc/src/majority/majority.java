package algorithm.algosrc.src.majority;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 4.1 주어진 배열에서 가장 많이 등장하는 숫자를 반환하기 
// 4.2 주어진 배열에서 가장 많이 등장하는 숫자를 반환하기 2
public class majority {
	
	private static Logger log = LoggerFactory.getLogger(majority.class);

	public static void main(String[] args) {
		
		int[] A = { 1, 2, 2, 3, 4, 5, 6, 7, 8, 9, 9};
		log.debug("{}", majority1(A));
		log.debug("{}", majority2(A));
	}

	// 주어진 배열 A 에서 가장 많이 등장하는 숫자를 반환한다.
	// 만약 두 가지 이상 있을 경우 아무 것이나 반환한다.
	public static int majority1(int[] A) {
		int N = A.length;
		int majority = -1, majorityCount = 0;
		for (int i = 0; i < N; i++) {
			// A 에 등장하는 A[i] 의 수를 센다
			int V = A[i];
			int count = 0;
			for (int j = 0; j < N; j++) {
				if (A[j] == V) {
					++count;
				}
			}
			if (count > majorityCount) {
				majorityCount = count;
				majority = V;
			}
		}
		return majority;
	}

	// A 가 0 부터 100 사이의 값일 경우 가장 많이 등장하는 숫자를 반환한다.
	public static int majority2(int[] A) {
		int N = A.length;
		int[] count = new int[A.length];

		for (int i = 0; i < N; i++) {
			count[A[i]]++;
		}

		int majority = 0;
		for (int i = 0; i < A.length; i++) {
			if (count[i] > count[majority]) {
				majority = i;
			}
		}
		return majority;
	}
}

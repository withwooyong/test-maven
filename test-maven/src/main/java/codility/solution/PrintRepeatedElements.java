package codility.solution;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PrintRepeatedElements {
	
	protected static final Logger log = LoggerFactory.getLogger(PrintRepeatedElements.class);

	public static void main(String[] args) {
		
		int[] A = { 6, 2, 6, 5, 2, 3, 10, 10 }; // 중복되는 항목 찾기 
		int[] B = { 1, 2, 3, 1, 2, 3, 3, 3, 1 };  // 하나의 숫자만 홀수번 나타난다.
		
//		solution(A);
		solution2(A);
		//solution3(B);
		
	}
	
	// 하나의 숫자만 홀수번 나타난다. 모든 항목에 비트 단위 XOR 을 수행한다. A XOR A = 0 
	// O(n)
	public static void solution3(int[] B) {
		log.debug("{}", Arrays.toString(B));
		int XOR = 0;
		for (int i = 0; i < B.length; i++) {
			XOR ^= B[i];
			log.debug("{} {}", XOR, B[i]);
			if (XOR == 0) {
				log.debug("{}", B[i]);
			}
		}
		
	}

	// O(n2) 중복되는 항목 찾기
	public static void solution(int[] A) {
		log.debug("{}", Arrays.toString(A));
		for (int i = 0; i < A.length; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if (A[i] == A[j]) { // 중복체크
					log.debug("{}", A[i]);
				}
			}
		}
	}
	
	// O(n) 중복되는 항목 찾기
	public static void solution2(int[] A) {
		Arrays.sort(A);
		log.debug("{}", Arrays.toString(A));
//		log.debug("{}", A.length-1);
//		log.debug("{}", A[A.length-1]);
		int[] count = new int[A[A.length - 1] + 1]; // A[] 에서 최고값  + null 만큼 생성 
		//int[] count = new int[Integer.MAX_VALUE]; // Requested array size exceeds VM limit
		for (int i = 0; i < A.length; i++) {
			count[A[i]]++;
			//log.debug("{}", count[A[i]]);
			if (count[A[i]] == 2) {
				log.debug("{}", A[i]);
			}
		}		
	}
}

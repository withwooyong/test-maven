package codility.lesson08.leader;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Leader {
	
	private static Logger log = LoggerFactory.getLogger(Leader.class);

	public static void main(String[] args) {
		//int[] A = { 6, 8, 4, 6, 8, 6, 6 };
		int[] A = { 4, 6, 6, 6, 6, 8, 8 };
		log.debug("O(n2) {}", slowLeader(A));
		log.debug("O(nlogn) {}", fastLeader(A));
		log.debug("O(n) {}", goldenLeader(A));
	}

	// O(n2)
	private static int slowLeader(int[] A) {
		int n = A.length;
		int leader = -1;
		int candidate = -1;

		for (int i = 0; i < A.length; i++) {
			candidate = A[i];
			int count = 0;
			for (int j = 0; j < A.length; j++) {
				if (A[i] == candidate) {
					count += 1; // 동일한 값이면 카운트
				}
			}
			if (count > n / 2) {
				leader = candidate;
			}
		}
		return leader;
	}
	
	// O(nlogn)
	private static int fastLeader(int[] A) {
		int n = A.length;
		int leader = -1;
		Arrays.sort(A);
		int candidate = A[n / 2];
		int count = 0;

		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count += 1;
			}
		}
		if (count > n / 2) {
			leader = candidate;
		}
		return leader;
	}
	
	// O(n)
	private static int goldenLeader(int[] A) {		
		int size = 0;
		int value = 0;
		for (int i = 0; i < A.length; i++) {
			if (size == 0) {
				size += 1;
				value = A[i];
			} else if (value != A[i]) {
				size -= 1;
			} else {
				size += 1;
			}
		}
		
		int candidate = -1;
		if (size > 0) {
			candidate = value;
		}
		int leader = -1;
		int count = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count += 1;
			}			
		}
		if (count > (A.length / 2)) {
			leader = candidate;
		}
		return leader;
	}
}

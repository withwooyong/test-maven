package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumSliceProblem {

	private static Logger log = LoggerFactory.getLogger(MaximumSliceProblem.class);
	
	public static void main(String[] args) {
		int[] A = { 5, -7, 3, 5, -2, 4, -1 };
		log.debug("{}", slowMaxSlice(A)); // The sum of this slice equals 10
	}
	
	// O(n3)
	private static int slowMaxSlice(int[] A) {
		int result = 0;
		
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
				int sum = 0;
				for (int k = i; k < j + 1; k++) {
					sum += A[k];
				}
				result = Math.max(result, sum);
			}
		}
		return result;
	}
	
	private static int quadratic_max_slice(int[] A, int pref) {
		
		int result = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = i; j < A.length; j++) {
//				int sum = pref[j + 1] - pref[i];
//				result = Math.max(result, sum);						
			}
		}
		return result;
	}
}



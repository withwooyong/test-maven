package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaximumSliceProblem {

	private static Logger log = LoggerFactory.getLogger(MaximumSliceProblem.class);
	
	public static void main(String[] args) {
		int[] A = { 5, -7, 3, 5, -2, 4, -1 };
		log.debug("{}", slowMaxSlice(A));
	}
	
	private static int slowMaxSlice(int[] A) {
		int n = A.length;
		int result = 0;
		
		for (int p = 0; p < n; p++) {
			for (int q = p; q < n; q++) {
				int sum = 0;
				for (int i = p; i < q + 1; i++) {
					sum += A[i];
				}
				result = Math.max(result, sum);
			}
		}
		return result;
	}
}

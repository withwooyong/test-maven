package codility.lesson05.prefixsums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MushroomPicker {

	private static Logger log = LoggerFactory.getLogger(MushroomPicker.class);

	public static void main(String[] args) {
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		int k = 4;
		int m = 6;

		// int[] A = {3, 2, 5, 1, 6, 11};
		// int k = 3;
		// int m = 4;
		int maxMushrooms = maximumMushrooms(A, k, m);

		log.debug("Maximum number of mushrooms is {}", maxMushrooms);

	}

	// Time : O(n ^ 2)
	// Space: O(n * m)
	private static int maximumMushrooms(int[] A, int k, int m) {

		int maxSum = 0;

		for (int p = 0; p <= m; p++) {
			boolean[] visited = new boolean[A.length];

			int currentSum = A[k];
			visited[k] = true;
			int leftMoves = p;

			for (int i = k, count = 0; (i - 1) >= 0 && count < leftMoves; i--, count++) {
				currentSum += A[i - 1];
				visited[i - 1] = true;
			}

			int rightMoves = m - p;
			for (int i = k - p, count = 0; (i + 1) < A.length && count < rightMoves; i++, count++) {
				if (visited[i + 1] == false) {
					currentSum += A[i + 1];
				}
			}

			if (currentSum > maxSum) {
				maxSum = currentSum;
			}
		}

		return maxSum;
	}

}

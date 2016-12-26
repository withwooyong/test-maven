package codility.lesson14.Binarysearchalgorithm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Permutation {

	private static Logger log = LoggerFactory.getLogger(Permutation.class);

	public static void main(String[] args) {
		//int A[] = { 1, 2, 3, 4, 5, 6 };
		int A[] = { 1, 2, 3 };
		permute(A, 0);
	}

	private static void permute(int[] A, int k) {
		if (k == A.length) {
			log.debug("{}", Arrays.toString(A));
		} else {
			for (int i = k; i < A.length; i++) {
				int temp = A[k];
				A[k] = A[i];
				A[i] = temp;
				permute(A, k + 1);
				temp = A[k];
				A[k] = A[i];
				A[i] = temp;
			}
		}
	}
}

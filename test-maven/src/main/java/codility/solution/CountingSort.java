package codility.solution;

import java.util.Arrays;

import org.apache.lucene.index.MergePolicy.MergeSpecification;

public class CountingSort {

	static int compCount;
	static int swapCount;
	
	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 6, 5, 5, 8, 9 };		
		mergeSort(A, 0);
	}

	public static int mergeSort(int[] a, int howMany) {
		if (a.length >= 2) {
			// split array into two halves
			int[] left = Arrays.copyOfRange(a, 0, a.length / 2);
			int[] right = Arrays.copyOfRange(a, a.length / 2, a.length);

			// sort the two halves
//			mergeSort(left, howMany);
//			mergeSort(right, howMany);

			// merge the sorted halves into a sorted whole
			merge(a, left, right);
		}
		return swapCount++;
	}

	// Merges the left/right elements into a sorted result.
	// Precondition: left/right are sorted
	public static void merge(int[] result, int[] left, int[] right) {
		int i1 = 0; // index into left array
		int i2 = 0; // index into right array

		for (int i = 0; i < result.length; i++) {
			compCount++;
			if (i2 >= right.length || (i1 < left.length && left[i1] <= right[i2])) {
				result[i] = left[i1]; // take from left
				i1++;
				swapCount++;
			} else {
				result[i] = right[i2]; // take from right
				i2++;
				swapCount++;
			}
		}
		System.out.println("merge sort " + compCount + " " + swapCount);
	}

}

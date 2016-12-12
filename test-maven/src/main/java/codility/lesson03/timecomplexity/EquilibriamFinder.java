package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquilibriamFinder {
	
	private static Logger log = LoggerFactory.getLogger(EquilibriamFinder.class);

	public static void main(String[] args) {
		//int[] arr = { 1, 1, 1, 1, 1, 1, 1, 1, 1 };
		//int[] A = { 1, 2, 3, 4, 5, 6, 7, 8 }; // Equilibraim value: 6 found at location 5
		int[] A = { -7, 1, 5, 2, -4, 3, 0 }; // Equilibraim value: 2 found at location 3
		findEqilibriam(A); 
	}

	// Finds the equilibrium position and element in the given array.
	private static void findEqilibriam(final int[] arr) {
		// If array length is less than 3, there is no equilibrium position in the array.
		if (arr.length < 3) {
			return;
		}

		int totalSum = 0;
		for (int i = 0; i < arr.length; i++) {
			totalSum += arr[i];
		}
		
		boolean found = false;
		int leftSum = 0;
		int rightSum = 0;
		// Iterate over array, skip first and last element
		for (int i = 1; i < arr.length - 1; i++) {

			// Find the sum of elements on left side of current element.
			leftSum += arr[i - 1];

			// Find the sum of elements on right side of current element.
			rightSum = totalSum - leftSum - arr[i];

			// If sum of elements on right side of current element is equal to
			// sum of elements on left side of the current element, there is an
			// equilibrium position.
			if (rightSum == leftSum) {
				log.debug("Equilibraim value: {} found at location {}", arr[i], i);
				found = true;
				break;
			}
		}

		if (!found) {
			log.debug("{}", "Equilibraim not present");
		}
	}
	
	// 위코드와 findEqilibriam 동일함 주석만 제거
	private static void solution(final int[] arr) {
		if (arr.length < 3) {
			return;
		}

		int totalSum = 0;
		for (int i = 0; i < arr.length; i++) {
			totalSum += arr[i];
		}
		
		boolean found = false;
		int leftSum = 0;
		int rightSum = 0;
		
		for (int i = 1; i < arr.length - 1; i++) {
			leftSum += arr[i - 1];			
			rightSum = totalSum - leftSum - arr[i];
			if (rightSum == leftSum) {
				log.debug("Equilibraim value: {} found at location {}", arr[i], i);
				found = true;
				break;
			}
		}

		if (!found) {
			log.debug("{}", "Equilibraim not present");
		}
	}
}

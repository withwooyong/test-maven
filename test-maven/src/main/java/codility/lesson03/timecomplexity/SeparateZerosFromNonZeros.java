package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/how-to-separate-zeros-from-non-zeros-in-an-array/
 * Problem : How To Separate Zeros From Non-Zeros In An Array?
 * Given an integer array, you have to separate all zero elements from non-zero elements. 
 * You have to move zeros either to end of the array or bring them to beginning of the array. 
 * For example, if {14, 0, 5, 2, 0, 3, 0} is the given array, 
 * then moving zeros to end of the array will result {14, 5, 2, 3, 0, 0, 0} 
 * and bringing zeros to front will result {0, 0, 0, 14, 5, 2, 3}.
 * 주어진 배열에서 0을 앞으로 옮기느냐 뒤로 옮기느냐.
 */
public class SeparateZerosFromNonZeros {

	private static Logger log = LoggerFactory.getLogger(SeparateZerosFromNonZeros.class);
	
	public static void main(String[] args) {
		int[] A = { 12, 0, 7, 0, 8, 0, 3 };
		moveZerosToEnd(new int[] { 12, 0, 7, 0, 8, 0, 3 });
		moveZerosToEnd(new int[] { 1, -5, 0, 0, 8, 0, 1 });
		moveZerosToEnd(new int[] { 0, 1, 0, 1, -5, 0, 4 });
		moveZerosToEnd(new int[] { -4, 1, 0, 0, 2, 21, 4 });

		moveZerosToFront(new int[] {12, 0, 7, 0, 8, 0, 3});
		moveZerosToFront(new int[] {1, -5, 0, 0, 8, 0, 1});
		moveZerosToFront(new int[] {0, 1, 0, 1, -5, 0, 4});
		moveZerosToFront(new int[] {-4, 1, 0, 0, 2, 21, 4});
	}
	
	private static void moveZerosToEnd(int inputArray[]) {
		// Initializing counter to 0
		int counter = 0;
		// Traversing inputArray from left to right

		for (int i = 0; i < inputArray.length; i++) {
			// If inputArray[i] is non-zero
			if (inputArray[i] != 0) {
				// Assigning inputArray[i] to inputArray[counter]
				inputArray[counter] = inputArray[i];
				// Incrementing the counter by 1
				counter++;
			}
		}
		// Assigning zero to remaining elements
		while (counter < inputArray.length) {
			inputArray[counter] = 0;
			counter++;
		}
		log.debug("{}", Arrays.toString(inputArray));
	}
	
	private static void moveZerosToFront(int inputArray[]) {
		// Initializing counter to position of last element
		int counter = inputArray.length - 1;
		// Traversing the inputArray from right to left

		for (int i = inputArray.length - 1; i >= 0; i--) {
			// If inputArray[i] is non-zero

			if (inputArray[i] != 0) {
				// Assigning inputArray[i] to inputArray[counter]
				inputArray[counter] = inputArray[i];
				// Decrementing the counter by 1
				counter--;
			}
		}

		// Assigning 0 to remaining elements
		while (counter >= 0) {
			inputArray[counter] = 0;
			counter--;
		}
		log.debug("{}", Arrays.toString(inputArray));
	}
}

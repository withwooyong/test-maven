package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/how-to-find-continuous-sub-array-whose-sum-is-equal-to-given-number/
 * Java Program To Find Continuous Sub Array In Array Whose Sum Is Equal To Number :
 * 배열의 부분합이 주어진 수와 같은게 있는지 
 */
public class SubArrayWhoseSumIsNumber {

	private static Logger log = LoggerFactory.getLogger(SubArrayWhoseSumIsNumber.class);

	public static void main(String[] args) {
		findSubArray(new int[] { 42, 15, 12, 8, 6, 32 }, 26);
		findSubArray(new int[] { 12, 5, 31, 13, 21, 8 }, 49);
		findSubArray(new int[] { 15, 51, 7, 81, 5, 11, 25 }, 41);
		
		findSubArray2(new int[] { 42, 15, 12, 8, 6, 32 }, 26);
	}

	private static void findSubArray(int[] inputArray, int inputNumber) {
		// Initializing sum with the first element of the inputArray
		int sum = inputArray[0];
		// Initializing starting point with 0
		int start = 0;
		// Iterating through inputArray starting from second element
		for (int i = 1; i < inputArray.length; i++) {
			// Adding inputArray[i] to the current 'sum'

			sum = sum + inputArray[i];

			// If sum is greater than inputNumber then following loop is executed until

			// sum becomes either smaller than or equal to inputNumber

			while (sum > inputNumber && start <= i - 1) {
				// Removing starting elements from the 'sum'

				sum = sum - inputArray[start];

				// Incrementing start by 1

				start++;
			}

			// If 'sum' is equal to 'inputNumber' then printing the sub array

			if (sum == inputNumber) {
				System.out.println("Continuous sub array of " + Arrays.toString(inputArray) + " whose sum is "
						+ inputNumber + " is ");

				for (int j = start; j <= i; j++) {
					System.out.print(inputArray[j] + " ");
				}

				System.out.println();
			}
		}
	}

	static void findSubArray2(int[] inputArray, int inputNumber) {
		// Initializing 'sum' to 0

		int sum = 0;

		// Iterating through 'inputArray'

		for (int i = 0; i < inputArray.length; i++) {
			// Assigning inputArray[i] to 'sum'

			sum = inputArray[i];

			for (int j = i + 1; j < inputArray.length; j++) {
				// Adding inputArray[j] to 'sum'

				sum = sum + inputArray[j];

				// If 'sum' is equal to 'inputNumber' then printing the sub array

				if (sum == inputNumber) {
					System.out.println("Continuous sub array of " + Arrays.toString(inputArray) + " whose sum is "
							+ inputNumber + " is ");

					for (int k = i; k <= j; k++) {
						System.out.print(inputArray[k] + " ");
					}

					System.out.println();
				}

				// if 'sum' is smaller than 'inputNumber', continue the loop

				else if (sum < inputNumber) {
					continue;
				}

				// if 'sum' is greater than 'inputNumber', then break the loop

				else if (sum > inputNumber) {
					break;
				}
			}
		}
	}
}

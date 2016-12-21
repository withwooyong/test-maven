package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SecondLargest {

	private static Logger log = LoggerFactory.getLogger(SecondLargest.class);
	
	public static void main(String[] args) {
		int[] A = { 45, 51, 28, 75, 49, 42 };
		log.debug("{}", secondLargest(new int[] { 45, 51, 28, 75, 49, 42 }));
		log.debug("{}", secondLargest(new int[] { 985, 521, 975, 831, 479, 861 }));
		log.debug("{}", secondLargest(new int[] { 9459, 9575, 5692, 1305, 1942, 9012 }));
		log.debug("{}", secondLargest(new int[] { 47498, 14526, 74562, 42681, 75283, 45796 }));
		solution(A);
	}
	
	private static void solution(int[] input) {
		Arrays.sort(input);
		log.debug("{}", input[input.length-2]);
	}

	private static int secondLargest(int[] input) {
		int firstLargest, secondLargest;

		// Checking first two elements of input array

		if (input[0] > input[1]) {
			// If first element is greater than second element
			firstLargest = input[0];
			secondLargest = input[1];
		} else {
			// If second element is greater than first element
			firstLargest = input[1];
			secondLargest = input[0];
		}

		// Checking remaining elements of input array

		for (int i = 2; i < input.length; i++) {
			if (input[i] > firstLargest) {
				// If element at 'i' is greater than 'firstLargest'
				secondLargest = firstLargest;
				firstLargest = input[i];
			} else if (input[i] < firstLargest && input[i] > secondLargest) {
				// If element at 'i' is smaller than 'firstLargest' and greater
				// than 'secondLargest'
				secondLargest = input[i];
			}
		}
		return secondLargest;
	}
}

package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PairsOfElementsInArray {
	
	private static Logger log = LoggerFactory.getLogger(PairsOfElementsInArray.class);

	public static void main(String[] args) {
		findThePairs(new int[] { 4, 6, 5, -10, 8, 5, 20 }, 10);
		findThePairs(new int[] { 4, -5, 9, 11, 25, 13, 12, 8 }, 20);
		findThePairs(new int[] { 12, 13, 40, 15, 8, 10, -15 }, 25);
		findThePairs(new int[] { 12, 23, 125, 41, -75, 38, 27, 11 }, 50);
	}

	// Java Program To Find All Pairs Of Elements In An Array Whose Sum Is Equal To A Given Number :
	private static void findThePairs(int inputArray[], int inputNumber) {
		log.debug("Pairs of elements whose sum is {} ", inputNumber);

		for (int i = 0; i < inputArray.length; i++) {
			for (int j = i + 1; j < inputArray.length; j++) {
				if (inputArray[i] + inputArray[j] == inputNumber) {
					log.debug("{} + {} = {}", inputArray[i], inputArray[j], inputNumber);
				}
			}
		}
	}
}

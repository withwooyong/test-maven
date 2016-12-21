package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectionSortInJava {

	private static Logger log = LoggerFactory.getLogger(SelectionSortInJava.class);

	public static void main(String[] args) {
		selectionSort(new int[] { 45, 84, 101, 62, 12, 45 });
		selectionSort(new int[] { 62, -45, 14, 8, -52, 45 });
		selectionSort(new int[] { 21, 12, 56, -48, 15, 45 });
	}

	private static void selectionSort(int[] inputArray) {
		int temp, pos;
		for (int i = 0; i < inputArray.length - 1; i++) {
			pos = i;
			// finding the position of smallest element between (i+1)th element and last element
			for (int j = i + 1; j < inputArray.length; j++) {
				if (inputArray[j] < inputArray[pos]) {
					pos = j;
				}
			}
			// Swapping inputArray[i] and inputArray[pos]
			temp = inputArray[i];
			inputArray[i] = inputArray[pos];
			inputArray[pos] = temp;
		}

		log.debug("{}", Arrays.toString(inputArray));
	}
}

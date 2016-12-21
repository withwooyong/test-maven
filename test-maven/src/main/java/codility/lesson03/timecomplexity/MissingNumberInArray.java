package codility.lesson03.timecomplexity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/java-puzzle-interview-program-find-missing-number-in-an-array/
 * Java Program To Find Missing Number In An Array :
 * 
 * Step 1 : First we find out sum of ‘n’ numbers by using formula n*(n+1)/2.
 * Step 2 : Then we will find sum of all elements of array ‘a’.
 * Step 3 : Missing_Number = (Sum of 1 to ‘n’ numbers) – (Sum of elements of array ‘a’)
 */
public class MissingNumberInArray {
	
	private static Logger log = LoggerFactory.getLogger(MissingNumberInArray.class);
	
	// Method to calculate sum of 'n' numbers
	public static void main(String[] args) {
		int n = 8;
		int[] a = { 1, 4, 5, 3, 7, 8, 6 };
		// Step 1
		int sumOfNnumbers = sumOfNnumbers(n);
		// Step 2
		int sumOfElements = sumOfElements(a);
		// Step 3
		int missingNumber = sumOfNnumbers - sumOfElements;
		log.debug("Missing Number is = {}", missingNumber);
	}

	static int sumOfNnumbers(int n) {
		int sum = (n * (n + 1)) / 2; // 합공식 요넘중요.
		return sum;
	}

	// Method to calculate sum of all elements of array
	static int sumOfElements(int[] array) {
		int sum = 0;
		for (int i = 0; i < array.length; i++) {
			sum = sum + array[i];
		}
		return sum;
	}	
}
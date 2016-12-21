package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SumOfAllDigits {

	private static Logger log = LoggerFactory.getLogger(SumOfAllDigits.class);

	public static void main(String[] args) {
		sumOfAllDigits(47862);
		sumOfAllDigits(416872);
		sumOfAllDigits(5674283);
		sumOfAllDigits(475496215);
	}

	// Java Program To Find The Sum Of All Digits Of A Number In Java :
	static void sumOfAllDigits(int inputNumber) {
		// Creating a copy of input number
		int copyOfInputNumber = inputNumber;
		// Initializing sum to 0
		int sum = 0;

		while (copyOfInputNumber != 0) {
			// Getting last digit of the input number
			int lastDigit = copyOfInputNumber % 10; // 10으로 나눈 나머지
			// Adding last digit to sum
			sum = sum + lastDigit;
			// Removing last digit from the input number
			copyOfInputNumber = copyOfInputNumber / 10; // 10으로 나눈 후 계속
		}
		// Printing sum
		log.debug("Sum Of All Digits In {} = {}", inputNumber, sum);
	}
}

package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/java-program-check-whether-given-number-binary-or-not/
 * Java Program To Check Whether The Given Number Is Binary Or Not :
 * 
 * Logic Used To Find Out Whether The Given Number Is Binary Or Not :
 * We check each and every digit of a given number. 
 * If any digit is greater than 1, then the given number is not a binary number. 
 * In this program, we use two simple operators – % and /, to extract the digits from the given number. 
 * You may know that, if we divide any number by 10 i.e number/10, it will remove last digit from the given number and if you use remainder operator like number%10, 
 * it will give last digit of the given number. 
 * For example, if 456 is the number, then 456/10 will result 45 and 456%10 will give 6. 
 * We use these two techniques to extract the digits from the given number one by one and we will check whether this extracted digit is greater than 1. 
 * If the extracted digit is greater than 1, then we will conclude that the given number is not a binary number.
 */
public class BinaryOrNot {
	
	private static Logger log = LoggerFactory.getLogger(BinaryOrNot.class);
	// A binary number is a number which contains only 0 or 1. 
	// For example : 101101, 110010110, 10010011 are binary numbers.
	public static void main(String[] args) {
		isBinaryOrNot(128956);
		isBinaryOrNot(101110);
		isBinaryOrNot(42578);
		isBinaryOrNot(10110101);
	}

	private static void isBinaryOrNot(int number) {
		boolean isBinary = true;

		int copyOfNumber = number;

		// 10으로 나눈 나머지가 0, 1 이면 10으로 나눈 다음 0이 될때 까지 계속
		while (copyOfNumber != 0) {
			int temp = copyOfNumber % 10; // Gives last digit of the number

			if (temp > 1) {
				isBinary = false;
				break;
			} else {
				//copyOfNumber = copyOfNumber / 10; // Removes last digit from the number
				copyOfNumber /= 10;
			}
		}

		if (isBinary) {
			log.debug("{} is a binary number", number);
		} else {
			log.debug("{} is not a binary number", number);
		}
	}
}

package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/how-to-find-largest-number-less-than-given-number-and-without-given-digit/
 * Java Program To Find Largest Number Less Than Given Number And Without A Given DIgit :
 * Write a java program to find the largest number ‘L’ less than a given number ‘N’ which should not contain a given digit ‘D’. 
 * For example, If 145 is the given number and 4 is the given digit, then you should find the largest number less than 145 such that 
 * it should not contain 4 in it. In this case, 139 will be the answer.
 * 
 * We keep on decrementing the given number by 1 until we get the number which does not contain a given digit. 
 * For this, we convert the given number to string ( Integer.toString(i) ) and given digit to character ( char c = Integer.toString(digit).charAt(0) ). 
 * And check whether that string contains the character ‘c’ or not ( Integer.toString(i).indexOf(c) ). 
 * If it contains, again we decrement the given number and convert it to string and check whether this string contains the character ‘c’ or not. 
 * We will continue this until we get the number which does not contain a given digit.
 */
public class LargestNumber {
	
	private static Logger log = LoggerFactory.getLogger(LargestNumber.class);

	public static void main(String[] args) {
		log.debug("{}", getLLessThanN(123, 2));		 // 123 보다 작은 숫자중 2를 포함하지 않는 숫자 119
        log.debug("{}", getLLessThanN(4582, 5));     // 4499
        log.debug("{}", getLLessThanN(98512, 5));    // 98499
        log.debug("{}", getLLessThanN(548624, 8));   // 547999
	}
	
	private static int getLLessThanN(int number, int digit) {
		// Converting digit to char
		char c = Integer.toString(digit).charAt(0);
		// Decrementing number & checking whether it contains digit
		for (int i = number; i > 0; --i) { // 숫자를 감소 시키면서 digit 문자가 있는지 없는지 체크
			if (Integer.toString(i).indexOf(c) == -1) {
				// If 'i' doesn't contain 'c'
				return i;
			}
		}
		return -1;
	}
}

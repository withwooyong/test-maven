package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/armstrong-number-in-java/
 * What Is An Armstrong Number?
 * A number is called an Armstrong number 
 * if it is equal to sum of its digits each raised to the power of number of digits in it. 
 * For example: 153, 371, 407, 9474, 54748 are some Armstrong numbers.
 * 153 = 13 + 53 + 33 = 1 + 125 + 27 = 153
 * 371 = 33 + 73 + 13 = 27 + 343 + 1 = 371
 * 407 = 43 + 03 + 73 = 64 + 0 + 343 = 407
 * 9474 = 94 + 44 + 74 + 44 = 6561 + 256 + 2401 + 256 = 9474
 * 54748 = 55 + 45 + 75 + 45 + 85 = 3125 + 1024 + 16807 + 1024 + 32768 = 54748
 * 
 * Logic Used To Find Out An Armstrong Number In Java :
 * First we create a copy of the given number as ‘copyOfNumber‘. 
 * We find out the number of digits in a given number and store it into a variable called ‘noOfDigits‘. 
 * For this, we use String.valueOf(number).length() which gives the number of digits in a given number. 
 * We also declare a variable ‘sum’ and initialize it to 0.
 * We extract each and every digit of a given number from last to first in a while loop. 
 * For this we use ‘%’ – Modulus operator. 
 * You may know that, if we use modulus operator like number%10, it will give last digit of the number. 
 * After getting last digit of the number, we multiply it by itself equal to ‘noOfDigits’ times, store it into ‘lastDigitToThePowerOfNoOfDigits’ and add it to ‘sum’.
 * After calculating ‘lastDigitToThePowerOfNoOfDigits’ and adding it to ‘sum’, we divide the ‘copyOfNumber‘ by 10 
 * i.e copyOfNumber/10. It will remove the last digit from the number. 
 * We continue these steps until ‘copyOfNumber‘ becomes 0.
 * At last, we check whether ‘sum’ is equal to given number. 
 * If it is equal, then given number is an Armstrong number.
 */
public class ArmstrongNumber {
	
	private static Logger log = LoggerFactory.getLogger(ArmstrongNumber.class);

	// 암스트롱넘버 : 수의 각 자릿수의 숫자 값을 ^3을 한뒤 더한값이 원래의 수가 되는것.
	// 153 의 경우 1, 5, 3 (1*1*1) + (5*5*5*) + (3*3*3) = 153
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		checkArmstrongNumber(153);  // an armstrong number
		checkArmstrongNumber(371);  // an armstrong number		
		checkArmstrongNumber(1674); // is not an armstrong number
		solution(153);
	}

	private static void checkArmstrongNumber(int number) {
		int copyOfNumber = number;
		int noOfDigits = String.valueOf(number).length();
		int sum = 0;

		while (copyOfNumber != 0) {
			int lastDigit = copyOfNumber % 10;
			int lastDigitToThePowerOfNoOfDigits = 1;
			
			for (int i = 0; i < noOfDigits; i++) {
				lastDigitToThePowerOfNoOfDigits = lastDigitToThePowerOfNoOfDigits * lastDigit;
			}
			sum = sum + lastDigitToThePowerOfNoOfDigits;
			copyOfNumber = copyOfNumber / 10;
		}

		if (sum == number) {
			log.debug("{} is an armstrong number", number);
		} else {
			log.debug("{} is not an armstrong number", number);
		}
	}
	
	private static void solution(int number) {
		String digit = String.valueOf(number);
		int sum = 0;
		for (int i = 0; i < digit.length(); i++) {
			sum += (int)Math.pow(Character.getNumericValue(digit.charAt(i)), 3); // 3제곱 Math.pow함수
		}
		if (sum == number) {
			log.debug("{} is an armstrong number", number);
		} else {
			log.debug("{} is not an armstrong number", number);
		}
	}

}

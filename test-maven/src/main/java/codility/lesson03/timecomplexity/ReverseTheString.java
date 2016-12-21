package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/java-program-to-reverse-a-string/
 */
public class ReverseTheString {
	
	private static Logger log = LoggerFactory.getLogger(ReverseTheString.class);

	public static void main(String[] args) {
		String str = "ABCD";
		
		// 1. Using StringBuffer Class
		
		StringBuffer sbf = new StringBuffer(str);
		log.debug("{}", sbf.reverse()); 

		// 2. Using iterative method

		char[] strArray = str.toCharArray();

		for (int i = strArray.length - 1; i >= 0; i--) {
			log.debug("{}", strArray[i]);
		}		

		// 3. Using Recursive Method

		log.debug("{}", recursiveMethod(str));
	}

	// Recursive method to reverse string
	static String recursiveMethod(String str) {
		if ((null == str) || (str.length() <= 1)) {
			return str;
		}
		return recursiveMethod(str.substring(1)) + str.charAt(0);
	}

}

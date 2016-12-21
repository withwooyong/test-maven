package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestForJavaInterView {
	
	private static Logger log = LoggerFactory.getLogger(TestForJavaInterView.class);

	public static void main(String[] args) {
		int A[] = { -7, 1, 5, 2, -4, 3, 0 };
		
	}
	
	/*
	 * http://javaconceptoftheday.com/java-program-to-check-user-input-is-number-or-not/
	 * Java Program To Check Whether User Input Is Number Or Not
	 */
	private static boolean numberOrNot(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException ex) {
			return false;
		}
		return true;
	}
	
	// Java Program To Reverse A String
	
	
	
	
	

}

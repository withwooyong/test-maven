package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/java-program-to-find-duplicate-elements-in-an-array/
 */
public class DuplicatesInArray {

	private static Logger log = LoggerFactory.getLogger(DuplicatesInArray.class);
	
	public static void main(String[] args) {
		String[] A = { "abc", "def", "mno", "xyz", "pqr", "xyz", "def" };

		for (int i = 0; i < A.length - 1; i++) {
			for (int j = i + 1; j < A.length; j++) {
				if ((A[i].equals(A[j]))) {
					log.debug("Duplicate Element is : {}", A[j]);
				}
			}
		}
	}

}

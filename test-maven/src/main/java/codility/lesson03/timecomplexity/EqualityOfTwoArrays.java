package codility.lesson03.timecomplexity;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/how-to-check-the-equality-of-two-arrays-in-java/
 * How To Check The Equality Of Two Arrays In Java?
 */
public class EqualityOfTwoArrays {
	
	private static Logger log = LoggerFactory.getLogger(EqualityOfTwoArrays.class);
	
	public static void main(String[] args) {
		solution();
		solution2();
		solution3();		
		solution4();
	}
	
	// 1) Iterative Method
	public static void solution() {
		
		int[] A = { 2, 5, 1, 7, 4 };
		int[] B = { 2, 5, 1, 7, 4 };
		
		boolean equalOrNot = true;

		if (A.length == B.length) {
			for (int i = 0; i < A.length; i++) {
				if (A[i] != B[i]) {
					equalOrNot = false;
				}
			}
		} else {
			equalOrNot = false;
		}

		if (equalOrNot) {
			log.debug("Two Arrays Are Equal");
		} else {
			log.debug("Two Arrays Are Not equal");
		}
	}
	
	// 2) Using Arrays.equals() Method :
	public static void solution2() {		
		String[] s1 = { "java", "j2ee", "struts", "hibernate" };
		String[] s2 = { "jsp", "spring", "jdbc", "hibernate" };
		String[] s3 = { "java", "j2ee", "struts", "hibernate" };
        log.debug("{}", Arrays.equals(s1, s2));        //Output : false 
        log.debug("{}", Arrays.equals(s1, s3));      //Output : true
	}
	
	public static void solution3() {
		String[] s1 = {"java", "swings", "j2ee", "struts", "jsp", "hibernate"};		 
        String[] s2 = {"java", "struts", "j2ee", "hibernate", "swings", "jsp"};
        log.debug("{}", Arrays.equals(s1, s2));       //Output : false
        Arrays.sort(s1); 
        Arrays.sort(s2); 
        log.debug("{}", Arrays.equals(s1, s2));       //Output : true
	}
	
	// 3) Using Arrays.deepEquals() Method :
	public static void solution4() {
		String[][] s1 = { {"java", "swings", "j2ee" }, { "struts", "jsp", "hibernate"} };		 
        String[][] s2 = { {"java", "swings", "j2ee" }, { "struts", "jsp", "hibernate"} }; 
        log.debug("{}", Arrays.deepEquals(s1, s2));     //Output : true         
        //Calling equals() method on same arrays will return false         
        log.debug("{}", Arrays.equals(s1, s2));        //Output : false         
        //That's why use deepEquals() method to compare multidimensional arrays
	}
}
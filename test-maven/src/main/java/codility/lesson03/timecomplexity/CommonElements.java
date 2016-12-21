package codility.lesson03.timecomplexity;

import java.util.Arrays;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://javaconceptoftheday.com/java-program-to-find-common-elements-between-two-arrays/
 * Java Program To Find Common Elements Between Two Arrays
 */
public class CommonElements {
	
	private static Logger log = LoggerFactory.getLogger(CommonElements.class);

	public static void main(String[] args) {
		solution1();
		solution2();
	}
	
	private static void solution1() {
		String[] s1 = { "ONE", "TWO", "THREE", "FOUR", "FIVE", "FOUR" };
		String[] s2 = { "THREE", "FOUR", "FIVE", "SIX", "SEVEN", "FOUR" };

		HashSet<String> set = new HashSet<String>();
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (s1[i].equals(s2[j])) {
					set.add(s1[i]);
				}
			}
		}
		log.debug("{}", set); // OUTPUT : [THREE, FOUR, FIVE]
	}

	// retainAll 사용
	// This is one of the easiest method to find the common elements from two arrays. 
	// In this method, we create two HashSets using given two arrays and then use reatinAll() method of HashSet 
	// to retain only common elements from the two sets.
	private static void solution2() {
		Integer[] i1 = { 1, 2, 3, 4, 5, 4 };
		Integer[] i2 = { 3, 4, 5, 6, 7, 4 };
		HashSet<Integer> set1 = new HashSet<>(Arrays.asList(i1));
		HashSet<Integer> set2 = new HashSet<>(Arrays.asList(i2));
		set1.retainAll(set2);
		log.debug("{}", set1); // Output : [3, 4, 5]
	}

}

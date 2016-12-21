package codility.lesson03.timecomplexity;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * Java Program To Count Occurrences Of Each Character In String :
 */
public class EachCharCountInString {
	
	private static Logger log = LoggerFactory.getLogger(EachCharCountInString.class);

	public static void main(String[] args) {
		characterCount("Java J2EE Java JSP J2EE");
		characterCount("All Is Well");
		characterCount("Done And Gone");
	}

	private static void characterCount(String inputString) {
		// Creating a HashMap containing char as a key and occurrences as a
		// value
		inputString = inputString.replaceAll("\\s", ""); // 공백제거
		
		HashMap<Character, Integer> charCountMap = new HashMap<Character, Integer>();
		// Converting given string to char array
		char[] strArray = inputString.toCharArray();
		// checking each char of strArray
		
		for (char c : strArray) {
			if (charCountMap.containsKey(c)) {
				// If char is present in charCountMap, incrementing it's count
				// by 1
				charCountMap.put(c, charCountMap.get(c) + 1);
			} else {
				// If char is not present in charCountMap,
				// putting this char to charCountMap with 1 as it's value
				charCountMap.put(c, 1);
			}
		}
		// Printing the charCountMap
		log.debug("{}", charCountMap.toString());
	}
}
;
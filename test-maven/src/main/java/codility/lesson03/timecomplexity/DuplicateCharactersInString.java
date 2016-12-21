package codility.lesson03.timecomplexity;

import java.util.HashMap;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuplicateCharactersInString {

	private static Logger log = LoggerFactory.getLogger(DuplicateCharactersInString.class);
	
	public static void main(String[] args) {
		duplicateCharCount("JavaJ2EE");
		duplicateCharCount("Fresh Fish");
		duplicateCharCount("Better Butter");
	}

	public static void duplicateCharCount(String inputString) {
		// Creating a HashMap containing char as key and it's occurrences as
		// value
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
		// Getting a Set containing all keys of charCountMap
		Set<Character> charsInString = charCountMap.keySet();
		log.debug("Duplicate Characters In {}", inputString);
		// Iterating through Set 'charsInString'

		for (Character ch : charsInString) {
			if (charCountMap.get(ch) > 1) {
				// If any char has a count of more than 1, printing it's count
				log.debug("{} : {}", ch, charCountMap.get(ch));
			}
		}
	}
}

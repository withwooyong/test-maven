package codility.lesson03.timecomplexity;

import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnagramProgram {
	
	private static Logger log = LoggerFactory.getLogger(AnagramProgram.class);
	
	/*
	 * What Is Anagram?
	 * Two strings are called anagrams if they contain same set of characters but in different order. 
	 * For example, “Dormitory – Dirty Room”, “keep – peek”,  “School Master – The Classroom” are some anagrams.
	 * 공백제거 및 정렬 후 문자열이 같으면 Anagram 문자열 이라고 한다.(대소문자 구분안함.)
	 */
	public static void main(String[] args) {
		isAnagram("Mother In Law", "Hitler Woman");
		isAnagram("ABCD", "abcd");
//		isAnagram("keEp", "peeK");
//		isAnagram("SiLeNt CAT", "LisTen AcT");
//		isAnagram("Debit Card", "Bad Credit");
//		isAnagram("School MASTER", "The ClassROOM");
//		isAnagram("DORMITORY", "Dirty Room");
//		isAnagram("ASTRONOMERS", "NO MORE STARS");
//		isAnagram("Toss", "Shot");
//		isAnagram("joy", "enjoy");
	}

	// 1) Anagram Program In Java Using sort() and equals() Methods
	private static void isAnagram(String s1, String s2) {
		// Removing all white spaces from s1 and s2
		String copyOfs1 = s1.replaceAll("\\s", ""); // 공백제거 white spaces 
		String copyOfs2 = s2.replaceAll("\\s", "");
		
		boolean status = true;

		if (copyOfs1.length() != copyOfs2.length()) {
			status = false;
		} else {
			char[] s1Array = copyOfs1.toLowerCase().toCharArray();
			char[] s2Array = copyOfs2.toLowerCase().toCharArray();

			// Sorting both s1Array and s2Array
			Arrays.sort(s1Array);
			Arrays.sort(s2Array);

			// Checking whether s1Array and s2Array are equal
			status = Arrays.equals(s1Array, s2Array);
		}

		if (status) {
			log.debug("{} and {} are anagrams", s1, s2);
		} else {
			log.debug("{} and {} are not anagrams", s1, s2);
		}
	}	
	
	// 2) Anagram Program In Java Using Iterative Method
	private static void isAnagram2(String s1, String s2) {
		String copyOfs1 = s1.replaceAll("\\s", "").toLowerCase();
		String copyOfs2 = s2.replaceAll("\\s", "").toLowerCase();
		boolean status = true;

		if (copyOfs1.length() != copyOfs2.length()) {
			status = false;
		} else {
			char[] s1ToArray = copyOfs1.toCharArray();
			
			for (char c : s1ToArray) {
				int index = copyOfs2.indexOf(c);
				if (index != -1) {
					copyOfs2 = copyOfs2.substring(0, index) + copyOfs2.substring(index + 1, copyOfs2.length());
				} else {
					status = false;
					break;
				}
			}
		}
		
		if (status) {
			log.debug("{} and {} are anagrams", s1, s2);
		} else {
			log.debug("{} and {} are not anagrams", s1, s2);
		}
	}
	
	// 3) Anagram Program In Java Using StringBuilder
	private static void isAnagram3(String s1, String s2) {
		String copyOfs1 = s1.replaceAll("\\s", "").toLowerCase();
		String copyOfs2 = s2.replaceAll("\\s", "").toLowerCase();
		boolean status = true;
		if (copyOfs1.length() != copyOfs2.length()) {
			status = false;
		} else {
			char[] s1Array = copyOfs1.toCharArray();
			StringBuilder sb = new StringBuilder(copyOfs2);

			for (char c : s1Array) {
				int index = sb.indexOf("" + c);

				if (index != -1) {
					sb = sb.deleteCharAt(index);
				} else {
					status = false;
					break;
				}
			}
		}
		if (status) {
			log.debug("{} and {} are anagrams", s1, s2);
		} else {
			log.debug("{} and {} are not anagrams", s1, s2);
		}
	}
	
	// 4) Anagram Program In Java Using HashMap
	private static void isAnagram4(String s1, String s2) {
		
		String copyOfs1 = s1.replaceAll("\\s", "").toLowerCase();
		String copyOfs2 = s2.replaceAll("\\s", "").toLowerCase();
		boolean status = true;

		if (copyOfs1.length() != copyOfs2.length()) {
			status = false;
		} else {
			HashMap<Character, Integer> map = new HashMap<Character, Integer>();

			for (int i = 0; i < copyOfs1.length(); i++) {
				char charAsKey = copyOfs1.charAt(i);
				int charCountAsValue = 0;
				if (map.containsKey(charAsKey)) {
					charCountAsValue = map.get(charAsKey);
				}
				map.put(charAsKey, ++charCountAsValue);
				charAsKey = copyOfs2.charAt(i);
				charCountAsValue = 0;
				if (map.containsKey(charAsKey)) {
					charCountAsValue = map.get(charAsKey);
				}
				map.put(charAsKey, --charCountAsValue);
			}
			
			for (int value : map.values()) {
				if (value != 0) {
					status = false;
				}
			}
		}
		
		if (status) {
			log.debug("{} and {} are anagrams", s1, s2);
		} else {
			log.debug("{} and {} are not anagrams", s1, s2);
		}
	}
}

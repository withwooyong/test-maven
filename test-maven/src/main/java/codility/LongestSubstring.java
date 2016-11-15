package codility;

/**
 * Longest Substring Without Repeating Characters
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 * Subscribe to see which companies asked this question
 * 
 * @author wooyong
 *
 */
public class LongestSubstring {

	public static void main(String[] args) {
		
		String s = "abcabcbb";
		lengthOfLongestSubstring(s);
	}

	public static int lengthOfLongestSubstring(String s) {
		
		boolean[] cache = new boolean[255];
		int count = 0;
		int maxCount = 0;
		int head = 0, tail = 0;
		while (head < s.length()) {
			if (cache[s.charAt(head)] != true) {
				cache[s.charAt(head)] = true;
				maxCount = Math.max(head - tail + 1, maxCount);
				head++;
			} else {
				cache[s.charAt(tail)] = false;
				tail++;
			}
		}

		return maxCount;
	}

}

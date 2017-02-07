package codility.solution.test;

import java.util.Arrays;

/*
 * 
 * You are given a string S consisting of N alphanumerical characters.
 * You would like to find the longest substring of S that is a valid password.
 * returns the length of its longest substring that is a valid password
 */
public class Task02 {

	public static void main(String[] args) {
		//String S = "a0Ba"; // 2
		String S = "a0bb"; // -1
		System.out.println(solution(S));
	}
	
	// http://stackoverflow.com/questions/39388558/java-find-the-longest-substring-without-any-number-and-at-least-one-upper-case
	private static int solution(String S) {
		int answer = Arrays.stream(S.split("[0-9]+"))
	    .filter(s -> s.matches("(.+)?[A-Z](.+)?"))
	    .sorted((s1, s2) -> s2.length() - s1.length())
	    .findFirst()
	    .orElse("")
	    .length();
		return answer > 0 ? answer : -1;
	}
	
	// https://munibsiddiqui.wordpress.com/
}

package codility.lesson09.maximumsliceproblem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Permutations {
	
	private static Logger log = LoggerFactory.getLogger(Permutations.class);

	public static void main(String[] args) {
		//int n = Integer.parseInt(args[0]);
		String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String elements = alphabet.substring(0, 3); // abc
		perm1(elements);
		perm2(elements);
		int[] A = { 1, 2, 3 }; 
		perm3(A, A.length);
	}

	// print n! permutation of the characters of the string s (in order)
	public static void perm1(String s) {
		perm1("", s);
	}

	private static void perm1(String prefix, String s) {
		int n = s.length();
		if (n == 0) {
			log.debug("{}", prefix);
		} else {
			for (int i = 0; i < n; i++) {
				perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
			}
		}

	}

	// print n! permutation of the elements of array a (not in order)
	public static void perm2(String s) {
		int n = s.length();
		char[] a = new char[n];
		for (int i = 0; i < n; i++) {
			a[i] = s.charAt(i);
		}
		perm2(a, n);
	}

	private static void perm2(char[] a, int n) {
		if (n == 1) {
			log.debug("{}", a);
			return;
		}
		for (int i = 0; i < n; i++) {
			swap(a, i, n - 1);
			perm2(a, n - 1);
			swap(a, i, n - 1);
		}
	}
	
	private static void perm3(int[] A, int n) {
		if (n == 1) {
			log.debug("{}", Arrays.toString(A));
			return;
		}
		for (int i = 0; i < n; i++) {
			swap(A, i, n - 1);
			perm3(A, n - 1);
			swap(A, i, n - 1);
		}
	}

	// swap the characters at indices i and j
	private static void swap(char[] a, int i, int j) {
		char c = a[i];
		a[i] = a[j];
		a[j] = c;
	}
	
	private static void swap(int[] a, int i, int j) {
		int c = a[i];
		a[i] = a[j];
		a[j] = c;
	}
}

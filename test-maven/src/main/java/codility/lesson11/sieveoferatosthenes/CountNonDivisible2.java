package codility.lesson11.sieveoferatosthenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CountNonDivisible
 * Calculate the number of elements of an array that are not divisors of each element.
 * 
 * You are given a non-empty zero-indexed array A consisting of N integers.
 * For each number A[i] such that 0 ≤ i < N, 
 * we want to count the number of elements of the array that are not the divisors of A[i]. 
 * We say that these elements are non-divisors.
 * 
 * For example, consider integer N = 5 and array A such that:
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * For the following elements:
 * 
 * A[0] = 3, the non-divisors are: 2, 6,
 * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 * A[2] = 2, the non-divisors are: 3, 3, 6,
 * A[3] = 3, the non-divisors are: 2, 6,
 * A[4] = 6, there aren't any non-divisors.
 * 
 * Write a function:
 * class Solution { public int[] solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, 
 * returns a sequence of integers representing the amount of non-divisors.
 * The sequence should be returned as:
 * 
 * For example, given:
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * the function should return [2, 4, 3, 2, 0], as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..50,000];
 * each element of array A is an integer within the range [1..2 * N].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class CountNonDivisible2 {
	
	private static Logger log = LoggerFactory.getLogger(CountNonDivisible2.class);

	/*
	 * Calculate the number of elements of an array that are not divisors of each element.
	 */
	public static void main(String[] args) {
		int[] A = { 3, 1, 2, 3, 6 };
		
		int B[] = solution(A);
		log.debug("Input={}", Arrays.toString(A));
		log.debug("result={}", Arrays.toString(B));
	}

	public static int[] solution(int[] A) {
		Set<Integer> setA = asSet(A);
		List<Set<Integer>> divisors = computeDivisors(A.length * 2);
		int occurrences[] = computeOccurrences(A);
		int nonDivisors[] = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			Set<Integer> d = divisors.get(value);
			int totalOccurances = 0;
			for (Integer divisor : d) {
				if (setA.contains(divisor)) {
					totalOccurances += occurrences[divisor];
				}
			}
			nonDivisors[i] = A.length - totalOccurances;
		}
		return nonDivisors;
	}

	/**
	 * Returns a set containing all elements of the given array
	 * 
	 * Space: O(N) Time: O(N)
	 * 
	 * @param A
	 *            The input array
	 * @return The set
	 */
	private static Set<Integer> asSet(int A[]) {
		Set<Integer> result = new HashSet<Integer>();
		for (int value : A) {
			result.add(value);
		}
		return result;
	}

	/**
	 * Computes a list that contains for each i in [0...maxValue+1] a set with
	 * all divisors of i. This is basically an "Eratosthenes Sieve". But in
	 * addition to setting the entries of a list to 'false' (indicating that the
	 * respective numbers are non-prime), this methods inserts the divisors into
	 * the corresponding set.
	 * 
	 * Space: O(N) (?) Time: O(N*logN) (?)
	 * 
	 * @param maxValue
	 *            The maximum value
	 * @return The list
	 */
	private static List<Set<Integer>> computeDivisors(int maxValue) {
		List<Boolean> prime = new ArrayList<Boolean>();
		prime.addAll(Collections.nCopies(maxValue + 1, Boolean.TRUE));
		List<Set<Integer>> divisors = new ArrayList<Set<Integer>>();
		for (int i = 0; i < maxValue + 1; i++) {
			Set<Integer> d = new HashSet<Integer>();
			d.add(1);
			d.add(i);
			divisors.add(d);
		}
		for (int i = 2; i <= maxValue; i++) {
			int next = i + i;
			while (next <= maxValue) {
				divisors.get(next).addAll(divisors.get(i));
				prime.set(next, Boolean.FALSE);
				next += i;
			}
		}
		return divisors;
	}

	/**
	 * Computes an array of length 2*A.length+1, where each entry i contains the
	 * number of occurrences of value i in array A
	 * 
	 * Space: O(N) Time: O(N)
	 * 
	 * @param A
	 *            The input array
	 * @return The occurrences array
	 */
	private static int[] computeOccurrences(int A[]) {
		int occurances[] = new int[A.length * 2 + 1];
		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			occurances[value]++;
		}
		return occurances;
	}
}

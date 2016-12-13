package codility.lesson11.sieveoferatosthenes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * https://stackoverflow.com/questions/21243729/countnondivisible-codility-training-task
 */
public class CountNonDivisible2 {
	
	private static Logger log = LoggerFactory.getLogger(CountNonDivisible2.class);

	/*
	 * Calculate the number of elements of an array that are not divisors of each element.
	 */
	public static void main(String[] args) {
		int[] A = { 3, 1, 2, 3, 6 };

		int B[] = solution(A); // [2, 4, 3, 2, 0]
		log.debug("Input={}", Arrays.toString(A));
		log.debug("result={}", Arrays.toString(B));
	}

	public static int[] solution(int[] A) {
		Set<Integer> setA = new HashSet<Integer>();
		for (int value : A) {
			setA.add(value);
		}
		
		int maxValue = A.length * 2;
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

		int occurrences[] = new int[A.length * 2 + 1];
		for (int i = 0; i < A.length; i++) {
			int value = A[i];
			occurrences[value]++;
		}

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
}

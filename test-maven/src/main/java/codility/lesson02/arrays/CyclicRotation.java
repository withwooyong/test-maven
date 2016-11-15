package codility.lesson02.arrays;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A zero-indexed array A consisting of N integers is given. Rotation of the array means that each element is shifted right by one index, and the last element of the array is also moved to the first place.

For example, the rotation of array A = [3, 8, 9, 7, 6] is [6, 3, 8, 9, 7]. The goal is to rotate array A K times; that is, each element of A will be shifted to the right by K indexes.

Write a function:

class Solution { public int[] solution(int[] A, int K); }
that, given a zero-indexed array A consisting of N integers and an integer K, returns the array A rotated K times.

For example, given array A = [3, 8, 9, 7, 6] and K = 3, the function should return [9, 7, 6, 3, 8].

Assume that:

N and K are integers within the range [0..100];
each element of array A is an integer within the range [−1,000..1,000].
In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 * @author wooyong
 *
 */
public class CyclicRotation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}
	
	public static int[] mySolution(int[] A,int K) {
		int size = A.length;
		if (K > 100 || K < 0 || size > 100 || size == 0) {
			return new int[]{};
		}
		
		int[] ret = new int[size];
		
		for (int c = 0; c < size; c++) {
			ret[(K + c) % size] = A[c];
		}
		return ret;
	}
	
	public int solution(int[] A) {
		Arrays.sort(A);
		int i;
		int res = 0;
		for (i = 0; i < A.length; i++) {
			res = res ^ A[i]; // Implementation of XOR
		}
		return res;
	}
	
	public int solutionSet(int[] A) {
		Set<Integer> set = new HashSet<>();
		
		for (int i : A) {
			if (set.contains(i)) {
				set.remove(i);
			} else {
				set.add(i);
			}
		}
		return set.iterator().next();
	}
	
	public int solutionJava8(int[] A) {
		return java.util.Arrays.stream(A).reduce(0, (a, b) -> a ^ b);
	}
}

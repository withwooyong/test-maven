package codility.lesson10.primeandcompositenumbers;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Flags
 * Find the maximum number of flags that can be set on mountain peaks.
 * 
 * A non-empty zero-indexed array A consisting of N integers is given.
 * A peak is an array element which is larger than its neighbours. 
 * More precisely, it is an index P such that 0 < P < N − 1 and A[P − 1] < A[P] > A[P + 1].
 * 
 * For example, the following array A:
 * A[0] = 1
 * A[1] = 5
 * A[2] = 3
 * A[3] = 4
 * A[4] = 3
 * A[5] = 4
 * A[6] = 1
 * A[7] = 2
 * A[8] = 3
 * A[9] = 4
 * A[10] = 6
 * A[11] = 2
 * has exactly four peaks: elements 1, 3, 5 and 10.
 * 
 * You are going on a trip to a range of mountains whose relative heights are represented by array A, as shown in a figure below. 
 * You have to choose how many flags you should take with you. 
 * 
 * The goal is to set the maximum number of flags on the peaks, according to certain rules.
 * 
 * Flags can only be set on peaks. 
 * What's more, if you take K flags, then the distance between any two flags should be greater than or equal to K. 
 * The distance between indices P and Q is the absolute value |P − Q|.
 * 
 * For example, given the mountain range represented by array A, above, with N = 12, 
 * if you take:
 * two flags, you can set them on peaks 1 and 5;
 * three flags, you can set them on peaks 1, 5 and 10;
 * four flags, you can set only three flags, on peaks 1, 5 and 10.
 * You can therefore set a maximum of three flags in this case.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A of N integers, 
 * returns the maximum number of flags that can be set on the peaks of the array.
 * 
 * For example, the following array A:
 * the function should return 3, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..400,000];
 * each element of array A is an integer within the range [0..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * 
 * @author wooyong
 *
 */
//Score: 100/14
public class Flags {
	
	private static Logger log = LoggerFactory.getLogger(Flags.class);
	/*
	 * Find the maximum number of flags that can be set on mountain peaks.
	 */
	public static void main(String[] args) {
		int[] A = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		log.debug("{}", solution(A)); // return 3
	}
	
	private static int solution(int[] A) {

		log.debug("A={}", Arrays.toString(A));
		
		ArrayList<Integer> array = new ArrayList<Integer>();
		
		int[] peaks = new int[A.length];
		
		// 처음과 끝은 peak 가 될수 없다. i = 1, A.length -1
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks[i] = 1;
				array.add(i);
			}
		}
		//     A=[1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]
		// peaks=[0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0]
		
		if (array.size() < 2) { // 깃발 하나만 꼽음.
			return array.size();
		}
		log.debug("{}", array.toString()); // [1, 3, 5, 10] 
		log.debug("peaks={}", Arrays.toString(peaks));
		
		ArrayList<Integer> list = new ArrayList<>();
		int startPosition = 0;
		int endPosition = 0;
		int position = 0;
		
		for (int i = 0; i < peaks.length; i++) {
			log.debug("i={} position={} peaks[i]={} start={} end={} list={}", i, position, peaks[i], startPosition, endPosition, list.toString());
			if (peaks[i] == 1) {
				if (position == 0) { // 첫번째 깃발
					list.add(i);
					position = i;
				} else if (i - position >= array.size()) {
					list.add(i);
					position = i;
				} else if (i - position < array.size()) {
					endPosition = i;
				}
			} 
		}
		return list.size();
	}
}

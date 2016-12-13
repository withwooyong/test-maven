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
//		log.debug("{}", solution(A)); // return 3
//		log.debug("{}", solution2(A)); // return 3
//		log.debug("{}", mySolution(A)); // return 3
		//test();
		int[] B = { 1, 3, 5, 10 };
		search(B, B.length);
	}
	
	public static void test() {
		int i = 0;
		for (; i < 10; i++) {
			log.debug("{}", i);
		}
	}
	
	// BruteForceSearch.java 참고해야 함. 하다말았음.
	// O(nlogn) lowIndex = 0, highIndex = n-1 을 관리
	// 합이 K와 같다면 해답, 작으면 hiIndex 감소, 크면 lowIndex 증가
	public static void search(int A[], int K) {
		log.debug("{} {}", Arrays.toString(A), K);
		int lowIndex = 0;
		int highIndex = A.length-1;
		int temp = 0;
		Arrays.sort(A);
		
		while (lowIndex < highIndex) {
			temp = Math.abs(A[lowIndex] - A[highIndex]);
			log.debug("{} {} {}", temp, lowIndex, highIndex);
			
			if (temp >= K) {
				log.debug("{} {} {}", A[lowIndex], A[highIndex], K);
				break;
			} else if (temp < K) {
				lowIndex++;
			} 
		}
		log.debug("{} {} {}", temp, lowIndex, highIndex);
	}
	
	/*
	 * find the peak array first
	 * use binary to find the flag number, start = 1, end = array.size(). make flag = (start + end) / 2;
	 * time O( nlgn).
	 * tips: pay attention to sf and ef , used flag
	 */
	public static int solution(int[] A) {
		ArrayList<Integer> array = new ArrayList<Integer>();
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i - 1] < A[i] && A[i + 1] < A[i]) {
				array.add(i);
			}
		}
		
		if (array.size() == 1 || array.size() == 0) {
			return array.size();
		}
		
		int sf = 1;
		int ef = array.size();
		int result = 1;
		
		while (sf <= ef) {
			int flag = (sf + ef) / 2;
			boolean suc = false;
			int used = 0;
			int mark = array.get(0);
			for (int i = 0; i < array.size(); i++) {
				if (array.get(i) >= mark) {
					used++;
					mark = array.get(i) + flag;
					if (used == flag) {
						suc = true;
						break;
					}
				}
			}
			if (suc) {
				result = flag;
				sf = flag + 1;
			} else {
				ef = flag - 1;
			}
		}
		return result;
	}
	
	// 이해안됬음.
	public static int mySolution(int[] A) {
		int N = A.length;
		ArrayList<Integer> peaks = new ArrayList<Integer>();
		for (int i = 1; i < N - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks.add(i);
			}
		}
		
		int size = peaks.size();
		int[] flags = new int[peaks.size()];
		int position = 0;
		
		while (position >= size) {
			
		}
		
		for (int i = 1; i < size; i++) {
			if (Math.abs(peaks.get(i-1) - peaks.get(i-1)) >= size) {
				
			}
		}
		log.debug("{}", Arrays.toString(flags));
		return 0;
	}
	
	public static int solution2(int[] A) {
		log.debug("{}", Arrays.toString(A));
		int N = A.length;

		// Find all the peaks
		ArrayList<Integer> peaks = new ArrayList<Integer>();
		for (int i = 1; i < N - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1])
				peaks.add(i);
		}

		log.debug("{}", peaks.toString());
		
		for (int i = 1; i <= N; i++) {
//			if (N % i != 0) {
//				continue; // skip if non-divisible
//			}
			int find = 0;
			int groups = N / i;
			boolean ok = true;
			// Find whether every group has a peak
			for (int j : peaks) {
				log.debug("i={} j={} j/i={} find={} groups={}", i, j, j/i, find, groups);
				if (j / i > find) {
					ok = false;
					break;
				}
				
				if (j / i == find) {
					find++;
				}
				
			}
			log.debug("find={} groups={}", find, groups);
			if (find != groups) {
				ok = false;
			}
			if (ok) {
				return groups;
			}
		}
		return 0;
	}
	
	
}

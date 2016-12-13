package codility.lesson10.primeandcompositenumbers;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Peaks
 * Divide an array into the maximum number of same-sized blocks, 
 * each of which should contain an index P such that A[P - 1] < A[P] > A[P + 1].
 * 
 * A non-empty zero-indexed array A consisting of N integers is given.
 * 
 * A peak is an array element which is larger than its neighbors.
 * More precisely, 
 * it is an index P such that 0 < P < N − 1,  A[P − 1] < A[P] and A[P] > A[P + 1].
 * 
 * For example, the following array A:
 * A[0] = 1
 * A[1] = 2
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
 * has exactly three peaks: 3, 5, 10.
 * 
 * We want to divide this array into blocks containing the same number of elements. 
 * More precisely, 
 * we want to choose a number K that will yield the following blocks:
 * 
 * A[0], A[1], ..., A[K − 1],
 * A[K], A[K + 1], ..., A[2K − 1],
 * ...
 * A[N − K], A[N − K + 1], ..., A[N − 1].
 * What's more, every block should contain at least one peak. 
 * Notice that extreme elements of the blocks (for example A[K − 1] or A[K]) can also be peaks, 
 * but only if they have both neighbors (including one in an adjacent blocks).
 * 
 * The goal is to find the maximum number of blocks into which the array A can be divided.
 * Array A can be divided into blocks as follows:
 * one block (1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2). This block contains three peaks.
 * two blocks (1, 2, 3, 4, 3, 4) and (1, 2, 3, 4, 6, 2). Every block has a peak.
 * three blocks (1, 2, 3, 4), (3, 4, 1, 2), (3, 4, 6, 2). Every block has a peak. 
 * 
 * Notice in particular that the first block (1, 2, 3, 4) has a peak at A[3], 
 * because A[2] < A[3] > A[4], even though A[4] is in the adjacent block.
 * 
 * However, array A cannot be divided into four blocks, (1, 2, 3), (4, 3, 4), (1, 2, 3) and (4, 6, 2), 
 * because the (1, 2, 3) blocks do not contain a peak. 
 * Notice in particular that the (4, 3, 4) block contains two peaks: A[3] and A[5].
 * 
 * The maximum number of blocks that array A can be divided into is three.
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, 
 * returns the maximum number of blocks into which A can be divided.
 * 
 * If A cannot be divided into some number of blocks, the function should return 0.
 * For example, given:
 * A[0] = 1
 * A[1] = 2
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
 * the function should return 3, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [0..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(log(N)));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class Peaks {
	
	private static Logger log = LoggerFactory.getLogger(Peaks.class);

	/*
	 * Divide an array into the maximum number of same-sized blocks, each of which should contain an index P such that A[P - 1] < A[P] > A[P + 1].
	 */
	// 이해안감.
	public static void main(String[] args) {
		int[] N = new int[] { 1, 2, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		log.debug("{}", solution(N));
		log.debug("{}", mySolution(N));
//		test();
	}
	
	public static void test() {
		for (int i = 0; i < 3; i++) {
			if (i == 1) {
				break;
			}
			log.debug("break={}", i);
		}
		for (int i = 0; i < 3; i++) {
			if (i == 1) {
				continue;
			}
			log.debug("continue={}", i);
		}
	}
	
	public static int mySolution(int[] A) {
		int N = A.length;
		ArrayList<Integer> peaks = new ArrayList<Integer>();
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks.add(i); // 좌, 우 값 체크해서 peaks 값을 찾는다.
			}
		}
		log.debug("size={} peaks={}", peaks.size(), peaks.toString());
		
		// 소수, 합성수
		// We want to divide this array into blocks containing the same number of elements.
		for (int i = 1; i <= N; i++) {  
			int blocks = N / i; // 블럭 1개 2개, 3개
			log.debug("blocks={} N={} i={}", blocks, N, i);
			if (N % i == 0 || blocks <= peaks.size()) { // 나누어서 0으로 떨어져야 same number of elements
				boolean ok = true;
				int threshold = 0;
				for (int j = 0; j < peaks.size(); j++) {					
					if (peaks.get(j) / i > threshold) {						
						ok = false;
						break;
					} else if (peaks.get(j) / i == threshold) {
						threshold++;
					}
				}
				log.debug("blocks={} threshold={}", blocks, threshold);
				if (threshold != blocks) {
					ok = false;
				}
				if (ok) {
					//log.debug("blocks={}", blocks);
					return blocks;
				}
			}
		}
		return 0;
	}

 	public static int solution(int[] A) {
		
		int peakCount = 0;
		
		ArrayList<Integer> peaks = new ArrayList<Integer>();
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks.add(i); // 좌, 우 값 체크해서 peaks 값을 찾는다.
				peakCount++;
			}
		}
		
		for (int size = 1; size <= A.length; size++) {
			int blocks = A.length / size;
			if (A.length % size != 0 || blocks > peakCount)
				continue;

			boolean ok = true;
			int threshold = 0;
			for (int j = 0; j < peaks.size(); j++) {
				if (peaks.get(j) / size > threshold) {
					ok = false;
					break;
				}
				if (peaks.get(j) / size == threshold)
					threshold++;
			}

			if (threshold != blocks)
				ok = false;
			if (ok)
				return blocks;
		}
		return 0;
	}
}

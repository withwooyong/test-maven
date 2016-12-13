package codility.lesson10.primeandcompositenumbers;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


// https://codility.com/demo/results/training97DBAE-GKV/
public class Flags3 {
	
	private static Logger log = LoggerFactory.getLogger(Flags3.class); 

	public static void main(String[] args) {
		int[] A = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		log.debug("{}", solution(A));
	}
	
	private static int solution(int[] A) {

		log.debug("A={}", Arrays.toString(A));
		int count = 0;
		int[] peaks = new int[A.length];
		int[] next = new int[A.length];
		
		next[A.length - 1] = -1;
		
		// 처음과 끝은 peak 가 될수 없다. i = 1, A.length -1
		for (int i = 1; i < A.length - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks[i] = 1;
				count++;
			}
			
			if (peaks[i - 1] == 1) {
				next[i - 1] = i;
			} else {
				next[i - 1] = next[i];
			}
		}
		//     A=[1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2]
		// peaks=[0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0] 
		//  next=[1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1, -1], total=4
		// peaks=[0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0] 
		//  next=[1, 3, 3, 5, 5, 10, 10, 10, 10, 10, -1, -1], total=3
		
		if (next.length > 1) {
			if (peaks[1] == 1) {
				next[0] = 1;
			} else {
				next[0] = next[1];
			}
		}		
		
		if (count < 2) {
			return count;
		}
		// int maxD = (int) Math.sqrt(N); // 어떤 수 x를 제곱하여 a가 되었을 때에, x를 a의 제곱근이라고 한다.
		int max = (int)Math.min(count, Math.sqrt(A.length)) + 1;
		
		for (int j = max; j > 0; j--) {
			if (check(peaks, next, j)) {
				return j;
			}
		}

		return 0;
	}

	public static boolean check(int[] peaks, int[] next, int total) {
		log.debug("peaks={} next={}, total={}", Arrays.toString(peaks), Arrays.toString(next), total);
		
		int count = 1;
		int base = next[0];
		int j = next[base];

		while (j < peaks.length && j > 0) {
			if (j - base >= total) {
				count++;
				base = j;
				j = next[base];
			} else {
				j = next[j];
			}
		}

		return count >= total;
	}

}

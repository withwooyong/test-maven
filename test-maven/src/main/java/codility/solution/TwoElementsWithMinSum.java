package codility.solution;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwoElementsWithMinSum {

	private static Logger log = LoggerFactory.getLogger(TwoElementsWithMinSum.class);
	
	public static void main(String[] args) {
		int[] A = { 1, 60, -10, 70, -80, 85 }; // -80, 85
		//solution(A);
		solution2(A);

	}
	
	// O(n2) 합이 0에 가장 가까운 두 항목 
	private static void solution(int[] A) {
		log.debug("{} {}", Arrays.toString(A), A.length);
		int sum = 0;
		int iIndex = 0;
		int jIndex = 0;
		int min = Integer.MAX_VALUE;
		log.debug("{}", min);
		
		for (int i = 0; i < A.length-1; i++) {
			for (int j = 1; j < A.length; j++) {
				sum = Math.abs(A[i] + A[j]);
				if (sum < min) {
					log.debug("sum={} min={}", sum, min);
					min = sum;
					iIndex = i;
					jIndex = j;
				}
			}
		}
		log.debug("{} {} {}", A[iIndex], A[jIndex], min);
	}
	
	// O(nlogn) 합이 0에 가장 가까운 두 항목 (못풀었음.)
	private static void solution2(int[] A) {
		log.debug("{} {}", Arrays.toString(A), A.length);
		Arrays.sort(A);
		int sum = 0;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < A.length-1; i++) {
			sum = A[i] + A[i+1];
			if (min > Math.abs(sum)) {
				min = sum;
			}
		}
		log.debug("{}", min);
	}

}

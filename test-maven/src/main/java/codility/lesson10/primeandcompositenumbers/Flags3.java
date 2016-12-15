package codility.lesson10.primeandcompositenumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
			log.debug("i={} {} start={} end={} list={}", i, peaks[i], startPosition, endPosition, list.toString());
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

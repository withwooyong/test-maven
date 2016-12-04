package codility.solution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DutchNationalFlag {

	private static Logger log = LoggerFactory.getLogger(DutchNationalFlag.class);
	public static void main(String[] args) {
		int[] A = { 12, 34, 45, 9, 8, 90, 3 }; // 12, 34, 90, 8, 9, 45, 3
		solution(A);
	}
	
	// 짝수와 홀수 구분하기
	private static void solution(int[] A) {
		
		List<Integer> positive = new ArrayList<>();
		List<Integer> negative = new ArrayList<>();
		
		for (int i = 0; i < A.length; i++) {
			if ((A[i] % 2) == 0) {
				positive.add(A[i]);
			} else {
				negative.add(A[i]);
			}
		}
		log.debug("{} {}", positive.toString(), negative.toString());
		positive.addAll(negative);
		log.debug("{}", positive.toString());
		Collections.sort(positive);
		log.debug("{}", positive.toString());
	}

}

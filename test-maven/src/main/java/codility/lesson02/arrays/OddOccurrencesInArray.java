package codility.lesson02.arrays;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. 
 * The array contains an odd number of elements, and each element of the array can be paired with another element that has the same value, 
 * except for one element that is left unpaired.
 * 
 * For example, in array A such that:
 *   A[0] = 9  
 *   A[1] = 3  
 *   A[2] = 9
 *   A[3] = 3  
 *   A[4] = 9  
 *   A[5] = 7
 *   A[6] = 9
 *   
 * the elements at indexes 0 and 2 have value 9,
 * the elements at indexes 1 and 3 have value 3,
 * the elements at indexes 4 and 6 have value 9,
 * the element at index 5 has value 7 and is unpaired.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * 
 * that, given an array A consisting of N integers fulfilling the above conditions, returns the value of the unpaired element.
 * 
 * For example, given array A such that:
 * 
 * the function should return 7, as explained in the example above.
 * 
 * Assume that:
 * N is an odd integer within the range [1..1,000,000];
 * each element of array A is an integer within the range [1..1,000,000,000];
 * all but one of the values in A occur an even number of times.
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class OddOccurrencesInArray {

	private static Logger log = LoggerFactory.getLogger(OddOccurrencesInArray.class);
	
	/*
	 * Find value that occurs in odd number of elements.
	 */
	// 배열 속에서 짝이 없는 요소의 값을 리턴.
	public static void main(String[] args) {
		int[] A = { 9, 3, 9, 3, 9, 7, 9 };
		
		log.debug("{}", mySolution(A));
		log.debug("{}", solution2(A));
	}
	
	// O(n)
	public static int solution2(int[] A) {
		TreeSet<Integer> unpaired = new TreeSet<>();
	    for (int i : A) {
	        if (unpaired.remove(i)) {
	            continue;
	        }
	        unpaired.add(i);
	    }
	    return unpaired.first();
	}
	
	public static int mySolution(int[] A) {
		// N is an odd integer within the range [1..1,000,000];
		// each element of array A is an integer within the range [1..1,000,000,000];
		// all but one of the values in A occur an even number of times.
		int result = 0;
		System.out.println(Arrays.toString(A));
		
		Map<Integer, Integer> map = new HashMap<>();
		
		//O(N); // 중복 = 0 중복아님 = 1
		for (int i = 0; i < A.length; i++) {
			if (map.containsKey(A[i])) { // 중복이면
				map.put(A[i], 0);
			} else {
				map.put(A[i], 1); // 중복아니면
			}
		}
		
		Set<Integer> set = map.keySet();
		for (Integer integer : set) {
			if (map.get(integer) == 1) {
				return integer;
			}
		}
		return result;
	}
	
	public static void solution(int[] A) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            if (map.containsKey(A[i])) {
                map.put(A[i], map.get(A[i]) + 1);
            } else {
                map.put(A[i], 1);
            }
        }
        Integer v = null;
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            if (m.getValue() % 2 != 0) {
                v = m.getKey();
                break;
            }
        }
        
	}
}

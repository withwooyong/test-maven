package codility.lesson05.prefixsums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Problem: You are given a non-empty, zero-indexed array A of n (1 <= n <= 100 000) integers a0, a1, . . . , an−1 (0 <= ai <= 1 000). 
 * This array represents number of mushrooms growing on the consecutive spots along a road. 
 * You are also given integers k and m (0 <= k,m < n).
 * A mushroom picker is at spot number k on the road and should perform m moves. 
 * In one move she moves to an adjacent spot. 
 * She collects all the mushrooms growing on spots she visits. 
 * The goal is to calculate the maximum number of mushrooms that the mushroom picker can collect in m moves.
 * 
 * For example, consider array A such that:
 * 2 3 7 5 1 3 9
 * The mushroom picker starts at spot k = 4 and should perform m = 6 moves. 
 * She might move to spots 3, 2, 3, 4, 5, 6 and thereby collect 1+5+7+3+9 = 25 mushrooms. 
 * This is the maximal number of mushrooms she can collect.
 * 
 * @author wooyong
 *
 */
public class Prefixsums {
	
	private static Logger log = LoggerFactory.getLogger(Prefixsums.class);

	// pdf 문제 이해안됨.
	public static void main(String[] args) {
		int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		int k = 4; // 0 <= k
		int m = 6; // m < n (A.length)
		solution(A, k, m);
	}	

	private static void solution(int[] A, int k, int m) {
		// int[] A = { 2, 3, 7, 5, 1, 3, 9 };
		//  k and m (0 <= k, m < n). 3, 2, 3, 4, 5, 6  1+5+7+3+9 = 25
		int sum = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = k; j < m; j++) {
				sum += A[j];
				log.debug("{}", sum);
			}
		}
		log.debug("{}", sum);
	}
	
}

package codility.lesson11.sieveoferatosthenes;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * CountNonDivisible
 * Calculate the number of elements of an array that are not divisors of each element.
 * 
 * You are given a non-empty zero-indexed array A consisting of N integers.
 * For each number A[i] such that 0 ≤ i < N, 
 * we want to count the number of elements of the array that are not the divisors of A[i]. 
 * We say that these elements are non-divisors.
 * 
 * For example, consider integer N = 5 and array A such that:
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * For the following elements:
 * 
 * A[0] = 3, the non-divisors are: 2, 6,
 * A[1] = 1, the non-divisors are: 3, 2, 3, 6,
 * A[2] = 2, the non-divisors are: 3, 3, 6,
 * A[3] = 3, the non-divisors are: 2, 6,
 * A[4] = 6, there aren't any non-divisors.
 * 
 * Write a function:
 * class Solution { public int[] solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, 
 * returns a sequence of integers representing the amount of non-divisors.
 * The sequence should be returned as:
 * 
 * For example, given:
 * A[0] = 3
 * A[1] = 1
 * A[2] = 2
 * A[3] = 3
 * A[4] = 6
 * the function should return [2, 4, 3, 2, 0], as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..50,000];
 * each element of array A is an integer within the range [1..2 * N].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N*log(N));
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class CountNonDivisible {
	
	private static Logger log = LoggerFactory.getLogger(CountNonDivisible.class);

	/*
	 * Calculate the number of elements of an array that are not divisors of each element.
	 */
	public static void main(String[] args) {
		
		int[] A = new int[] { 3, 1, 2, 3, 6 };
//		log.debug("{}", Arrays.toString(solution(A)));
		log.debug("{}", Arrays.toString(mySolution(A)));
		//test(A);
	}
	
	public static void test(int[] A) {
		ArrayList<Integer> list = new ArrayList<>();
		for (int i = 0; i < A.length; i++) {
			list.add(A[i]);
		}
		log.debug("{}", list.toString());
		list.remove(0);
		log.debug("{}", list.toString());
	}
	
	// 주어진 배열에서 각 요소별 약수가 아닌 배열의 갯수
	// 즉 3의 약수는 1, 3 인데 주어진 배열 { 3, 1, 2, 3, 6 }; 에서 1 과 3이 아닌 갯수는 2와 6 이므로 2개
	/*
	 *  A[0] = 3, the non-divisors are: 2, 6,
	 *  A[1] = 1, the non-divisors are: 3, 2, 3, 6,
	 *  A[2] = 2, the non-divisors are: 3, 3, 6,
	 *  A[3] = 3, the non-divisors are: 2, 6,
	 *  A[4] = 6, there aren't any non-divisors.
	 *   [2, 4, 3, 2, 0]
	 */
	public static int[] mySolution(int[] A) {
		
		int[] res = new int[A.length];
		
		for (int i = 0; i < A.length; i++) {
			ArrayList<String> B = new ArrayList<>();
			for (int j = 0; j < A.length; j++) {
				B.add(""+A[j]);
			}
			//log.debug("{}", B.toString());
			for (int j = 1; j <= A[i]; j++) {
				if (A[i] % j == 0) { // 약수
					B.remove(""+j);
					while (B.contains(""+j)) { // 중복된거 있는지 확인해서 한번더 지운다.
						B.remove(""+j);
					}
				}
			}
			log.debug("i={}, B={}, size={}", i, B.toString(), B.size());
			res[i] = B.size();
		}
		return res;
	}
	
	// return [2, 4, 3, 2, 0]
	public static int[] solution(int[] A) {
		int[][] D = new int[2 * A.length + 1][2];
		int[] res = new int[A.length];
		
		// 2차원 배열초기화 뒤는 true, false 값으로 사용
		for (int i = 0; i < A.length; i++) {
			D[A[i]][0]++;
			D[A[i]][1] = -1;
		}
		
		for (int i = 0; i < A.length; i++) {
			if (D[A[i]][1] == -1) {
				D[A[i]][1] = 0;
				for (int j = 1; j * j <= A[i]; j++) {
					if (A[i] % j == 0) {
						D[A[i]][1] += D[j][0];
						if (A[i] / j != j)
							D[A[i]][1] += D[A[i] / j][0];
					}

				}
			}
		}
		for (int i = 0; i < A.length; i++) {
			res[i] = A.length - D[A[i]][1];
		}
		return res;
	}

}

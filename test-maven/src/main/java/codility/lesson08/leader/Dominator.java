package codility.lesson08.leader;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Dominator
 * Find an index of an array such that its value occurs at more than half of indices in the array.
 * 
 * A zero-indexed array A consisting of N integers is given. 
 * The dominator of array A is the value that occurs in more than half of the elements of A.
 * 
 * For example, consider array A such that
 * A[0] = 3    
 * A[1] = 4    
 * A[2] = 3
 * A[3] = 2    
 * A[4] = 3    
 * A[5] = -1
 * A[6] = 3    
 * A[7] = 3
 * The dominator of A is 3 because it occurs in 5 out of 8 elements of A (namely in those with indices 0, 2, 4, 6 and 7) and 5 is more than a half of 8.
 * 
 * Write a function
 * class Solution { public int solution(int[] A); }
 * that, given a zero-indexed array A consisting of N integers, returns index of any element of array A in which the dominator of A occurs. 
 * The function should return −1 if array A does not have a dominator.
 * 
 * Assume that:
 * N is an integer within the range [0..100,000];
 * each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
 * 
 * For example, given array A such that
 * A[0] = 3    A[1] = 4    A[2] =  3
 * A[3] = 2    A[4] = 3    A[5] = -1
 * A[6] = 3    A[7] = 3
 * the function may return 0, 2, 4, 6 or 7, as explained above.
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 */
public class Dominator {
	
	private static Logger log = LoggerFactory.getLogger(Dominator.class);
	
	/*
	 * Find an index of an array such that its value occurs at more than half of indices in the array.
	 */
	// 배열에서 가장 많은 빈도수를 갖는 숫자의 위치중 아무거나 반환하는 문제
	// return 0, 2, 4, 6 or 7 중 하나
	public static void main(String[] args) {
		int[] A = { 3, 4, 3, 2, 3, -1, 3, 3 };
		log.debug("{}", solution(A)); // return 0, 2, 4, 6 or 7
		log.debug("{}", mySolution(A));
	}
	
	public static int solution(int[] A) {
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty()) {
				stack.push(A[i]);
				continue;
			}
			
			if (stack.peek() == A[i]) {
				stack.push(A[i]);
			} else {
				stack.pop();
			}
		}
		
		if (stack.isEmpty()) {
			return -1;
		}
		int domCandidate = stack.peek();
		int occurances = 0;
		int randomIndex = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == domCandidate) {
				occurances++;
				randomIndex = i;
			}
		}

		return occurances > A.length / 2 ? randomIndex : -1;
	}
	
	// 위치를 반환해야 하기 때문에 정렬할 수 없음.
	// 빈도수 = A.length / 2 이어야하기 때문에 for문 하나 더 있음.
	// O(n)
	public static int mySolution(int[] A) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < A.length; i++) {
			if (stack.isEmpty()) {
				stack.push(A[i]);
			} else {
				if (stack.peek() == A[i]) {
					stack.push(A[i]); 
				} else {
					stack.pop();
				}
			}
		}
		log.debug("{}", stack.toString());
		int candidate = stack.pop();
		int count = 0;
		int randomIndex = -1;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == candidate) {
				count++;
				randomIndex = i;
			}
		}
		return count > A.length / 2 ? randomIndex : -1;
	} 
}

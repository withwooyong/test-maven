package codility.lesson08.leader;

import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * EquiLeader
 * Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.
 * 
 * A non-empty zero-indexed array A consisting of N integers is given.
 * The leader of this array is the value that occurs in more than half of the elements of A.
 * 
 * An equi leader is an index S such that 0 ≤ S < N − 1 and two sequences 
 * A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N − 1] have leaders of the same value.
 * 
 * For example, given array A such that:
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * 
 * we can find two equi leaders:
 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
 * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
 * 
 * The goal is to count the number of equi leaders.
 * 
 * Write a function:
 * class Solution { public int solution(int[] A); }
 * that, given a non-empty zero-indexed array A consisting of N integers, returns the number of equi leaders.
 * 
 * For example, given:
 * A[0] = 4
 * A[1] = 3
 * A[2] = 4
 * A[3] = 4
 * A[4] = 4
 * A[5] = 2
 * the function should return 2, as explained above.
 * 
 * Assume that:
 * N is an integer within the range [1..100,000];
 * each element of array A is an integer within the range [−1,000,000,000..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(N);
 * expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
 * Elements of input arrays can be modified.
 * @author wooyong
 *
 */
public class EquiLeader {
	private static Logger log = LoggerFactory.getLogger(EquiLeader.class);

	/*
	 * Find the index S such that the leaders of the sequences A[0], A[1], ..., A[S] and A[S + 1], A[S + 2], ..., A[N - 1] are the same.
	 */
	// return 2
	public static void main(String[] args) {
		int[] A = { 4, 3, 4, 4, 4, 2 };
		log.debug("{}", solution(A));
		
	}
	
	// The goal is to count the number of equi leaders.
	// The leader of this array is the value that occurs in more than half of the elements of A.
	// we can find two equi leaders:
	// 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
	// 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
	public static int solution(int[] A) {

		// check if it is dominator at all
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
			return 0; // there's no dominator
		}
		
		int dominator = stack.peek();
		int domOccurances = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == dominator) {
				domOccurances++;
			}
		}
		
		if (domOccurances <= A.length / 2) {
			return 0;// not dominator
		} 
		
		// dominator  = 4인걸로 확인했음.
		// { 4, 3, 4, 4, 4, 2 }; 절반이상 나오는 leader of this array = 4
		log.debug("dominator={}", dominator);
		// 요기까지 이해 했음.
		
		int nonDomOccurances = A.length - domOccurances;
		stack.clear();
		int dom = 0;
		int nonDom = 0;
		int equiLeaders = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] == dominator) { // 4와 동일하면
				dom++;
			} else {
				nonDom++;
			}
			
			// half of the elements = leader
			// 3, because sequences: (4, 3, 4, 4) and (4, 2) is not leader <= 요고이해.
			if ((dom > nonDom) && ((domOccurances - dom) > (nonDomOccurances - nonDom))) {
				equiLeaders++;
			}
		}
		return equiLeaders;
	}	
}

package codility.lesson03.timecomplexity;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/*
 * Equilibrium index of an array is an index such that 
 * the sum of elements at lower indexes is equal to the sum of elements at higher indexes. 
 * 
 * For example, in an arrya A:
 * A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0
 * 
 * 3 is an equilibrium index, because: A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 * 6 is also an equilibrium index, because sum of zero elements is zero,
 * i.e., A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0
 * 7 is not an equilibrium index, because it is not a valid index of array A.
 * 
 * Write a function int equilibrium(int[] arr, int n); that given a sequence arr[] of size n, 
 * returns an equilibrium index (if any) or -1 if no equilibrium indexes exist.
 * 
 * Method 1 (Simple but inefficient) Use two loops. 
 * Outer loop iterates through all the element and inner loop finds out whether the current
 * index picked by the outer loop is equilibrium index or not. 
 * Time complexity of this solution is O(n^2). 
 * Time Complexity: O(n^2)
 */
public class EquilibriumIndex {
	
	private static Logger log = LoggerFactory.getLogger(EquilibriumIndex.class);
	
	public static void main(String[] args) {
		int A[] = { -7, 1, 5, 2, -4, 3, 0 };
		log.debug("{}", solution(A, A.length));
	}
	
	/*
	 * 3 is an equilibrium index, because: A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
	 * 6 is also an equilibrium index, because sum of zero elements is zero,
	 * i.e., A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0
	 * 7 is not an equilibrium index, because it is not a valid index of array A.
	 */
	public static int solution(int arr[], int n) {
		/*
		 * Check for indexes one by one until an equilibrium index is found
		 */
		for (int i = 0; i < n; i++) {
			int leftsum = 0; // initialize left sum for current index i
			int rightsum = 0; // initialize right sum for current index i
			
			for (int j = 0; j < i; j++) { // 왼쪽배열
				leftsum += arr[j];
			}
			
			for (int k = i + 1; k < n; k++) { // 오른쪽배열
				rightsum += arr[k];
			}
			log.debug("i={} leftsum={} rightsum={}", i, leftsum, rightsum);
			
			if (leftsum == rightsum) {
				log.debug("equilibrium index is {}", i);
				//return i;
			}
		}
		return -1; // if no equilibrium index is found
	} 
	
	
}

package codility.solution.test;

/*
 This is a demo task. 
 A zero-indexed array A consisting of N integers is given. 
 An equilibrium index of this array is integer P such that 0 <= P < N 
 and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e.
 
 A[0] + A[1] + ... + A[P -1] = A[P+1] + ... + A[N-2] + A[N-1].
 Sum of zero elements is assumed to be equal to 0. This can happen if P = 0 or if P = N -1.
 
 For example, consider the following array A consisting of N = 8 elements:
 
 A[] = {-1, 3, -4, 5, 1, -6, 2, 1 }
 
 P = 1 is an equilibrium index of this array, because:
 
 
 
  
 You can read about this task and its solutions in this blog post.
 A zero-indexed array A consisting of N integers is given. 
 An equilibrium index of this array is any integer P such that 0 ≤ P < N and the sum of elements of lower indices is equal to the sum of elements of higher indices, i.e.
 
 A[0] + A[1] + ... + A[P−1] = A[P+1] + ... + A[N−2] + A[N−1].
 Sum of zero elements is assumed to be equal to 0. 
 This can happen if P = 0 or if P = N−1.
 For example, consider the following array A consisting of N = 7 elements:
 A[0] = -7   A[1] =  1   A[2] = 5
 A[3] =  2   A[4] = -4   A[5] = 3
 A[6] =  0
 
 P = 3 is an equilibrium index of this array, because:
 •A[0] + A[1] + A[2] = A[4] + A[5] + A[6]
 P = 6 is also an equilibrium index, because:
 •A[0] + A[1] + A[2] + A[3] + A[4] + A[5] = 0
 and there are no elements with indices greater than 6.
 P = 7 is not an equilibrium index, because it does not fulfill the condition 0 ≤ P < N.
 */
public class TaskDemo {

	public static void main(String[] args) {
		int[] A = {-1, 3, -4, 5, 1, -6, 2, 1 };
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {
		long sum = 0;
		int i = 0;

		for (i = 0; i < A.length; i++) {
			sum += (long) A[i];
		}

		long sum_left = 0;
		for (i = 0; i < A.length; i++) {
			long sum_right = sum - sum_left - (long) A[i];

			if (sum_left == sum_right) {
				System.out.println("i=" + i);
				//return i;
			}
			sum_left += (long) A[i];
		}
		return -1;// write your code here
	}
	
	/// <summary>
	/// Returns the first equilibrium found of an array
	/// </summary>
	/// <param name="A">The array in question</param>
	/// <returns>The equilibrium of the array, if it exists, otherwise
	/// -1</returns>
	public static int solution2(int[] A) throws Exception {
		// Default function result
		int equilibrium = -1;
		
		// Get initial left and right sums
		long sumLeft = 0;
		long sumRight = 0;
		for (int i = 0; i < A.length; i++) {
			sumRight += A[i];
		}

		// int[] A = { -7, 1, 5, 2, -4, 3, 0 };
		// Traverse the array, looking for the first equilibrium
		for (int i = 0; i < A.length; i++) {
			long tempRight = sumRight - A[i];
			System.out.println("i=" + i + " sumRight=" + sumRight + " - A[i]=" + A[i] + " tempRight=" + tempRight);
			if (sumLeft == tempRight) {
				// We have found a solution at the i-th element
				equilibrium = i;
				System.out.println("equilibrium="+ equilibrium);
			} else {
				// Prepare for next comparison
				sumLeft += A[i];
				sumRight = tempRight;
			}
		}
		// Return the result
		return equilibrium;
	}
}

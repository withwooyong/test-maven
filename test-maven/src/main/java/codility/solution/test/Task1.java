package codility.solution.test;

/*
 * 
 * https://gist.github.com/Linyes/10219849
A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between any two consecutive elements is the same.
For example, these are arithmetic sequences
1, 3, 5, 7, 9
7, 7, 7, 7, 
3, -1, -5, -9
The sequence [1, 1, 2, 5, 7] is not arithmetic.
 

 */
public class Task1 {
	
	public static void main(String[] args) {		
		int[] A = { -1, 1, 3, 3, 3, 2, 1, 0 };
		System.out.println(solution(A));
	}
	
	public static int solution(int[] A) {		
		if (A.length < 3) {
			return 0; 
		}
		
		int start = 0; 
		int nos = 0;

		while (start < A.length - 2) {
			int end = start + 1; 
			int diff = A[end] - A[start];
			
			while (end < A.length - 1 && A[end + 1] - A[end] == diff) {
				end++;
			}

			int los = end - start + 1;			
			if (los >= 3) {
				nos += ((los - 2) * (los - 1) / 2);
			}			
			start = end;
		}
		return (nos > 1000000000) ? -1 : nos;
	}
}

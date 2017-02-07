package codility.solution.test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
 * 
 */
public class Task3 {
	
	public static void main(String[] args) {
		
		int M = 5;
		int X = 2;
		int Y = 200;
		int[] A = { 60, 80, 40 };
		int[] B = { 2, 3, 5 }; // 5
		
//		int M = 3;
//		int X = 5;
//		int Y = 200;
//		int[] A = { 40, 40, 100, 80, 20 };
//		int[] B = { 3, 3, 2, 2, 3 }; // 6
		System.out.println(solution(A, B, M, X, Y));

	}

	public static int solution(int[] A, int[] B, int M, int X, int Y) {
		List<Integer> arrList = new ArrayList<Integer>();
		long twpr = 0;
		int ts = 0;
		int mpc = 0;
		int cp = 0;
		boolean start = false;
		while (cp < A.length) {
			if ((twpr + A[cp]) <= Y && (mpc + 1) <= X) {
				twpr += A[cp];
				mpc++;
				arrList.add(B[cp]);
				if (cp == A.length - 1) {
					start = true;
				}
				cp++;
			} else {
				start = true;
			}

			if (start) {
				ArrayList<Integer> newArrList = new ArrayList<Integer>(new HashSet<Integer>(arrList));
				ts += newArrList.size() + 1;
				start = false;
				mpc = 0;
				twpr = 0;
				arrList.clear();
			}
		}
		return ts;
	}


}

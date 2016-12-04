package codility.solution;

import java.util.Arrays;
import java.util.Hashtable;

/**
 * Equilibrium index
 * Equilibrium index of a sequence is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes. 
 * For example, in a sequence A:
 * A[0]=-7 
 * A[1]=1 
 * A[2]=5 
 * A[3]=2 
 * A[4]=-4 
 * A[5]=3 
 * A[6]=0
 * 
 *  3 is an equilibrium index, 
 *  because: A[0]+A[1]+A[2] = A[4]+A[5]+A[6]
 *  
 *  6 is also an equilibrium index, 
 *  because: A[0]+A[1]+A[2]+A[3]+A[4]+A[5]=0
 *  (sum of zero elements is zero) 7 is not an equilibrium index, because it is not a valid index of sequence A.
 *  
 *  Write a function: int equi(int[] A); that given a sequence, returns its equilibrium index (any) or -1 if no equilibrium indexes exist. 
 *  Assume that the sequence may be very long.
 * @author wooyong
 *
 */
public class EquilibriumIndex {

	public static void main(String[] args) {

		int[] A = { -7, 1, 5, 2, -4, 3, 0 };
		System.out.println("For the array: " + Arrays.toString(A));
		System.out.println("The first Equilibrium Index: " + equi(A));
		
		int sum = 0;
		int N = 10;
		for (int i = 0; i <= N; i++) {
			sum += i;
		}
		System.out.println(sum);
		System.out.println(N * (N + 1) / 2); // summation formula 합공식 sum = n*(n+1)/2
		
		int[] B = { 1, 2, 3, 4, 6, 7, 8, 9, 10 };
		int sumB = B.length * (B.length + 1) /2 ;
		System.out.println(sumB);
		for (int i = 0; i < B.length; i++) {
			sumB -= B[i];
		}
		System.out.println(Math.abs(sumB));		
		
	}

	// this return the first one, order N (linear)
	public static int equi(int[] A) {
		Hashtable<Object, Object> hashtable = new Hashtable<>();
		
		int leftSum = 0;
		int totalSum = 0;
		
		for (int i = 0; i < A.length; i++) {
			totalSum += A[i];
			int key = (2 * leftSum) + A[i];
			if (!hashtable.containsKey(key)) // we are only concerned about the first index
				hashtable.put(key, i);
			leftSum += A[i];
		}
		return hashtable.get(totalSum) == null ? -1 : (int) hashtable.get(totalSum); // just get the first occurrence
	}

}

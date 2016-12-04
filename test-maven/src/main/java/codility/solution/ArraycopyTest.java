package codility.solution;

import java.util.Arrays;

public class ArraycopyTest {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 4, 5 };
		int[] B = new int[] { 6, 7, 8, 9, 0 };
		int[] C = new int[6]; 
		
		System.arraycopy(A,  0, C, 0, A.length);
		System.out.println(Arrays.toString(C));
		

	}

}

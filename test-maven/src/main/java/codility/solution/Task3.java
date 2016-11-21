package codility.solution;

import java.util.Arrays;

public class Task3 {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 6, 5, 5, 8, 9 };		
		System.out.println(solution(A));
	}
	
	public static int solution3(int[] A) {
		boolean numbersSwitched;
		int compareCount = 0;
		do {
			numbersSwitched = false;
			for (int i = 0; i < A.length - 1; i++) {
				if (A[i + 1] < A[i]) {
					int tmp = A[i + 1];
					A[i + 1] = A[i];
					A[i] = tmp;
					numbersSwitched = true;
					
				}
				compareCount++;
			}
		} while (numbersSwitched);
		return 0;
	}
	
	public static int solution2(int[] A) {
		
		int low = A[0];
		int high = A[0];
		for (int i = 1; i < A.length; i++) {
			if (A[i] < low) {
				low = A[i];
			} else if (A[i] > high) {
				high = A[i];
			}
		}

		int[] counts = new int[high - low + 1]; // this will hold all possible values, from low to high
		
		for (int x : A) {
			counts[x - low]++; // - low so the lowest possible value is always 0
		}
		
		int current = 0;
		for (int i = 0; i < counts.length; i++) {
			System.out.println("current=" + current + ":" + counts[i] + ":" + i + low);
			Arrays.fill(A, current, current + counts[i], i + low); // fills counts[i] elements of value i + low in current
			current += counts[i]; // leap forward by counts[i] steps
		}
		
		return 0;
	}
	
	public static int solution(int[] A) {
		     
	    int[] aux = new int[A.length];
	 
	    // find the smallest and the largest value
	    int min = A[0];
	    int max = A[0];
	    for (int i = 1; i < A.length; i++) {
	      if (A[i] < min) {
	        min = A[i];
	      } else if (A[i] > max) {
	        max = A[i];
	      }
	    }
	 
	    // init array of frequencies
	    int[] counts = new int[max - min + 1];
	 
	    // init the frequencies
	    for (int i = 0;  i < A.length; i++) {
	      counts[A[i] - min]++;
	      
	    }
	 
	    // recalculate the array - create the array of occurences
	    counts[0]--;
	    for (int i = 1; i < counts.length; i++) {
	      counts[i] = counts[i] + counts[i-1];
	      System.out.print(counts[i]);
	    }
	    System.out.println("========");
	 
	    for (int i = A.length - 1; i >= 0; i--) {
	        aux[counts[A[i] - min]--] = A[i];
	        System.out.print(A[i]);
	    }
	    System.out.println("========");
	    return 0;
	}

}

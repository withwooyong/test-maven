package programcreek;

import java.util.Arrays;

/*
 * Can we do this in O(1) space?
 * This solution is like a bubble sort.
 * However, the time is O(n*k).
 * Here is an example (length=7, order=3):
 */
public class Solution2_Bubble_Rotate {
	
	public static void main(String[] args) {
		
		int k = 3;
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 }; // [5,6,7,1,2,3,4]
		rotate(nums, k);

	}
	
	public static void rotate(int[] arr, int order) {
		if (arr == null || order < 0) {
		    throw new IllegalArgumentException("Illegal argument!");
		}
	 
		for (int i = 0; i < order; i++) {
			for (int j = arr.length - 1; j > 0; j--) {
				int temp = arr[j];
				arr[j] = arr[j - 1];
				arr[j - 1] = temp;
			}
		}
		System.out.println(Arrays.toString(arr));
	}
	

}

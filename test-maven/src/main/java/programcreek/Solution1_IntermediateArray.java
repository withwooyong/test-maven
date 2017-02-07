package programcreek;

import java.util.Arrays;

/*
 * Rotate an array of n elements to the right by k steps.
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4]. 
 * How many different ways do you know to solve this problem?
 * Solution 1 - Intermediate Array
 * In a straightforward way, we can create a new array and then copy elements to the new array. 
 * Then change the original array by using System.arraycopy().
 * 
 * Space is O(n) and time is O(n). You can check out the difference between System.arraycopy() and Arrays.copyOf().
 */
public class Solution1_IntermediateArray {

	public static void main(String[] args) {
		int k = 3;
		int[] nums = { 1, 2, 3, 4, 5, 6, 7 }; // [5,6,7,1,2,3,4]
		rotate(nums, k);

	}
	
	public static void rotate(int[] nums, int k) {
		if (k > nums.length) {
			k = k % nums.length;
		}

		int[] result = new int[nums.length];

		for (int i = 0; i < k; i++) {
			result[i] = nums[nums.length - k + i];
		}
		System.out.println(Arrays.toString(result));
		int j = 0;
		for (int i = k; i < nums.length; i++) {
			result[i] = nums[j];
			j++;
		}
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(nums));
		System.arraycopy(result, 0, nums, 0, nums.length);
		System.out.println(Arrays.toString(result));
		System.out.println(Arrays.toString(nums));
	}

}

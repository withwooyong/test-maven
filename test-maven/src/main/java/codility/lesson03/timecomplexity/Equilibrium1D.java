package codility.lesson03.timecomplexity;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://www.shuati123.com/blog/2014/12/17/equilibrium-points-in-2d-array/
 * 
 * Question
 * Equilibrium Points in 2D Array
 *  
 * Twitter conducted an online programming test at IIT-Bombay. Following questions were asked:
 * ###
 * 1) Given a string, check if there exists some anagram of the string which is a palindrome.
 * Function Signature: bool anagramPalindrome(string word) *
 *  
 * Sample Testcases:
 * a) anagramPalindrome("rotate") returns false, no anagram of "rotate" is a palindrome
 * b) anagramPalindrome("hanna") returns true, since using letters from "hanna", we can form the palindrome "nahan"
 * 
 * ###
 * 2) Given a permutation which contains numbers in the range [1, N], return the length of the largest cycle in the permutation. Function Signature: int longestCycle(vector<int> perm)
 * 
 * Sample Testcases:
 * a) longestCycle([2 3 1]) returns 3, since only cycle is (1 2 3) whose length is 3
 * b) longestCycle([5 4 3 2 1]) returns 2, since the permutation can be decomposed into (1 5), (2 4), (3)
 * 
 * Somehow, I was also able to get hold of the questions asked at IIT-Delhi's twitter programming test:
 * 
 * 1) Find the number of "visible" nodes in a binary tree. 
 *    A node is a "visible" node if the path from root to that node does not encounter any node of value higher than that node.
 * 2) In a 2D matrix of dimensions M*N, find number of "equilibrium" points.  
 *    A point (i, j) is said to be an "equilibrium" point only if all following conditions hold:
 *    a) sum of rows 1...(i-1) =  sum of rows (i+1)...M
 *    b) sum of columns 1...(j-1)  = sum of columns (j+1)...N
 * 
 * 
 * In a 2D matrix of dimensions M*N, find number of “equilibrium” points. 
 * A point (i, j) is said to be an “equilibrium” point only if all following conditions hold:
 * a) sum of rows 1…(i-1) = sum of rows (i+1)…M
 * b) sum of columns 1…(j-1) = sum of columns (j+1)…N
 * 
 * solution
 * This is a generalize question of Equilibrium index.
 * Refer to Equilibrium index, read this. 
 * The idea is to get total sum of array first. Then Iterate through the array calculate left sum == sum / 2.
 * 
 * Equilibrium index of an array is an index such that the sum of elements at lower indexes is equal to the sum of elements at higher indexes. For example, in an arrya A:

A[0] = -7, A[1] = 1, A[2] = 5, A[3] = 2, A[4] = -4, A[5] = 3, A[6]=0

3 is an equilibrium index, because: A[0] + A[1] + A[2] = A[4] + A[5] + A[6]

6 is also an equilibrium index, because sum of zero elements is zero, i.e., A[0] + A[1] + A[2] + A[3] + A[4] + A[5]=0
Well, for Equilibrium Points in 2D Array, should be similar. DIY and leave me a comment!
 * 
 */

public class Equilibrium1D {
	
	private static Logger log = LoggerFactory.getLogger(Equilibrium1D.class);

	public static void main(String[] args) {
		
		int[] A = { -7, 1, 5, 2, -4, 3, 0 };
		int[][] B = new int[][] { 
			//8, 17, 8
			{ 2, 7, 5 }, // 14
			{ 3, 1, 1 }, // 5
			{ 2, 1, -7 },// -4
			{ 0, 2, 1 }, // 3
			{ 1, 6, 8 } };// 15
		log.debug("{}", array2DSum(B));
		log.debug("{}", equilibrium1D(A));
		log.debug("{}", equilibrium1D2(A, A.length));	
	}
	
	public static int array2DSum(int[][] A) {
		int num = 0;
		for (int i = 0; i < A.length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				num += A[i][j];
			}
			log.debug("각 행 가로의 합은 : {}", num);
			num = 0;
		}
		log.debug("");
		for (int i = 0; i < A[0].length; i++) {
			for (int j = 0; j < A[i].length; j++) {
				num += A[j][i];
			}
			log.debug("각 열 세로의 합은 : {}", num);
			num = 0;
		}
		return 0;
	}
	
	public static List<Integer> equilibrium1D(int[] array) {
	    List<Integer> ans = new ArrayList<Integer>();
	    int sum = 0;
	    for (int i = 0; i < array.length; i++) {
	        sum += array[i];
	    }
	    int runningSum = 0;
	    for (int i = 0; i < array.length; i++) {
	        if (2 * runningSum + array[i] == sum) {
	            ans.add(i);
	        }
	        runningSum += array[i];
	    }
	    return ans;
	}
	
	public static int equilibrium1D2(int arr[], int n) {
		/*
		 * Check for indexes one by one until an equilibrium index is found
		 */
		int equilibriumCount = 0;
		for (int i = 0; i < n; i++) {
			int leftsum = 0; // initialize left sum for current index i
			int rightsum = 0; // initialize right sum for current index i
			
			for (int j = 0; j < i; j++) { // 왼쪽배열
				leftsum += arr[j];
			}
			
			for (int k = i + 1; k < n; k++) { // 오른쪽배열
				rightsum += arr[k];
			}
			//log.debug("i={} leftsum={} rightsum={}", i, leftsum, rightsum);
			
			if (leftsum == rightsum) {
				log.debug("equilibrium index is {}", i);
				equilibriumCount++;
				//return i;
			}
		}
		return equilibriumCount; // if no equilibrium index is found
	} 
	
	/*
	 * input: A 2D array, number in that is weight.
	 * output: The point of equilibrium
	 * Infect, point of equilibrium means center of gravity. Thus, all need to do is get the weighted arithmetic mean in each direction, row and col.
	 * Interestingly, in Python, we can do that by only 4 lines of code.
	 * 
	 * def find_center(arr):
	 * sums = []
	 * sums.append(map(sum, [[r[col] for r in arr] for col in xrange(0,len(arr))])) # get the sum in Y deriction
	 * sums.append(map(sum, arr))                                                  # get the sum in X deriction
	 * center = [float(sum((sum_[i]*i for i in xrange(0,len(sum_))))) / sum(sum_) for sum_ in sums]
	 * # calculation of 2 weighted means
	 * return center
	 * pass
	 * testcase_1 = [
	 * [1,3,2,4,5], # 15
	 * [2,4,3,2,1], # 12
	 * [5,7,3,4,1], # 20
	 * [7,6,8,5,4], # 30
	 * [6,5,4,3,5], # 23
	 * ]
	 * #21,25,20,18,16
	 * print "Case 1 point of equilibrium is %s " %  (find_center(testcase_1))
	 */

}

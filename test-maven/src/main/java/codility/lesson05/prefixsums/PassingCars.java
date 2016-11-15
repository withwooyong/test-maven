package codility.lesson05.prefixsums;

import java.util.Iterator;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. The consecutive elements of array A represent consecutive cars on a road.

Array A contains only 0s and/or 1s:

0 represents a car traveling east,
1 represents a car traveling west.
The goal is to count passing cars. We say that a pair of cars (P, Q), where 0 ≤ P < Q < N, is passing when P is traveling to the east and Q is traveling to the west.

For example, consider array A such that:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
We have five pairs of passing cars: (0, 1), (0, 3), (0, 4), (2, 3), (2, 4).

Write a function:

class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A of N integers, returns the number of pairs of passing cars.

The function should return −1 if the number of pairs of passing cars exceeds 1,000,000,000.

For example, given:

  A[0] = 0
  A[1] = 1
  A[2] = 0
  A[3] = 1
  A[4] = 1
the function should return 5, as explained above.

Assume that:

N is an integer within the range [1..100,000];
each element of array A is an integer that can have one of the following values: 0, 1.
Complexity:

expected worst-case time complexity is O(N);
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class PassingCars {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int solution(int[] A) {
        // write your code in Java SE 8
        int toEast = 0;
        
        long pairCount = 0;
        
        for(int i = 0 ; i < A.length ; i++){
            if(A[i] == 0){
                toEast++; 
            } else{
                pairCount += toEast; 
            }
        }

        if(pairCount > 1000000000){
            return -1;   
        }
        
        return (int)pairCount;
    }
	
	public int solution2(int[] A) {
		int carsGoingEastCount = 0;
		int passingPairs = 0;
		
		for (int i : A) {
			if (i == 0) {
				carsGoingEastCount++;
			} else if (carsGoingEastCount > 0) {
				passingPairs += carsGoingEastCount;
				if (passingPairs > 1_000_000_000) {
					return -1;
				}
			}
		}
		return passingPairs;
	}
	
	public int solution1(int[] A) {
		int length = A.length;
		int NoOf1s = 0;
		int noOfTotalPair = 0;
		for(int index = length -1; index >= 0; index--){
		if(A[index] == 1){
		NoOf1s++;
		}else{
		noOfTotalPair += NoOf1s;
		}
		if(noOfTotalPair > 1_000_000_000){
		noOfTotalPair = -1;
		break;
		}
		}
		return noOfTotalPair;
		}
	
	

}

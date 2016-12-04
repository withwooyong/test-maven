package codility.lesson06.sorting;

/**
 * A non-empty zero-indexed array A consisting of N integers is given. The product of triplet (P, Q, R) equates to A[P] * A[Q] * A[R] (0 ≤ P < Q < R < N).

For example, array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
contains the following example triplets:

(0, 1, 2), product is −3 * 1 * 2 = −6
(1, 2, 4), product is 1 * 2 * 5 = 10
(2, 4, 5), product is 2 * 5 * 6 = 60
Your goal is to find the maximal product of any triplet.

Write a function:

class Solution { public int solution(int[] A); }
that, given a non-empty zero-indexed array A, returns the value of the maximal product of any triplet.

For example, given array A such that:

  A[0] = -3
  A[1] = 1
  A[2] = 2
  A[3] = -2
  A[4] = 5
  A[5] = 6
the function should return 60, as the product of triplet (2, 4, 5) is maximal.

Assume that:

N is an integer within the range [3..100,000];
each element of array A is an integer within the range [−1,000..1,000].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
Copyright 2009–2016 by Codility Limited. All Rights Reserved. Unauthorized copying, publication or disclosure prohibited.
 * @author wooyong
 *
 */
public class MaxProductOfThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 * function solution(arr){
    if(arr.length === 3){
        return arr[0] * arr[1] * arr[2];
    }

    var positive_res = find_triplet_product(arr.slice());
    var negative_res = check_negative(arr.slice());

    return Math.max(positive_res, negative_res);

}

function find_triplet_product(arr){
    arr.sort(function(a, b){return a-b});
    var arr_length = arr.length;
    var res = arr.slice(arr_length-3, arr_length);
    return res[0] * res[1] * res[2];
}

function check_negative(arr){
    arr.sort(function(a, b){return a-b});
    for(var i=0; i<arr.length; i++){
        if(arr[i] >= 0){
            break;
        }
    }
    if(i < 1)
        return 0; // all of array is negative
    if(i < 2)
        return -1000; // one element is negative

    var negative_arr = arr.slice(0, i);
    var the_other = arr.slice(i, arr.length);
    var n_len = negative_arr.length;
    // console.log('negative_arr', negative_arr, 'the_other', the_other);

    if(the_other.length === 0){
        return negative_arr[n_len -1] * negative_arr[n_len-2] *negative_arr[n_len-3]
    }
    

    return negative_arr[0] * negative_arr[1] * the_other[the_other.length-1];
}

console.log(solution([2, 100, 3, -1000]));
console.log(solution([-5,5,-4,1]));
console.log(solution([-1,-4,-3,-2,-6,-5]));
console.log(solution([4,5,1,0]));
console.log(solution([-5, -9, 3, 4, 2]));
console.log(solution([-3, 1,2,-2,5,6]))
	 */
	

}
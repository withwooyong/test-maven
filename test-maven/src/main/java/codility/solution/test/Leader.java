package codility.solution.test;

/*
 * Get Leader of the array in the value that occurs in more than half of the elements of A.
 * A non-empty zero-indexed array A consisting of N integers and sorted in a non-decreasing order is given. The leader of this array is the value that occurs in more than half of the elements of A.

You are given an implementation of a function:

class Solution { public int solution(int[] A); }

that, given a non-empty zero-indexed array A consisting of N integers, sorted in a non-decreasing order, returns the leader of array A. The function should return −1 if array A does not contain a leader.

For example, given array A consisting of ten elements such that:

  A[0] = 2 

  A[1] = 2

  A[2] = 2

  A[3] = 2

  A[4] = 2

  A[5] = 3

  A[6] = 4

  A[7] = 4

  A[8] = 4

  A[9] = 6

the function should return −1, because the value that occurs most frequently in the array, 2, occurs five times, and 5 is not more than half of 10.



Given array A consisting of five elements such that:




  A[0] = 1

  A[1] = 1

  A[2] = 1

  A[3] = 1

  A[4] = 50

the function should return 1.



Unfortunately, despite the fact that the function may return expected result for the example input, there is a bug in the implementation, which may produce incorrect results for other inputs. Find the bug and correct it. You should modify at most three lines of code.



Assume that:



N is an integer within the range [1..100,000];

each element of array A is an integer within the range [0..2147483647];

array A is sorted in non-decreasing order.

Complexity:



expected worst-case time complexity is O(N);

expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).

Elements of input arrays can be modified.



위의 문제는 A라는 array에 값이 들어 있는데, 2,2,3,3,4,4 이런식으로 점점 커지는 수가 들어 있는 array이다. 이중 전체 갯수에서 같은수가 절반이 넘었을때 그때의 값을 리턴해 주고, 절반이거나 절반이 되지 않으면 -1을 리턴하라는 문제이다. 풀이보단 문제 이해가 너무 어려운 문제이다.


 */
public class Leader {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int solution(int A[]) {
		int length = A.length;

		// A 어레이의 길이가 0 이거나 1이면 -1을 리턴한다. 1개이면 절대 절반이상의 갯수를 만족할수 없다.

		if (length <= 1) {

			return -1;

		}

		// 첫번째 인자를 가장최근의 인자라고 생각할때

		int lastInput = A[0];

		int count = 0;

		int tmpValue = -1;

		for (int i = 0; i < A.length; i++) {

			if (lastInput == A[i]) { // 가장 마지막에 가지고 있는 값과 첫번째 값이 //같으면 카운트를 하나
										// 올리고,
										// 그 값을 저장하자

				count++;

				tmpValue = A[i];

				// System.out.println("count : " + count);

			}

			else { // 만약 아니면 위에서 계산한 count를 계산하여 절반이상이면

				// return해주고, 아니면 count를 1로 세팅한다 (1로세팅하는 이유는 새로 하나가 추가됐기

				// 때문이다

				// System.out.println("count : " + count);

				// System.out.println("(length/2) : " + (length/2));

				if (count > (length / 2)) {

					return tmpValue;

				}

				else {

					count = 1;

				}

			}

			// 가장최근값을 가장 마지막에 저정한 값으로 넣어준다

			lastInput = A[i];

			System.out.println("lastInput : " + lastInput);

			// 이건 마지막 놈을 체크하기 위한 루틴이다. 만약 이걸 하지 않으면 for문 //에서 else에서만 절반이 넘는지
			// 체크를 하기
			// 때문에 문제가 될수 있다. 예를들어 //{2,2,2,3,3,3,3,3} 이런식이면 if는 들어가지만 else에
			// 못들어가
			// 문제가 된다.

			if (i == (length - 1)) {
				if (count > (length / 2)) {
					return tmpValue;
				}
			}
		}
		return -1;
	}
}
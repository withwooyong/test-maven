/*
	A zero-indexed array A consisting of N integers is given. It contains daily prices of a stock share for a period of N consecutive days. If a single share was bought on day P and sold on day Q, where 0 ≤ P ≤ Q < N, then the profit of such transaction is equal to A[Q] − A[P], provided that A[Q] ≥ A[P]. Otherwise, the transaction brings loss of A[P] − A[Q].
	For example, consider the following array A consisting of six elements such that:
	  A[0] = 23171  
	  A[1] = 21011  
	  A[2] = 21123
	  A[3] = 21366  
	  A[4] = 21013  
	  A[5] = 21367
	If a share was bought on day 0 and sold on day 2, a loss of 2048 would occur because A[2] − A[0] = 21123 − 23171 = −2048. If a share was bought on day 4 and sold on day 5, a profit of 354 would occur because A[5] − A[4] = 21367 − 21013 = 354. Maximum possible profit was 356. It would occur if a share was bought on day 1 and sold on day 5.
	Write a function,
	class Solution { public int solution(int[] A); }
	that, given a zero-indexed array A consisting of N integers containing daily prices of a stock share for a period of N consecutive days, returns the maximum possible profit from one transaction during this period. The function should return 0 if it was impossible to gain any profit.
	For example, given array A consisting of six elements such that:
	  A[0] = 23171  
	  A[1] = 21011  
	  A[2] = 21123
	  A[3] = 21366  
	  A[4] = 21013  
	  A[5] = 21367
	the function should return 356, as explained above.
	Assume that:
	N is an integer within the range [0..400,000];
	each element of array A is an integer within the range [0..200,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 0 인덱스 배열 A가 제공됩니다. 
	그것은 연속 N 일 동안 주식의 일일 가격을 포함합니다. 
	단일 주식이 P 일에 매수되고 Q가 0 일 때 Q ≤ Q <N 일 때 매매 된 경우, A [Q] ≥ 조건 일 때 그러한 거래의 이익은 A [Q] - A [P]와 같습니다. A [P]. 그렇지 않으면 거래가 A [P] -A [Q]를 잃게됩니다.
	예를 들어, 다음과 같은 여섯 개의 요소로 구성된 다음 배열 A를 고려하십시오.
	A [0] = 23171
	A [1] = 21011
	A [2] = 21123
	A [3] = 21366
	A [4] = 21013
	A [5] = 21367
	A [2] - A [0] = 21123 - 23171 = -2048이기 때문에 0 일에 주식을 매수하고 2 일에 매도 한 경우 2048의 손실이 발생합니다. 
	주식이 4 일에 매수되고 5 일에 팔린다면, A [5] - A [4] = 21367-21013 = 354이기 때문에 354의 이익이 발생합니다. 
	가능한 최대 이익은 356입니다. 
	1 일에 구입하여 5 일에 팔았습니다.
	
	함수를 작성하십시오.
	클래스 솔루션 {public int solution (int [] A); }
	연속 된 N 일 동안 주식의 일일 주가가 포함 된 N 개의 정수로 구성된 인덱스가 0 인 배열 A가 주어지면이 기간 동안 한 거래에서 가능한 최대 이익을 반환합니다. 이익을 얻는 것이 불가능한 경우 함수는 0을 반환해야합니다.
	
	예를 들어 주어진 배열 A는 다음과 같은 6 개의 요소로 구성됩니다.
	A [0] = 23171
	A [1] = 21011
	A [2] = 21123
	A [3] = 21366
	A [4] = 21013
	A [5] = 21367
	함수는 위에서 설명한대로 356을 반환해야합니다.
	
	가정하자면 :
	N은 [0..400,000] 범위의 정수입니다.
	배열 A의 각 요소는 [0..200,000] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.maximumslice;

import java.util.Arrays;

public class MaxProfit {
	public static void main(String[] args) {
		int[] A = new int[] { 23171, 21011, 21123, 21366, 21013, 21367 };
		System.out.println(solution(A));
	}

	public static int solution(int[] A) {
		if (A.length == 0)
			return 0;
		int[] array = new int[A.length];
		array[0] = 0;
		for (int i = 1; i < A.length; i++) {
			array[i] = A[i] - A[i - 1];
		}
		return goldenMaxSlice(array);
	}

	public static int goldenMaxSlice(int[] A) {
		int arrMax = Arrays.stream(A).max().getAsInt();
		if (arrMax < 0)
			return arrMax;
		int maxEnding = 0;
		int maxSlice = 0;
		for (int i = 0; i < A.length; i++) {
			maxEnding = (maxEnding + A[i]) > 0 ? (maxEnding + A[i]) : 0;
			maxSlice = maxSlice > maxEnding ? maxSlice : maxEnding;
		}
		return maxSlice;
	}

}

/*
	You have to climb up a ladder. The ladder has exactly N rungs, numbered from 1 to N. With each step, you can ascend by one or two rungs. More precisely:
	with your first step you can stand on rung 1 or 2,
	if you are on rung K, you can move to rungs K + 1 or K + 2,
	finally you have to stand on rung N.
	Your task is to count the number of different ways of climbing to the top of the ladder.
	For example, given N = 4, you have five different ways of climbing, ascending by:
	1, 1, 1 and 1 rung,
	1, 1 and 2 rungs,
	1, 2 and 1 rung,
	2, 1 and 1 rungs, and
	2 and 2 rungs.
	Given N = 5, you have eight different ways of climbing, ascending by:
	1, 1, 1, 1 and 1 rung,
	1, 1, 1 and 2 rungs,
	1, 1, 2 and 1 rung,
	1, 2, 1 and 1 rung,
	1, 2 and 2 rungs,
	2, 1, 1 and 1 rungs,
	2, 1 and 2 rungs, and
	2, 2 and 1 rung.
	The number of different ways can be very large, so it is sufficient to return the result modulo 2P, for a given integer P.
	Write a function:
	class Solution { public int[] solution(int[] A, int[] B); }
	that, given two non-empty zero-indexed arrays A and B of L integers, returns an array consisting of L integers specifying the consecutive answers; position I should contain the number of different ways of climbing the ladder with A[I] rungs modulo 2B[I].
	For example, given L = 5 and:
	    A[0] = 4   B[0] = 3
	    A[1] = 4   B[1] = 2
	    A[2] = 5   B[2] = 4
	    A[3] = 5   B[3] = 3
	    A[4] = 1   B[4] = 1
	the function should return the sequence [5, 1, 8, 0, 1], as explained above.
	Assume that:
	L is an integer within the range [1..30,000];
	each element of array A is an integer within the range [1..L];
	each element of array B is an integer within the range [1..30].
	Complexity:
	expected worst-case time complexity is O(L);
	expected worst-case space complexity is O(L), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	사다리를 타고 올라 가야합니다. 사다리는 1에서 N까지 번호가 매겨진 N 개의 렁이 있습니다. 각 단계마다 하나 또는 두 개의 렁이 올라갈 수 있습니다. 더 정확하게:
당신의 첫 번째 단계에서 당신은 1 또는 2 단계에 서있을 수 있습니다,
R 단계에있는 경우 K + 1 또는 K + 2 단계로 이동할 수 있으며,
마침내 너는 열 N에서야한다.
너의 임무는 등반의 다른 방법의 수를 사다리 꼭대기에 세는 것이다.
예를 들어 N = 4 일 때 다음과 같은 다섯 가지 등반 방법이 있습니다.
1, 1, 1 및 1 단계,
1, 1 및 2 단계,
1, 2 및 1 단계,
2, 1, 1 단계 및
2 및 2 단계.
N = 5 인 경우 다음과 같은 8 가지 등반 방법이 있습니다.
1, 1, 1, 1 및 1 단계,
1, 1, 1 및 2 단계,
1, 1, 2 및 1 단계,
1, 2, 1 및 1 단계,
1, 2 및 2 단계,
2, 1, 1 및 1 단계,
2, 1 및 2 단계 및
2, 2 및 1 단계.
다른 방법의 수는 매우 클 수 있습니다. 따라서 주어진 정수 P에 대해 2P를 기준으로 결과를 반환하는 것으로 충분합니다.
함수 작성 :
클래스 솔루션 {public int [] solution (int [] A, int [] B); }
L 개의 정수가 아닌 두 개의 비어 있지 않은 Z- 색인 배열 A와 B가 주어지면 연속적인 답을 지정하는 L 개의 정수로 구성된 배열을 반환합니다. 위치에는 2B [I]를 기준으로 한 사다리를 오르는 다양한 방법이 포함되어야합니다.
예를 들어, L = 5이고 :
A [0] = 4 B [0] = 3
A [1] = 4 B [1] = 2
A [2] = 5 B [2] = 4
A [3] = 5 B [3] = 3
A [4] = 1 B [4] = 1
함수는 위에서 설명한대로 [5, 1, 8, 0, 1] 시퀀스를 반환해야합니다.
가정하자면 :
L은 [1.30,000] 범위 내의 정수이고;
배열 A의 각 요소는 [1..L] 범위의 정수입니다.
배열 B의 각 요소는 [1..30] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (L)이다.
기대 최악의 경우의 공간 복잡도는 입력 저장 (입력 인수에 필요한 저장 장치를 계산하지 않음) 이상으로 O (L)입니다.
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/75
package codility.solution.fibonaccinumbers;

import java.math.BigInteger;
import java.util.Arrays;
 
public class Ladder {
	int number = 0;
	public static void main(String[] args) {
		int[] A = new int[]{4,4,5,5,1};
		int[] B = new int[]{3,2,4,3,1};
		System.out.println(Arrays.toString(solution(A,B)));
	}
	
	public static int[] solution(int[] A, int[] B) {
		BigInteger[] fibs = new BigInteger[A.length+2];
		fibs[0] = new BigInteger("0");
		fibs[1] = new BigInteger("1");
		for (int i = 2; i < A.length+2; i++) {
			fibs[i] = fibs[i-1].add(fibs[i-2]);
		}
		int[] res = new int[A.length];
		for (int i = 0; i < B.length; i++) {
			BigInteger currPow = new BigInteger(String.valueOf((long)Math.pow(2, B[i])));
			res[i] = fibs[A[i]+1].mod(currPow).intValue();
		}
		return res;
	}
}

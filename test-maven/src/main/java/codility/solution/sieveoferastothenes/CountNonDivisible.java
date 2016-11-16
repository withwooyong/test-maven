/*
	You are given a non-empty zero-indexed array A consisting of N integers.
	For each number A[i] such that 0 ≤ i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.
	For example, consider integer N = 5 and array A such that:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	For the following elements:
	A[0] = 3, the non-divisors are: 2, 6,
	A[1] = 1, the non-divisors are: 3, 2, 3, 6,
	A[2] = 2, the non-divisors are: 3, 3, 6,
	A[3] = 3, the non-divisors are: 2, 6,
	A[6] = 6, there aren't any non-divisors.
	Write a function:
	class Solution { public int[] solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.
	The sequence should be returned as:
	a structure Results (in C), or
	a vector of integers (in C++), or
	a record Results (in Pascal), or
	an array of integers (in any other programming language).
	For example, given:
	    A[0] = 3
	    A[1] = 1
	    A[2] = 2
	    A[3] = 3
	    A[4] = 6
	the function should return [2, 4, 3, 2, 0], as explained above.
	Assume that:
	N is an integer within the range [1..50,000];
	each element of array A is an integer within the range [1..2 * N].
	Complexity:
	expected worst-case time complexity is O(N*log(N));
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 정수로 구성된 0이 아닌 인덱스가없는 배열 A가 제공됩니다.
각각의 수 A [i]가 0 ≤ i <N이되도록, 우리는 A [i]의 약수가 아닌 배열의 원소의 수를 세고 싶다. 우리는 이러한 요소들이 비약자라고 말합니다.
예를 들어 정수 N = 5와 배열 A를 다음과 같이 고려하십시오.
A [0] = 3
A [1] = 1
A [2] = 2
A [3] = 3
A [4] = 6
다음 요소의 경우 :
A [0] = 3이면, 제수수는 2, 6,
A [1] = 1 일 때, 비 제수는 3, 2, 3, 6,
A [2] = 2이고, 비 제 약자는 3, 3, 6,
A [3] = 3이고, 비 제 약자는 2, 6,
A [6] = 6, 비 제수는 없습니다.
함수 작성 :
클래스 솔루션 {public int [] solution (int [] A); }
N 개의 정수로 구성된 비어 있지 않은 제로 인덱스 배열 A가 주어지면 비 제수의 양을 나타내는 정수 시퀀스를 반환합니다.
시퀀스는 다음과 같이 반환되어야합니다.
구조 결과 (C), 또는
정수 벡터 (C ++에서) 또는
기록 결과 (파스칼), 또는
정수 배열 (다른 프로그래밍 언어에서)
예를 들어, 주어진 :
A [0] = 3
A [1] = 1
A [2] = 2
A [3] = 3
A [4] = 6
함수는 위에서 설명한대로 [2, 4, 3, 2, 0]을 반환해야합니다.
가정하자면 :
N은 [1..50,000] 범위의 정수입니다.
배열 A의 각 요소는 [1..2 * N] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N * log (N))이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.sieveoferastothenes;

import java.util.Arrays;

public class CountNonDivisible {
	
	public static void main (String[] args) {
		int[] A = new int[] {3,1,2,3,6};
		System.out.println(Arrays.toString(solution(A)));
	}
	
	public static int[] solution(int[] A) {
	    int[][] D = new int[2*A.length + 1][2];
	    int[] res = new int[A.length];
	    for (int i = 0; i < A.length; i++) {
			D[A[i]][0]++;
			D[A[i]][1] = -1;
		}
	    for (int i = 0; i < A.length; i++) {
	    	if(D[A[i]][1]==-1) {
	    		D[A[i]][1]=0;
	    		for (int j = 1; j*j <= A[i]; j++) {
					if(A[i] % j == 0) {
						D[A[i]][1]+= D[j][0];
						if (A[i]/j != j)
							D[A[i]][1]+= D[A[i]/j][0];
					}
					
				}
			}
		}
	    for (int i = 0; i < A.length; i++) {
			res[i] = A.length - D[A[i]][1]; 
		}
	    return res;
	} 	
}

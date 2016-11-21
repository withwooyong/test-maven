/*
	A prime is a positive integer X that has exactly two distinct divisors: 1 and X. 
	The first few prime integers are 2, 3, 5, 7, 11 and 13.
	A semiprime is a natural number that is the product of two (not necessarily distinct) prime numbers. 
	The first few semiprimes are 4, 6, 9, 10, 14, 15, 21, 22, 25, 26.
	You are given two non-empty zero-indexed arrays P and Q, each consisting of M integers. 
	These arrays represent queries about the number of semiprimes within specified ranges.
	Query K requires you to find the number of semiprimes within the range (P[K], Q[K]), where 1 ≤ P[K] ≤ Q[K] ≤ N.
	
	For example, consider an integer N = 26 and arrays P, Q such that:
	    P[0] = 1    Q[0] = 26
	    P[1] = 4    Q[1] = 10
	    P[2] = 16   Q[2] = 20
	
	The number of semiprimes within each of these ranges is as follows:
	(1, 26) is 10,
	(4, 10) is 4,
	(16, 20) is 0.
	
	Write a function:
	class Solution { public int[] solution(int N, int[] P, int[] Q); }
	
	that, given an integer N and two non-empty zero-indexed arrays P and Q consisting of M integers, returns an array consisting of M elements specifying the consecutive answers to all the queries.
	For example, given an integer N = 26 and arrays P, Q such that:
	    P[0] = 1    Q[0] = 26
	    P[1] = 4    Q[1] = 10
	    P[2] = 16   Q[2] = 20
	the function should return the values [10, 4, 0], as explained above.
	
	Assume that:
	N is an integer within the range [1..50,000];
	M is an integer within the range [1..30,000];
	each element of arrays P, Q is an integer within the range [1..N];
	P[i] ≤ Q[i].
	
	Complexity:
	expected worst-case time complexity is O(N*log(log(N))+M);
	expected worst-case space complexity is O(N+M), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	프라임은 정확히 2 개의 구별되는 약수 인 1과 X가있는 양의 정수 X입니다. 처음 소수의 정수는 2, 3, 5, 7, 11 및 13입니다.
	세미 프리즘은 2 개의 (반드시 구별되는 것은 아님) 소수의 결과 인 자연수입니다. 처음 몇 개의 반주문은 4, 6, 9, 10, 14, 15, 21, 22, 25, 26입니다.
	두 개의 비어 있지 않은 제로 색인 배열 P와 Q가 주어지며, 각각은 M 개의 정수로 구성됩니다. 이 배열은 지정된 범위 내의 반수 수에 대한 쿼리를 나타냅니다.
	질의 K는 범위 (P [K], Q [K]) 내에서 1 ≤ P [K] ≤ Q [K] ≤N 인 반제품의 수를 찾아야합니다.
	
	예를 들어 정수 N = 26 및 배열 P, Q를 다음과 같이 가정합니다.
	P [0] = 1 Q [0] = 26
	P [1] = 4 Q [1] = 10
	P [2] = 16 Q [2] = 20
	이들 각각의 범위 내의 반수의 수는 다음과 같습니다 :
	(1, 26)은 10이고,
	(4, 10)은 4이고,
	(16, 20)는 0입니다.
	
	함수 작성 :
	클래스 솔루션 {public int [] solution (int N, int [] P, int [] Q); }
	정수 N과 두 개의 비어 있지 않은 제로 색인 배열 P와 Q가 M 개의 정수로 구성되어 있으면 모든 검색어에 대한 연속적인 답을 지정하는 M 개의 요소로 구성된 배열을 반환합니다.
	
	예를 들어, 정수 N = 26이고 배열 P, Q는 다음과 같습니다.
	P [0] = 1 Q [0] = 26
	P [1] = 4 Q [1] = 10
	P [2] = 16 Q [2] = 20
	함수는 위에서 설명한대로 [10, 4, 0] 값을 반환해야합니다.
	
	가정하자면 :
	N은 [1..50,000] 범위의 정수입니다.
	M은 [1..30,000] 범위의 정수입니다.
	배열 P, Q의 각 요소는 [1..N] 범위의 정수입니다.
	P [i] ≤ Q [i]이다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N * log (log (N)) + M)이다.
	기대 최악의 경우의 공간 복잡도는 O (N + M)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//Score: 100/100
package codility.solution.sieveoferastothenes;

import java.util.Arrays;

public class CountSemiprimes {

	public static void main(String[] args) {
		int[] A = new int[] { 1, 4, 16 };
		int[] B = new int[] { 26, 10, 20 };
		int N = 26;
		System.out.println(Arrays.toString(solution(A, B, N)));

	}

	public static int[] solution(int[] A, int[] B, int N) {
		int[] factArray = factorizationArray(N);
		int[] semiPrimes = new int[factArray.length];
		for (int i = 0; i < semiPrimes.length; i++) {
			if (factArray[i] != 0 && factArray[i / factArray[i]] == 0)
				semiPrimes[i] = 1;
		}
		int[] semiPrimesPreSum = prefixSum(semiPrimes);
		int[] res = new int[A.length];
		for (int i = 0; i < B.length; i++) {
			res[i] = semiPrimesPreSum[B[i]] - semiPrimesPreSum[A[i] - 1];
		}
		return res;
	}

	// preparing array for factorization (array with primes)
	public static int[] factorizationArray(int n) {
		int[] F = new int[n + 1];
		for (int i = 2; i * i <= n; i++) {
			if (F[i] == 0) {
				for (int k = i * i; k <= n; k += i) {
					if (F[k] == 0)
						F[k] = i;
				}
			}
		}
		return F;
	}

	public static int[] prefixSum(int[] A) {
		int[] prefSum = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			if (i == 0)
				prefSum[i] = A[i];
			else
				prefSum[i] = prefSum[i - 1] + A[i];
		}
		return prefSum;
	}
}

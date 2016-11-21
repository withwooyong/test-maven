/*
	A prime is a positive integer X that has exactly two distinct divisors: 1 and X. The first few prime integers are 2, 3, 5, 7, 11 and 13.
	A prime D is called a prime divisor of a positive integer P if there exists a positive integer K such that D * K = P. For example, 2 and 5 are prime divisors of 20.
	You are given two positive integers N and M. 
	The goal is to check whether the sets of prime divisors of integers N and M are exactly the same.
	
	For example, given:
	N = 15 and M = 75, the prime divisors are the same: {3, 5};
	N = 10 and M = 30, the prime divisors aren't the same: {2, 5} is not equal to {2, 3, 5};
	N = 9 and M = 5, the prime divisors aren't the same: {3} is not equal to {5}.
	
	Write a function:
	class Solution { public int solution(int[] A, int[] B); }
	that, given two non-empty zero-indexed arrays A and B of Z integers, returns the number of positions K for which the prime divisors of A[K] and B[K] are exactly the same.
	
	For example, given:
	    A[0] = 15   B[0] = 75
	    A[1] = 10   B[1] = 30
	    A[2] = 3    B[2] = 5
	the function should return 1, because only one pair (15, 75) has the same set of prime divisors.
	
	Assume that:
	Z is an integer within the range [1..6,000];
	each element of arrays A, B is an integer within the range [1..2147483647].
	
	Complexity:
	expected worst-case time complexity is O(Z*log(max(A)+max(B))2);
	expected worst-case space complexity is O(1), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	프라임은 정확히 2 개의 구별되는 약수 인 1과 X가있는 양의 정수 X입니다. 
	처음 소수의 정수는 2, 3, 5, 7, 11 및 13입니다.
	소수 D는 D * K = P와 같은 양의 정수 K가있는 경우 양의 정수 P의 소수 제수라고합니다. 
	예를 들어 2와 5는 20의 소수입니다.
	두 개의 양의 정수 N과 M이 주어집니다. 
	목표는 정수 N과 M의 소수 제수가 정확히 같은지 확인하는 것입니다.
	
	예를 들어, 주어진 :
	N = 15 및 M = 75 인 경우, 제수는 동일합니다 : {3, 5};
	N = 10 및 M = 30 인 경우, 소수 제수는 동일하지 않습니다. {2, 5}는 ​​{2, 3, 5}와 같지 않습니다.
	N = 9 및 M = 5 인 경우, 소수 제수는 동일하지 않습니다. {3}은 {5}와 같지 않습니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A, int [] B); }
	두 개의 비어 있지 않은 제로 색인 된 Z 배열의 A와 B가 주어지면 A [K]와 B [K]의 제수가 똑같은 위치 K의 수를 반환합니다.
	
	예를 들어, 주어진 :
	A [0] = 15 B [0] = 75
	A [1] = 10 B [1] = 30
	A [2] = 3 B [2] = 5
	한 쌍 (15, 75)만이 프라임 제수 집합이 같기 때문에이 함수는 1을 반환해야합니다.
	
	가정하자면 :
	Z는 [1..6,000] 범위의 정수입니다.
	배열 A, B의 각 요소는 [1..2147483647] 범위의 정수입니다.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (Z * log (max (A) + max (B)) 2);
	예상되는 최악의 경우의 공간 복잡도는 입력 저장소 (입력 인수에 필요한 저장소를 계산하지 않음) 이상으로 O (1)입니다.
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.euclideanalgorithm;

public class CommonPrimeDivisors {

	public static void main(String[] args) {
		int[] A = new int[] { 15, 10, 3 };
		int[] B = new int[] { 75, 30, 5 };
		System.out.println(solution(A, B));

	}

	private static int solution(int[] A, int[] B) {
		int res = 0;
		
		for (int i = 0; i < A.length; i++) {
			int x = A[i];
			int y = B[i];
			int gcd = gcd(x, y, 1);
			int gcdTmp = 0;
			
			// x
			while (x != 1) {
				gcdTmp = gcd(x, gcd, 1);				
				if (gcdTmp == 1) {
					break;
				}
				x /= gcdTmp;
			}
			
			if (x != 1) {
				continue;
			}

			// y
			while (y != 1) {
				gcdTmp = gcd(y, gcd, 1);				
				if (gcdTmp == 1) {
					break;
				}
				y /= gcdTmp;
			}
			
			if (y != 1) {
				continue;
			}
			res++;
		}
		return res;
	}

	private static int gcd(int a, int b, int res) {
		if (a == b) {
			return res * a;
		} else if (a % 2 == 0 && b % 2 == 0) {
			return gcd(a / 2, b / 2, res * 2);
		} else if (a % 2 == 0) {
			return gcd(a / 2, b, res);
		} else if (b % 2 == 0) {
			return gcd(a, b / 2, res);
		} else if (a > b) {
			return gcd(a - b, b, res);
		} else {
			return gcd(a, b - a, res);
		}
	}
}

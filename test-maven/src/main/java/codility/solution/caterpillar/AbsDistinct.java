/*
	A non-empty zero-indexed array A consisting of N numbers is given. The array is sorted in non-decreasing order. The absolute distinct count of this array is the number of distinct absolute values among the elements of the array.
	For example, consider array A such that:
	  A[0] = -5    
	  A[1] = -3    
	  A[2] = -1
	  A[3] =  0    
	  A[4] =  3    
	  A[5] =  6
	The absolute distinct count of this array is 5, because there are 5 distinct absolute values among the elements of this array, namely 0, 1, 3, 5 and 6.
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A consisting of N numbers, returns absolute distinct count of array A.
	For example, given array A such that:
	  A[0] = -5    
	  A[1] = -3    
	  A[2] = -1
	  A[3] =  0    
	  A[4] =  3    
	  A[5] =  6
	the function should return 5, as explained above.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647];
	array A is sorted in non-decreasing order.
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	N 개의 숫자로 구성된 비어 있지 않은 Z 인덱싱 된 배열 A가 제공됩니다. 
	배열은 내림차순으로 정렬됩니다. 
	이 배열의 절대적인 고유 카운트는 배열 요소 사이의 고유 한 절대 값의 수입니다.
	예를 들어 다음과 같은 배열 A를 고려하십시오.
	A [0] = -5
	A [1] = -3
	A [2] = -1
	A [3] = 0
	A [4] = 3
	A [5] = 6
	이 배열의 요소 중 절대 값이 5 개 (0, 1, 3, 5 및 6)이므로이 배열의 절대 고유 카운트는 5입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int [] A); }
	N 개의 숫자로 구성된 0이 아닌 인덱스가없는 배열 A가 주어지면 배열 A의 절대 고유 카운트를 반환합니다.
	예를 들어, 주어진 배열 A는 다음과 같습니다.
	A [0] = -5
	A [1] = -3
	A [2] = -1
	A [3] = 0
	A [4] = 3
	A [5] = 6
	함수는 위에서 설명한대로 5를 반환해야합니다.
	
	가정하자면 :
	N은 [1..100,000] 범위 내의 정수입니다.
	배열 A의 각 요소는 [-2,147,483,648.2,147,483,647] 범위의 정수입니다.
	배열 A는 내림차순으로 정렬됩니다.
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (N)이다.
	예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
	입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100 (both solutions)
package codility.solution.caterpillar;

import java.util.Arrays;
import java.util.stream.IntStream;

public class AbsDistinct {

	public static void main(String[] args) {
		int[] A = new int[] { -5, -3, -1, 0, 3, 6 };
		System.out.println(solution(A));
	}

	// using Java 8
	public static int solution(int[] A) {
		return (int) IntStream.of(A).map(i -> Math.abs(i)).distinct().count();
	}

	public static int solution1(int[] A) {
		int dupls = 0;
		for (int i = 0; i < A.length; i++) {
			if (A[i] < 0)
				A[i] = -A[i];
		}
		Arrays.sort(A);
		for (int i = 1; i < A.length; i++) {
			if (A[i] == A[i - 1])
				dupls++;
		}
		return A.length - dupls;
	}

}

/*
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the minimal positive integer that does not occur in A.
	For example, given:
	  A[0] = 1    
	  A[1] = 3    
	  A[2] = 6
	  A[3] = 4    
	  A[4] = 1    
	  A[5] = 2
	the function should return 5.
	Assume that:
	N is an integer within the range [1..100,000];
	each element of array A is an integer within the range [−2,147,483,648..2,147,483,647].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
	
	함수 작성 :
클래스 솔루션 {public int solution (int [] A); }
N 개의 정수로 구성된 0이 아닌 인덱스가없는 공백 배열 A가 주어지면 A에서 발생하지 않는 최소 양의 정수를 반환합니다.
예를 들어, 주어진 :
A [0] = 1
A [1] = 3
A [2] = 6
A [3] = 4
A [4] = 1
A [5] = 2
함수는 5를 반환해야합니다.
가정하자면 :
N은 [1..100,000] 범위 내의 정수입니다.
배열 A의 각 요소는 [-2,147,483,648.2,147,483,647] 범위의 정수입니다.
복잡성:
기대 최악의 경우의 시간 복잡도는 O (N)이다.
예상 최악의 경우의 공간 복잡도는 O (N)이며, 입력 저장소를 초과합니다 (입력 인수에 필요한 저장소를 계산하지 않음).
입력 배열의 요소를 수정할 수 있습니다.
 */

//SCORE: 100/100
package codility.solution.countingelements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class MissingInteger {
	public static void main(String[] args) {
		int[] A = new int[] { 1, 3, 6, 4, 1, 2 };
		System.out.println(solution(A));
		
		System.out.println("==================");
		Arrays.asList(1,2,3,1,2,3).stream().filter(i-> i <= 2).forEach(System.out::println);
		System.out.println("==================");
		Arrays.asList(3,2,1,3,2,1).stream().filter(i-> i <= 2).sorted().distinct().forEach(System.out::println);
		
	}

	// N 개의 정수로 구성된 0이 아닌 인덱스가없는 공백 배열 A가 주어지면 A에서 발생하지 않는 최소 양의 정수를 반환합니다.
	public static int solution(int[] A) {
		// boxed() 메소드는 int, long, double 요소를 Integer, Long, Double 요소로 박싱해서 Stream을 생성.
		// sorted() 정렬 distinct() 중복제거
		// filter() stream의 요소마다 비교를 하고 비교문을 만족하는 요소로만 구성된 stream을 반환합니다.
		ArrayList<Integer> numbers = IntStream.of(A).boxed().filter(x -> x > 0).sorted().distinct()
				.collect(Collectors.toCollection(ArrayList<Integer>::new));		
		if (numbers.size() == 0) {
			return 1;
		}
		for (int i = 0; i < numbers.size(); i++) {
			if (numbers.get(i) != i + 1) {
				System.out.println("result=" + (i + 1));
				return i + 1;
			}
		}
		return numbers.size() + 1;
	}
}

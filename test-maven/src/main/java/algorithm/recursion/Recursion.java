package algorithm.recursion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Recursion {
	
	private static Logger log = LoggerFactory.getLogger(Recursion.class);

	public static void main(String[] args) {
		
		int[] A = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		int[] B = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
//		log.debug("{}", search(A, A.length, 5));
//		log.debug("{}", findMax(A.length, A));
		log.debug("{}", isDisjoint(A.length, A, B.length, B));
		
	}
	
	// 순차탐색
	// data[0]에서 data[n-1] 사이에서 target을 검색한다.
	private static int search(int[] data, int n, int target) {
		if (n <= 0) {
			return -1;
		} else if (target == data[n - 1]) {
			return n - 1;
		} else {
			return search(data, n - 1, target);
		}
	}
	
	// 최대값 찾기
	// data[0]에서 data[n-1] 사이에서 최대값을 찾아	반환한다.
	private static int findMax(int n, int data[]) {
		if (n == 1) {
			return data[0];
		} else {
			return Math.max(data[n - 1], findMax(n - 1, data));
		}
	}
	
	// Disjoint Sets
	// 배열 A의 A[0],…,A[m-1]과 배열 B의 B[0],…,B[n-1]에 정수들이 정렬되어 저장되어 있을 때 
	// 두 배열의 정수들이 disjoint한지 검사한다.
	private static boolean isDisjoint(int m, int A[], int n, int B[]) {
		if (m < 0 || n < 0) {
			return true;
		} else if (A[m - 1] == B[n - 1]) {
			return false;
		} else if (A[m - 1] > B[n - 1]) {
			return isDisjoint(m - 1, A, n, B);
		} else {
			return isDisjoint(m, A, n - 1, B);
		}
	}
	
	// 이진 탐색: Iterative Version
	private static int binarySearch(int[] data, int n, int target) {
		int begin = 0;
		int end = data.length -1;
		while (begin <= end) {
			int middle = (begin + end) / 2;
			if (data[middle] == target) { 
				return middle;				
			} else if (data[middle] > target) {
				end = middle  - 1;
			} else {
				begin = middle + 1;
			}
		}
		return -1;
	}
	
	// 이진탐색: Recursion
	// items[begin]에서 items[end] 사이에서 target을 검색한다.
	private static int binarySearch(int[] data, int target, int begin, int end) {
		if (begin > end) {
			return -1;
		} else {
			int middle = (begin + end) / 2;
			if (data[middle] == target) {
				return middle;
			} else if (data[middle] > target) {
				return binarySearch(data, target, begin, middle - 1); // 왼쪽배열
			} else {
				return binarySearch(data, target, middle + 1, end); // 오른쪽배열
			}
		}
	}
	
	// 2-SUM
	// data[begin]에서 data[end] 사이에서 합이 K가 되는 쌍이 존재하는지 검사한다. 
	// 데이터는 오름차순으로 정렬되어 있다고 가정한다.
	private static boolean twoSum(int data[], int begin, int end, int K) {
		if (begin >= end) { // 만약 중복 선택이 가능하다면 =을 빼면 됨
			return false;
		} else {
			if (data[begin] + data[end] == K) {
				return true;
			} else if (data[begin] + data[end] < K) {
				return twoSum(data, begin + 1, end, K);
			} else {
				return twoSum(data, begin, end - 1, K);
			}
		}
	}

	// Towers of Hanoi
	private static void showMoves(int n, char start, char dest, char temp) {
		if (n == 1) {
			log.debug("Move disk 1 from peg {} to peg {}", start, dest);
		} else {
			showMoves(n - 1, start, temp, dest);
			log.debug("Move disk {} from peg {} to peg {}", n, start, dest);
			showMoves(n - 1, temp, dest, start);
		}
	}

}

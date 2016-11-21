package codility.solution;

import java.util.ArrayList;
import java.util.List;

/*
 * list is sorted after one swap
 * http://stackoverflow.com/questions/29999113/javascript-check-if-list-is-sorted-after-one-swap
 */
public class Task2 {

	public static void main(String[] args) {
		//int[] A = new int[] { 1, 5, 3, 3, 7 };
		//int[] A = new int[] { 1, 3, 5, 3, 4 };
		int[] A = new int[] { 1, 3, 5 };
//		System.out.println(solution(A));
//		System.out.println(checkSortedness(A));
//		System.out.println(isSorted(A));
		System.out.println(sortByOneSwap(A));
		
	}
	
	public static boolean sortByOneSwap(int A[]) {
		// 배열값 확인
		if (A == null || A.length == 0) {
			return false;
		}
		
		for (int i = 1; i < A.length; ++i) {
			// 값 비교
			if (A[i - 1] <= A[i]) {
				continue;
			}
			int x = A[i - 1];
			int left = i - 1;
			// x가 동일한 원소의 순서 중 하나라면 가장 왼쪽
			while (left - 1 >= 0 && A[left - 1] == x) {
				--left;
			}
			// x보다 작지 않은 가장 빠른 요소 찾기
			for (++i; i < A.length; ++i) {
				if (A[i] >= x) {
					break; 
				}
			}			
			int right = i - 1;
			int y = A[right];			
			// 스왑 x and y.
			A[left] = y;
			A[right] = x;
			
			for (i = (left == 0 ? 1 : left); i < A.length; ++i) {
				if (A[i - 1] > A[i]) {
					return false; // 정렬안됨
				}
			}
			return true; // 정렬됨
		}
		return true;
	}
	
	public static boolean solution(int[] A) {
		List<Integer> input = new ArrayList<Integer>();
	    for (int index = 0; index < A.length; index++) {
	        input.add(A[index]);
	    }
	    int j = 0;
	    while (j < input.size() - 1 && input.get(j) <= input.get(j + 1)) {
	        j++;
	    }
	    if (j == input.size() - 1) {
	        return true;
	    }
	    return false;
	}
	
	public static boolean checkSortedness(int[] data) {
		for (int i = 1; i < data.length; i++) {
			if (data[i - 1] > data[i]) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isSorted(int[] data) {
		int previous = data[0];
		for (int i = 1; i < data.length; i++) {
			if (previous > data[i])
				return false;
			previous = data[i];
		}
		return true;
	}

}

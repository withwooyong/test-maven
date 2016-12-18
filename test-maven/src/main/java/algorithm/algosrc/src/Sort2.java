package algorithm.algosrc.src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 4.8 선택 정렬과 삽입 정렬
public class Sort2 {

	private static Logger log = LoggerFactory.getLogger(Sort2.class);
	
	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 5, 4, 3, 2, 1 };
		log.debug("{}", selectionSort(A));
		log.debug("{}", insertionSort(A));

	}
	
	// 선택정렬 : 처음과 다음번째 것을 비교하여 스왑
	private static int[] selectionSort(int[] A) {
		for (int i = 0; i < A.length; ++i) {
			int minIndex = i;
			for (int j = i + 1; j < A.length; ++j) {
				if (A[minIndex] > A[j]) {
					minIndex = j;
				}
			}
			// swap(A[i], A[minIndex]);
			int temp = 0;
			temp = A[i];
			A[i] = A[minIndex];
			A[minIndex] = temp;
		}
		return A;
	}

	// 삽입정렬 : 전체 배열 중 정렬되어 있는 부분배열에 새 원소를 끼워넣는 일을 반복하는 방식으로 동작
	private static int[] insertionSort(int[] A) {
		for (int i = 0; i < A.length; ++i) {
			int val = A[i], j = i - 1;
			while (j >= 0 && A[j] > val) {
				A[j + 1] = A[j];
				--j;
			}
			A[j + 1] = val;
		}
		return A;
	}


}

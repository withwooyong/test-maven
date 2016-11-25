package algorithm.recursion;

import java.util.Arrays;

/*
 * 거품 정렬(Bubble sort)은 두 인접한 원소를 검사하여 정렬하는 방법이다. 
 * 시간 복잡도가 {\displaystyle O(n^{2})} O(n^{2})로 상당히 느리지만, 코드가 단순하기 때문에 자주 사용된다. 
 * 원소의 이동이 거품이 수면으로 올라오는 듯한 모습을 보이기 때문에 지어진 이름이다.
 */
public class BubbleSort {

	public static void main(String[] args) {

		int arrayList[] = { 5, 3, 9, 7, 1, 8 };
		bubbleSort(arrayList);
	}
	
	// Bubble sort 두 인접한 원소를 검사하여 정렬하는 방법이다.
	public static void bubbleSort(int[] arr) {

		for (int i = 0; i < arr.length; i++) {
			for (int j = 1; j < arr.length; j++) {
				if (arr[j-1] > arr[j]) {
					swap(arr, j-1, j);
				}
			}
			System.out.println(Arrays.toString(arr));
		}
	}

	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

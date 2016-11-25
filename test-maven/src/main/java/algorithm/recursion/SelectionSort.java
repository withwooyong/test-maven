package algorithm.recursion;

import java.util.Arrays;

/*
 * 선택 정렬(選擇整列, selection sort)은 제자리 정렬 알고리즘의 하나로, 다음과 같은 순서로 이루어진다.
 * 주어진 리스트 중에 최솟값을 찾는다.
 * 그 값을 맨 앞에 위치한 값과 교체한다(패스(pass)).
 * 맨 처음 위치를 뺀 나머지 리스트를 같은 방법으로 교체한다.
 * 비교하는 것이 상수 시간에 이루어진다는 가정 아래, n개의 주어진 리스트를 이와 같은 방법으로 정렬하는 데에는 Θ(n2) 만큼의 시간이 걸린다.
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr = { 9, 1, 6, 8, 4, 3, 2, 0 };
		selectionSort(arr);
	}

	public static void selectionSort(int[] arr) {
		int min;

		for (int i = 0; i < arr.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, min, i); // 리스트 중에 최솟값을 찾는다.
//			temp = list[indexMin];
//			list[indexMin] = list[i];
//			list[i] = temp;
			System.out.println(Arrays.toString(arr));
		}
	}
	
	public static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}

package algorithm.recursion;

import java.util.Arrays;

/*
 * 선택정렬(Selection Sort) 알고리즘은 반복적으로 특정 값을 정렬된 최종 위치에 배치시킴으로써 값들의 목록을 정렬한다. 
 * 즉, 목록의 각 위치에 대해서 이 알고리즘은 그 위치에 배치되어야 하는 값을 선택하고, 그 값을 그 곳에 배치한다.
 * 
 * 선택 정렬에서 일반적인 전략은 다음과 같다.
 * 값들의 목록을 오름차순으로 배치하는 문제를 생각해보자.
 * 
 * ① 목록 전체를 조사하여 가장 작은 값을찾는다.
 * ② 이 값과 목록의 첫 번째 위치에 있는 값을 교환한다.
 * ③ 목록의 나머지 값들(첫 번째 값을 제외한 모든 값)을 조사하여 가장 작은 값을 찾고, 이 값과 목록의 두 번째 위치에 있는 값을 교환한다.
 * ④ 목록의 나머지 값들(처음 두 번째 값을 제외한 모든 값)을 조사하여 가장 작은 값을 찾고, 이 값과 목록의 세 번째 위치에 있는 값을 교환한다.
 * ⑤ 목록의 각 위치에 대해서 이러한 과정을 계속한다.
 */
public class SelectionSort {

	public static void main(String[] args) {
		int[] arr1 = { 29, 10, 14, 37, 13 };
		int[] arr2 = selectionSort(arr1);
		for (int i : arr2) {
			System.out.print(i);
			System.out.print(", ");
		}
	}

	// 첫번재 두번째 비교하여 
	public static int[] selectionSort(int[] arr) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i; 
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[index]) { // 가장 큰 수를 찾아서 
					index = j;
				}
			}			
			int smallerNumber = arr[index];
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		
			System.out.println("smallerNumber=" + smallerNumber + ":index=" + index + ":" + Arrays.toString(arr));
		}
		return arr;
	}
}

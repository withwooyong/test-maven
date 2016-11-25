package algorithm.recursion;

import java.util.Arrays;

/*
 * 삽입 정렬(揷入整列, insertion sort)은 
 * 자료 배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여, 
 * 자신의 위치를 찾아 삽입함으로써 정렬을 완성하는 알고리즘이다.
 * k번째 반복 후의 결과 배열은, 앞쪽 k + 1 항목이 정렬된 상태이다.
 * 
 * 31	[25]	12	22	11		 	두 번째 원소를 부분 리스트에서 적절한 위치에 삽입한다.
 * <25>	31	[12]	22	11		 	세 번째 원소를 부분 리스트에서 적절한 위치에 삽입한다.
 * <12>	25	31	[22]	11		 	네 번째 원소를 부분 리스트에서 적절한 위치에 삽입한다.
 * 12	<22>	25	31	[11]		 	마지막 원소를 부분 리스트에서 적절한 위치에 삽입한다.
 * <11>	12	22	25	31		 	종료.
 * 
 */
public class InsertionSort {

	public static void main(String[] args) {
		int[] arr = { 31, 25, 12, 22, 11 };
		insertionSort(arr);
	}
	
	public static void insertionSort(int[] arr) {
		
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int pre = i - 1;
			
			while (pre >= 0 && temp < arr[pre]) {
				System.out.println("pre=" + pre +":arr[pre]=" + arr[pre] + ":arr[pre + 1]=" + arr[pre + 1]);
				arr[pre + 1] = arr[pre]; // 뒤로 민다.
				pre--;
			}	
			arr[pre + 1] = temp;
			System.out.println(Arrays.toString(arr));
		}
	}
	
	public static void insertionSort2(int[] arr) {
		
		for (int i = 1; i < arr.length; i++) {
			int temp = arr[i];
			int pre = i - 1;
		
			while ((pre >= 0) && (arr[pre] > temp)) {
				arr[pre + 1] = arr[pre];
				pre--; // 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교
			}
			arr[pre + 1] = temp;
			System.out.println(Arrays.toString(arr));
		}
	}

}

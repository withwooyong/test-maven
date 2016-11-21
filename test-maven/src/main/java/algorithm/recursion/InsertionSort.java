package algorithm.recursion;

import java.util.Arrays;

/**
 * 삽입 정렬(insertion sort) 알고리즘은 반복적으로 특정 값을 이미 정렬된 목록의 부분 집합에 삽입함으로써 값들의 목록을 정렬한다.
 * 한번에 하나씩 정렬되지 않은 원소는 정렬된 부분 집합의 적절한 위치에 삽입된다. 
 * 이러한 과정은 전체 목록이 정렬될 때까지 계속된다.
 * 
 * 2번째부터 기준이 되어 시작한다. (왼쪽과 비교해야 하므로 2번째부터 시작한다)
 * 
 * ① 단지 한 개의 값만을 포함하는 정렬된 목록으로 시작한다.
 * ② 목록의 처음 두 개의 값을 비교하고, 필요하면 이들을 교환함으로써 정렬한다.
 * ③ 목록의 세 번째 값을 이미 정렬된 처음 두 개의 값과 비교하여 적절한 위치에 삽입한다.
 * ④ 다음에 네 번째 값을 목록의 처음 세 개의 값과 비교하여 적절한 위치에 삽입한다.
 * ⑤ 삽입이 이루어질때마다 정렬된 부분 집합에 속한 값들의 개수는 하나씩 증가한다.
 * ⑥ 이러한 과정을 목록에 포함된 모든값들이 적절한 위치에 삽입될 때까지 계속한다.
 * 삽입 과정은 삽입되는 원소에 대한 공간을 마련하기 위해서 목록에 포함된 다른 값들에 대한 이동을 요구한다.
 * 
 * @author wooyong
 *
 */
public class InsertionSort {

	public static void main(String[] args) {
		//int[] arr = { 15, 12, 13, 10, 14, 11 };
		int[] arr = { 10, 2, 6, 4, 3, 7, 5 };
		
		// 2번째부터 기준이 되어 시작한다. (왼쪽과 비교해야 하므로 2번째부터 시작한다)
		for (int i = 1; i < arr.length; i++) {
			int standard = arr[i]; // 기준
			int aux = i - 1; // 비교할 대상

			while (aux >= 0 && standard < arr[aux]) {
				arr[aux + 1] = arr[aux]; // 비교대상이 큰 경우 오른쪽으로 밀어냄
				aux--;
			}
			arr[aux + 1] = standard; // 기준값 저장
			System.out.println(i + ":" + Arrays.toString(arr));
		}
		System.out.println(Arrays.toString(arr));
		
	}


}

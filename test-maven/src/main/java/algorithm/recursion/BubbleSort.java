package algorithm.recursion;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * 거품 정렬(Bubble sort)은 두 인접한 원소를 검사하여 정렬하는 방법이다. 
 * 시간 복잡도가 O(n^2) 로 상당히 느리지만, 코드가 단순하기 때문에 자주 사용된다. 
 * 원소의 이동이 거품이 수면으로 올라오는 듯한 모습을 보이기 때문에 지어진 이름이다.
 */
public class BubbleSort {
	
	private static Logger log = LoggerFactory.getLogger(BubbleSort.class);

	public static void main(String[] args) {
		int arrayList[] = { 29, 10, 14, 37, 13 };
		log.debug("Final result:{}", Arrays.toString(bubbleSort(arrayList)));
	}

	public static int[] bubbleSort(int[] arr) {
		int temp;
		
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 1; j < arr.length - i; j++) {
				if (arr[j - 1] > arr[j]) {
					temp = arr[j - 1];  
					arr[j - 1] = arr[j]; // swap
					arr[j] = temp;
				}
			}
			log.debug("{}th iteration result: {}", (i + 1), Arrays.toString(arr));
		}
		return arr;
	}
}

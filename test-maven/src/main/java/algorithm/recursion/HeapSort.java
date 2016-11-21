package algorithm.recursion;

import java.util.Arrays;

/*
 * 정의: 힙이라는 자료구조를 이용하여 정렬하는 방법
 * 힙은 삭제 연산을 수행하면 가장 최상위 루트원소를 반환한다.
 * 최상위 루트원소는 힙 내부에서 가장 큰 원소이다.
 */
public class HeapSort {

	public static void main(String[] args) {
		int[] numbers = { 55, 2, 93, 1, 23, 10, 66, 12, 7, 54, 3 };
		
		System.out.println(Arrays.toString(numbers));
		//Arrays.sort(numbers);
		sort(numbers);
		System.out.println(Arrays.toString(numbers));
	}

	private static int[] a;
	private static int n;
	private static int left;
	private static int right;
	private static int largest;

	public static void buildheap(int[] a) {
		n = a.length - 1;
		for (int i = n / 2; i >= 0; i--) {
			maxheap(a, i);
		}
	}

	public static void maxheap(int[] a, int i) {
		left = 2 * i;
		right = 2 * i + 1;

		if (left <= n && a[left] > a[i]) {
			largest = left;
		} else {
			largest = i;
		}

		if (right <= n && a[right] > a[largest]) {
			largest = right;
		}
		if (largest != i) {
			exchange(i, largest);
			maxheap(a, largest);
		}
	}

	public static void exchange(int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}

	public static void sort(int[] myarray) {
		a = myarray;
		buildheap(a);
		for (int i = n; i > 0; i--) {
			exchange(0, i);
			n = n - 1;
			maxheap(a, 0);
		}
	}
}

package codility.lesson05.prefixsums;

public class MergeSortTest {

	/*
	 * Before Sort : PrintArray()
	 * 4 6 7 1 3 18 16 15 15 20 9
	 * Arter Sort : PrintArray()
	 * 1 3 4 6 7 9 15 15 16 18 20 
	 */
	public static void main(String[] args) {
		int inputArray[] = { 4, 6, 7, 1, 3, 18, 16, 15, 15, 20, 9 };
		System.out.println("Before Sort : PrintArray()");
		printArray(inputArray);
		mergeSort(inputArray, 0, inputArray.length - 1);
		System.out.println("Arter Sort : PrintArray()");
		printArray(inputArray);
	}

	public static void mergeSort(int[] array, int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			mergeSort(array, low, middle);
			mergeSort(array, middle + 1, high);
			merge(array, low, middle, high);
		}
	}

	public static void merge(int[] array, int low, int middle, int high) {
		int[] helper = new int[array.length];

		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}

		int helperLeft = low;
		int helperRight = middle + 1;
		int current = low;

		while (helperLeft <= middle && helperRight <= high) {
			if (helper[helperLeft] <= helper[helperRight]) {
				array[current] = helper[helperLeft];
				helperLeft++;

			} else {
				array[current] = helper[helperRight];
				helperRight++;

			}
			current++;

		}
		int remain = middle - helperLeft;
		for (int i = 0; i <= remain; i++) {
			array[current + i] = helper[helperLeft + i];
		}
	}

	public static final void printArray(int array[]) {
		for (int element : array) {
			System.out.print(element + " ");
		}
		System.out.println("");
	}
}

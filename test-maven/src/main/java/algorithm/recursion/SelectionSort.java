package algorithm.recursion;

/*
 * See more at: http://www.java2novice.com/java-sorting-algorithms/selection-sort/#sthash.1P1QkUc1.dpuf
 */
public class SelectionSort {

	public static void main(String[] args) {
		//int[] arr1 = { 29, 10, 14, 37, 13 };
		int[] arr1 = { 5, 4, 3, 2, 1, 0 };
		int[] arr2 = selectionSort(arr1);
		for (int i : arr2) {
			System.out.print(i);
			System.out.print(", ");
		}
	}

	// 가장 큰 값을 찾아서 맨끝자리 값과 바꾼다.
	public static int[] selectionSort(int[] arr) {
		
		for (int i = 0; i < arr.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[i] > arr[j]) { // 가장 큰 수를 찾아서 
					index = j;
				}
			}			
			int smallerNumber = arr[index];
			System.out.println("smallerNumber=" + smallerNumber + ":index=" + index);
			arr[index] = arr[i];
			arr[i] = smallerNumber;
		
			for (int j : arr) {
				System.out.print(j + ",");
			}
			System.out.println();
		}
		return arr;
	}
}

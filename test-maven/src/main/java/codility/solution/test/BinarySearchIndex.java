package codility.solution.test;

/*
 * http://kyejusung.com/2015/05/codility-com%EC%9D%84-%EC%86%8C%EA%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4/
 */
public class BinarySearchIndex {
	
	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6 };

		System.out.println(binarySearchIndex(arr, 2));
	}

	public static int binarySearchIndex(int[] arr, int item) {

		int length = arr.length;

		int hi = length - 1;
		int lo = 0;
		int mid;

		while (lo <= hi) {

			mid = (hi + lo) / 2;
			if (item > arr[mid]) {
				lo = mid + 1;
			} else if (item < arr[mid]) {
				hi = mid - 1;
			} else {
				return mid;
			}
		}

		return -1;

	}	

}

package codility.solution.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://kyejusung.com/2015/05/codility-com%EC%9D%84-%EC%86%8C%EA%B0%9C%ED%95%A9%EB%8B%88%EB%8B%A4/
 */
public class BinarySearchIndex {
	
	private static Logger log = LoggerFactory.getLogger(BinarySearchIndex.class);
	
	public static void main(String[] args) {

		int[] arr = { 1, 2, 3, 4, 5, 6 };

		log.debug("{}", binarySearchIndex(arr, 2));
		log.debug("{}", solution(arr, 7));
	}

	public static int binarySearchIndex(int[] arr, int item) {
		int length = arr.length;
		int hi = length - 1;
		int lo = 0;
		int mid;

		while (lo <= hi) {
			mid = (hi + lo) / 2; // 요넘 중요
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
	
	public static int solution(int[] A, int item) {
		log.debug("{}", A.length);
		int hi = A.length - 1; // ArrayIndexOutOfBoundsException 방지위해 -1
		int lo = 0;
		int mid = 0;
		
		while (lo <= hi) {
			mid = (hi + lo) / 2;
			if (item > A[mid]) {
				lo = mid + 1; // 더함
			} else if (item < A[mid]) {
				hi = mid - 1; // 뺌
			} else {
				return mid;
			}
		}
		return -1;
	}

}

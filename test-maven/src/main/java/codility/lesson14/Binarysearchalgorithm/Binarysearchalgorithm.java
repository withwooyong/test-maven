package codility.lesson14.Binarysearchalgorithm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Binarysearchalgorithm {

	private static Logger log = LoggerFactory.getLogger(Binarysearchalgorithm.class);
	
	public static void main(String[] args) {
		int[] A = new int[] { 1, 2, 3, 5, 4 };
		int key = 5;
		
		log.debug("{}", Arrays.binarySearch(A, key));		
		log.debug("{}", binarySearch(key, A));
		
	}
	
	public static int binarySearch(int key, int[] A) {
		int low = 0;
		int high = A.length - 1;
		
		while (low <= high) {
			int mid = (low + high) / 2; // 중간위치 지정
			int midVal = A[mid]; 		// 중간값 저장
			if (midVal < key) { 		// 만약 중간값이 찾을값보다 작다면
				low = mid + 1;  		// 한칸 증가하고 low로 지정
			} else if (midVal > key) { 	// 중간값이 찾을값보다 크면
				high = mid - 1; 		// 한칸 감소하고 high 지정
			} else {
				log.debug("{} {} {} {}", low, high, mid, midVal);
				return mid;
			}
		}
		return 0;
	}

}

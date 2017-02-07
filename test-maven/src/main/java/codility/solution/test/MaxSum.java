package codility.solution.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://jungmonster.tistory.com/126
 */
public class MaxSum {
	
	private static Logger log = LoggerFactory.getLogger(MaxSum.class);

	public static void main(String[] args) {
		int[] A = { -7, 4, -3, 6, 3, -8, 3, 4 };
		log.debug("{}", inefficientMaxSum(A));
		log.debug("{}", betterMaxSum(A));
		log.debug("{}", fastMaxSum(A, 0, A.length-1));
		log.debug("{}", fastestMaxSum(A));
		
	}
	
	private static int inefficientMaxSum(int[] A) {
		int N = A.length;
		int ret = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			for (int j = i; j < N; j++) {
				int sum = 0;
				for (int k = i; k <= j; k++) {
					sum += A[k];
				}
				ret = Math.max(ret,  sum);
			}
		}
		return ret;
	}
	
	private static int betterMaxSum(int[] A) {
		int N = A.length;
		int ret = Integer.MIN_VALUE;
		
		for (int i = 0; i < N; i++) {
			int sum = 0;
			for (int j = i; j < N; j++) {
				sum += A[j];
				ret = Math.max(ret,  sum);				
			}
		}
		return ret;
	}
	
	private static int fastMaxSum(int[] A, int low, int high) {
		if (low == high) {
			return A[low];
		}
		int mid = (low + high) / 2;
		int left = Integer.MIN_VALUE;
		int right = Integer.MIN_VALUE;
		int sum = 0;

//		for (int i = mid; i > low; --i) {
//			sum += A[i];
//			left = Math.max(left, sum);
//		}
		
		for (int i = low; i <= mid; i++) {
			sum += A[i];
			left = Math.max(left, sum);
		}

		sum = 0;
		for (int j = mid + 1; j <= high; j++) {
			sum += A[j];
			right = Math.max(right, sum);
		}

		int single = Math.max(fastMaxSum(A, low, mid), fastMaxSum(A, mid + 1, high));

		return Math.max(left + right, single);

	}
	
	// 연속된 부분 구간 중 그 합이 최대인 구간을 찾는 문제
	private static int fastestMaxSum(int[] A) {
		int N = A.length;
		int ret = Integer.MIN_VALUE;
		int psum = 0;
		for (int i = 0; i < N; i++) {
			psum = Math.max(psum, 0) + A[i];
			ret = Math.max(psum, ret);
		}
		return ret;
	}
	

}

package algorithm.algosrc.src.maxsum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxSum2 {

	private static Logger log = LoggerFactory.getLogger(MaxSum2.class);
	
	public static void main(String[] args) {
		/*
		 * run("N^3", inefficientMaxSum);
		 * run("N^2", betterMaxSum);
		 * run("NlgN", fastMaxSumAdapter);
		 * run("N", fastestMaxSum);
		 */
		int[] A = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		
		log.debug("{}", inefficientMaxSum(A));
		log.debug("{}", betterMaxSum(A));
		log.debug("{}", fastMaxSumAdapter(A));
		log.debug("{}", fastestMaxSum(A));
	}
	
	private static int fastMaxSumAdapter(int[] A) {
		return fastMaxSum(A, 0, A.length - 1);
	}
	
	// A[]의 연속된 부분 구간의 최대 합을 구한다. 시간 복잡도: O(n^3)
	private static int inefficientMaxSum(int[] A) {
		int N = A.length, ret = Integer.MIN_VALUE;
		for(int i = 0; i < N; ++i) 
			for(int j = i; j < N; ++j) {
				// 구간 A[i..j] 의 합을 구한다
				int sum = 0;
				for(int k = i; k <= j; k++) 
					sum += A[k];
				ret = Math.max(ret, sum);
			}
		return ret;
	}

	// A[]의 연속된 부분 구간의 최대 합을 구한다. 시간 복잡도: O(n^2)
	private static int betterMaxSum(int[] A) {
		int N = A.length, ret = Integer.MIN_VALUE;
		for(int i = 0; i < N; ++i) {
			int sum = 0;
			for(int j = i; j < N; ++j) {
				// 구간 A[i..j] 의 합을 구한다
				sum += A[j];
				ret = Math.max(ret, sum);
			}
		}
		return ret;
	}

	// A[lo..hi]의 연속된 부분 구간의 최대 합을 구한다. 시간 복잡도: O(nlgn)
	private static int fastMaxSum(int[] A, int lo, int hi) {
		// 기저 사례: 구간의 길이가 1 일 경우
		if(lo == hi) return A[lo];
		// 배열을 A[lo..mid], A[mid+1..hi] 의 두 조각으로 나눈다.
		int mid = (lo + hi) / 2;
		// 두 부분에 모두 걸쳐 있는 최대 합 구간을 찾는다. 이 구간은 
		// A[i..mid] 와 A[mid+1..j] 형태를 갖는 구간의 합으로 이루어진다.
		// A[i..mid] 형태를 갖는 최대 구간을 찾는다.
		int left = Integer.MIN_VALUE, right = Integer.MIN_VALUE, sum = 0;
		for(int i = mid; i >= lo; --i) {
			sum += A[i];
			left = Math.max(left, sum);
		}
		// A[mid+1..j] 형태를 갖는 최대 구간을 찾는다.
		sum = 0;
		for(int j = mid+1; j <= hi; ++j) {
			sum += A[j];
			right = Math.max(right, sum);
		}
		// 최대 구간이 두 조각 중 하나에만 속해 있는 경우의 답을 재귀호출로 찾는다.
		int single = Math.max(fastMaxSum(A, lo, mid), fastMaxSum(A, mid+1, hi));
		// 두 경우 중 최대값을 반환한다.
		return Math.max(left + right, single);
	}

	// A[]의 연속된 부분 구간의 최대 합을 구한다. 시간 복잡도: O(n)
	private static int fastestMaxSum(int[] A) {
		int N = A.length, ret = Integer.MIN_VALUE, psum = 0;
		for(int i = 0; i < N; ++i) {
			psum = Math.max(psum, 0) + A[i];
			ret = Math.max(psum, ret);
		}
		return ret;
	}

}

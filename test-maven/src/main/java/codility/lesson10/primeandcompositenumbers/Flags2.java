package codility.lesson10.primeandcompositenumbers;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Flags2 {
	
	private static Logger log = LoggerFactory.getLogger(Flags2.class);

	public static void main(String[] args) {
		int[] A = new int[] { 1, 5, 3, 4, 3, 4, 1, 2, 3, 4, 6, 2 };
		log.debug("{}", solution(A));
	}
	
	private static int solution(int[] A) {
		int N = A.length;
		ArrayList<Integer> peaks = new ArrayList<>();
		int maxPeaks;
		int maxFlags = 2;
		int nowFlags;
		int base;
		
		// peaks 구하기
		for (int i = 1; i < N - 1; i++) {
			if (A[i] > A[i - 1] && A[i] > A[i + 1]) {
				peaks.add(i);
			}
		}
		log.debug("{}", peaks.toString());
		int peaksLength = peaks.size();
		// peak가 2개일 경우 무조건 가능하므로 리턴
//		if (peaksLength > 2) {
//			return peaksLength;
//		}
		
		for (int K = 3; K <= peaksLength; ++K) {
			nowFlags = 1;
			base = 0;
			// K거리만큼 떨어진 봉우리를 찾는다.
			for (int j = 1; j < peaksLength; ++j) {
				if (peaks.get(j) - peaks.get(base) < K) {
					continue;
				}
				// K갯수만큼 깃발을 꼽았으면 종료
				if (++nowFlags >= K) {
					break;
				}
				base = j;
			}
			// 깃발 갯수가 증가하면 갱신
			if (maxFlags < nowFlags) {
				maxFlags = nowFlags;
			} else { // 깃발 갯수가 줄어든다면 그게 최대 갯수이다.
				return maxFlags;
			}
			
		}
		return maxFlags;
	}	
}

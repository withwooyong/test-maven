package codility.lesson08.leader;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EquiLeader2 {
	private static Logger log = LoggerFactory.getLogger(EquiLeader2.class);

	// return 2
	public static void main(String[] args) {
		int[] A = { 4, 3, 4, 4, 4, 2 };
		log.debug("{}", solution(A));

	}
	
	/*
	 * 접근방법:
	 * 순방향(왼쪽에서 오른쪽), 역방향(오른쪽에서 왼쪽)으로 차례대로 탐색하며 최빈값, 빈도수를 기록
	 * 순방향과 역방향 최빈값이 같고 빈도수가 절반을 초과한 수를 센다.
	 * ※ 순방향은 index와 동일하여 접근하기 쉽지만 역방향은 반대라 헷갈린다. 단순화하자.
	 * The goal is to count the number of equi leaders.
	 * The leader of this array is the value that occurs in more than half of the elements of A.
	 * we can find two equi leaders:
	 * 0, because sequences: (4) and (3, 4, 4, 4, 2) have the same leader, whose value is 4.
	 * 2, because sequences: (4, 3, 4) and (4, 4, 2) have the same leader, whose value is 4.
	 */
	public static int solution(int[] A) {
		if (A.length == 1)
			return 0;

		int equiLeadersNumber = 0; // Return Value
		HashMap<Integer, Integer> numberCounterMap = new HashMap<Integer, Integer>();
		BidirectionalModeRecoder[] bidirectionalModeRecoder = new BidirectionalModeRecoder[A.length - 1];

		int totCount = 0;
		int num = 0;
		int lastNum = 0;
		int modeNumCount = 0;

		// Forward direction
		for (int i = 0; i < A.length - 1; i++) {
			bidirectionalModeRecoder[i] = new BidirectionalModeRecoder();
			totCount++;
			num = A[i];
			Integer numCount = numberCounterMap.get(num);
			if (numCount == null)
				numCount = 0;
			numberCounterMap.put(num, numCount + 1);

			if (i == 0 || numCount + 1 > modeNumCount) {
				modeNumCount = numCount + 1;
				bidirectionalModeRecoder[i].setForwardModeNum(totCount, num, modeNumCount);
				lastNum = num;
			} else if (i > 0) {
				bidirectionalModeRecoder[i].setForwardModeNum(totCount, lastNum, modeNumCount);
			}
		}

		numberCounterMap.clear(); // Map Clear
		totCount = 0;
		num = 0;
		lastNum = 0;
		modeNumCount = 0;

		// Reverse direction
		for (int i = A.length - 1; i > 0; i--) {
			totCount++;
			num = A[i];
			Integer numCount = numberCounterMap.get(num);
			if (numCount == null)
				numCount = 0;
			numberCounterMap.put(num, numCount + 1);
			if (i == A.length - 1 || numCount + 1 > modeNumCount) {
				modeNumCount = numCount + 1;
				bidirectionalModeRecoder[i - 1].setReverseModeNum(totCount, num, modeNumCount);
				lastNum = num;
			} else if (i < A.length - 1) {
				bidirectionalModeRecoder[i - 1].setReverseModeNum(totCount, lastNum, modeNumCount);
			}
			if (bidirectionalModeRecoder[i - 1].isEquiLeader() == true) {
				equiLeadersNumber++;
			}
		}

		return equiLeadersNumber;
	}
}
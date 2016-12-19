package algorithm.algosrc.problems.clocks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 6.8 시계 맞추기 문제를 해결하는 완전 탐색 알고리즘
public class Clocks2 {
	
	private static Logger log = LoggerFactory.getLogger(Clocks2.class);

	private static int INF = 9999;
	private static int SWITCHES = 10;
	private static int CLOCKS = 16;
	// [SWITCHES][CLOCKS+1]
	private static char[][] linked = {
			{ 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
			{ 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'O' },
			{ 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'X' },
			{ 'X', 'O', 'O', 'O', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
			{ 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'X', 'O', 'X', 'O', 'O', 'O' },
			{ 'X', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X' },
			{ 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X' },
			{ 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X' },
			{ 'O', 'X', 'X', 'X', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O' },
			{ 'O', 'O', 'O', 'X', 'X', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'X', 'O', 'O' } };
	
	public static void main(String[] args) {
		
		/*
		 * 2
		 * 12 6 6 6 6 6 12 12 12 12 12 12 12 12 12 12
		 * 12 9 3 12 6 6 9 3 12 9 12 9 12 12 6 6
		 */
		int[] clocks = { 12, 6, 6, 6, 6, 6, 12, 12, 12, 12, 12, 12, 12, 12, 12, 12 };
		log.debug("{}", solve(clocks, 0)); // 2
	}
	
	private static boolean areAligned(int[] clocks) {
		for (int i = 0; i < CLOCKS; i++) {
			if (clocks[i] % 4 != 0) {
				return false;
			}
		}
		return true;
	}

	private static void push(int[] clocks, int swtch) {
		for (int clock = 0; clock < CLOCKS; ++clock) {
			if (linked[swtch][clock] == 'X') {
				clocks[clock] += 3;
			}
		}
	}

	private static int solve(int[] clocks, int swtch) {
		if (swtch == SWITCHES) {
			return areAligned(clocks) ? 0 : INF;
		}
		int ret = INF;
		for (int cnt = 0; cnt < 4; ++cnt) {
			ret = Math.min(ret, cnt + solve(clocks, swtch + 1));
			push(clocks, swtch);
		}
		return ret;
	}
}

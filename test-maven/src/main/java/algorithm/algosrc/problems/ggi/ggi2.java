package algorithm.algosrc.problems.ggi;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ggi2 {
	
	private static Logger log = LoggerFactory.getLogger(ggi2.class);
	/*
	 * 2
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 2
3 3 1 2 3 4 1
1 5 2 9 4 7 0
7
2 5 1 6 1 4 1
6 1 1 2 2 9 3
7 2 3 2 1 3 1
1 1 3 1 7 1 2
4 1 2 3 4 1 3
3 3 1 2 3 4 1
1 5 2 9 4 7 0 

	 */
	
	private static int n = 7;
	private static int[][] board = { 
			{ 2, 5, 1, 6, 1, 4, 1 }, 
			{ 6, 1, 1, 2, 2, 9, 3 }, 
			{ 7, 2, 3, 2, 1, 3, 1 },
			{ 1, 1, 3, 1, 7, 1, 2 }, 
			{ 4, 1, 2, 3, 4, 1, 2 }, 
			{ 3, 3, 1, 2, 3, 4, 1 }, 
			{ 1, 5, 2, 9, 4, 7, 0 } };
	
	private static int[][] cache = new int[board.length][board[0].length];
	/*
	 * [1, 0, 1, 0, 0, 0, 0], 
	 * [0, 0, 1, 1, 0, 0, 0], 
	 * [0, 0, 0, 0, 0, 0, 0], 
	 * [0, 0, 0, 1, 0, 0, 0], 
	 * [0, 0, 0, 1, 0, 0, 1], 
	 * [0, 0, 0, 0, 0, 0, 0], 
	 * [0, 0, 0, 0, 0, 0, 0]]
	 */
	public static void main(String[] args) {
		log.debug("{}", jump(0, 0));
		log.debug("{}", jump2(0, 0));
		log.debug("{}", Arrays.deepToString(cache));
	}

	private static boolean jump(int y, int x) {
		// 기저 사례: 게임판 밖을 벗어난 경우
		if (y >= n || x >= n) {
			return false;
		}
		// 기저 사례: 마지막 칸에 도착한 경우
		if (y == n - 1 && x == n - 1) {
			return true;
		}
		int jumpSize = board[y][x];
		
		if (jump(y + jumpSize, x) || jump(y, x + jumpSize)) {
			return true;
		}
		return false;
	}

	private static int jump2(int y, int x) {
		// 기저 사례: 게임판 밖을 벗어난 경우
		if (y >= n || x >= n) {
			return 0;
		}
		// 기저 사례: 마지막 칸에 도착한 경우
		if (y == n - 1 && x == n - 1) {
			return 1;
		}
		
		if (cache[y][x] != 0) {
			return cache[y][x];
		}
		int jumpSize = board[y][x];
		int ret = 0;
		if (jump2(y + jumpSize, x) > 0 || jump2(y, x + jumpSize) > 0) {
			ret = 1;
		}
		return cache[y][x] = ret;
	}
}

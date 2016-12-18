package algorithm.algosrc.problems.picnic;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 6.4 소풍 문제를 해결하는 재귀호출 코드 (입력값 이해못함.)
public class Picnic2 {
	
	private static Logger log = LoggerFactory.getLogger(Picnic2.class);
	
	private static int n = 10;

	public static void main(String[] args) {
		
		boolean[][] areFriends = new boolean[10][10];
		log.debug("{}", Arrays.deepToString(areFriends));
		/*
		 * 3
		 * 2 1
		 * 0 1
		 * 4 6
		 * 0 1 1 2 2 3 3 0 0 2 1 3
		 * 6 10
		 * 0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
		 */
		int a = 2;
		int b = 1;
		int c = 0;
		int d = 1;
		int e = 4;
		int f = 6;
		areFriends[a][b] = areFriends[b][a] = true;	
		areFriends[c][d] = areFriends[d][c] = true;
		areFriends[e][f] = areFriends[f][e] = true;
		log.debug("{}", Arrays.deepToString(areFriends));
		boolean[] taken = new boolean[10];
		//log.debug("{}", countPairings1(areFriends, taken)); // 잘못된
		log.debug("{}", countPairings2(areFriends, taken)); // 잘된
	}
	
	
	// taken[i] = i번째 학생이 짝을 이미 찾았으면 true, 아니면 false
	private static int countPairings1(boolean[][] areFriends, boolean[] taken) {
		// 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
		boolean finished = true;
		for (int i = 0; i < (n); i++)
			if (!taken[i])
				finished = false;
		if (finished)
			return 1;

		int ret = 0;
		for (int i = 0; i < (n); i++)
			for (int j = 0; j < (n); j++)
				if (!taken[i] && !taken[j] && areFriends[i][j]) {
					taken[i] = taken[j] = true;
					ret += countPairings1(areFriends, taken);
					taken[i] = taken[j] = false;
				}
		return ret;
	}
	
	
	private static int countPairings2(boolean[][] areFriends, boolean[] taken) {
		// 남은 학생들 중 가장 번호가 빠른 학생을 찾는다
		int firstFree = -1;
		for (int i = 0; i < n; i++) {
			if (!taken[i]) {
				firstFree = i;
				break;
			}
		}
		// 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
		if (firstFree == -1) {
			return 1;
		}
		int ret = 0;
		log.debug("{}", Arrays.toString(taken));
		for (int pairWith = (firstFree + 1); pairWith < n; pairWith++) {
			if (!taken[pairWith] && areFriends[firstFree][pairWith]) {
				taken[firstFree] = true;
				taken[pairWith] = true;
				ret += countPairings2(areFriends, taken);
				taken[firstFree] = false;
				taken[pairWith] = false;
			}
		}
		log.debug("{} {}", ret, Arrays.toString(taken));
		return ret;
	}

}

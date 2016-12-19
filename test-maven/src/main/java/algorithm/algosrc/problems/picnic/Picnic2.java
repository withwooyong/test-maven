package algorithm.algosrc.problems.picnic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 6.4 소풍 문제를 해결하는 재귀호출 코드
// 6.5 소풍 문제를 해결하는 재귀호출 코드
public class Picnic2 {
	
	private static Logger log = LoggerFactory.getLogger(Picnic2.class);

	public static void main(String[] args) {
		/*
		 * 3
		 * 2 1
		 * 0 1
		 * 4 6
		 * 0 1 1 2 2 3 3 0 0 2 1 3
		 * 6 10
		 * 0 1 0 2 1 2 1 3 1 4 2 3 2 4 3 4 3 5 4 5
		 */
//		int n = 2; // 학생수
//		int[] A = { 0, 1 }; // 1쌍 result 1
		
//		int n = 4; // 학생수
//		int[] A = { 0, 1, 1, 2, 2, 3, 3, 0, 0, 2, 1, 3 }; // 6쌍 result 3
		
		int n = 6; // 학생수
		int[] A = { 0, 1, 0, 2, 1, 2, 1, 3, 1, 4, 2, 3, 2, 4, 3, 4, 3, 5, 4, 5 }; // 10쌍 result 4
		
		//int m = A.length / 2; // 친구쌍의수
		
		boolean[][] areFriends = new boolean[n][n];
		
		for (int i = 0; i < A.length; i += 2) {
			int a = A[i];
			int b = A[i + 1];
			areFriends[a][b] = true;
			areFriends[b][a] = true;	
		}
		
		//log.debug("{}", Arrays.deepToString(areFriends));
		boolean[] taken = new boolean[n];
		log.debug("{}", countPairings1(areFriends, taken)); // 잘못된
		log.debug("{}", countPairings2(areFriends, taken)); // 잘된
	}
	
	private static int countPairings2(boolean[][] areFriends, boolean[] taken) {
		// 남은 학생들 중 가장 번호가 빠른 학생을 찾는다
		//log.debug("{}", Arrays.deepToString(areFriends));
		//log.debug("{}", Arrays.toString(taken));
		
		int firstFree = -1;
		for (int i = 0; i < taken.length; i++) {
			if (taken[i] == false) {
				firstFree = i;
				break;
			}
		}
		
		// 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
		if (firstFree == -1) {
			return 1;
		}
		int ret = 0;
		//log.debug("{}", Arrays.toString(taken));
		for (int pairWith = (firstFree + 1); pairWith < taken.length; pairWith++) {
			//log.debug("{} {} ", pairWith, firstFree);
			if (taken[pairWith] == false && areFriends[firstFree][pairWith] == true) {
				taken[firstFree] = true;
				taken[pairWith] = true;
				ret += countPairings2(areFriends, taken);
				taken[firstFree] = false;
				taken[pairWith] = false;
			}
		}
		//log.debug("{} {}", ret, Arrays.toString(taken));
		return ret;
	}
	
	
	// taken[i] = i번째 학생이 짝을 이미 찾았으면 true, 아니면 false
	private static int countPairings1(boolean[][] areFriends, boolean[] taken) {
		// 기저 사례: 모든 학생이 짝을 찾았으면 한 가지 방법을 찾았으니 종료한다.
		boolean finished = true;
		for (int i = 0; i < taken.length; i++)
			if (!taken[i])
				finished = false;
		if (finished)
			return 1;

		int ret = 0;
		for (int i = 0; i < taken.length; i++)
			for (int j = 0; j < taken.length; j++)
				if (!taken[i] && !taken[j] && areFriends[i][j]) {
					taken[i] = taken[j] = true;
					ret += countPairings1(areFriends, taken);
					taken[i] = taken[j] = false;
				}
		return ret;
	}
	
	
	

}

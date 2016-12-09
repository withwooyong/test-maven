package codility.lesson09.maximumsliceproblem;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Path {

	private static Logger log = LoggerFactory.getLogger(Path.class);
	/*
	 * 제일 만족하는 길찾기 문제
	 * 6 X 6 바둑판같은 길이 있다고 하고, 
	 * 1) 최소경로로 가야하며
	 * 2) 밟고 다니는 숫자의 합이 최대인 길을 찾아야 하며
	 * 3) 찾는 속도가 좀 빨라야한다.
	 * - 처음인경우 (즉 1 X 1 블럭인경우)
	 * - 위쪽인경우 (1 X N) 이전단계와 지금 점수를 더한다
	 * - 옆쪽인 경우 (N X 1) 이전단계와 지금 점수를 더한다.
	 * - 아니면 그냥 보통인 경우 (N X N) 위나 옆 두칸 중 큰 숫자를 선택해서 더한다.
	 */
	public static void main(String[] args) {
		int[][] map = { 
				{ 1, 2, 4, 1, 5, 5 }, 
				{ 2, 5, 2, 1, 4, 5 }, 
				{ 2, 5, 2, 1, 5, 6 }, 
				{ 1, 3, 4, 2, 5, 1 },
				{ 1, 2, 5, 2, 4, 1 }, 
				{ 2, 5, 1, 4, 3, 4 } };
		
		log.debug("{}", Arrays.deepToString(map));
		log.debug("{}", sum(map, 6 - 1, 6 - 1));
	}

	public static int sum(int[][] map, int x, int y) {
		if (x == 0 && y == 0) { 					// 처음인경우 (즉 1 X 1 블럭인경우)
			return map[x][y];
		} else if (x == 0) { 						// 위쪽인경우 (1 X N) 이전단계와 지금 점수를 더한다
			return sum(map, x, y - 1) + map[x][y];
		} else if (y == 0) { 						// 옆쪽인 경우 (N X 1) 이전단계와 지금 점수를 더한다.
			return sum(map, x - 1, y) + map[x][y];
		} else {									// 아니면 그냥 보통인 경우 (N X N) 위나 옆 두칸 중 큰 숫자를 선택해서 더한다.
			return max(sum(map, x - 1, y), sum(map, x, y - 1)) + map[x][y];
		}
	}

	public static int max(int n1, int n2) {
		return (n1 > n2) ? n1 : n2;
	}
}

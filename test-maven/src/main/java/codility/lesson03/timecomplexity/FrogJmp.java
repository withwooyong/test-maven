package codility.lesson03.timecomplexity;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A small frog wants to get to the other side of the road. 
 * The frog is currently located at position X and wants to get to a position greater than or equal to Y. 
 * The small frog always jumps a fixed distance, D.
 * Count the minimal number of jumps that the small frog must perform to reach its target.
 * 
 * Write a function:
 * class Solution { public int solution(int X, int Y, int D); }
 * that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
 * 
 * For example, given:
 *   X = 10
 *   Y = 85
 *   D = 30
 * the function should return 3, because the frog will be positioned as follows:
 * after the first jump, at position 10 + 30 = 40
 * after the second jump, at position 10 + 30 + 30 = 70
 * after the third jump, at position 10 + 30 + 30 + 30 = 100
 * 
 * Assume that:
 * X, Y and D are integers within the range [1..1,000,000,000];
 * X ≤ Y.
 * Complexity:
 * expected worst-case time complexity is O(1);
 * expected worst-case space complexity is O(1).
 * 
 * 작은 개구리가 길 건너편으로 가고 싶어합니다.
 * 개구리는 현재 X 위치에 있으며 Y 이상의 위치를 원합니다.
 * 작은 개구리는 항상 고정 된 거리 D로 점프합니다.
 * 작은 개구리가 표적에 도달하기 위해 수행해야하는 최소 점프 수를 세십시오.
 * 함수 작성 :
 * 클래스 솔루션 {public int solution (int X, int Y, int D); }
 * 3 개의 정수 X, Y, D가 주어지면, X 위치에서 Y 이상의 위치까지 최소 점프 수를 리턴합니다.
 * 예를 들어, 주어진 :
 * X = 10
 * Y = 85
 * D = 30
 * 개구리는 다음과 같이 위치 할 것이므로 함수는 3을 반환해야합니다.
 * 첫 번째 점프 후, 위치 10 + 30 = 40
 * 두 번째 점프 후, 위치 10 + 30 + 30 = 70
 * 세 번째 점프 후, 위치 10 + 30 + 30 + 30 = 100
 * 다음과 같이 가정하십시오.
 * X, Y 및 D는 [1..1,000,000,000] 범위의 정수입니다.
 * X ≤Y.
 * 복잡성 :
 *  예상 된 최악의 경우의 시간 복잡도는 O (1)이다.
 *  예상 된 최악의 경우의 공간 복잡성은 O (1)이다.
 * @author wooyong
 *
 */
public class FrogJmp {
	
	private static Logger log = LoggerFactory.getLogger(FrogJmp.class);

	/*
	 * Count minimal number of jumps from position X to Y.
	 */
	// 개구리가 X-->Y로 가는 점프(D)를 이용하여 최소 점프 횟수 구하기
	// 접근방법 : 처음 위치의 값(X)을 제외한 Y위치를 구한 후 점프거리로 나눈 몫을 올림하여 횟수 계산
	// (10, 71, 30) -->  (71-10) / 30 = 2.03 => 3
	public static void main(String[] args) {
		int X = 10, Y = 85, D = 30;
		log.debug("{}", solution(X, Y, D));
		log.debug("{}", solution1(X, Y, D));
		log.debug("{}", mySolution(X, Y, D));
	}
	
	public static int mySolution(int X, int Y, int D) {
		double result = (Y - X) / (double)D;
		log.debug("{}", result);
		return (int)Math.ceil(result);
	}
	
	public static int solution1(int X, int Y, int D) {
		int result = 0;
		int distance = Y - X;
		result = (distance % D == 0) ? (distance / D) : (distance / D) + 1;
		return result;
	}
	
	// 이게 좋음.
	public static int solution(int X, int Y, int D) {
		int result = 0;
		if ((X > 0) && (X < 1000000000) && (Y > 0) && (Y < 1000000000) && (Y > 0) && (Y < 1000000000) && (X <= Y)) {
			int distance = Y - X;
			result = (distance % D == 0) ? (distance / D) : (distance / D) + 1;
		} 
		return result; 
    }
}

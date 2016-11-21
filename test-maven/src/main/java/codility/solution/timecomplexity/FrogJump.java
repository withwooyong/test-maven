/*
	A small frog wants to get to the other side of the road. 
	The frog is currently located at position X and wants to get to a position greater than or equal to Y. 
	The small frog always jumps a fixed distance, D.
	Count the minimal number of jumps that the small frog must perform to reach its target.
	
	Write a function:
	class Solution { public int solution(int X, int Y, int D); }
	that, given three integers X, Y and D, returns the minimal number of jumps from position X to a position equal to or greater than Y.
	
	For example, given:
	  X = 10
	  Y = 85
	  D = 30
	the function should return 3, because the frog will be positioned as follows:
	after the first jump, at position 10 + 30 = 40
	after the second jump, at position 10 + 30 + 30 = 70
	after the third jump, at position 10 + 30 + 30 + 30 = 100
	
	Assume that:
	X, Y and D are integers within the range [1..1,000,000,000];
	X ≤ Y.
	
	Complexity:
	expected worst-case time complexity is O(1);
	expected worst-case space complexity is O(1).
	
	작은 개구리가 도로 반대편으로 가고 싶어합니다. 개구리는 현재 X 위치에 있으며 Y보다 크거나 같은 위치로 가고 싶어합니다. 작은 개구리는 항상 고정 된 거리 D를 점프합니다.
	작은 개구리가 표적에 도달하기 위해 수행해야하는 점프의 최소 횟수를 세십시오.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int X, int Y, int D); }
	세 개의 정수 X, Y, D가 주어지면, X 위치에서 Y 이상의 위치까지 최소 점프 수를 반환합니다.
	
	예를 들어, 주어진 :
	X = 10
	Y = 85
	D = 30.
	개구리는 다음과 같이 위치 할 것이므로 함수는 3을 반환해야합니다.
	첫 번째 점프 후, 위치 10 + 30 = 40
	두 번째 점프 후, 위치 10 + 30 + 30 = 70
	세 번째 점프 후, 위치 10 + 30 + 30 + 30 = 100
	
	가정하자면 :
	X, Y 및 D는 [1..1,000,000,000] 범위의 정수입니다.
	X ≤Y.
	
	복잡성:
	기대 최악의 경우의 시간 복잡도는 O (1)이다.
	예상 최악의 공간 복잡도는 O (1)입니다.
 */

//SCORE: 100/100
package codility.solution.timecomplexity;

public class FrogJump {

	public static void main(String[] args) {
		int X = 10; // 현재위치
		int Y = 85; // 가고싶어하는 위치
		int D = 30; // 한번에 가는 거리
		System.out.println(solution(X, Y, D));
	}
	
	// (가고싶어하는위치 - 현재위치) / 한번에 가는 거리 
	public static int solution(int x, int y, int d) {
		return (int)Math.ceil((double)(y - x) / d);
	}
}

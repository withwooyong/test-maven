/*
 * Two positive integers N and M are given.
 * Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
 * 
 * You start to eat the chocolates. 
 * After eating a chocolate you leave only a wrapper.
 * You begin with eating chocolate number 0. 
 * Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
 * More precisely, if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
 * You stop eating when you encounter an empty wrapper.
 * 
 * For example, given integers N = 10 and M = 4. 
 * You will eat the following chocolates: 0, 4, 8, 2, 6.
 * The goal is to count the number of chocolates that you will eat, following the above rules.
 * 
 * Write a function:
 * class Solution { public int solution(int N, int M); }
 * 
 * that, given two positive integers N and M, returns the number of chocolates that you will eat.
 * 
 * For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
 * 
 * Assume that:
 * N and M are integers within the range [1..1,000,000,000].
 * 
 * Complexity:
 * expected worst-case time complexity is O(log(N+M));
 * expected worst-case space complexity is O(1).
	
	2 개의 양의 정수 N과 M이 주어진다. 
	정수 N은 0에서 N-1까지 번호가 매겨진 원 안에 배열 된 초콜릿의 수를 나타냅니다.
	당신은 초콜릿을 먹기 시작합니다. 
	초콜릿을 먹은 후에는 포장지 만 남겨 두십시오.
	당신은 초콜릿 번호 0을 먹기 시작합니다. 
	그러면 다음 M - 1 초콜릿이나 포장지를 생략하고 다음과 같은 것을 먹습니다.
	좀 더 정확히 말하자면, 초콜릿 번호 X를 먹었다면, 다음으로 숫자 (X + M) N (나머지 부분)을 가진 초콜릿을 먹을 것입니다.
	빈 래퍼가 발생하면 먹는 것을 멈춥니다.
	
	예를 들어, 주어진 정수 N = 10 및 M = 4. 다음과 같은 초콜릿을 먹을 것입니다 : 0, 4, 8, 2, 6.
	목표는 위의 규칙에 따라 먹을 초콜릿의 수를 세는 것입니다.
	
	함수 작성 :
	클래스 솔루션 {public int solution (int N, int M); }
	두 개의 양의 정수 N과 M이 주어지면 먹을 수있는 초콜릿의 수를 반환합니다.
	예를 들어, 주어진 정수 N = 10 및 M = 4. 함수는 위에서 설명한대로 5를 반환해야합니다.
	
	가정하자면 :
	N과 M은 [1..1,000,000,000] 범위의 정수입니다.
	복잡성:
	예상 된 최악의 경우의 시간 복잡도는 O (log (N + M))이다.
	예상 최악의 공간 복잡도는 O (1)입니다.
 */

//SCORE: 100/100
package codility.solution.euclideanalgorithm;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChocoladeByNumbers {
	
	private static Logger log = LoggerFactory.getLogger(ChocoladeByNumbers.class);

	public static void main(String[] args) {
		int N = 10;
		int M = 4;
		int res = 1;
		log.debug("{}", solution(N, M, res));
		log.debug("{}", mySolution(N, M));
	}
	
	private static int mySolution(int N, int M) {
		// Integer N represents the number of chocolates arranged in a circle, numbered from 0 to N − 1.
		// You begin with eating chocolate number 0. 
		// Then you omit the next M − 1 chocolates or wrappers on the circle, and eat the following one.
		// More precisely, 
		// if you ate chocolate number X, then you will next eat the chocolate with number (X + M) modulo N (remainder of division).
		// You stop eating when you encounter an empty wrapper.
		// For example, given integers N = 10 and M = 4. the function should return 5, as explained above.
		// 결과 값 0, 4, 8, 2, 6.
		//  
		// 0부터 시작 You begin with eating chocolate number 0.
		// X(9)+M(4)/9=4, 0+4=4
		// X(8)+M(4)/8=4, 4+4=8
		// X(7)+M(4)/7=4, 8+4=12%10
		// X(6)+M(4)/6=4, 2+4=6
		// X(5)+M(4)/5=4, 6+4=10%10 <= 포장지
		
		int X = N;
		int position = 0;
		int sum = 0;
		int[] chocolates = new int[N];
		
		for (int i = 0; i < chocolates.length; i++) {
			chocolates[i] = 0;
		}
		
		for (int i = 0; i < N; i++) {
			if (i == 0) { // 0번째 초콜릿
				chocolates[i] = 1;
				log.debug("{} {} {}", sum, position, Arrays.toString(chocolates));
			} else {
				//log.debug("{} {} {}", X, M, position);
				
				position = (X + M) % X;
				sum += position;
				if (sum >= N) {
					sum = sum % N;
				}
				if (chocolates[sum] == 1) { // 포장지
					break;
				} else {
					chocolates[sum] = 1;
				}
				log.debug("{} {} {}", sum, position, Arrays.toString(chocolates));
			}
			X--;
		}
		return 0;
	}
	

	private static int solution(int N, int M, int res) {
		return N / greatestCommonDivisor(N, M, 1);
	}

	private static int greatestCommonDivisor(int a, int b, int res) {
		if (a == b)
			return res * a;
		else if (a % 2 == 0 && b % 2 == 0)
			return greatestCommonDivisor(a / 2, b / 2, res * 2);
		else if (a % 2 == 0)
			return greatestCommonDivisor(a / 2, b, res);
		else if (b % 2 == 0)
			return greatestCommonDivisor(a, b / 2, res);
		else if (a > b)
			return greatestCommonDivisor(a - b, b, res);
		else
			return greatestCommonDivisor(a, b - a, res);
	}
}

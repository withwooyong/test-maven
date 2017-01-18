package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11778 {	
	
	private static Logger log = LoggerFactory.getLogger(P11778.class); 

	/*
	 * 피보나치 수와 최대공약수
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	146	60	50	60.241%
문제
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n과 m이 주어졌을 때, n번째 피보나치 수와 m번째 피보나치 수의 최대공약수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n과 m이 주어진다. n과 m은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 n번째 피보나치 수와 m번째 피보나치 수의 최대공약수를 1,000,000,007으로 나눈 나머지를 출력한다.

예제 입력  복사
16 24
예제 출력  복사
21
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11443 {	
	
	private static Logger log = LoggerFactory.getLogger(P11443.class); 

	/*
	 * 짝수번째 피보나치 수의 합
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	256 MB	95	56	53	85.484%
문제
피보나치 수는 0과 1로 시작한다. 0번째 피보나치 수는 0이고, 1번째 피보나치 수는 1이다. 그 다음 2번째 부터는 바로 앞 두 피보나치 수의 합이 된다.

이를 식으로 써보면 Fn = Fn-1 + Fn-2 (n>=2)가 된다.

n=17일때 까지 피보나치 수를 써보면 다음과 같다.

0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597

n이 주어졌을 때, 0번째 피보나치 수부터 n번째 피보나치 수 중에서 짝수번째 피보나치 수의 합을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 n이 주어진다. n은 1,000,000,000,000,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 0번째 피보나치 수부터 n번째 피보나치 수 중에서 짝수번째 피보나치 수의 합을 1,000,000,007으로 나눈 나머지를 출력한다.

예제 입력  복사
7
예제 출력  복사
12
예제 입력 2  복사
10
예제 출력 2  복사
88
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

package algorithm.baekjoon.middle2.F_min_value;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11003 {	
	
	private static Logger log = LoggerFactory.getLogger(P11003.class); 

	/*
	 * 최소값 찾기
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
3 초	256 MB	988	278	161	27.521%
문제
N개의 수 A1, A2, ..., AN과 L이 주어진다.

Di = Ai-L+1 ~ Ai 중의 최소값이라고 할 때, D에 저장된 수를 출력하는 프로그램을 작성하시오. 이 때, i ≤ 0 인 Ai는 무시하고 D를 구해야 한다.

입력
첫째 줄에 N과 L이 주어진다. (1 ≤ L ≤ N ≤ 5,000,000)

둘째 줄에는 N개의 수 Ai가 주어진다. (-109 ≤ Ai ≤ 109)

출력
첫째 줄에 Di를 공백으로 구분하여 순서대로 출력한다.

예제 입력  복사
12 3
1 5 2 3 6 2 3 7 3 5 2 6
예제 출력  복사
1 1 1 2 2 2 2 2 3 3 2 2
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

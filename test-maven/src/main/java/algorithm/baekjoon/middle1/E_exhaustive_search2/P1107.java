package algorithm.baekjoon.middle1.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1107 {	
	
	private static Logger log = LoggerFactory.getLogger(P1107.class); 

	/*
	 * 리모컨
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	3201	630	402	18.741%
문제
수빈이는 TV를 보고 있다. 수빈이는 채널을 돌리려고 했지만, 버튼을 너무 세게 누르는 바람에, 일부 숫자 버튼을 망가뜨렸다.

리모컨에는 버튼이 0부터 9까지 숫자, +와 -가 있다. +를 누르면 현재 보고있는 채널에서 +1된 채널로 이동하고, -를 누르면 -1된 채널로 이동한다. 채널 0에서 -를 누른 경우에는 채널이 변하지 않고, 채널은 무한대 만큼 있다.

수빈이가 지금 이동하려고 하는 채널은 N이다. 어떤 버튼이 망가져있는지 주어졌을 때, N으로 이동하기 위해서 버튼을 최소 몇 번 눌러야하는지 구하는 프로그램을 작성하시오. 

수빈이가 지금 보고 있는 채널은 100번이다.

입력
첫째 줄에 수빈이가 이동하려고 하는 채널 N (0 ≤ N ≤ 500,000)이 주어진다.  둘째 줄에는 망가진 버튼의 개수 M (0 ≤ M ≤ 10)이 주어진다. 셋째 줄에는 망가진 버튼이 주어지며, 같은 버튼이 중복되서 주어지는 경우는 없다.

출력
첫째 줄에 N으로 이동하기 위해 버튼을 최소 몇 번 눌러야 하는지를 출력한다.

예제 입력  복사
5457
3
6 7 8
예제 출력  복사
6
예제 입력 2  복사
100
5
0 1 2 3 4
예제 출력 2  복사
0
예제 입력 3  복사
500000
8
0 2 3 4 6 7 8 9
예제 출력 3  복사
11117
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/2c32984ad5c42b333c38
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

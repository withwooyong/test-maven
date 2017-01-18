package algorithm.baekjoon.middle2.G_sum;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1395 {	
	
	private static Logger log = LoggerFactory.getLogger(P1395.class); 

	/*
	 * 스위치
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	396	172	107	41.313%
문제
준규네 집에는 총 N개의 스위치가 있고 이를 편하게 1번부터 N번까지 차례대로 번호를 매겼다. 그리고 준규의 취미는 이 스위치들을 켜고 끄는 것이다.

준규가 하는 스위치를 갖고 노는 일은 크게 두 가지이다. 하나는 A번부터 B번 사이의 스위치 상태를 반전시키는 것이고 다른 하나는 C번부터 D번 사이의 스위치 중 켜져 있는 상태의 스위치의 개수를 세는 것이다.

하지만 준규가 싫증을 느껴 우리가 이 귀찮은 일을 떠맡게 되었고 프로그래밍을 통해 일을 처리하도록 결정하였다.

입력
첫 줄에는 스위치의 개수 N(2 ≤ N ≤ 100,000)과 처리할 일의 개수 M(1 ≤ M ≤ 100,000)이 주어진다. 다음 M개의 줄에 대해 각 줄에 처리할 일에 대한 정보가 담겨진 세 개의 정수 O, Si, Ti가 입력된다. O가 0이면 Si번 스위치부터 Ti번 스위치까지 스위치 상태를 반전시키는 일이고 1이면 Si번 스위치부터 Ti번 스위치까지 중 켜져 있는 상태의 스위치 개수를 묻는 일이다. 단, 초기에는 모든 스위치의 상태는 꺼져있는 상태로 되어있다.

출력
입력에서 켜진 스위치 개수에 대한 답을 한 줄에 하나씩 출력한다.

예제 입력  복사
4 5
0 1 2
0 2 4
1 2 3
0 2 4
1 1 4
예제 출력  복사
1
2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/247c11d7da24f577d74d 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

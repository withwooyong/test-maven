package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1126 {	
	
	private static Logger log = LoggerFactory.getLogger(P1126.class); 

	/*
	 * 같은 탑
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1646	342	197	17.732%
문제
홍준이는 N개의 직사각형 블럭을 가지고 있다. 홍준이는 블럭 위에 또다른 블럭을 올려놓는 방식으로 탑을 만들 수 있다. 이 때, 두 개의 탑을 만드는데, 이 두 탑의 높이가 같게 만드려고 한다. (각 탑은 적어도 한 개의 블럭을 포함해야 한다) 홍준이는 되도록이면 탑의 높이를 최대로 하려고 한다. 그리고 모든 블럭을 사용할 필요는 없다.

각 블럭의 높이가 주어질 때, 홍준이가 만들 수 있는 탑의 높이의 최대값을 출력하는 프로그램을 작성하시오.
 

입력
첫째 줄에 조각의 개수 N이 주어진다. N은 50보다 작거나 같은 자연수이다. 둘째 줄에 각 조각의 높이가 주어진다. 높이는 500,000보다 작거나 같은 자연수이고, 모든 조각의 높이의 합은 500,000을 넘지 않는다.

출력
첫째 줄에 문제의 정답을 출력한다. 불가능할 때는 -1을 출력한다.

예제 입력  복사
3
2 3 5
예제 출력  복사
5
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/bfc5bacbe4fae6558cba43eb4da8ec5e
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

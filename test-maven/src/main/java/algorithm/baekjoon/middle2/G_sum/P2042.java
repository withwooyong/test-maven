package algorithm.baekjoon.middle2.G_sum;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2042 {	
	
	private static Logger log = LoggerFactory.getLogger(P2042.class); 

	/*
	 * 구간 합 구하기
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	256 MB	4105	1394	775	32.025%
문제
어떤 N개의 수가 주어져 있다. 그런데 중간에 수의 변경이 빈번히 일어나고 그 중간에 어떤 부분의 합을 구하려 한다. 만약에 1,2,3,4,5 라는 수가 있고, 3번째 수를 6으로 바꾸고 2번째부터 5번째까지 합을 구하라고 한다면 17을 출력하면 되는 것이다. 그리고 그 상태에서 다섯 번째 수를 2로 바꾸고 3번째부터 5번째까지 합을 구하라고 한다면 12가 될 것이다.

입력
첫째 줄에 수의 개수 N(1<=N<=1,000,000)과 M(1<=M<=10,000), K(1<=k<=10,000) 가 주어진다. M은 수의 변경이 일어나는 회수이고, K는 구간의 합을 구하는 회수이다. 그리고 둘째 줄부터 N+1번째 줄까지 N개의 수가 주어진다. 그리고 N+2번째 줄부터 N+M+K+1번째 줄까지 세 개의 정수 a,b,c가 주어지는데, a가 1인 경우 b번째 수를 c로 바꾸고 a가 2인 경우에는 b번째 수부터 c번째 수까지의 합을 구하여 출력하면 된다.

출력
첫째 줄부터 K줄에 걸쳐 구한 구간의 합을 출력한다. (단 정답은 long long 범위를 넘지 않는다)

예제 입력  복사
5 2 2
1
2
3
4
5
1 3 6
2 2 5
1 5 2
2 3 5
예제 출력  복사
17
12
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/43c378ae1661830f1d92
	 * https://gist.github.com/Baekjoon/fe54fa44050f0e1f4826 
	 * https://gist.github.com/Baekjoon/20b134a15afb54eddb79
	 * https://gist.github.com/Baekjoon/fcf34370c7c51c436647b8266f8dab95
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		
		sc.close();
	}
}

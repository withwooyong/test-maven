package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9461 {

	private static Logger log = LoggerFactory.getLogger(P9461.class);
	
	/*
	 * 파도반 수열
	 * 문제
오른쪽 그림과 같이 삼각형이 나선 모양으로 놓여져 있다. 첫 삼각형은 정삼각형으로 변의 길이는 1이다. 그 다음에는 다음과 같은 과정으로 정삼각형을 계속 추가한다. 나선에서 가장 긴 변의 길이를 k라 했을 때, 그 변에 길이가 k인 정삼각형을 추가한다.

파도반 수열 P(N)은 나선에 있는 정삼각형의 변의 길이이다. P(1)부터 P(10)까지 첫 10개 숫자는 1, 1, 1, 2, 2, 3, 4, 5, 7, 9이다.

N이 주어졌을 때, P(N)을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 T가 주어진다. 각 테스트 케이스는 한 줄로 이루어져 있고, N이 주어진다. (1 ≤ N ≤ 100)

출력
각 테스트 케이스 마다 P(N)을 출력한다.

2
6
12

3
16
	 */
	public static void main(String[] args) {
		solution();

	}
	
	/*
	 * https://gist.github.com/Baekjoon/e7826c911f92e1fb0369
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        long[] d = {0,1,1,1,2,2,3,4,5,7,9};
        d = Arrays.copyOf(d, 101);
        for (int i=10; i<=100; i++) {
            d[i] = d[i-1] + d[i-5];
        }
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            System.out.println(d[n]);
        }
        sc.close();
	}

}

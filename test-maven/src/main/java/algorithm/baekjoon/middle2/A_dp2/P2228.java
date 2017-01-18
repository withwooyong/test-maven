package algorithm.baekjoon.middle2.A_dp2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2228 {	
	
	private static Logger log = LoggerFactory.getLogger(P2228.class); 

	/*
	 * 구간 나누기
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1618	425	243	23.547%
문제
N(1≤N≤100)개의 수로 이루어진 1차원 배열이 있다. 이 배열을 M(1≤M≤N/2 올림)개의 구간으로 나눠서 구간에 속한 수들의 총 합이 최대가 되도록 하려 한다. 단, 다음의 조건들이 만족되어야 한다.

하나의 구간은 하나 이상의 연속된 수들로 이루어진다.
두 개의 구간이 서로 겹치거나 붙어 있어서는 안 된다.
M개의 구간이 모두 있어야 한다. M개 이하가 아니다.
N개의 수들이 주어졌을 때, 답을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 두 정수 N, M이 주어진다. 다음 N개의 줄에는 배열을 이루는 수들이 차례로 주어진다. 배열을 이루는 수들은 -32768 이상 32767 이하의 정수이다.

출력
첫째 줄에 답을 출력한다.

예제 입력  복사
6 2
-1
3
1
2
4
-1
예제 출력  복사
9
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/30a479acead102c97c28
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[]a = new int[n+1];
        int[]s = new int[n+1];
        for (int i=1; i<=n; i++) {
            a[i] = sc.nextInt();
            s[i] = s[i-1] + a[i];
        }
        int[][] d = new int[n+1][m+1];
        boolean[][] c = new boolean[n+1][m+1];
        System.out.println(go(a,s,c,d,n,m));
		sc.close();
	}
	
	public static int min = -32786*101;
    public static int go(int[]a, int []s, boolean[][]c, int[][]d, int n, int m) {
        if (m == 0) {
            return 0;
        }
        if (n <= 0) {
            return min;
        }
        if (c[n][m]) {
            return d[n][m];
        }
        c[n][m] = true;
        d[n][m] = go(a,s,c,d,n-1,m);
        for (int i=1; i<=n; i++) {
            int temp = go(a,s,c,d,i-2,m-1) + (s[n]-s[i-1]);
            d[n][m] = Math.max(d[n][m],temp);
        }
        return d[n][m];
    }
}

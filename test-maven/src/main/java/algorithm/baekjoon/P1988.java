package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1988 {

	private static Logger log = LoggerFactory.getLogger(P1988.class);
	/*
	 * 동혁이는 낮잠 시간을 N개의 구간으로 나누어서 그 중 B개의 구간동안만 잠을 자려고 한다. 
	 * B개의 구간이 연속일 필요는 없다. 
	 * 각 구간동안에 동혁이가 얻을 수 있는 피로 회복량이 정해져 있는데, 동혁이는 이 양을 최대화 하려고 한다. 
	 * 잠을 설치는 경우가 한 예이다.
	 * 예를 들어 구간을 5개로 나누었을 경우, B=3이고 [2 3 4]구간에 잠을 자기로 했다고 치자. 
	 * 잠을 들기 위해서는 준비 시간이 필요하기 때문에 이 준비 시간 동안에는 피로 회복이 되지 않는다. 
	 * 각 분할의 첫 구간에서는 피로가 회복되지 않는 것이다. 
	 * 따라서 [2 3 4] 구간에 잠을 자면 [2]구간에서는 회복을 못하고 [3 4]구간에서만 회복할 수 있다. 
	 * 이 구간은 N번째 구간과 1번째 구간이 이어져있지 않다고 생각하자.
	 * 
	 * 첫 줄에 N과 B가 주어진다. 
	 * N은 3이상 3,000이하이고 B는 2이상 N미만이다. 
	 * 다음으로 N줄에 걸쳐 피로회복량이 주어지는 0이상 200,000 이하의 자연수이다.
	 * 
	 *  첫 줄에 가능한 최대 피로회복량을 출력한다.
	 *  int dp1[3001][3001], dp2[3001][3001], n, b, a[3001];
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 5
        int b = sc.nextInt(); // 3
        int[][] dp1 = new int[3001][3001];
        int[][] dp2 = new int[3001][3001];
        int[] A = new int[3001];
        
        log.debug("{}", A);
        /*
    	 * O(nb)
    	 * dp1[i][j]: a[1...i]에서 j개 구간을 선택했을 때 구간 i에서 회복하는 최대 피로회복량
    	 * dp2[i][j]: a[1...i]에서 j개 구간을 선택했을 때 최대 피로회복량
    	 * dp1[i][j]=max(dp2[i-1][j-2],dp1[i-1][j-1])+a[i]
    	 * dp2[i][j]=max(dp2[i-1][j],dp1[i][j])
    	 */
		for (int i = 1; i <= n; i++) {
			
			for (int j = 2; j <= b; j++) {
				dp1[i][j] = Math.max(dp2[i - 1][j - 2], dp1[i - 1][j - 1]) + A[i];
				dp2[i][j] = Math.max(dp2[i - 1][j], dp1[i][j]);
			}
		}
	    log.debug("{}", dp2[n][b]);
        
	}	
}

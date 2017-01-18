package algorithm.baekjoon.middle2.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2003 {	
	
	private static Logger log = LoggerFactory.getLogger(P2003.class); 

	/*
	 * 수들의 합 2
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
0.5 초	128 MB	1719	971	727	56.620%
문제
N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다. 이 수열의 i번째 수부터 j번째 수까지의 합 A[i]+A[i+1]+…+A[j-1]+A[j]가 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1≤N≤10,000), M(1≤M≤300,000,000)이 주어진다. 다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다. 각각의 A[x]는 30,000을 넘지 않는 자연수이다.

출력
첫째 줄에 경우의 수를 출력한다.

예제 입력  복사
4 2
1 1 1 1
예제 출력  복사
3
예제 입력 2  복사
10 5
1 2 3 4 2 5 3 1 1 2
예제 출력 2  복사
3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/886927f47c13809f58a2
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n+1];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int left=0;
        int right=0;
        int sum=a[0];
        int ans=0;
        while (left <= right && right < n) {
            if (sum < s) {
                right += 1;
                sum += a[right];
            } else if (sum == s) {
                ans += 1;
                right += 1;
                sum += a[right];
            } else if (sum > s) {
                sum -= a[left];
                left++;
            }
        }
        System.out.println(ans);
		
		sc.close();
	}
}

package algorithm.baekjoon.middle2.E_exhaustive_search2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1806 {	
	
	private static Logger log = LoggerFactory.getLogger(P1806.class); 

	/*
	 * 부분합
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	2198	710	472	30.412%
문제
10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다. 이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (10 < N < 100,000)과 S (0 < S < 100,000,000)가 주어진다. 둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

출력
첫째 줄에 구하고자 하는 최소의 길이를 출력한다. 만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.

예제 입력  복사
10 15
5 1 3 5 10 7 4 9 2 8
예제 출력  복사
2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/907e123eeee200f1eebc
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n+1];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int i=0;
        int j=0;
        int sum=a[0];
        int ans=n+1;
        while (i <= j && j < n) {
            if (sum < s) {
                j += 1;
                sum += a[j];
            } else if (sum == s) {
                ans = Math.min(j-i+1,ans);
                j += 1;
                sum += a[j];
            } else if (sum > s) {
                ans = Math.min(j-i+1,ans);
                sum -= a[i];
                i++;
            }
        }
        if (ans > n) {
            ans = 0;
        }
        System.out.println(ans);
		
		sc.close();
	}
}

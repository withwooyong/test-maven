package algorithm.baekjoon.middle2.E_exhaustive_search2;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1208 {	
	
	private static Logger log = LoggerFactory.getLogger(P1208.class); 

	/*
	 * 부분집합의 합 2
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	64 MB	2176	315	204	16.465%
문제
N개의 정수로 이루어진 집합이 있을 때, 이 집합의 공집합이 아닌 부분집합 중에서 그 집합의 원소를 다 더한 값이 S가 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 정수의 개수를 나타내는 N과 정수 S가 주어진다. (1≤N≤40, |S|≤1,000,000) 둘째 줄에 N개의 정수가 빈 칸을 사이에 두고 주어진다. 주어지는 정수의 절대값은 100,000을 넘지 않는다. 같은 수가 여러번 주어질 수도 있다.

출력
첫째 줄에 합이 S가 되는 부분집합의 개수를 출력한다.

예제 입력  복사
5 0
-7 -3 -2 5 8
예제 출력  복사
1
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/30cdb032514859caa106 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int s = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int m = n/2;
        n = n-m;
        int[] first = new int[1<<n];
        for (int i=0; i<(1<<n); i++) {
            for (int k=0; k<n; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    first[i] += a[k];
                }
            }
        }
        int[] second = new int[1<<m];
        for (int i=0; i<(1<<m); i++) {
            for (int k=0; k<m; k++) {
                if ((i&(1<<k)) == (1<<k)) {
                    second[i] += a[k+n];
                }
            }
        }
        Arrays.sort(first);
        Arrays.sort(second);
        n = (1<<n);
        m = (1<<m);
        for (int i=0; i<m/2; i++) {
            int temp = second[i];
            second[i] = second[m-i-1];
            second[m-i-1] = temp;
        }
        int i = 0;
        int j = 0;
        long ans = 0;
        while (i < n && j < m) {
            if (first[i] + second[j] == s) {
                long c1 = 1;
                long c2 = 1;
                i += 1;
                j += 1;
                while (i < n && first[i] == first[i-1]) {
                    c1 += 1;
                    i += 1;
                }
                while (j < m && second[j] == second[j-1]) {
                    c2 += 1;
                    j += 1;
                }
                ans += c1*c2;
            } else if (first[i] + second[j] < s) {
                i+=1;
            } else {
                j+=1;
            }
        }
        if (s == 0) ans -= 1;

        System.out.println(ans);
		
		sc.close();
	}
}

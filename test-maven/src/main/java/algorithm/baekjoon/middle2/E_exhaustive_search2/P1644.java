package algorithm.baekjoon.middle2.E_exhaustive_search2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1644 {	
	
	private static Logger log = LoggerFactory.getLogger(P1644.class); 

	/*
	 * 소수의 연속합 성공
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	1795	660	455	36.753%
문제
하나 이상의 연속된 소수의 합으로 나타낼 수 있는 자연수들이 있다. 몇 가지 자연수의 예를 들어 보면 다음과 같다.

3 : 3 (한 가지)
41 : 2+3+5+7+11+13 = 11+13+17 = 41 (세 가지)
53 : 5+7+11+13+17 = 53 (두 가지)
하지만 연속된 소수의 합으로 나타낼 수 없는 자연수들도 있는데, 20이 그 예이다. 7+13을 계산하면 20이 되기는 하나 7과 13이 연속이 아니기에 적합한 표현이 아니다. 또한 한 소수는 반드시 한 번만 덧셈에 사용될 수 있기 때문에, 3+5+5+7과 같은 표현도 적합하지 않다.

2 이상의 자연수가 주어졌을 때, 이 자연수를 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 자연수 N이 주어진다. (1≤N≤4,000,000)

출력
첫째 줄에 자연수 N을 연속된 소수의 합으로 나타낼 수 있는 경우의 수를 출력한다.

예제 입력  복사
20
예제 출력  복사
0
예제 입력 2  복사
3
예제 출력 2  복사
1
예제 입력 3  복사
41
예제 출력 3  복사
3
예제 입력 4  복사
53
예제 출력 4  복사
2
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/02482585f719e2ece210
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean[] c = new boolean[n+1];
        List<Integer> p = new ArrayList<Integer>();
        for (int i=2; i<=n; i++) {
            if (!c[i]) {
                p.add(i);
                for (int j=i*2; j<=n; j+=i) {
                    c[j] = true;
                }
            }
        }
        int l=0;
        int r=0;
        int sum = p.size() == 0 ? 0 : p.get(0);
        int ans = 0;

        while (l <= r && r < p.size()) {
            if (sum <= n) {
                if (sum == n) {
                    ans += 1;
                }
                r++;
                if (r < p.size()) {
                    sum += p.get(r);
                }
            } else {
                sum -= p.get(l++);
            }
        }
        System.out.println(ans);
		
		sc.close();
	}
}

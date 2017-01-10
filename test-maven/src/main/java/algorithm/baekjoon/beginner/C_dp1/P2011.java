package algorithm.baekjoon.beginner.C_dp1;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2011 {

	private static Logger log = LoggerFactory.getLogger(P2011.class);

	/*
	 * 암호코드
	 * 상근이와 선영이가 다른 사람들이 남매간의 대화를 듣는 것을 방지하기 위해서 대화를 서로 암호화 하기로 했다. 
	 * 그래서 다음과 같은 대화를 했다.
	 * 상근: 그냥 간단히 암호화 하자. A를 1이라고 하고, B는 2로, 그리고 Z는 26으로 하는거야.
	 * 선영: 그럼 안돼. 만약, "BEAN"을 암호화하면 25114가 나오는데, 이걸 다시 글자로 바꾸는 방법은 여러가지가 있어.
	 * 상근: 그렇네. 25114를 다시 영어로 바꾸면, "BEAAD", "YAAD", "YAN", "YKD", "BEKD", "BEAN" 총 6가지가 나오는데, 
	 * BEAN이 맞는 단어라는건 쉽게 알수 있잖아?
	 * 선영: 예가 적절하지 않았네 ㅠㅠ 만약 내가 500자리 글자를 암호화 했다고 해봐. 
	 * 그 때는 나올 수 있는 해석이 정말 많은데, 그걸 언제 다해봐?
	 * 상근: 얼마나 많은데?
	 * 선영: 구해보자!
	 * 어떤 암호가 주어졌을 때, 그 암호의 해석이 몇 가지가 나올 수 있는지 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 5000자리 이하의 암호가 주어진다.
	 * 나올 수 있는 해석의 가짓수를 구하시오. 
	 * 정답이 매우 클 수 있으므로, 1000000으로 나눈 나머지를 출력한다.
	 * 25114
	 * 6
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/725d19f289d7dda4a191
	 */
	public static int mod = 1000000;
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        String s = sc.nextLine().trim();
        int n = s.length();
        s = " " + s;
        int[] d = new int[n+1];
        d[0] = 1;
        for (int i=1; i<=n; i++) {
            int x = s.charAt(i) - '0';
            if (1 <= x && x <= 9) {
                d[i] += d[i-1];
                d[i] %= mod;
            }
            if (i==1) {
                continue;
            }
            if (s.charAt(i-1) == '0') {
                continue;
            }
            x = (s.charAt(i-1)-'0')*10 + (s.charAt(i)-'0');
            if (10 <= x && x <= 26) {
                d[i] += d[i-2];
                d[i] %= mod;
            }
        }
        System.out.println(d[n]);
        sc.close();
	}

	/*
	 * a[i] : i번쨰 숫자
	 * c[i][1] : i번째를 1개로 인식할 때의 경우의 수.
	 * c[i][2] : i번째를 2번째 숫자로 인식할 때의 경우의수
	 * 위와 같이 두면,
	 * 1. c[i][1] = c[i][2] + c[i][1] (a[i] 는 0이 아닐 때)
	 * 2. c[i][2] = (a[i - 1] == '1' || a[i - 1] == '2' && a[i] <= '6') ? c[i - 1][0] : 0;
	 * 2-1. a[i-1]이 1이거나 2 이고, a[i]가 6보다 작다면 c[i][1]. 그렇지 않다면 경우의수는 없다.
	 * 출처: http://kthng.tistory.com/55 [ㅁㅁ]
	 * 못풀었음.
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int[] A = { 2, 5, 1, 1, 4 };
		
		int[][] Dp = new int[A.length][3];		
		for (int i = 1; i < A.length; i++) {
			if (A[i] > 0) {
				Dp[i][1] = Dp[i][2] + Dp[i][1];
			}
			if (A[i - 1] == 1 || (A[i - 1] == 2 && A[i] <= 6)) {
				Dp[i][2] = Dp[i - 1][0];
			} else {
				Dp[i][2] = 0;
			}
		}
		log.debug("{}", Arrays.deepToString(Dp));
		sc.close();
	}
}

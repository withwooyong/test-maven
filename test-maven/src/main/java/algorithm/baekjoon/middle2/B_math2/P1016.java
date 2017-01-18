package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1016 {	
	
	private static Logger log = LoggerFactory.getLogger(P1016.class); 

	/*
	 * 제곱 ㄴㄴ 수
문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
2 초	128 MB	7085	860	573	18.110%
문제
어떤 수 X가 1보다 큰 제곱수로 나누어 떨어지지 않을 때, 제곱ㄴㄴ수라고 한다. 제곱수는 정수의 제곱이다. min과 max가 주어지면, min과 max를 포함한 사이에 제곱ㄴㄴ수가 몇 개 있는지 출력한다.

입력
첫째 줄에 min과 max가 주어진다. min은 1보다 크거나 같고, 1,000,000,000,000보다 작거나 같은 자연수이고, max는 min보다 크거나 같고, min+1,000,000보다 작거나 같은 자연수이다.

출력
첫째 줄에 [min,max]구간에 제곱ㄴㄴ수가 몇 개인지 출력한다.

예제 입력  복사
1 10
예제 출력  복사
7
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/b3e73785626d7522c4c7cfcff213a67d
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        long st = sc.nextLong();
        long ed = sc.nextLong();
        boolean[] a = new boolean[(int)(ed-st)+1];
        for (long i=2; i*i <= ed; i++) {
            long w = i*i-st%(i*i);
            if (w == i*i) {
                w = 0;
            }
            while (w <= ed-st) {
                a[(int)w] = true;
                w += i*i;
            }
        }
        int ans = 0;
        for (int i=0; i<=ed-st; i++) {
            if (a[i] == false) {
                ans += 1;
            }
        }
        System.out.println(ans);
		
		sc.close();
	}
}

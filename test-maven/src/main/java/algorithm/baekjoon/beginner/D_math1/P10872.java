package algorithm.baekjoon.beginner.D_math1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P10872 {	
	
	private static Logger log = LoggerFactory.getLogger(P10872.class); 

	/*
	 * 팩토리얼
	 * 문제
0보다 크거나 같은 정수 N이 주어진다. 이 때, N!을 출력하는 프로그램을 작성하시오.

입력
첫째 줄에 정수 N(0 ≤ N ≤ 12)가 주어진다.

출력
첫째 줄에 N!을 출력한다.

10

3628800
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/9c4ef5ad314704d04431a38159bbbe38
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 1;
        for (int i=2; i<=n; i++) {
            ans *= i;
        }
        System.out.println(ans);
		
		sc.close();
	}
}

package algorithm.baekjoon.beginner.F_graph1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2331 {	
	
	private static Logger log = LoggerFactory.getLogger(P2331.class); 

	/*
	 * 반복수열
	 * 문제
다음과 같이 정의된 수열이 있다.

D[1] = A
D[n] = D[n-1]의 각 자리의 숫자를 P번 곱한 수들의 합
예를 들어 A=57, P=2일 때, 수열 D는 {57, 74(=5^2+7^2=25+49), 65, 61, 37, 58, 89, 145, 42, 20, 4, 16, 37, …}이 된다. 그 뒤에는 앞서 나온 수들(57부터가 아니라 58부터)이 반복된다.

이와 같은 수열을 계속 구하다 보면 언젠가 이와 같은 반복수열이 된다. 이 때, 반복되는 부분을 제외했을 때, 수열에 남게 되는 수들의 개수를 구하는 프로그램을 작성하시오. 위의 예에서는 {57, 74, 65, 61}의 네 개의 수가 남게 된다.

입력
첫째 줄에 A(1≤A≤9999), P(1≤P≤5)가 주어진다.

출력
첫째 줄에 반복되는 부분을 제외했을 때, 수열에 남게 되는 수들의 개수를 출력한다.

57 2

4
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * 
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int p = sc.nextInt();
        System.out.println(length(a, p, 1));
		
		sc.close();
	}
	
	public static int[] check = new int[1000000];
    public static int pow(int x, int p) {
        int ans = 1;
        for (int i=0; i<p; i++) {
            ans = ans * x;
        }
        return ans;
    }
    public static int next(int a, int p) {
        int ans = 0;
        while (a > 0) {
            ans += pow(a%10, p);
            a /= 10;
        }
        return ans;
    }
    public static int length(int a, int p, int cnt) {
        if (check[a] != 0) {
            return check[a]-1;
        }
        check[a] = cnt;
        int b = next(a, p);
        return length(b, p, cnt+1);
    }
}

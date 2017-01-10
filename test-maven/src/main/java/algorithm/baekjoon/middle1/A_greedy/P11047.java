package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11047 {	
	
	private static Logger log = LoggerFactory.getLogger(P11047.class); 

	/*
	 * 동전 0
	 * 준규가 가지고 있는 동전은 총 N종류이고, 각각의 동전을 매우 많이 가지고 있다.
	 * 동전을 적절히 사용해서 그 가치의 합을 K로 만드려고 한다. 
	 * 이 때 필요한 동전 개수의 최소값을 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 N과 K가 주어진다. (1 ≤ N ≤ 10, 1 ≤ K ≤ 100,000,000)
	 * 둘째 줄부터 N개의 줄에 동전의 가치 Ai가 오름차순으로 주어진다. 
	 * (1 ≤ Ai ≤ 1,000,000, A1 = 1, i ≥ 2인 경우에 Ai는 Ai-1의 배수)
	 * 
	 * 첫째 줄에 K원을 만드는데 필요한 동전 개수의 최소값을 출력한다.
	 * 
	 * 10 4200
	 * 1
	 * 5
	 * 10
	 * 50
	 * 100
	 * 500
	 * 1000
	 * 5000
	 * 10000
	 * 50000
	 * 
	 * 6
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/6542e3d066eb668599180027d1ef5af1
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0;
        for (int i=n-1; i>=0; i--) {
            ans += k/a[i];
            k %= a[i];
        }
        System.out.println(ans);
        sc.close();
	}

	/*
	 * 접근 방식 : 개념적으로는 매우 쉽다. 매 순간마다 k를 넘지 않는 가장 큰 값을 고르는 경우를 세면 된다. 
	 * 그러나 이 경우를 세는 식을 세우는 방법에 따라서 걸리는 시간이 천차만별이기에 시간을 고려하여 세워야 한다.
	 * 4200원을 1000으로 최대한 만들어 볼 때 1000원짜리는 몇개를 쓰고, 얼마가 남는가? 
	 * 바로 4개를 쓰고 200원이 남는다. 
	 * 즉, 동전사용횟수는 4가 되고 k=200이 된다는 얘기다. 
	 * 이를 빼기를 이용해 계속 빼줄 이유가 없고 나누기를 이용해서 횟수를 구한 다음, 
	 * k는 %연산자를 이용해 값을 갱신해 주면 된다는 것이다.
	 * http://m.blog.naver.com/occidere/220802824513
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int m = 0;
		int n = sc.nextInt(); // 10
		int k = sc.nextInt(); // 4200
		int a[] = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		for (int i = n; i > 0; i--) {
			m += k / a[i]; // 횟수
			k = k % a[i]; // 나머지 잔돈
		}
		System.out.println(m);
		sc.close();
	}
	
	private static void solution2() {
		int N = 10;
		int K = 4200;
		int M = 0;
		int[] A = { 1, 5, 10, 50, 100, 500, 1000, 5000, 10000, 50000 };
		log.debug("{}", A[A.length-1]);
		
		for (int i = A.length - 1; i > 0; i--) {
			M += K / A[i];
			K = K % A[i];
			log.debug("{} {} {}", i, M, K);
		}
		System.out.println(M);
	}
}

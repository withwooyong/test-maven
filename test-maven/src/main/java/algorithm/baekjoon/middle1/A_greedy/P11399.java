package algorithm.baekjoon.middle1.A_greedy;

import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11399 {	
	
	private static Logger log = LoggerFactory.getLogger(P11399.class); 

	/*
	 * ATM
	 * 인하은행에는 ATM이 1대밖에 없다. 
	 * 지금 이 ATM앞에 N명의 사람들이 줄을 서있다. 
	 * 사람은 1번부터 N번까지 번호가 매겨져 있으며, i번 사람이 돈을 인출하는데 걸리는 시간은 Pi분이다.
	 * 사람들이 줄을 서는 순서에 따라서, 돈을 인출하는데 필요한 시간의 합이 달라지게 된다. 
	 * 예를 들어, 총 5명이 있고, P1 = 3, P2 = 1, P3 = 4, P4 = 3, P5 = 2 인 경우를 생각해보자. 
	 * [1, 2, 3, 4, 5] 순서로 줄을 선다면, 1번 사람은 3분만에 돈을 뽑을 수 있다. 
	 * 2번 사람은 1번 사람이 돈을 뽑을 때 까지 기다려야 하기 때문에, 3+1 = 4분이 걸리게 된다. 
	 * 3번 사람은 1번, 2번 사람이 돈을 뽑을 때까지 기다려야 하기 때문에, 총 3+1+4 = 8분이 필요하게 된다. 
	 * 4번 사람은 3+1+4+3 = 11분, 5번 사람은 3+1+4+3+2 = 13분이 걸리게 된다. 
	 * 이 경우에 각 사람이 돈을 인출하는데 필요한 시간의 합은 3+4+8+11+13 = 39분이 된다.
	 * 줄을 [2, 5, 1, 4, 3] 순서로 줄을 서면, 2번 사람은 1분만에, 5번 사람은 1+2 = 3분, 1번 사람은 1+2+3 = 6분, 
	 * 4번 사람은 1+2+3+3 = 9분, 3번 사람은 1+2+3+3+4 = 13분이 걸리게 된다. 
	 * 각 사람이 돈을 인출하는데 필요한 시간의 합은 1+3+6+9+13 = 32분이다. 
	 * 이 방법보다 더 필요한 시간의 합을 최소로 만들 수는 없다.
	 * 줄을 서 있는 사람의 수 N과 각 사람이 돈을 인출하는데 걸리는 시간 Pi가 주어졌을 때, 
	 * 각 사람이 돈을 인출하는데 필요한 시간의 합의 최소값을 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 사람의 수 N(1 ≤ N ≤ 1,000)이 주어진다. 둘째 줄에는 각 사람이 돈을 인출하는데 걸리는 시간 Pi가 주어진다. (1 ≤ Pi ≤ 1,000)
	 * 첫째 줄에 각 사람이 돈을 인출하는데 필요한 시간의 합의 최소값을 출력한다.
	 * 5
	 * 3 1 4 3 2
	 * 
	 * 32
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}
	
	/*
	 * https://gist.github.com/Baekjoon/a29efd6debc4c11ca8bff11673424cdc
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i=0; i<n; i++) {
            a[i] = sc.nextInt();
        }
        int sum = 0;
        int ans = 0;
        Arrays.sort(a);
        for (int i=0; i<n; i++) {
            sum += a[i];
            ans += sum;
        }
        System.out.println(ans);
        sc.close();
	}

	/*
	 * 이 문제의 경우 줄에 선 사람들의 순서대로 인출하는데 걸리는 시간을 최소화 하는것이 목적이다. 
	 * 사람 수를 변수 n에 입력받고, 입력받은 사람의 수 만큼 인출하는데 걸리는 시간을 p[1001] 배열에 입력한다.
	 * 샘플 데이터를 기반으로 생각해보자. 
	 * 사람 수(n)은 5명이고 p[i] = {3,1,4,3,2}로 주어졌다. 
	 * 이때 줄을 1,2,3,4,5번 차례대로 세운다면 걸리는 총 시간은 39분이 된다.
	 * 반면 줄 세우는 순서를 바꿔보자. 
	 * 사람들을 2, 5, 1, 4, 3번 순서로 줄을 세우면 p[i] = {1,2,3,3,4}가 되고 총 소요시간은 32분으로 최솟값이 된다.
	 * 이를 잘 살펴보면 앞에 위치한 사람이 걸리는 시간 만큼이 누적되서 계속 더해지는 것을 볼 수 있다. 
	 * 결국 앞에 위치해서 중복되어 더해지는 시간의 값이 최소가 되어야 하는 것이며, 
	 * 결국 p[i]를 작은 수 부터 오름차순으로 정렬한 뒤 소요시간을 계산해 전부 더해버리면 되는 것이다.
	 * 문제에서 사람의 수가 1,000명 이하이고, 각 사람마다 걸리는 시간이 1,000 이하이므로 정렬에 걸리는 시간은 
	 * 최악의 경우 O(N^2) = 1000,000이 된다. 
	 * 이정도 시간은 별 문제가 되지 않으므로 간편하게 퀵 소트를 사용하여 정렬하도록 한다.
	 * [출처] [백준] 11399 - ATM|작성자 occidere
	 * http://blog.naver.com/occidere/220790825104
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int[] A = { 3, 1, 4, 3, 2 };
		int[] B = new int[A.length + 1];
		Arrays.sort(A);
		int min = 0;
		B[0] = A[0];
		min = B[0]; 
		for (int i = 1; i < A.length; i++) {
			B[i] += A[i] + B[i - 1];
			min += B[i];
		}
		log.debug("{}", Arrays.toString(B));
		log.debug("{}", min);
		sc.close();
	}
	
	private static void solution2() {
		Scanner sc = new Scanner(System.in);
		int min = 0;
		int N = sc.nextInt(); // 입력
		int p[] = new int[N + 1];
		for (int i = 1; i <= N; i++) {
			p[i] = sc.nextInt(); // 입력
		}
		Arrays.sort(p); 
		for (int i = 1; i <= N; i++) {
			p[i] = p[i - 1] + p[i];
			min += p[i];
		}
		System.out.println(min);
		sc.close();
	}
}

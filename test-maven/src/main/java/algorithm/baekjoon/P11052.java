package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11052 {

	private static Logger log = LoggerFactory.getLogger(P11052.class);

	/*
	 * 강남역에서 붕어빵 장사를 하고 있는 해빈이는 지금 붕어빵이 N개 남았다.
	 * 해빈이는 적절히 붕어빵 세트 메뉴를 구성해서 붕어빵을 팔아서 얻을 수 있는 수익을 최대로 만드려고 한다. 
	 * 붕어빵 세트 메뉴는 붕어빵을 묶어서 파는 것을 의미하고, 세트 메뉴의 가격은 이미 정해져 있다.
	 * 붕어빵 i개로 이루어진 세트 메뉴의 가격은 Pi 원이다.
	 * 붕어빵이 4개 남아 있고, 1개 팔 때의 가격이 1, 2개는 5, 3개는 6, 4개는 7인 경우에 해빈이가 얻을 수 있는 최대 수익은 10원이다. 
	 * 2개, 2개로 붕어빵을 팔면 되기 때문이다.
	 * 
	 * 1개 팔 때의 가격이 5, 2개는 2, 3개는 8, 4개는 10 인 경우에는 20이 된다. 
	 * 1개, 1개, 1개, 1개로 붕어빵을 팔면 되기 때문이다.
	 * 
	 * 마지막으로, 1개 팔 때의 가격이 3, 2개는 5, 3개는 15, 4개는 16인 경우에는 정답은 18이다. 
	 * 붕어빵을 3개, 1개로 팔면 되기 때문이다.
	 * 세트 메뉴의 가격이 주어졌을 때, 해빈이가 얻을 수 있는 최대 수익을 구하는 프로그램을 작성하시오.
	 * 
	 * 첫째 줄에 해빈이가 가지고 있는 붕어빵의 개수 N이 주어진다. (1 ≤ N ≤ 1,000)
	 * 둘째 줄에는 Pi가 P1부터 PN까지 순서대로 주어진다. (1 ≤ Pi ≤ 10,000)
	 * 해빈이가 얻을 수 있는 최대 수익을 출력한다.
	 * 4
	 * 1 5 6 7
	 * 
	 * 10
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	/*
	 * 1. 붕어빵을 끊임없이 갱신하면 최대값을 저장한다.
	 * 2. 즉 A[j] = A[[j-i] + Pi[i] 란 현재 i 값어치의 붕어빵을 넣었다고 가정했을때 이전의 값어치의 최대값에서 더하는 값이다.  
	 * 즉 이전의 저장된 값이랑 이 값을 비교해서 최대값을 저장하다보면 마지막에 최대값만 남게 될 것이다.
	 * 출처: http://wootool.tistory.com/89 [우투리와툴툴]
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] Pi = new int[N + 1];
		
		for (int i = 1; i <= N; i++) {
			Pi[i] = sc.nextInt();
		}
		int[] A = new int[Pi.length];

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (i <= j) {
					A[j] = (A[j] > A[j - i] + Pi[i]) ? A[j] : A[j - i] + Pi[i];
				}
			}
		}
		log.debug("{}", A[N]);
	}
}

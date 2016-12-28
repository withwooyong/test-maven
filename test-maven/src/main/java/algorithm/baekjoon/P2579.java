package algorithm.baekjoon;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2579 {
	
	private static Logger log = LoggerFactory.getLogger(P2579.class);

	/*
	 * 계단 오르기 게임은 계단 아래 시작점부터 계단 꼭대기에 위치한 도착점까지 가는 게임이다. 
	 * <그림 1>과 같이 각각의 계단에는 일정한 점수가 쓰여 있는데 계단을 밟으면 그 계단에 쓰여 있는 점수를 얻게 된다.
	 * 예를 들어 <그림 2>와 같이 시작점에서부터 첫 번째, 두 번째, 네 번째, 여섯 번째, 계단을 밟아 도착점에 도달하면 총 점수는 10 + 20 + 25 + 20 = 75점이 된다.
	 * 계단 오르는 데는 다음과 같은 규칙이 있다.
	 * 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
	 * 연속된 세 개의 계단을 모두 밟아서는 안된다. 단, 시작점은 계단에 포함되지 않는다.
	 * 마지막 도착 계단은 반드시 밟아야 한다.
	 * 따라서 첫 번째 계단을 밟고 이어 두 번째 계단이나, 세 번째 계단으로 오를 수 있다. 
	 * 하지만, 첫 번째 계단을 밟고 이어 네 번째 계단으로 올라가거나, 첫 번째, 두 번째, 세번째 계단을 연속해서 모두 밟을 수는 없다.
	 * 각 계단에 쓰여 있는 점수가 주어질 때 이 게임에서 얻을 수 있는 총 점수의 최대값을 구하는 프로그램을 작성하시오.
	 * 
	 * 입력의 첫째 줄에 계단의 개수가 주어진다.
	 * 둘째 줄부터 한 줄에 하나씩 제일 아래에 놓인 계단부터 순서대로 각 계단에 쓰여 있는 점수가 주어진다. 
	 * 계단의 개수는 300이하의 자연수이고, 계단에 쓰여 있는 점수는 10,000이하의 자연수이다.
	 * 
	 * 첫째 줄에 계단 오르기 게임에서 얻을 수 있는 총 점수의 최대값을 출력한다.
	 */
	public static void main(String[] args) throws Exception {
	
		//solution();
		solution2();
	}
	
	private static void solution1() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int k;
		int n = Integer.parseInt(in.readLine());
		int max1 = 0;
		int max2 = 0;
		int sum = 0;
		int[] A = new int[n];
		int i = 0;
		while (n-- > 0 && n < 300) {
			k = Integer.parseInt(in.readLine());
			if (k > 10000) {
				break;
			} else {				
				A[i++] = k;
			}
		}

		for (int j = 1; j < A.length - 2; j += 3) {
			max1 = A[j] + A[j + 1];
			max2 = A[j] + A[j + 2];
			sum += Math.max(max1, max2);
		}
		System.out.println(sum);
	}
	
	/*
	 * Nx2배열을 만든 후에 D[N,0]은 
	 * 이전단계까지 더해온 값에 현재 계단 값을 더한 값이고, 
	 * D[N,1]은 2계단 전까지 왔던 값에 현재값을 더한 값이다. 
	 * 즉, 그 두 값 중 max값을 계속 갱신해가면 마지막엔 최대값이 담겨있게 된다.
	 */
	private static void solution() {		
		int[] A = { 6, 10, 20, 15, 25, 10, 20 };
		
		log.debug("{}", Arrays.toString(A));
		
		int max1 = 0;
		int max2 = 0;
		int sum = 0;
		for (int j = 0; j < A.length - 2; j++) {
			max1 = A[j] + A[j + 1];
			max2 = A[j] + A[j + 2];			
			if (max1 > max2) {
				j += 1;
				log.debug("{}", j);
			} else if (max1 < max2) {
				j += 2;
				log.debug("{}", j);
			}
			sum += Math.max(max1, max2);
		}
		log.debug("{}", sum);
	}
	
	/*
	 * 1. 이 문제는 계단을 오르는건데 조건이 3개 연속으로 밟을 수 없는 경우다. 즉 현재의 계단에 올 수 있는 경우의 수는 두가지다.
	 * 2. 첫번째로 현재 있는 계단 전의 계단을 밟지 않는 경우와 
	 *            현재 있는 계단 전의 계단을 밟은 경우 이렇게 두가지로 나올 수 있다.
	 * 3. 즉 B[i] = Math.max(A[i] + B[i - 2], A[i] + A[i - 1] + B[i - 3]) 
	 * 값을 계속 비교해나가면서 더하면 N 번째에 원하는 정답을 구할 수 있다.
	 */
	private static void solution2() {
		
		int[] A = { 6, 10, 20, 15, 25, 10, 20 };
		int[] B = new int[A.length]; // sum
		for (int i = 1; i <= 3; i++) {
			if (i != 3) {
				B[i] = B[i - 1] + A[i]; 
			} else {
				B[i] = Math.max(A[i] + B[i - 2], A[i] + A[i - 1]);
			}
		}
	 
		for (int i = 4; i < A.length; i++) {
			// 이전계단을 밟은 경우 
			B[i] = Math.max(A[i] + B[i - 2], A[i] + A[i - 1] + B[i - 3]);
		}
	    
	    log.debug("A={}", Arrays.toString(A));
	    log.debug("B={}", Arrays.toString(B));
	}
	
	// http://m.blog.naver.com/occidere/220788947949
	private static void solution3() {
		Scanner sc = new Scanner(System.in);
		int i;
		int n = sc.nextInt();
		int d[][] = new int[n + 1][2];
		int a[] = new int[n + 1];
		
		for (i = 1; i <= n; i++) {
			a[i] = sc.nextInt();
		}
		
		d[1][0] = a[1];
		d[1][1] = a[1];
		
		for (i = 2; i <= n; i++) {
			d[i][0] = d[i - 1][1] + a[i]; // 1칸씩
			d[i][1] = Math.max(d[i - 2][0], d[i - 2][1]) + a[i]; // 2칸씩
			// d[i][1] = Max(d[i - 2][0], d[i - 2][1]) + a[i];
			// d[i][0] = d[i - 1][1] + a[i];
		}
		
		System.out.println(Math.max(d[n][0], d[n][1]));
		sc.close();
	}
	
}

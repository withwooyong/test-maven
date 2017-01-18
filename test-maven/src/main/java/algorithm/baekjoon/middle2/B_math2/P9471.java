package algorithm.baekjoon.middle2.B_math2;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P9471 {	
	
	private static Logger log = LoggerFactory.getLogger(P9471.class); 

	/*
	 * 피사노 주기
한국어 원문 문제집 
시간 제한	메모리 제한	제출	정답	맞은 사람	정답 비율
1 초	128 MB	114	83	70	76.923%
문제
1960년, IBM의 직원 Donald Wall은 피보나치 수열을 m으로 나눈 나머지가 주기를 이룬다는 것을 증명했다.

예를 들어, 피보나치 수열의 처음 10개를 11로 나눈 예는 다음과 같다.

n	1	2	3	4	5	6	7	8	9	10
F(n)	1	1	2	3	5	8	13	21	34	55
F(n) mod 11	1	1	2	3	5	8	2	10	1	0
나머지를 이용해서 만든 수열은 주기가 나타날 수 있다. k(m)을 반복하는 부분 수열의 길이라고 했을 때, k(11) = 10임을 알 수 있다.

Wall은 아래와 같은 여러가지 성질도 증명했다.

m > 2인 경우에 k(m)은 짝수이다.
임의의 짝수 정수 n > 2에 대해서, k(m) = n인 m이 항상 존재한다.
k(m) ≤ m2 - 1
k(2n) = 3×2(n-1)
k(5n) = 4×5n
k(2×5n) = 6n
n > 2라면, k(10n) = 15×10(n-1)
m이 주어졌을 때, k(m)을 구하는 프로그램을 작성하시오.

입력
첫째 줄에 테스트 케이스의 개수 P (1 ≤ P ≤ 1000)가 주어진다. 각 테스트 케이스는 N과 M으로 이루어져 있다. N은 테스트 케이스의 번호이고, M은 문제에서 설명한 m이다. (2 ≤ M ≤ 1,000,000)

출력
각 테스트 케이스마다 테스트 케이스 번호를 출력하고 k(M)을 출력한다.

예제 입력  복사
5
1 4
2 5
3 11
4 123456
5 987654
예제 출력  복사
1 6
2 20
3 10
4 15456
5 332808
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
		
		sc.close();
	}
}

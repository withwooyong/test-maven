package algorithm.baekjoon.middle1.D_exhaustive_search1;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1722 {

	private static Logger log = LoggerFactory.getLogger(P1722.class);

	/*
	 * 순열의 순서
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 677 207 173 37.365%
	 * 문제
	 * 1부터 N까지의 수를 임의로 배열한 순열은 총 N! = N×(N-1)×…×2×1 가지가 있다.
	 * 
	 * 임의의 순열은 정렬을 할 수 있다. 예를 들어 N=3인 경우 {1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}의 순서로 생각할 수 있다.
	 * 첫 번째 수가 작은 것이 순서상에서 앞서며, 첫 번째 수가 같으면 두 번째 수가 작은 것이, 두 번째 수도 같으면 세 번째 수가 작은 것이….
	 * 
	 * N이 주어지면, 아래의 두 소문제 중에 하나를 풀어야 한다. k가 주어지면 k번째 순열을 구하고, 임의의 순열이 주어지면 이 순열이 몇 번째 순열인지를 출력하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 N(1≤N≤20)이 주어진다. 둘째 줄의 첫 번째 수는 소문제 번호이다. 1인 경우 k(1≤k≤N!)를 입력받고, 2인 경우 임의의 순열을 나타내는 N개의 수를 입력받는다. N개의 수에는
	 * 1부터 N까지의 정수가 한 번씩만 나타난다.
	 * 
	 * 출력
	 * k번째 수열을 나타내는 N개의 수를 출력하거나, 몇 번째 수열인지를 출력하면 된다.
	 * 
	 * 예제 입력 복사
	 * 4
	 * 1 3
	 * 예제 출력 복사
	 * 1 3 2 4
	 * 예제 입력 2 복사
	 * 4
	 * 2 1 3 2 4
	 * 예제 출력 2 복사
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/1b94fe874444c2287fe5
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);

		sc.close();
	}
}
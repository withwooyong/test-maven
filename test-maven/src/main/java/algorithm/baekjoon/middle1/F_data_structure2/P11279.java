package algorithm.baekjoon.middle1.F_data_structure2;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P11279 {

	private static Logger log = LoggerFactory.getLogger(P11279.class);

	/*
	 * 최대 힙
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 256 MB 1813 731 568 43.793%
	 * 문제
	 * 널리 잘 알려진 자료구조 중 최대 힙이라는 것이 있다. 최대 힙을 이용하여 다음과 같은 연산을 지원하는 프로그램을 작성하시오.
	 * 
	 * 배열에 자연수 x를 넣는다.
	 * 배열에서 가장 큰 값을 출력하고, 그 값을 배열에서 제거한다.
	 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
	 * 
	 * 입력
	 * 첫째 줄에 연산의 개수 N(1≤N≤100,000)이 주어진다. 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다. 만약 x가 자연수라면 배열에 x라는 값을 넣는(추가하는) 연산이고,
	 * x가 0이라면 배열에서 가장 큰 값을 출력하고 그 값을 배열에서 제거하는 경우이다. 입력되는 자연수는 2^31보다 작다.
	 * 
	 * 출력
	 * 입력에서 0이 주어진 회수만큼 답을 출력한다. 만약 배열이 비어 있는 경우인데 가장 큰 값을 출력하라고 한 경우에는 0을 출력하면 된다.
	 * 
	 * 예제 입력 복사
	 * 13
	 * 0
	 * 1
	 * 2
	 * 0
	 * 0
	 * 3
	 * 2
	 * 1
	 * 0
	 * 0
	 * 0
	 * 0
	 * 0
	 * 예제 출력 복사
	 * 0
	 * 2
	 * 1
	 * 3
	 * 2
	 * 1
	 * 0
	 * 0
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/5b1349b9159e7548bb54
	 */
	static class Compare implements Comparator<Integer> {
		public int compare(Integer one, Integer two) {
			return two.compareTo(one);
		}
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		Compare cmp = new Compare();
		PriorityQueue<Integer> q = new PriorityQueue<Integer>(1, cmp);
		int n = sc.nextInt();
		while (n-- > 0) {
			int x = sc.nextInt();
			if (x == 0) {
				if (q.isEmpty()) {
					System.out.println(0);
				} else {
					System.out.println(q.poll());
				}
			} else {
				q.offer(x);
			}
		}
	}

	/*
	 * https://gist.github.com/Baekjoon/87a8137d783fcbf45aa7
	 */
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		while (n-- > 0) {
			int x = sc.nextInt();
			if (x == 0) {
				if (hn == 0) {
					System.out.println(0);
				} else {
					System.out.println(pop());
				}
			} else {
				push(x);
			}
		}

		sc.close();
	}

	public static int[] heap = new int[100001];
	public static int hn;

	public static void push(int x) {
		heap[++hn] = x;
		for (int i = hn; i > 1; i /= 2) {
			if (heap[i / 2] < heap[i]) {
				int temp = heap[i / 2];
				heap[i / 2] = heap[i];
				heap[i] = temp;
			} else {
				break;
			}
		}
	}

	public static int pop() {
		int ans = heap[1];
		heap[1] = heap[hn];
		heap[hn--] = 0;
		for (int i = 1; i * 2 <= hn;) {
			if (heap[i] > heap[i * 2] && heap[i] > heap[i * 2 + 1]) {
				break;
			} else if (heap[i * 2] > heap[i * 2 + 1]) {
				int temp = heap[i];
				heap[i] = heap[i * 2];
				heap[i * 2] = temp;
				i = i * 2;
			} else {
				int temp = heap[i];
				heap[i] = heap[i * 2 + 1];
				heap[i * 2 + 1] = temp;
				i = i * 2 + 1;
			}
		}
		return ans;
	}
}

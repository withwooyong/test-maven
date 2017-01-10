package algorithm.baekjoon.beginner.B_data_structure1;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1158 {

	private static Logger log = LoggerFactory.getLogger(P1158.class);

	/*
	 * 조세퍼스 문제는 다음과 같다. 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 M(≤ N)이 주어진다. 이제
	 * 순서대로 M번째 사람을 제거한다. 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다. 이 과정은 N명의
	 * 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를 (N, M)-조세퍼스 순열이라고 한다. 예를 들어 (7,
	 * 3)-조세퍼스 순열은 <3, 6, 2, 7, 5, 1, 4>이다. N과 M이 주어지면 (N,M)-조세퍼스 순열을 구하는 프로그램을
	 * 작성하시오.
	 * 
	 * 첫째 줄에 N과 M이 빈 칸을 사이에 두고 순서대로 주어진다. (1 ≤ M ≤ N ≤ 5,000) 
	 * 7 3 
	 * <3, 6, 2, 7, 5, 1, 4>
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}
	
	/*
	 * https://gist.github.com/Baekjoon/da7ca551223efc6e21ab
	 */
	private static void solution() {
		Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append('<');
        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i=1; i<=n; i++) {
            queue.offer(i);
        }
        for (int i=0; i<n-1; i++) {
            for (int j=0; j<m-1; j++) {
                queue.offer(queue.poll());
            }
            sb.append(queue.poll() + ", ");
        }
        sb.append(queue.poll() + ">");
        System.out.println(sb);
        sc.close();
	}

	// 7 3 
	private static void solution1() {
		Scanner sc = new Scanner(System.in);
		Deque<Integer> deque = new ArrayDeque<Integer>();
		StringBuilder sb = new StringBuilder("<");
//		int n = sc.nextInt();
//		int m = sc.nextInt();
		
		int n = 7;
		int m = 3;

		log.debug("{} {}", n, m);
		for (int i = 1; i <= n; i++) {
			deque.add(i);
		}

		while (!deque.isEmpty()) {
			for (int i = 0; i < m - 1; i++) {
				deque.addLast(deque.removeFirst());
			}
			sb.append(deque.removeFirst() + ", ");
			log.debug("{}", deque.toString());
		}
		System.out.println(sb.toString().substring(0, sb.length() - 2) + ">");
	}
}

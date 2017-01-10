package algorithm.baekjoon.middle1.B_divide_conquer;

import java.util.Scanner;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P2263 {

	private static Logger log = LoggerFactory.getLogger(P2263.class);

	/*
	 * 트리의 순회
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 5 초 128 MB 519 213 149 41.972%
	 * 문제
	 * n개의 정점을 갖는 이진 트리의 정점에 1부터 n까지의 번호가 중복 없이 매겨져 있다. 이와 같은 이진 트리의 인오더와 포스트오더가 주어졌을 때, 프리오더를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 n(1≤n≤100,000)이 주어진다. 다음 줄에는 인오더를 나타내는 n개의 자연수가 주어지고, 그 다음 줄에는 같은 식으로 포스트오더가 주어진다.
	 * 
	 * 출력
	 * 첫째 줄에 프리오더를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 3
	 * 1 2 3
	 * 1 3 2
	 * 예제 출력 복사
	 * 2 1 3
	 */
	public static void main(String[] args) throws Exception {
		//solution();
		log.debug("{}", "");
		
		Scanner sc = new Scanner(System.in);
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		int count = st.countTokens();
		for (int i = 0; i < count; i++) {
			log.debug("{} {} {}", count, i, st.nextToken());	
		}
		
		sc.close();
	}

	/*
	 * https://gist.github.com/Baekjoon/8244aa7f00e2352cc78f462c4b9bbecc
	 */
	private static int[] inorder = new int[100000];
	private static int[] postorder = new int[100000];
	private static int[] position = new int[100001];

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		String str = sc.nextLine();
		StringTokenizer st = new StringTokenizer(str, " ");
		//int count = st.countTokens();
		for (int i = 0; i < n; i++) {
			inorder[i] = Integer.parseInt(st.nextToken());
		}		 
		
		str = sc.nextLine();
		st = new StringTokenizer(str, " ");
		for (int i = 0; i < n; i++) {
			postorder[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 0; i < n; i++) {
			position[inorder[i]] = i;
		}
		solve(0, n - 1, 0, n - 1);
		sc.close();
	}

	private static void solve(int in_start, int in_end, int post_start, int post_end) {
		if (in_start > in_end || post_start > post_end)
			return;
		int root = postorder[post_end];
		System.out.println(root);
		int p = position[root];

		// inorder: in_start p in_end
		// postorder: post_start post_end
		// left: p-in_start
		// right: in_end-p
		int left = p - in_start;
		solve(in_start, p - 1, post_start, post_start + left - 1);
		solve(p + 1, in_end, post_start + left, post_end - 1);
	}

}

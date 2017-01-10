package algorithm.baekjoon.middle1.B_divide_conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1517 {

	private static Logger log = LoggerFactory.getLogger(P1517.class);

	/*
	 * 버블 소트
	 * 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 1 초 8 MB 1553 285 175 25.289%
	 * 문제
	 * N개의 수로 이루어진 수열 A[1], A[2], …, A[N]이 있다. 이 수열에 대해서 버블 소트를 수행할 때, Swap이 총 몇 번 발생하는지 알아내는 프로그램을 작성하시오.
	 * 
	 * 버블 소트는 서로 인접해 있는 두 수를 바꿔가며 정렬하는 방법이다. 예를 들어 수열이 3 2 1 이었다고 하자. 이 경우에는 인접해 있는 3, 2가 바뀌어야 하므로 2 3 1 이 된다. 다음으로는 3,
	 * 1이 바뀌어야 하므로 2 1 3 이 된다. 다음에는 2, 1이 바뀌어야 하므로 1 2 3 이 된다. 그러면 더 이상 바꿔야 할 경우가 없으므로 정렬이 완료된다.
	 * 
	 * 입력
	 * 첫째 줄에 N(1≤N≤500,000)이 주어진다. 다음 줄에는 N개의 정수로 A[1], A[2], …, A[N]이 주어진다. 각각의 A[i]는 0≤|A[i]|≤1,000,000,000의 범위에 들어있다.
	 * 
	 * 출력
	 * 첫째 줄에 Swap 횟수를 출력한다
	 * 
	 * 예제 입력 복사
	 * 3
	 * 3 2 1
	 * 예제 출력 복사
	 * 3
	 */
	public static void main(String[] args) throws Exception {
		solution();
		log.debug("{}", "");
	}

	/*
	 * https://gist.github.com/Baekjoon/bfe783fdd0e7a8973735762e05e4d51c
	 */
	private static void solution() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.valueOf(br.readLine());
		int[] a = new int[n];
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			a[i] = Integer.valueOf(line[i]);
		}
		long ans = solve(a, 0, n - 1);
		System.out.println(ans);
	}

	public static long solve(int[] a, int start, int end) {
		if (start == end) {
			return 0;
		}
		int mid = (start + end) / 2;
		int[] b = new int[end - start + 1];
		long ans = solve(a, start, mid) + solve(a, mid + 1, end);
		{
			int i = start;
			int j = mid + 1;
			int k = 0;
			while (i <= mid || j <= end) {
				if (i <= mid && (j > end || a[i] <= a[j])) {
					b[k++] = a[i++];
				} else {
					ans += (long) (mid - i + 1);
					b[k++] = a[j++];
				}
			}
		}
		for (int i = start; i <= end; i++) {
			a[i] = b[i - start];
		}
		return ans;
	}
}

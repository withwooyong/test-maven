package algorithm.baekjoon.middle1.C_binary_search;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class P1561 {

	private static Logger log = LoggerFactory.getLogger(P1561.class);

	/*
	 * 놀이 공원
	 * 한국어 원문 문제집
	 * 시간 제한 메모리 제한 제출 정답 맞은 사람 정답 비율
	 * 2 초 128 MB 1255 265 159 19.533%
	 * 문제
	 * N명의 아이들이 한 줄로 줄을 서서 놀이공원에서 1인승 놀이기구를 기다리고 있다. 이 놀이공원에는 총 M종류의 1인승 놀이기구가 있으며, 1번부터 M번까지 번호가 매겨져 있다.
	 * 
	 * 모든 놀이기구는 각각 정해진 운행 시간이 정해져 있어서, 운행 시간이 지나면 탑승하고 있던 아이는 내리게 된다. 놀이 기구가 비어 있으면 현재 줄에서 가장 앞에 서 있는 아이가 빈 놀이기구에 탑승한다.
	 * 만일 여러 개의 놀이기구가 동시에 비어 있으면, 더 작은 번호가 적혀 있는 놀이기구를 먼저 탑승한다고 한다.
	 * 
	 * 놀이기구가 모두 비어 있는 상태에서 첫 번째 아이가 놀이기구에 탑승한다고 할 때, 줄의 마지막 아이가 타게 되는 놀이기구의 번호를 구하는 프로그램을 작성하시오.
	 * 
	 * 입력
	 * 첫째 줄에 N(1<= N<= 2,000,000,000)과 M(1<= M<= 10,000)이 빈칸을 사이에 두고 주어진다. 둘째 줄에는 각 놀이기구의 운행 시간을 나타내는 M개의 자연수가 순서대로
	 * 주어진다. 운행 시간은 1 이상 30 이하의 자연수이며, 단위는 분이다.
	 * 
	 * 출력
	 * 첫째 줄에 마지막 아이가 타게 되는 놀이기구의 번호를 출력한다.
	 * 
	 * 예제 입력 복사
	 * 22 5
	 * 1 2 3 4 5
	 * 예제 출력 복사
	 * 4
	 */
	public static void main(String args[]) {
		solution();
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int p = sc.nextInt();
		int n = sc.nextInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++) {
			a[i] = sc.nextInt();
		}
		if (p <= n) {
			System.out.println(p);
			return;
		}
		long left = 0;
		long right = 2000000000L * 1000000L;
		while (left <= right) {
			long mid = (left + right) / 2;
			long begin, end;
			begin = end = 0;
			end = n;
			for (int i = 0; i < n; i++) {
				end += mid / a[i];
			}
			begin = end;
			for (int i = 0; i < n; i++) {
				if (mid % a[i] == 0) {
					begin -= 1;
				}
			}
			begin += 1;
			if (p < begin) {
				right = mid - 1;
			} else if (p > end) {
				left = mid + 1;
			} else {
				for (int i = 0; i < n; i++) {
					if (mid % a[i] == 0) {
						if (p == begin) {
							System.out.println(i + 1);
							return;
						}
						begin += 1;
					}
				}
			}
		}
		sc.close();
	}
}

package codility.lesson14.Binarysearchalgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * http://cafe.naver.com/aphone/59100
 * 6명 있을경우.
 * 2명씩 짝지을수잇는 모든경우의수를
 */
public class Pairing {
	
	private static Logger log = LoggerFactory.getLogger(Pairing.class);

	private static String[] name = { "A", "B", "C", "D", "E", "F" };
	
	public static void main(String args[]) {
		int[] arr = new int[6];
		int[][] pair = new int[3][2];
		
		makePair(arr, arr.length, pair, 0);
	}	

	public static void print(int[][] pair) {
		System.out.format("[%s %s]", name[pair[0][0]], name[pair[0][1]]);
		for (int i = 1; i < pair.length; i++) {
			System.out.format(",[%s %s]", name[pair[i][0]], name[pair[i][1]]);
		}
		System.out.println();
	}

	/**
	 * makePair
	 * 
	 * arr 에 짝이 없는 사람들을 짝지어줌
	 * 
	 * @param arr
	 *            사람들, -1 은 짝 없음.
	 * @param n
	 *            남은 사람 수
	 * @param pair
	 *            짝
	 * @param p
	 *            짝의 수
	 */
	public static void makePair(int[] arr, int n, int[][] pair, int p) {
		int i, j;
		
		// 남은 사람이 없으면, pair를 출력하고 되돌아감.
		if (n <= 0) {
			print(pair);
			return;
		}
		// 짝이 없는 첫번째 사람을 선택
		for (i = 0; i < arr.length; i++) {
			if (arr[i] == -1)
				break;
		}
		arr[i] = p;
		pair[p][0] = i;

		// 나머지 사람들에 대해
		for (j = i + 1; j < arr.length; j++) {
			if (arr[j] == -1) { // 짝이 없으면
				pair[p][1] = j; // 짝을 맺어보고
				arr[j] = p; // 짝으로 등록
				makePair(arr, n - 2, pair, p + 1); // 남은 사람들을 짝지음
				arr[j] = -1; // 다른 사람과 짝을 맺어보기 위해 헤어짐.
			}
		}
		arr[i] = -1;
	}
}

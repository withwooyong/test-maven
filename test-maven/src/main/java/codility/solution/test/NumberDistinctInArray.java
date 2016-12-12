package codility.solution.test;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NumberDistinctInArray {
	
	private static Logger log = LoggerFactory.getLogger(NumberDistinctInArray.class);

	public static void main(String[] args) {
		int[] a = { -5, -3, -1, 0, 3, 6 };
		int[] b = { -1, -1 };
		log.debug("{}", solution(a));
		log.debug("{}", solution(b));
		genericTets();
	}
	
	public static int solution(int[] A) {
		int distance = 0;
		int[] B = new int[A.length];

		if (A.length == 1) {
			return 1;
		} else {
			for (int i = 0; i < A.length; i++) {
				B[i] = Math.abs(A[i]); // 절대값 / 숫자간의 거리 측정할 경우 음수를 포함해야 한다.
			}
			Arrays.sort(B);

			// 여기 좀 이상함 왜 B를 안쓰지?
			for (int i = 1; i < A.length; i += 2) {
				if (A[i] != A[i - 1]) {
					if (i == 1 && A.length > 3) {
						distance++;
					} else {
						distance += 2;
					}
				} else {
					distance++;
				}
			}
			return distance;
		}
	}
	
	public static void genericTets() {
		Set<Object> set = new HashSet<>();
		Integer i = new Integer(10);
		set.add(i);
		String s = "d";
		set.add(s);
		log.debug("{}", set.contains(i));
		log.debug("{}", set.contains(s));
	}
}

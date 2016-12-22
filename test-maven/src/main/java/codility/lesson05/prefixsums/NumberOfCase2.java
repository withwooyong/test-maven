package codility.lesson05.prefixsums;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
 * permutation 순열
 * combination 조합
 * nPr, nCr, 펙토리얼 경우의수 구하기
 */
public class NumberOfCase2 {

	private static Logger log = LoggerFactory.getLogger(NumberOfCase2.class);

	public static void main(String[] args) {
		int n = 10;
		int r = 2;
		log.debug("permutation={}", permutation(n, r));
		log.debug("combination={}", combination(n, r));
	}

	/*
	 * nCr (조합) : n개의 카드 중에 r개를 뽑아 나열할 수 있는 경우의 수,
	 * ### 단 중복은 불가능하다.
	 * 예) 10명의 인원에서 2명씩 짝을 이룰 수 있는 경우의 수.
	 * 수학공식 : nCr = nPr / r!
	 */
	private static int combination(int n, int r) {
		return permutation(n, r) / factorial(r);
	}

	/*
	 * nPr (순열) : n개의 카드 중에 r개를 뽑아 나열할 수 있는 경우의 수,
	 * ### 중복이 가능하다.
	 * 예) 10명의 인원에서 2명을 뽑아 반장, 부반장을 뽑을 수 있는 경우의 수.
	 * 수학공식 : nPr = n! / (n-r)!
	 * nPr = n * (n-1) * (n-2) *...*(n-r+1)
	 */
	private static int permutation(int n, int r) {
		if (r == 1) {
			return n;
		} else {
			return n * permutation(n - 1, r - 1);
		}
	}

	/*
	 * ! (펙토리얼) : n개를 일렬로 줄 세워서 나올 수 있는 경우의 수.
	 * 예) 10명의 인원을 한줄로 세울 수 있는 경우의 수.
	 * 수학공식 : n! = n * (n-1) * (n-2) *...*(1)
	 */
	private static int factorial(int r) {
		if (r == 1) {
			return 1;
		} else {
			return r * factorial(r - 1);
		}
	}
}

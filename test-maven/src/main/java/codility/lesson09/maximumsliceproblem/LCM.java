package codility.lesson09.maximumsliceproblem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LCM {

	private static Logger log = LoggerFactory.getLogger(LCM.class);

	public static void main(String[] args) {

		long[] C = { 12, 8 };
		int A = 12;
		int B = 8;
		euclid(A, B);

		log.debug("{}", lcm(4, 6)); // 12
		log.debug("{}", lcm(21, 6)); // 42
		log.debug("{}", lcm(-5, -4)); // 20
		log.debug("{}", lcm(-9, 2)); // 18
		log.debug("{}", lcm(0, 0)); // 0

		// 세 숫자의 최소공배수 구하기
		long result = lcm(45, lcm(120, 75));
		log.debug("{}", result); // 1800

		// 네 숫자의 최소공배수 구하기
		result = lcm(112, lcm(113, lcm(114, 119)));
		log.debug("{}", result); // 12263664
	}

	private static void euclid(int A, int B) {
		log.debug("{} {}", A, B);
		if (A < B) { // 큰값 작은값 순으로 들어와야 함.
			return;
		}
		int R = 1;
		// 최소공배수
		int LCM = 0;
		int C = A;
		int D = B;
		while (R > 0) {
			R = A % B;
			A = B;
			B = R;
		}
		LCM = C * D / A;
		log.debug("최소공배수 LCM={}", LCM); // 24
		/*
		 * 최대공약수(最大公約數)란, 0이 아닌 두 정수나 다항식의 공통되는 약수 중에서 가장 큰 수를 말한다. 두 정수 a와 b의
		 * 최대공약수를 기호로 gcd(a,b)로 표기한다.
		 */
		log.debug("최대공약수 GCD={}", A); // 4
	}

	// 최소 공배수 계산 메서드
	// 최소공배수는 엄청나게 큰 숫자가 나올 수도 있기에
	// long형으로 다루어야 합니다.
	public static long lcm(long a, long b) {
		int gcd_value = gcd((int) a, (int) b);

		if (gcd_value == 0)
			return 0; // 인수가 둘다 0일 때의 에러 처리

		return Math.abs((a * b) / gcd_value);
	}

	// 최대 공약수 계산 함수; 최소 공배수 계산에 필요함
	// 최대 공약수는 그리 큰 숫자가 나오지 않기에 int형으로
	public static int gcd(int a, int b) {
		while (b != 0) {
			int temp = a % b;
			a = b;
			b = temp;
		}
		return Math.abs(a);
	}

}

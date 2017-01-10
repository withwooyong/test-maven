package algorithm.baekjoon;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitOperation {	
	
	private static Logger log = LoggerFactory.getLogger(BitOperation.class); 

	/*
	 * <비트 연산자>
	 * 컴퓨터 내부의 정보를 비트 단위로 비교하거나 조작할때 사용한다.
	 * 
	 * ○ 논리곱(and) &
	 * 각 비트를 비교하여 양쪽 모두 1 이면 1, 아니면 0 을 반환한다.
	 * ○ 논리합(or) |
	 * 각 비트를 비교하여 어느 한쪽이 1 이면 1, 그렇지 않으면 0 을 반환한다.
	 * ○ 배타적 논리함(xor) ^
	 * 각 비트를 비교하여 한쪽이 1 이고 다른 한쪽이 0 이면 1을, 아니면 0 을 반환한다.
	 * ○ 1의 보수 표현(not) ~
	 * 각 비트를 반전시킨 값을 반환한다.
	 */
	public static void main(String[] args) throws Exception {
		int A = 170; // A   = 1 0 1 0 1 0 1 0
		int B = 245; // B   = 1 1 1 1 0 1 0 1
		log.debug("{}", Integer.toBinaryString(A));
		log.debug("{}", Integer.toBinaryString(B));
		and(A, B);
		or(A, B);
		xor(A, B);
		not(A);
		test();
		log.debug("{}", Integer.toBinaryString(570));
	}

	private static void solution() {
		Scanner sc = new Scanner(System.in);
		int A = 0;
		int B = 0;
		log.debug("~A={}", ~A);
		log.debug("A & B={}", A & B);
		log.debug("A | B={}", A | B);
		log.debug("A ^ B={}", A ^ B);
		
		B = 1;
		log.debug("");
		log.debug("~A={}", ~A);
		log.debug("A & B={}", A & B);
		log.debug("A | B={}", A | B);
		log.debug("A ^ B={}", A ^ B);
		sc.close();
	}
	
	/*
	 * ○ 논리곱(and) &
	 * 각 비트를 비교하여 양쪽 모두 1 이면 1, 아니면 0 을 반환한다.
	 */
	public static void and(int A, int B) {
		// A&B = 1 0 1 0 0 0 0 0
		log.debug("{}", Integer.toBinaryString(A & B));
		log.debug("{}", A & B);
	}
	
	/*
	 * ○ 논리합(or) |
	 * 각 비트를 비교하여 어느 한쪽이 1 이면 1, 그렇지 않으면 0 을 반환한다.
	 */
	public static void or(int A, int B) {
		log.debug("{}", Integer.toBinaryString(A | B));
		log.debug("{}", A | B);
	}
	
	/*
	 * ○ 배타적 논리함(xor) ^
	 * 각 비트를 비교하여 한쪽이 1 이고 다른 한쪽이 0 이면 1을, 아니면 0 을 반환한다.
	 */
	public static void xor(int A, int B) {
		log.debug("{}", Integer.toBinaryString(A ^ B));
		log.debug("{}", A ^ B);
	}
	
	public static void not(int A) {
		log.debug("{}", Integer.toBinaryString(~A));
		log.debug("{}", ~A);
	}
	
	/*
	 * ○ 왼쪽 시프트 연산자 <<
	 * 178 << 2 : 178 의 이진코드를 왼쪽으로 2비트 시프트 한다.
	 *   10110010
	 *   1011001000
	 *   왼쪽으로 두칸 밀면서, 비게 되는 오른쪽 두칸은 0 으로 채운다.
	 *   그런데, 문제는 왼쪽으로 밀면서 기존의 크기를 넘어서기 때문에 왼쪽으로 넘어선 2개의 비트는 삭제된다.
	 *   따라서, 10110010 을 왼쪽으로 밀면 왼쪽 두개 비트인 10 은 삭제되고, 오른쪽의 2개 비트는 0으로 채워져
	 *   결과값은 11001000 이 된다.
	 *   출처:[java] 비트 연산자와 시프트 연산자
	 */
	
	/*
	 * ○ 오른쪽 시프트 연산자 >>
	 * 178 >> 2 : 178의 이진코드를 오른쪽으로 2 비트 시프트 한다.
	 * 10110010
	 * 1110110010
	 * 오른쪽으로 2비트 이동한후, 비게되는 왼쪽의 2개비트는 1로 채워지고, 오른쪽에서 2비트 넘어간 부분은 삭제된다.
	 * 따라서, 10110010 을 오른쪽으로 2비트 시프트하면, 
	 *          11101100 이 된다.
	 * 그런데, 주의할점은, 오른쪽으로 밀면서 왼쪽에 비게되는 비트부분이 무조건 1 로 채워지는 것이 아니라,
	 * 밀기전의 최초 첫째자리 값과 동일한 값으로 채워진다는 것이다.
	 * 여기에서는 밀기전의 첫째자리값이 1 이었으므로, 1 로 채워진 것이다.
	 * 출처:[java] 비트 연산자와 시프트 연산자
	 */
	
	/*
	 * ○ 논리 오른쪽 시프트 연산자 >>>
	 * 178 >>> 2 : 오른쪽으로 2 비트 시프트한다.
	 * 자바에 추가된 논리 시프트는 오른쪽으로 밀면서 비게되는 앞쪽 비트를 무조건 0 으로 채워넣는 것이다.
	 *   10110010
	 * 0010110010
	 * 으로 되는 것으로,
	 * 역시 오른쪽으로 밀려난 2개 비트 10 은 삭제되고, 비게되는 왼쪽 2비트는 무조건 0으로 채워진다.
	 * 따라서 10110010 을 오른쪽으로 논리 시프트 하면, 
	 *     00101100 이 된다.
	 * 출처:[java] 비트 연산자와 시프트 연산자
	 */
	
	public static void test() {
		byte a = 10;  // 00001010
	    byte b = 9;   // 00001001
	    byte c = 1;  //시프트할 칸수
	    System.out.println(a + " & " + b + " = " + (a&b));  //논리합
	    System.out.println(a + " | " + b + " = " + (a|b));  //논리곱
	    System.out.println(a + " ^ " + b + " = " + (a^b));  //배타적 논리합(xor)
	    System.out.println("~10 = " + (~a));  //a 의 보수(반전)
	    System.out.println(a + " << " + c + " = " + (a<<c));  //왼쪽 1비트 시프트(뒤를 0 으로 채움)
	    System.out.println(a + " >> " + c + " = " + (a>>c));  //오른쪽 1비트 시프트(앞을 밀리기전 첫째자리와 동일한 비트로 채움)
	    System.out.println(a + " >>> " + c + " = " + (a>>>c)); //오른쪽 1비트 논리 시프트(앞을 0 으로 채움)
	    System.out.println(-1 * a + " >> " + c + " = " + (-1*a >> c));


	}
}

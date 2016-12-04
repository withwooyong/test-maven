package codility.solution;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FindMissingNumber {

	private static Logger log = LoggerFactory.getLogger(FindMissingNumber.class);
	public static void main(String[] args) {
		int[] A = { 1, 2, 3, 4, 6, 7, 8, 9, 10 };
		solution(A, A.length);
		
		int a = 1;
		int b = 2;
		log.debug("{}", Integer.toBinaryString(a));
		log.debug("{}", Integer.toBinaryString(b));
		log.debug("{}", a ^ b);
		
	}
	
	//  ^ (비트 XOR) 두 피연산자의 대응되는 비트에서 서로 같은 경우에는 0을, 다른 경우에는 1을 반환 a ^ b 
	// 1. 모든 배열의 항목을 XOR 한다. XOR의 결과를 X
	// 2. 1에서 n까지의 모든 숫자를 XOR 한다. 이결과를 Y
	// 3. X와 Y를 XOR하면 빠진 숫자가 나온다.
	// O(n)
	
	public static void solution(int[] A, int N) {
		int X = 0;
		int Y = 0;
		log.debug("{}", Arrays.toString(A));
		for (int i = 0; i < N; i++) {
			X ^= A[i];
			System.out.print(" X=" + X);
		}
		System.out.println();
		for (int j = 1; j <= N+1; j++) {
			Y ^= j;
			System.out.print(" Y=" + Y);
		}
		System.out.println();
		log.debug("{}", X);
		log.debug("{}", Y);
		log.debug("{}", X ^ Y);
	}

}

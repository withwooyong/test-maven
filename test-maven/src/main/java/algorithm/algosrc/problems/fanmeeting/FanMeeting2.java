package algorithm.algosrc.problems.fanmeeting;

import java.math.BigInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FanMeeting2 {
	
	private static Logger log = LoggerFactory.getLogger(FanMeeting2.class);

	public static void main(String[] args) {
	    
	    String members = "FFFMMM";
	    String fans = "MMMFFF";
	    log.debug("{}", hugs(members, fans));	
	}
	
	private static int hugs(String members, String fans) {
		int N = members.length();
		int M = fans.length();
		int[] A = new int[N];
		int[] B = new int[M];
		
		for(int i = 0; i < N; i++) {
			if (members.charAt(i) == 'M') { // 남성이면 1 여성이면 0
				A[i] = 1;
			}
			 
		}
		for(int i = 0; i < M; i++) {
			if (fans.charAt(i) == 'M') {
				B[M-i-1] = 1;
			}
		}
		
		// karatsuba 알고리즘에서 자리 올림은 생략한다
		BigInteger C = karatsuba(A, B);
		int allHugs = 0;
		for(int i = N-1; i < M; ++i) { 
			if(C.toString(i) == "0") {
				allHugs++;
			}
		}
		return allHugs;
	}

	public static BigInteger karatsuba(BigInteger x, BigInteger y) {

		// cutoff to brute force
		int N = Math.max(x.bitLength(), y.bitLength());
		if (N <= 2000)
			return x.multiply(y); // optimize this parameter

		// number of bits divided by 2, rounded up
		N = (N / 2) + (N % 2);

		// x = a + 2^N b, y = c + 2^N d
		BigInteger b = x.shiftRight(N);
		BigInteger a = x.subtract(b.shiftLeft(N));
		BigInteger d = y.shiftRight(N);
		BigInteger c = y.subtract(d.shiftLeft(N));

		// compute sub-expressions
		BigInteger ac = karatsuba(a, c);
		BigInteger bd = karatsuba(b, d);
		BigInteger abcd = karatsuba(a.add(b), c.add(d));

		return ac.add(abcd.subtract(ac).subtract(bd).shiftLeft(N)).add(bd.shiftLeft(2 * N));
	}
}

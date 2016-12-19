package algorithm.algosrc.problems.fanmeeting;

import java.math.BigInteger;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Karatsuba {
	
	private static Logger log = LoggerFactory.getLogger(Karatsuba.class);
	
	private final static BigInteger ZERO = new BigInteger("0");
	
	public static void main(String[] args) {
		long start;
		long stop;
		long elapsed;
        Random random = new Random();
        int N = Integer.parseInt("10");
        BigInteger a = new BigInteger(N, random);
        BigInteger b = new BigInteger(N, random);

        log.debug("{} {}", a, b);
        start = System.currentTimeMillis(); 
        BigInteger c = karatsuba(a, b);
        stop = System.currentTimeMillis();
        log.debug("{}", stop - start);

        start = System.currentTimeMillis(); 
        BigInteger d = a.multiply(b);
        stop = System.currentTimeMillis();
        log.debug("{}", stop - start);
        
        log.debug("{}", (c.equals(d)));
        log.debug("{}", c);
        log.debug("{}", d);
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

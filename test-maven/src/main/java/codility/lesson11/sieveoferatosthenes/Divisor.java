package codility.lesson11.sieveoferatosthenes;

import java.math.BigInteger;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Divisor {
	
	private static Logger log = LoggerFactory.getLogger(Divisor.class);

	public static void main(String[] args) {
		
		int[] A = new int[] { 3, 1, 2, 3, 6 };
//		log.debug("{}", solution());
//		log.debug("{}", solution2());
//		log.debug("{}", solultion3(A));		
		log.debug("{}", solultion4(6));
	}
	
	private static int solultion4(int input) {		
		ArrayList<Integer> divisors = new ArrayList<>();	
		int count = 0;
		int high = input;
		// 1 부터 n까지의 약수 divisor
		for (int n = 1; n <= high; n++) {
			if (input % n == 0) {
				divisors.add(n);
//				int x = input / n;
//				divisors.add(n);
//				high = x - 1;
			} 
			count++;
		}
		log.debug("{} {}", count, divisors.toString());
		return count;
		
	}
	
	// 배열의 요소별 약수를 구해보자..
	private static int solultion3(int[] A) {
		
		for (int i = 0; i < A.length; i++) {
			ArrayList<Integer> divisors = new ArrayList<>();
			
			for (int j = 1; j <= A[i]; j++) {
				if (A[i] % j == 0) { // divisor
					divisors.add(j);
				}
			}
			log.debug("{} {} ", A[i], divisors.toString());
		}		
		return 0;
	}
	
	
	// 약수(約數, divisor)는 어떤 수를 나누었을 때 나머지가 0인 수
	// 98의 약수는 1, 2, 7, 14, 49, 98, are divisors of 98
	private static int solution() {
		int n = 20; // 1, 2, 7, 14, 49, 98, are divisors of 98

		ArrayList<Integer> arr = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			// If remainder is 0 when 'n' is divided by 'i',
			if (n % i == 0) { // divisor
				arr.add(i); 
			}
		}
		log.debug("{}", arr.toString());
		return arr.size();
	}
	
	// get greatest common divisor
	private static int solution2() {
		int a = 10;  // [1, 2, 5, 10]
		int b = 20;  // [1, 2, 4, 5, 10, 20]
		BigInteger b1 = BigInteger.valueOf(a);
	    BigInteger b2 = BigInteger.valueOf(b);
	    BigInteger gcd = b1.gcd(b2);
	    
	    log.debug("{} {} {}", b1, b2, gcd.intValue());
	    return gcd.intValue();
	}
}

package algorithm.algosrc.src;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Fraction2 {
	
	private static Logger log = LoggerFactory.getLogger(Fraction2.class);

	public static void main(String[] args) {
		printDecimal(3, 8);	
		printDecimal2(3, 8);
//		printDecimal(52, 8);	
//		printDecimal2(52, 8);
//		printDecimal(521, 8);		
//		printDecimal(1, 8);		
//		printDecimal(2, 4);		
//		printDecimal(23, 400);		
//		printDecimal(4712, 400);		
//		printDecimal(54, 6);

	}
	
	// 분수 a/b 의 소수 표현을 출력한다
	private static void printDecimal(int a, int b) {
		int iter = 0;
		while(a > 0) {
			if(iter++ == 1) {
				log.debug("{}", ".");
			}
			log.debug("{}", a / b);
			a = (a % b) * 10;
		}
	}
	
	private static void printDecimal2(double a, double b) {
		log.debug("{}", a / b);
	}

}

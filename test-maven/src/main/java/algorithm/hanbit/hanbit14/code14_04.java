package algorithm.hanbit.hanbit14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code14_04 {

	private static Logger log = LoggerFactory.getLogger(code14_04.class);
	
	/*
	 * 2
	 * 12 21 31 40 49 58 69 79 90 101
	 * 15
	 * 12 20 30 40 25 60 70 80 90 11
	 * 21
	 * 실행안됨.
	 */
	public static void main(String[] args) {
		int Period = 15;
		int i, j;
		
		
		int[] daypay = { 12, 21, 31, 40, 49, 58, 69, 79, 90, 101 };
		int[] OptimalPay = new int[daypay.length]; 

	
		for (i = 0; i < daypay.length; i++) {
			OptimalPay[i] = (i + 1) * daypay[0];
		}
		
		for (i = 1; i < daypay.length; i++) {
			int fixedDaypay = daypay[i];
			if (OptimalPay[i] > fixedDaypay)
				OptimalPay[i] = fixedDaypay;

			for (j = i + 1; j < daypay.length; j++) {
				if (OptimalPay[j] >(fixedDaypay + OptimalPay[j - i - 1]))
					OptimalPay[j] = (fixedDaypay + OptimalPay[j - i - 1]);
			}
		}
		log.debug("{}", OptimalPay[Period - 1]);
		
	}
}

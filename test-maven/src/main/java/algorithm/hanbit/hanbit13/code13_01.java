package algorithm.hanbit.hanbit13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code13_01 {
	
	private static Logger log = LoggerFactory.getLogger(code13_01.class);

	private static int MAX = 301;
	
	public static void main(String[] args) {
	
		int testCases = 2;
		int tc;
		int baseNumber = 10;
		int i;
		int Solution;

		for (tc = 0; tc < testCases; tc++) {
			Solution = 0;

			for (i = 1; i < MAX; i++) {
				Solution += makeReverseNumber(i * i, baseNumber);
			}
			log.debug("{}", Solution);
		}
	}

	private static int makeReverseNumber(int num_Origin, int b) {
		int tmp;
		int num_Reverse = 0;
		tmp = num_Origin;
		num_Reverse = 0;

		while (tmp > 0) {
			num_Reverse = (num_Reverse * b + (tmp % b));
			tmp = tmp / b;
		}

		if (num_Reverse == num_Origin) {
			log.debug("num_Reverse : {}, num_Origin : {}", num_Reverse, num_Origin);
		}

		return num_Reverse;
	}

}

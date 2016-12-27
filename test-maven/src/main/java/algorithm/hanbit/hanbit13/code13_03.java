package algorithm.hanbit.hanbit13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code13_03 {

	private static Logger log = LoggerFactory.getLogger(code13_03.class);

	public static void main(String[] args) {

		int[] data = { -1, 5, 2, -2, 8 };
		int Solution;
		
		int i;
		int sum = 0;			
		Solution = Integer.MIN_VALUE;
		for (i = 0; i < data.length; i++) {
			sum = sum + data[i];

			if (Solution < sum) {
				Solution = sum;
				log.debug("{}", Solution);
			}

			if (sum < 0) {
				sum = 0;
			}
		}
		log.debug("{}", sum);
	}
}

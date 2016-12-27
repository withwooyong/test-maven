package algorithm.hanbit.hanbit12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code12_03 {

	private static Logger log = LoggerFactory.getLogger(code12_03.class);
	
	public static void main(String[] args) {
		int testCases = 10;
		int maxNumber = 10;
		int Solutions;		
		int flag = 0;
		
		for (int i = 0; i < testCases; i++) {
			Solutions = 0;
			for (int j = 1; j <= maxNumber; j++) {
				for (int k = 2; k <= maxNumber; k++) {
					if (j < k && GCD(j, k) == 1) {
						if (flag == 0) {
							log.debug("{} {}", 0, 1);
							flag = 1;
						}

						log.debug("{} {}", j, k);
						Solutions++;
					}
				}
			}
			log.debug("{} {}", 1, 1);
			log.debug("{}", Solutions + 2);
		}

	}
	
	private static int GCD(int p, int q) {
		if (q == 0) {
			return p;
		}
		return GCD(q, p % q);
	}

}

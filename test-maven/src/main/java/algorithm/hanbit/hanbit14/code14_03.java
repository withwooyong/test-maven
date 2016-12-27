package algorithm.hanbit.hanbit14;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code14_03 {

	private static Logger log = LoggerFactory.getLogger(code14_03.class);
	
	// 실행안됨.
	public static void main(String[] args) {
		double[] xpos = { 3.00, 6.00, 8.00, 12.00, 17.00, 20.00, 26.00, 28.00, 32.00 };
		double[] heights = { 10.00, 5.00, 13.00, 6.00, 4.00, 10.00, 17.00, 14.00, 4.00 };
		int numofStake = 9;
		int i = 1;
		int Solutions = 0;
		double ratio;
		double maxRatio;		
		
		while (i < numofStake) {
			maxRatio = Double.MAX_VALUE;

			for (int j = i + 1; j < xpos.length; j++) {
				ratio = (heights[j - 1] - heights[i - 1]) / (xpos[j - 1] - xpos[i - 1]);

				if (ratio > maxRatio) {
					maxRatio = ratio;
					Solutions = j;
					log.debug("maxRatio : {}, Solutions : {}", maxRatio, Solutions);
				}
			}
			i = Solutions;
			log.debug("{}", Solutions);

			if (i == numofStake) {
				break;
			}
		}
	}
}

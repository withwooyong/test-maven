package algorithm.hanbit.hanbit12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code12_04 {

	private static Logger log = LoggerFactory.getLogger(code12_04.class);	 
	
	public static void main(String[] args) {
		// 2 5 1 1 1 1 6 2 1 3
		int testCases = 10;
		int num_Pockets = 10;
		int num_Moves;
		int totalBeans;
		int averBeans;
		int MAX_POCKETS = 32000;
		int[] eachPocket = new int[MAX_POCKETS];
		
		for (int i = 0; i < testCases; i++) {
			num_Moves = 0;
			totalBeans = 0;
			
			for (int j = 0; j < num_Pockets; j++) {
				totalBeans = totalBeans + eachPocket[j];
			}
			
			if ((totalBeans % num_Pockets) > num_Pockets) {
				num_Moves = -1;
			} else {
				averBeans = totalBeans / num_Pockets;

				for (int j = 0; j < num_Pockets; j++) {
					if (eachPocket[j] < averBeans) {
						num_Moves = num_Moves + (averBeans - eachPocket[j]);
						log.debug("{} {}", j, (averBeans - eachPocket[j]));
					}
				}
			}
			log.debug("{}", num_Moves);
		}
	}
}

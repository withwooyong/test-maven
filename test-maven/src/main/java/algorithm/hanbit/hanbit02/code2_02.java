package algorithm.hanbit.hanbit02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code2_02 {

	private static Logger log = LoggerFactory.getLogger(code2_02.class);
	
	public static void main(String[] args) {
		
		int[] Scores = { 1, 2, 3, 4, 5 };
		double total, aver;

		total = 0;

		for (int i = 0; i < Scores.length; i++) {
			total += Scores[i];
		}

		aver = total / Scores.length;
		log.debug("{} {}", total, aver);
	}

}

package algorithm.topcoder;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KiwiJuiceEasy {

	private static Logger log = LoggerFactory.getLogger(KiwiJuiceEasy.class);
	
	/*
	 * 
	 */
	public static void main(String[] args) throws Exception {
		solution();
	}

	private static void solution() {
		int[] capacities = { 10, 10 };
		int[] bottles = { 5, 8 };
		int[] fromId = { 0 };
		int[] toId = { 1 };
		for (int i = 0; i < fromId.length; i++) {
			int f = fromId[i];
			int t = toId[i];			
			int space = capacities[t] - bottles[t];
			
			if (space >= bottles[f]) {
				int vol = bottles[f];
				bottles[t] += vol;
				bottles[f] = 0;
			} else {
				int vol = space;
				bottles[t] += vol;
				bottles[f] -= vol;
			}
		}
		log.debug("{}", Arrays.toString(bottles));
		
	}
}

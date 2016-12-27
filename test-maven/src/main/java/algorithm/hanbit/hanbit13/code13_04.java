package algorithm.hanbit.hanbit13;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code13_04 {

	private static Logger log = LoggerFactory.getLogger(code13_04.class);

	private static int[] data = { 75, 30, 100, 38, 50, 51, 52, 20, 81, 5 };

	public static void main(String[] args) {		
		log.debug("{}", getHeight(1, 10));
	}

	private static int getHeight(int n, int m) {
		int min = Integer.MAX_VALUE;
		
		log.debug("{}", Arrays.toString(data));

		for (int j = n - 1; j < m; j++) {
			log.debug("{} {}", min, data[j]);
			if (min > data[j]) {
				min = data[j];
			}
		}
		return min;

	}
}

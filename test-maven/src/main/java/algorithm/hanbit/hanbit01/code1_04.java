package algorithm.hanbit.hanbit01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_04 {

	private static Logger log = LoggerFactory.getLogger(code1_04.class);
	
	public static void main(String[] args) {
		int i;
		int input = 10;
		int[] data = new int[1000];

		for (i = 0; i < 1000; i++) {
			data[i] = i + 1;
		}
		
		for (i = 0; i < 1000; i++) {
			if (input == data[i]) {
				log.debug("{}", i + 1);
				break;
			}
		}

	}

}

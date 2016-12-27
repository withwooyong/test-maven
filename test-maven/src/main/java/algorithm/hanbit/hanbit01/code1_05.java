package algorithm.hanbit.hanbit01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_05 {

	private static Logger log = LoggerFactory.getLogger(code1_05.class);
	
	public static void main(String[] args) {
		int i;
		int input = 10;
		int[] data = new int[1000];
		int min = 0;
		int max = 1000;

		for (i = 0; i < 1000; i++) {
			data[i] = i + 1;
		}		

		i = (max + min) / 2; // 분할정복
		while (min <= max) {
			if (input == data[i]) {
				log.debug("{}", i + 1);
				break;
			} else if (input < data[i]) {
				max = (max + min) / 2 - 1;
			} else {
				min = (max + min) / 2 + 1;
			}
			i = (max + min) / 2;
		}
	}

}

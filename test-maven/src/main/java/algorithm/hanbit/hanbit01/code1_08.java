package algorithm.hanbit.hanbit01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_08 {

	private static Logger log = LoggerFactory.getLogger(code1_08.class);
	
	public static void main(String[] args) {
		int i;

		for (i = 0; i < 10; i++) {
			log.debug("{} X {} = {}", i, i, i * i);
		}
	}
}

package algorithm.hanbit.hanbit01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_09 {

	private static Logger log = LoggerFactory.getLogger(code1_09.class);
	
	public static void main(String[] args) {
		int Sum = 0;
		int i;

		for (i = 1; i <= 100; i++) {
			Sum = Sum + i;
		}

		log.debug("{}", Sum);


	}

}

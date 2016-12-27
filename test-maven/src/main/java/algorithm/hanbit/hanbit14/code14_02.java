package algorithm.hanbit.hanbit14;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code14_02 {

	private static Logger log = LoggerFactory.getLogger(code14_02.class);

	public static void main(String[] args) {
		
		int widthFrame = 8;
		int maxNumber = 100;
		int Solutions = 0;
		int localSum_1, localSum_2, localSum_3 = 0;

		if (widthFrame == 1) {
			Solutions = 2 % maxNumber;
		} else if (widthFrame == 2) {
			Solutions = 3 % maxNumber;
		} else {
			localSum_1 = 1;
			localSum_2 = 3;

			for (int i = 2; i < widthFrame; i++) {
				localSum_3 = (localSum_2 + 2 * localSum_1) % maxNumber;
				localSum_1 = localSum_2;
				localSum_2 = localSum_3;
				log.debug("localSum_1 : {}, localSum_2 : {}, localSum_3 : {}", localSum_1, localSum_2, localSum_3);
			}
			Solutions = localSum_3;
		}

		log.debug("{}", Solutions);

	}
}

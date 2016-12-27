package algorithm.hanbit.hanbit01;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_06 {

	private static Logger log = LoggerFactory.getLogger(code1_06.class);
	
	public static void main(String[] args) {
		int i;
		int[] data = new int[100];

		for (i = 0; i < 10; i++) {
			data[i] = i;
		}
		
		log.debug("{}", Arrays.toString(data));
	}

}

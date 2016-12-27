package algorithm.hanbit.hanbit01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code1_07 {

	private static Logger log = LoggerFactory.getLogger(code1_07.class);

	private static int Num_A;
	private static int Num_B;

	public static void main(String[] args) {
		Num_A = 100;
		Num_B = 90;

		log.debug("{}", Add());
		log.debug("{}", Subtract());
	}

	private static int Add() {
		return Num_A + Num_B;
	}

	private static int Subtract() {
		return Num_A - Num_B;
	}

}

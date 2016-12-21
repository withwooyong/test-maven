package codility.lesson03.timecomplexity;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RandomNumbers {
	
	private static Logger log = LoggerFactory.getLogger(RandomNumbers.class);

	public static void main(String[] args) {
		Random random = new Random();

		// Generating random integers using Random class
		for (int i = 0; i < 5; i++) {
			log.debug("{} Random Integers", random.nextInt());
		}		

		// Generating random doubles using Random class
		for (int i = 0; i < 5; i++) {
			log.debug("{} Random Doubles", random.nextDouble());
		}

		// Generating random booleans using Random class

		for (int i = 0; i < 5; i++) {
			log.debug("{} Random booleans", random.nextBoolean());
		}
	}

}

package algorithm.hanbit.hanbit13;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code13_05 {

	private static Logger log = LoggerFactory.getLogger(code13_05.class);
	
	public static void main(String[] args) {
		
		char[] initMatrix = { 'A', 'B', 'C', 'D' };
		for (int i = 0; i < initMatrix.length; i++) {
			for (int j = 0; j < initMatrix.length; j++) {
				char c = initMatrix[i ^ j];
				if (j == initMatrix.length - 1) {
					log.debug("{}", c);
				} else {
					log.debug("{}", c);
				}
			}
		}

	}
}

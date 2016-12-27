package algorithm.hanbit.hanbit14;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code14_01 {

	private static Logger log = LoggerFactory.getLogger(code14_01.class);
	
	public static void main(String[] args) {
		int oneJumpSteps, noJumpSteps;
		int num_Steps = 6;
		int[] value_Steps = { 10, 20, 15, 25, 10, 20 };
		int[] Solutions = new int[num_Steps];
		
		Solutions[0] = 0;
		Solutions[1] = value_Steps[1];
		Solutions[2] = value_Steps[1] + value_Steps[2];

		for (int j = 3; j < value_Steps.length; j++) {
			noJumpSteps = value_Steps[j] + value_Steps[j - 1] + Solutions[j - 3];
			oneJumpSteps = value_Steps[j] + Solutions[j - 2];

			if (noJumpSteps > oneJumpSteps) {
				Solutions[j] = noJumpSteps;
			} else {
				Solutions[j] = oneJumpSteps;
			}
			log.debug("Solutions {} : {}", j, Solutions[j]);
		}
		log.debug("{}", Arrays.toString(Solutions));
	}
}

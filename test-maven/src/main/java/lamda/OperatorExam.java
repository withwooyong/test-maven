package lamda;

import java.util.function.DoubleBinaryOperator;
import java.util.function.IntBinaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorExam {
	
	private final static Logger log = LoggerFactory.getLogger(OperatorExam.class);

	private static int[] scores = { 100, 92, 81, 78, 88, 96, 55, 94 };

	public static int maxOrMin(IntBinaryOperator operator) {
		int result = scores[0];
		for (int score : scores) {
			result = operator.applyAsInt(result, score);
			log.debug("result={}", result);
		}
		return result;
	}

	public static void main(String[] args) {
		int max = maxOrMin((a, b) -> {
			if (a >= b) { 
				return a;
			} else {
				return b;
			}
		});
		log.debug("최대 값 : {}", max);

		int min = maxOrMin((a, b) -> {
			if (a <= b) {
				return a;
			} else {
				return b;
			}
		});
		log.debug("최소 값 : {}", min);

	}

}

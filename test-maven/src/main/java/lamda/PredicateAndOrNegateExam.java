package lamda;

import java.util.function.IntPredicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PredicateAndOrNegateExam {
	
	private static final Logger log = LoggerFactory.getLogger(PredicateAndOrNegateExam.class);

	public static void main(String[] args) {
        IntPredicate predicateA = a -> a % 2 == 0; 
        IntPredicate predicateB = b -> b % 3 == 0;
 
        boolean result;
        IntPredicate predicateAB = predicateA.and(predicateB); // &&, ||, !
        result = predicateAB.test(9);
        log.debug("{}", "9는 2와 3의 배수입니까? " + result);
 
        predicateAB = predicateA.or(predicateB); // &&, ||, !
        result = predicateAB.test(9);
        log.debug("{}", "9는 2또는 3의 배수입니까? " + result);
 
        predicateAB = predicateA.negate(); // &&, ||, !
        result = predicateAB.test(9);
        log.debug("{}", "9는 홀수입니까? " + result);
 
    }
 
}

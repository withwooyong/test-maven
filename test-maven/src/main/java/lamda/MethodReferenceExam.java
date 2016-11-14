package lamda;

import java.util.function.IntBinaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MethodReferenceExam {

	private static final Logger log = LoggerFactory.getLogger(MethodReferenceExam.class);
	
	public static void main(String[] args) {
        IntBinaryOperator operator;
 
        operator = (x, y) -> Calculator.staticMethod(x, y);
        log.debug("{}", "결과1: " + operator.applyAsInt(1, 5));
 
        operator = Calculator::staticMethod;
        log.debug("{}", "결과2: " + operator.applyAsInt(5, 1));
 
        Calculator calc = new Calculator();
        operator = (x, y) -> calc.instanceMethod(x, y);
        log.debug("{}", "결과3: " + operator.applyAsInt(4, 8));
 
        operator = calc::instanceMethod;
        log.debug("{}", "결과4: " + operator.applyAsInt(8, 4));
    }
}

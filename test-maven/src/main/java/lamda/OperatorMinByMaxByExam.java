package lamda;

import java.util.function.BinaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OperatorMinByMaxByExam {

	private static final Logger log = LoggerFactory.getLogger(OperatorMinByMaxByExam.class);
	
	public static void main(String[] args) {
        BinaryOperator<Fruit> binaryOperator;
        Fruit fruit;
 
        binaryOperator = BinaryOperator.minBy((f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("Strawberry", 5000), new Fruit("Graph", 9000));
        log.debug("{}", fruit.name);
 
        binaryOperator = BinaryOperator.maxBy((f1, f2) -> Integer.compare(f1.price, f2.price));
        fruit = binaryOperator.apply(new Fruit("Strawberry", 5000), new Fruit("Graph", 9000));
        log.debug("{}", fruit.name);
 
    }
}

package lamda;

import java.util.function.Predicate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PredicateIsEqualExam {

	private static final Logger log = LoggerFactory.getLogger(PredicateIsEqualExam.class);
	
	public static void main(String[] args) {
        Predicate<String> predicate;
 
        predicate = Predicate.isEqual(null);
        log.debug("{}", "null, null: " + predicate.test(null));
 
        predicate = Predicate.isEqual("Java");
        log.debug("{}", "Java, null: " + predicate.test(null));
 
        predicate = Predicate.isEqual(null);
        log.debug("{}", "null, Java: " + predicate.test("Java"));
 
        predicate = Predicate.isEqual("Java");
        log.debug("{}", "Java, Java: " + predicate.test("Java"));
 
    }
}

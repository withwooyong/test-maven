package lamda;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConstructorReferenceExam {
	
	private static final Logger log = LoggerFactory.getLogger(ConstructorReferenceExam.class);

	public static void main(String[] args) {
        Function<String, Member> function1 = Member::new;
        Member member1 = function1.apply("Jolie");
 
        BiFunction<String, String, Member> function2 = Member::new;
        Member member2 = function2.apply("쥴리", "Jolie");
    }
 
}

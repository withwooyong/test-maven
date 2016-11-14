package lamda;

import java.util.function.ToIntBiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArgumentMethodReferencesExam {
	
	private static final Logger log = LoggerFactory.getLogger(ArgumentMethodReferencesExam.class);
	
	public static void main(String[] args) {
        ToIntBiFunction<String, String> function;
 
        function = (a, b) -> a.compareToIgnoreCase(b);
        print(function.applyAsInt("Java8", "JAVA8"));
 
        function = String::compareToIgnoreCase;
        print(function.applyAsInt("JAVA8", "Java8"));
    }
 
    public static void print(int order) {
        if (order < 0) {
            log.debug("{}", "사전순으로 먼저 나옵니다.");
        } else if (order == 0) {
        	log.debug("{}", "동일한 문자열입니다.");
        } else {
        	log.debug("{}", "사전순으로 나중에 나옵니다.");
        }
    }
}

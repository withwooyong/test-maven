package lamda;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FunctionAndThenComposeExam {
	
	private static final Logger log = LoggerFactory.getLogger(FunctionAndThenComposeExam.class);

	public static void main(String[] args) {
        Function<Member, Address> functionA;
        Function<Address, String> functionB;
        Function<Member, String> functionAB;
        
        String city;
        
        functionA = (m) -> m.getAddress();
        functionB = (a) -> a.getCity();
        
        functionAB = functionA.andThen(functionB);
        city = functionAB.apply(new Member("Jolie", "jolie34", new Address("France", "Cannes")));
        
        log.debug("{}", "거주 도시: " + city);
        
    }
}

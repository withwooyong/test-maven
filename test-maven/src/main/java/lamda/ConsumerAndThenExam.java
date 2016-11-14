package lamda;

import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerAndThenExam {

	private static final Logger log = LoggerFactory.getLogger(ConsumerAndThenExam.class);
	
	public static void main(String[] args) {
        Consumer<Member> consumerA = (m) -> {
            log.debug("{}", "conusmerA: " + m.getName());
        };
 
        Consumer<Member> consumerB = (m) -> {
        	log.debug("{}", "consumerB: " + m.getId());
        };
 
        Consumer<Member> consumerAB = consumerA.andThen(consumerB);
        consumerAB.accept(new Member("Jolie", "jolie34", null));
    }
}

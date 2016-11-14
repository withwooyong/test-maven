package problem;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ProblemTest {
	
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		ProblemTest test = new ProblemTest();
		test.solution();
	}
	
	public void solution() {
		log.debug("{}", "solution");
	}

}

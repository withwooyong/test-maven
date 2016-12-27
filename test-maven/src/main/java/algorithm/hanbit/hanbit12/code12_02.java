package algorithm.hanbit.hanbit12;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code12_02 {
	
	private static Logger log = LoggerFactory.getLogger(code12_02.class);

	public static void main(String[] args) {
		log.debug("{}", gcd(120, 32));

	}
	
	private static int gcd(int p, int q) {
		if (q == 0) {
			return p;
		}
		return gcd(q, p % q);
	}
}

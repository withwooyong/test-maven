package algorithm.hanbit.hanbit02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code2_01 {

	private static Logger log = LoggerFactory.getLogger(code2_01.class);
	
	public static void main(String[] args) {
		int i = 0;
		char[] buf = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k' };
		for (i = 0; i < buf.length; i++) {
			log.debug("{} {}", buf[i], buf[i] - ('a' - 'A')); // 이넘 무슨연산이지?
		}
	}

}

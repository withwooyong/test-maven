package algorithm.hanbit.hanbit02;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code2_05 {

	private static Logger log = LoggerFactory.getLogger(code2_05.class);
	
	// 자바는 포인터 없음.
	public static void main(String[] args) {
		int data = 10;
		int ptr = data;
		log.debug("{} {}", data, ptr);
	}
}

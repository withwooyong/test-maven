package algorithm.hanbit.hanbit12;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code12_01 {

	private static Logger log = LoggerFactory.getLogger(code12_01.class);
	private static int Cnt;
	
	public static void main(String[] args) {
		int n = 5;		
		hanoi(n, 1, 2);
		log.debug("{}", Cnt);
	}
	
	private static void hanoi(int n, int a, int b) {
		int temp;
		if (n == 1) {
			log.debug("{} {} {}", n, a, b);
		} else {
			temp = 6 - a - b;
			hanoi(n - 1, a, temp);
			log.debug("{} {} {}", n, a, b);
			hanoi(n - 1, temp, b);
		}
		Cnt++;
	}
}

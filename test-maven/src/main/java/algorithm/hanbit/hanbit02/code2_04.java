package algorithm.hanbit.hanbit02;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code2_04 {

	private static Logger log = LoggerFactory.getLogger(code2_04.class);
	
	private static int[][] Grade = new int[5][2];
	
	public static void main(String[] args) {		

		for (int i = 0; i < 5; i++) {			
			Grade[i][0] = i;
			Grade[i][1] = i;
		}
		log.debug("{}", Arrays.deepToString(Grade));
		DisplayTable();

	}
	
	private static void DisplayTable() {
		int i;
		int[] Total = { 0, 0 };
		
		for (i = 0; i < 5; i++) {
			log.debug("{} {} {}", i + 1, Grade[i][0], Grade[i][1]);
			Total[0] += Grade[i][0];
			Total[1] += Grade[i][1];
		}
		
		log.debug("{} {}", Total[0], Total[1]);
		log.debug("{} {}", Total[0] / 5, Total[1] / 5);
	}

}

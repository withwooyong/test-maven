package algorithm.hanbit.hanbit03;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 링크드 리스트 
public class code3_01 {

	private static Logger log = LoggerFactory.getLogger(code3_01.class);
	
	public static void main(String[] args) {
		
		char[] Data = { 'A', 'B', 'D', 'E' }; // [A, B, C, D]
		char temp;
		char temp2;
		char c = 'C';
		
		log.debug("{} {}", Arrays.toString(Data), Data.length);		

		int i = 0;
		for (i = 0; i < Data.length; i++) {
			if (Data[i] > c) {
				break;
			}
		}

		temp = Data[i];
		Data[i] = c;
		i++;

		log.debug("{}", Data.length);
		
		for (; i < Data.length; i++) {
			temp2 = Data[i];
			Data[i] = temp;
			temp = temp2;
		}

		log.debug("{} {}", Arrays.toString(Data), Data.length);
	}

}

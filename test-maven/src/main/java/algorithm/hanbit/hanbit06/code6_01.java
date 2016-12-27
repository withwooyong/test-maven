package algorithm.hanbit.hanbit06;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code6_01 {

	private static Logger log = LoggerFactory.getLogger(code6_01.class);

	private static int MAX = 10;
	private static int[] Buf = new int[MAX];

	// SelectionSort
	public static void main(String[] args) {		
		MakeRandomNumber();
		log.debug("{}", Arrays.toString(Buf));
		SelectionSort();
		log.debug("{}", Arrays.toString(Buf));
	}
	
	// swap
	private static void SelectionSort() {
		int min, dummy;
		for (int i = 0; i < MAX; i++) {
			min = i;
			for (int j = i + 1; j < MAX; j++) {
				if (Buf[j] < Buf[min]) {
					min = j;
				}
			}
			dummy = Buf[min];
			Buf[min] = Buf[i];
			Buf[i] = dummy;
		}
	}

	private static void MakeRandomNumber() {
		int i = 1;
		int Num;
		Buf[0] = MAX;
		
		while (i < MAX) {
			//Num = (int)(Math.random() * MAX) % MAX; 
			Num = (int)(Math.random() * MAX);
			log.debug("{}", Num);
			if (!IsNumberExit(Num, i)) {
				Buf[i] = Num;
				i++;
			}
		}
		log.debug("{}", Arrays.toString(Buf));
	}		

	private static boolean IsNumberExit(int number, int index) {
		for (int i = 0; i < index; i++) {
			if (Buf[i] == number || number == 0) {
				return true;
			}
		}
		return false;
	}
}

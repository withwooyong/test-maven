package algorithm.hanbit.hanbit06;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code6_02 {

	private static Logger log = LoggerFactory.getLogger(code6_02.class);

	private static int MAX = 100;
	private static int Buf[] = new int[MAX];

	// InsertionSort
	public static void main(String[] args) {
		MakeRandomNumber();
		log.debug("{}", Arrays.toString(Buf));
		InsertionSort();
		log.debug("{}", Arrays.toString(Buf));
	}

	private static void InsertionSort() {
		int j, dummy;
		for (int i = 0; i < MAX; i++) {
			dummy = Buf[i];
			j = i;

			while (Buf[j - 1] > dummy && j > 0) {
				Buf[j] = Buf[j - 1];
				j--;
			}

			Buf[j] = dummy;
		}
	}

	private static void MakeRandomNumber() {
		int i, Num;
		i = 1;
		Buf[0] = 100;

		while (i < MAX) {
			Num = (int) (Math.random() * MAX);

			if (!IsNumberExit(Num, i)) {
				Buf[i] = Num;
				i++;
			}
		}
	}

	private static boolean IsNumberExit(int number, int index) {
		for (int i = 0; i < index; i++) {
			if (Buf[i] == number || number == 0)
				return true;
		}
		return false;
	}

}

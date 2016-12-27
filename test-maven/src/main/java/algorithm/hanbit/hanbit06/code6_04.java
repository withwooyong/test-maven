package algorithm.hanbit.hanbit06;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code6_04 {

	private static Logger log = LoggerFactory.getLogger(code6_04.class);

	private static int MAX = 100;
	private static int Buf[] = new int[MAX];

	// InsertionSort
	public static void main(String[] args) {
		MakeRandomNumber();
		log.debug("{}", Arrays.toString(Buf));
		ShellSort();
		log.debug("{}", Arrays.toString(Buf));
	}

	private static void ShellSort() {
		int i, j, h, v;
		for (h = 0; h < MAX; h = 3 * h + 1)
			;
		for (; h > 0; h /= 3) {
			for (i = h; i < MAX; i++) {
				v = Buf[i];
				j = i;

				while (j >= h && Buf[j - h] > v) {
					Buf[j] = Buf[j - h];
					j -= h;
				}

				Buf[j] = v;
			}
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

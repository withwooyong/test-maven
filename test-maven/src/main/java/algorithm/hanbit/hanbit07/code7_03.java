package algorithm.hanbit.hanbit07;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class code7_03 {

	private static Logger log = LoggerFactory.getLogger(code7_03.class);

	private static int MAX = 100;
	private static int Buf[] = new int[MAX];
	private static int temp[] = new int[MAX];

	// MergeSort
	public static void main(String[] args) {
		MakeRandomNumber();
		log.debug("{}", Arrays.toString(Buf));
		MergeSort(Buf, 0, MAX - 1);
		log.debug("{}", Arrays.toString(Buf));
	}

	private static void MergeSort(int data[], int left, int right) {
		int i, j, k, middle;
		if (right > left) {
			middle = (left + right) / 2;

			MergeSort(data, left, middle);
			MergeSort(data, middle + 1, right);

			for (i = middle + 1; i > left; i--)
				temp[i - 1] = data[i - 1];

			for (j = middle; j < right; j++)
				temp[right + middle - j] = data[j + 1];

			for (k = left; k <= right; k++)
				data[k] = (temp[i] < temp[j]) ? temp[i++] : temp[j--];
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